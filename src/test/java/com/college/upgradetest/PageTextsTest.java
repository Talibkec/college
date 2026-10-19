package com.college.upgradetest;

import com.college.core.tags.PageKeys;
import org.junit.After;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Existing texts of the pages made editable (&lt;kec:section&gt; + docs/sql/dynamic/15_page_section_pages_seed.sql,
 * Academic, Facilities, T&amp;P, Approval and RTI pages), the button bar of the content screens and the header's "Website Content" link.
 * Changed texts are restored through the screen afterwards ("restore the original text").
 */
public class PageTextsTest extends AbstractAppTest {

    private static final Pattern TAG = Pattern.compile("<kec:section key=\"([^\"]+)\"(?: title=\"([^\"]*)\")?>(.*?)</kec:section>", Pattern.DOTALL);

    private long sectionId(String pageKey, String sectionKey) {
        return jdbc.queryForObject("select section_id from page_section where page_key = ? and section_key = ?", Long.class, pageKey, sectionKey);
    }

    private String body(long id) {
        return jdbc.queryForObject("select body_html from page_section where section_id = ?", String.class, id);
    }

    @After
    public void restoreOriginalTexts() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        List<Map<String, Object>> changed = jdbc.queryForList("select entity_id, min(log_id) first_log from content_change_log "
                + "where entity = 'page_section' and changed_by like ? group by entity_id", TestData.MARK + "%");
        for (Map<String, Object> row : changed) {
            String id = String.valueOf(row.get("entity_id"));
            // only sections this test class changes (PageSectionsTest restores About itself)
            String page = jdbc.queryForObject("select page_key from page_section where section_id = ?", String.class, Long.valueOf(id));
            if (!page.startsWith("about.")) {
                admin.postForm("/manage/sections/restore", "id", id, "logId", String.valueOf(row.get("first_log")), "before", "1");
                admin.postForm("/manage/sections/save", "id", id, "html", body(Long.parseLong(id)), "visible", "on");
            }
        }
        jdbc.update("update page_section set updated_by = null, updated_at = null where page_key not like 'about.%'");
    }

    /** Every wrapped text has its seed row with exactly the JSP's HTML and the area of PageKeys (who may edit it). */
    @Test
    public void everyWrappedTextIsSeededWithTheSameHtml() throws Exception {
        Path jsps = Paths.get("src/main/webapp/jsp");
        List<Path> files;
        try (Stream<Path> walk = Files.walk(jsps)) {
            files = walk.filter(p -> p.toString().endsWith(".jsp")).sorted().collect(Collectors.toList());
        }
        int count = 0;
        for (Path file : files) {
            Matcher m = TAG.matcher(new String(Files.readAllBytes(file), StandardCharsets.UTF_8));
            while (m.find()) {
                String[] key = m.group(1).split("/");
                List<Map<String, Object>> rows = jdbc.queryForList("select area_code, title, body_html, updated_by from page_section "
                        + "where page_key = ? and section_key = ?", key[0], key[1]);
                assertEquals(file + " " + m.group(1) + " is seeded (run the docs/sql/dynamic seeds)", 1, rows.size());
                Map<String, Object> row = rows.get(0);
                assertEquals(m.group(1) + " area", PageKeys.area(key[0]), row.get("area_code"));
                if (!key[0].startsWith("about.")) {
                    assertEquals(m.group(1) + " title", m.group(2), row.get("title"));
                }
                if (row.get("updated_by") == null) { // not changed on the website
                    assertEquals(m.group(1) + " body", m.group(3).replace("\r\n", "\n"), ((String) row.get("body_html")).replace("\r\n", "\n"));
                }
                count++;
            }
        }
        assertTrue("page texts found: " + count, count >= 51);
    }

    @Test
    public void subAdminChangesAnAdmissionTextAndRestoresTheOriginal() throws Exception {
        String before = anonymous().get("/academic/admission").body();
        long lateral = sectionId("academic.admission", "lateral");
        WebSession sub = loginAs(TestData.SUBADMIN);

        WebSession.Response page = sub.get("/manage/blocks?page=academic.admission");
        assertContains("edit-this-page screen lists the texts", page, "id=\"page-sections\"");
        assertContains("text link", page, "/manage/sections?id=" + lateral);
        assertContains("text title", page, "UG Admission: residential rules");

        sub.postForm("/manage/sections/save", "id", String.valueOf(lateral), "html", "<p>" + TestData.MARK + " lateral</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/academic/admission");
        assertContains("new text", changed, "<p>" + TestData.MARK + " lateral</p>");
        assertContains("course table from the database still there", changed, "Lateral student</th>");
        assertFalse(changed.body().contains("(A) Passed Diploma"));

        Long firstLog = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(lateral));
        sub.postForm("/manage/sections/restore", "id", String.valueOf(lateral), "logId", String.valueOf(firstLog), "before", "1");
        assertEquals("original restored byte for byte", before, anonymous().get("/academic/admission").body());

        sub.postForm("/manage/sections/save", "id", String.valueOf(lateral), "html", body(lateral));
        assertFalse("hidden text", anonymous().get("/academic/admission").body().contains("(A) Passed Diploma"));
    }

    /** Hostel fee table: SubAdmin (hostel area) changes it, the accordion around it stays code; TPO may not, but edits the Startup Cell (tp). */
    @Test
    public void facilitiesTextsFollowTheirAreas() throws Exception {
        long fees = sectionId("facilities.hostels", "fees");
        assertEquals("hostel", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, fees));
        String before = anonymous().get("/facilities/hostels").body();
        WebSession sub = loginAs(TestData.SUBADMIN);
        sub.postForm("/manage/sections/save", "id", String.valueOf(fees), "html", "<p>" + TestData.MARK + " fees</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/facilities/hostels");
        assertContains("new fee text", changed, "<p>" + TestData.MARK + " fees</p>");
        assertContains("accordion kept", changed, "Hostel Fee Structure");
        assertContains("other table kept", changed, "Hostel In Charges");
        assertFalse(changed.body().contains("Hostel Admission Charge"));
        Long firstLog = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(fees));
        sub.postForm("/manage/sections/restore", "id", String.valueOf(fees), "logId", String.valueOf(firstLog), "before", "1");
        assertEquals("original restored byte for byte", before, anonymous().get("/facilities/hostels").body());

        WebSession tpo = loginAs(TestData.TPO);
        assertEquals(403, tpo.get("/manage/sections?id=" + fees).status);
        long startup = sectionId("facilities.startupcell", "main");
        assertEquals(200, tpo.get("/manage/sections?id=" + startup).status);
        assertContains("tpo: startup cell listed", tpo.get("/manage/blocks?page=facilities.startupcell"), "Startup Cell: text");
    }

    /** T&P texts belong to TPO (area tp), RTI texts to the PIO: TPO changes the resume tips, but not the RTI status. */
    @Test
    public void tpoChangesTrainingAndPlacementTextsButNotRti() throws Exception {
        long tips = sectionId("trainingplacement.tipsforresume", "main");
        String before = anonymous().get("/trainingplacement/tipsforresume").body();
        assertTrue("quotes as entities, no mojibake", before.contains("&lsquo;available upon request&rsquo;"));
        WebSession tpo = loginAs(TestData.TPO);
        tpo.postForm("/manage/sections/save", "id", String.valueOf(tips), "html", "<p>" + TestData.MARK + " tips</p>", "visible", "on");
        assertContains("new tips", anonymous().get("/trainingplacement/tipsforresume"), "<p>" + TestData.MARK + " tips</p>");
        Long firstLog = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(tips));
        tpo.postForm("/manage/sections/restore", "id", String.valueOf(tips), "logId", String.valueOf(firstLog), "before", "1");
        assertEquals("original restored byte for byte", before, anonymous().get("/trainingplacement/tipsforresume").body());

        long rti = sectionId("rti.rtistatus", "main");
        assertEquals("rti", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, rti));
        assertEquals(403, tpo.get("/manage/sections?id=" + rti).status);
        assertEquals(200, loginAs(TestData.SUBADMIN).get("/manage/sections?id=" + rti).status);
    }

    @Test
    public void onlyTheAreaEditorsMayChangeAcademicTexts() throws Exception {
        long syllabus = sectionId("academic.syllabus", "main");
        assertEquals("exam", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, syllabus));
        WebSession tpo = loginAs(TestData.TPO);
        assertEquals(403, tpo.get("/manage/sections?id=" + syllabus).status);
        assertEquals(403, tpo.postForm("/manage/sections/save", "id", String.valueOf(syllabus), "html", "<p>x</p>").status);
        assertEquals(403, tpo.get("/manage/blocks?page=academic.syllabus").status);
        assertFalse(tpo.get("/manage/sections").body().contains("Syllabus: links"));
        assertTrue(anonymous().get("/manage/sections?id=" + syllabus).redirectPath().endsWith("/login"));
    }

    @Test
    public void embeddedPdfOfThisSiteSurvivesSavingOthersAreRemoved() throws Exception {
        long regulations = sectionId("academic.regulations", "main");
        WebSession admin = loginAs(TestData.ADMIN);
        admin.postForm("/manage/sections/save", "id", String.valueOf(regulations), "html",
                "<p>" + TestData.MARK + "</p><object data=\"/jsp/academic/regulation.pdf\" type=\"application/pdf\" width=\"700\" height=\"1000\">"
                        + "<embed src=\"/jsp/academic/regulation.pdf\" type=\"application/pdf\"></object>"
                        + "<object data=\"//evil.example/x.pdf\" type=\"application/pdf\"></object>"
                        + "<object data=\"https://evil.example/x.swf\" type=\"application/x-shockwave-flash\"></object>"
                        + "<embed src=\"javascript:alert(1)\">", "visible", "on");
        String stored = body(regulations);
        assertTrue(stored, stored.contains("<object data=\"/jsp/academic/regulation.pdf\" type=\"application/pdf\" width=\"700\" height=\"1000\">"));
        assertTrue(stored, stored.contains("<embed src=\"/jsp/academic/regulation.pdf\" type=\"application/pdf\""));
        assertFalse(stored, stored.contains("evil.example") || stored.contains("javascript") || stored.contains("flash"));
    }

    @Test
    public void contentScreensHaveAButtonBarAndTheHeaderAWebsiteContentLink() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        WebSession.Response overview = sub.get("/manage");
        assertContains("button bar", overview, "id=\"manage-nav\"");
        for (String url : new String[]{"/manage/sections", "/manage/blocks", "/manage/settings", "/manage/links"}) {
            assertContains("button " + url, overview, "<a href=\"" + url + "\"");
        }
        assertFalse("no admin buttons", overview.body().contains("<a href=\"/auth/roles\""));
        assertContains("active button", sub.get("/manage/sections"), "<a href=\"/manage/sections\" class=\"px-3 py-1 rounded-md text-sm border bg-blue-600");
        assertContains("header link", sub.get("/"), "id=\"website-content-link\"");

        WebSession tpo = loginAs(TestData.TPO);
        WebSession.Response tpoOverview = tpo.get("/manage");
        assertContains("tpo: add to a page", tpoOverview, "<a href=\"/manage/blocks\"");
        assertFalse("tpo: no menus", tpoOverview.body().contains("<a href=\"/manage/links\""));

        WebSession admin = loginAs(TestData.ADMIN);
        WebSession.Response roles = admin.get("/auth/roles");
        assertCompletePage("roles", roles);
        assertContains("roles: button bar", roles, "id=\"manage-nav\"");
        assertContains("roles: areas button", roles, "<a href=\"/auth/roles/areas\"");
        assertContains("admin: page texts", admin.get("/manage/blocks"), "<a href=\"/manage/sections\"");

        assertFalse("store user: no link", loginAs(TestData.SK).get("/").body().contains("website-content-link"));
        assertFalse("anonymous: no link", anonymous().get("/").body().contains("website-content-link"));
    }
}
