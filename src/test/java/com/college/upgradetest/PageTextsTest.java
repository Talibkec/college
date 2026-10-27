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
 * Academic, Facilities, T&amp;P, Approval, RTI, Legal, Home and department pages), the button bar of the content screens and the header's "Website Content" link.
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
        assertTrue("page texts found: " + count, count >= 160);
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

    /** Legal pages: the refund text is editable, its phone and e-mail stay contact settings; fee structure is area fees. */
    @Test
    public void legalTextsKeepTheirContactSettings() throws Exception {
        long refund = sectionId("legal.refundandcancellation", "main");
        String before = anonymous().get("/legal/refundandcancellation").body();
        WebSession sub = loginAs(TestData.SUBADMIN);
        sub.postForm("/manage/sections/save", "id", String.valueOf(refund), "html", "<p>" + TestData.MARK + " refund</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/legal/refundandcancellation");
        assertContains("new refund text", changed, "<p>" + TestData.MARK + " refund</p>");
        assertContains("phone setting kept", changed, "<li>Phone: ");
        Long firstLog = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(refund));
        sub.postForm("/manage/sections/restore", "id", String.valueOf(refund), "logId", String.valueOf(firstLog), "before", "1");
        assertEquals("original restored byte for byte", before, anonymous().get("/legal/refundandcancellation").body());

        assertEquals("fees", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class,
                sectionId("legal.feestructure", "main")));
        assertEquals(403, loginAs(TestData.TPO).get("/manage/sections?id=" + sectionId("legal.privacypolicy", "main")).status);
    }

    /** Home page: SubAdmin changes the Important Links; notices and slider (database) stay; TPO may not. */
    @Test
    public void homeTextsAreEditableByTheHomeArea() throws Exception {
        long links = sectionId("home", "importantlinks");
        assertEquals("home", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, links));
        String before = anonymous().get("/").body();
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("home texts listed", sub.get("/manage/blocks?page=home"), "Home: Important Links");
        sub.postForm("/manage/sections/save", "id", String.valueOf(links), "html",
                "<div class=\"link\">&raquo; <a href=\"https://www.aicte-india.org/\" target=\"_blank\">" + TestData.MARK + "</a></div>", "visible", "on");
        WebSession.Response changed = anonymous().get("/");
        assertContains("new link", changed, TestData.MARK + "</a></div>");
        assertContains("notices still there", changed, "Latest Notices");
        assertFalse(changed.body().contains("Social Welfare Dept. Bhar"));
        Long firstLog = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(links));
        sub.postForm("/manage/sections/restore", "id", String.valueOf(links), "logId", String.valueOf(firstLog), "before", "1");
        assertEquals("original restored byte for byte", before, anonymous().get("/").body());
        assertEquals(403, loginAs(TestData.TPO).get("/manage/sections?id=" + links).status);
    }

    /** CSE site: SubAdmin changes the introduction; notices (database) stay; CSE Faculty (add only) and TPO may not. */
    @Test
    public void cseTextsAreEditableByTheDepartmentArea() throws Exception {
        long intro = sectionId("department.cse.about", "intro");
        assertEquals("department.cse", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, intro));
        String before = anonymous().get("/department/cse/about").body();
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("CSE texts listed", sub.get("/manage/blocks?page=department.cse.about"), "About CSE: introduction");
        sub.postForm("/manage/sections/save", "id", String.valueOf(intro), "html", "<p>" + TestData.MARK + " cse</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/cse/about");
        assertContains("new text", changed, "<p>" + TestData.MARK + " cse</p>");
        assertContains("notices still there", changed, "Latest News / Updates");
        assertFalse(changed.body().contains("Theory of computation"));
        Long firstLog = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(intro));
        sub.postForm("/manage/sections/restore", "id", String.valueOf(intro), "logId", String.valueOf(firstLog), "before", "1");
        assertEquals("original restored byte for byte", before, anonymous().get("/department/cse/about").body());
        assertEquals("faculty adds blocks only", 403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + intro).status);
        assertEquals(403, loginAs(TestData.TPO).get("/manage/sections?id=" + sectionId("department.cse.labs", "main")).status);
    }

    /** ASH site: its own area (CSE Faculty may not touch it); the vision bullet is an entity now (was mojibake). */
    @Test
    public void ashTextsBelongToTheAshArea() throws Exception {
        long vision = sectionId("department.ash.vision", "main");
        assertEquals("department.ash", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, vision));
        assertContains("bullet as entity", anonymous().get("/department/ash/vision"), "1.&bull;\tTo cater students");
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("ASH texts listed", sub.get("/manage/blocks?page=department.ash.labs"), "ASH Labs: laboratory list");
        sub.postForm("/manage/sections/save", "id", String.valueOf(sectionId("department.ash.labs", "main")), "html",
                "<ol><li>" + TestData.MARK + " ash lab</li></ol>", "visible", "on");
        assertContains("new lab list", anonymous().get("/department/ash/labs"), "<li>" + TestData.MARK + " ash lab</li>");
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + vision).status);
        assertEquals("still 404 (attendace.jsp)", 404, anonymous().get("/department/ash/attendance").status);
    }

    /** The HOD of a site changes only that site's texts: the CSE HOD may edit CSE, not ASH or Civil (scope own_department). */
    @Test
    public void hodChangesOnlyTheTextsOfOwnDepartment() throws Exception {
        String hodOfCse = jdbc.queryForObject("select hod_username from department_site where site_code = 'cse'", String.class);
        jdbc.update("update department_site set hod_username = ? where site_code = 'cse'", TestData.HOD.username);
        try {
            WebSession hod = loginAs(TestData.HOD);
            long cse = sectionId("department.cse.students", "main");
            assertContains("own texts listed", hod.get("/manage/sections"), "CSE Students: text");
            WebSession.Response editor = hod.get("/manage/sections?id=" + cse);
            for (String type : new String[]{"heading", "subheading", "text", "image", "document", "link"}) {
                assertContains("add " + type + " from the text editor", editor,
                        "href=\"/manage/blocks?page=department.cse.students&add=" + type + "#form\"");
            }
            assertFalse("no ASH texts listed", hod.get("/manage/sections").body().contains("ASH Students: text"));
            assertEquals(302, hod.postForm("/manage/sections/save", "id", String.valueOf(cse), "html",
                    "<p>" + TestData.MARK + " hod cse</p>", "visible", "on").status);
            assertContains("own site changed", anonymous().get("/department/cse/students"), TestData.MARK + " hod cse");
            long ash = sectionId("department.ash.students", "main");
            String ashBefore = body(ash);
            assertEquals("other site", 403, hod.get("/manage/sections?id=" + ash).status);
            assertEquals("other site", 403, hod.postForm("/manage/sections/save", "id", String.valueOf(ash), "html",
                    "<p>" + TestData.MARK + " hod ash</p>", "visible", "on").status);
            assertEquals("ASH text unchanged", ashBefore, body(ash));
            assertEquals("other site's page", 403, hod.get("/manage/blocks?page=department.civil.labs").status);
        } finally {
            jdbc.update("update department_site set hod_username = ? where site_code = 'cse'", hodOfCse);
        }
    }

    /** /academic/lab?dept_id=n is one page per department: the HOD of CSE edits the CSE lab page (1), not Civil's (2). */
    @Test
    public void hodEditsTheLabPageOfOwnDepartment() throws Exception {
        String hodOfCse = jdbc.queryForObject("select hod_username from department_site where site_code = 'cse'", String.class);
        jdbc.update("update department_site set hod_username = ? where site_code = 'cse'", TestData.HOD.username);
        try {
            WebSession hod = loginAs(TestData.HOD);
            WebSession.Response lab = hod.get("/academic/lab?dept_id=1");
            assertContains("add buttons on the own lab page", lab, "href=\"/manage/blocks?page=academic.lab.1&add=text#form\"");
            assertContains("lab document upload", lab, "href=\"/auth/uploadLabDoc\"");
            assertFalse("not on Civil's lab page", hod.get("/academic/lab?dept_id=2").body().contains("page-blocks-add"));
            assertEquals("page opened by its address", "/manage/blocks?page=academic.lab.1",
                    hod.get("/manage/blocks?url=" + java.net.URLEncoder.encode("/academic/lab?dept_id=1", "UTF-8")).redirectPath());
            assertContains("page link", hod.get("/manage/blocks?page=academic.lab.1"), "href=\"/academic/lab?dept_id=1\"");
            assertEquals("other department", 403, hod.get("/manage/blocks?page=academic.lab.2").status);

            hod.postMultipart("/manage/blocks/save", java.util.Map.of("page", "academic.lab.1", "type", "text",
                    "html", "<p>" + TestData.MARK + " cse lab</p>", "visible", "on"), java.util.Collections.emptyMap());
            assertEquals("department.cse", jdbc.queryForObject("select area_code from page_block where page_key = 'academic.lab.1' "
                    + "and created_by = ?", String.class, TestData.HOD.username));
            assertContains("shown on the CSE lab page", anonymous().get("/academic/lab?dept_id=1"), TestData.MARK + " cse lab");
            assertFalse("not on Civil's", anonymous().get("/academic/lab?dept_id=2").body().contains(TestData.MARK + " cse lab"));
        } finally {
            jdbc.update("update department_site set hod_username = ? where site_code = 'cse'", hodOfCse);
            WebSession admin = loginAs(TestData.ADMIN); // through the app, so its block cache is refreshed
            for (Long id : jdbc.queryForList("select block_id from page_block where page_key = 'academic.lab.1' and created_by = ?",
                    Long.class, TestData.HOD.username)) {
                admin.postForm("/manage/blocks/delete", "page", "academic.lab.1", "blockId", String.valueOf(id));
            }
        }
    }

    /** Civil site: SubAdmin changes the lab list; the share links above the attendance text stay in code. */
    @Test
    public void civilTextsAreEditableByTheDepartmentArea() throws Exception {
        long labs = sectionId("department.civil.labs", "main");
        assertEquals("department.civil", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, labs));
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("Civil texts listed", sub.get("/manage/blocks?page=department.civil.labs"), "Civil Labs: laboratories and equipment");
        sub.postForm("/manage/sections/save", "id", String.valueOf(labs), "html", "<ul><li>" + TestData.MARK + " civil lab</li></ul>", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/civil/labs");
        assertContains("new lab list", changed, "<li>" + TestData.MARK + " civil lab</li>");
        assertFalse(changed.body().contains("Total Station"));
        sub.postForm("/manage/sections/save", "id", String.valueOf(sectionId("department.civil.attendance", "main")), "html",
                "<p>" + TestData.MARK + " civil attendance</p>", "visible", "on");
        WebSession.Response attendance = anonymous().get("/department/civil/attendance");
        assertContains("new text", attendance, TestData.MARK + " civil attendance");
        assertContains("share links stay", attendance, "https://twitter.com/home?status=About+Civil+Engg.");
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + labs).status);
    }

    /** ECE site: a lab description is one text; the accordion (buttons, collapse targets) stays in code. */
    @Test
    public void eceLabDescriptionsAreTextsInsideTheAccordion() throws Exception {
        long lab = sectionId("department.ece.labs", "lab4");
        assertEquals("department.ece", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, lab));
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("ECE texts listed", sub.get("/manage/blocks?page=department.ece.labs"), "ECE Labs: Simulation Lab");
        sub.postForm("/manage/sections/save", "id", String.valueOf(lab), "html", TestData.MARK + " simulation", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/ece/labs");
        assertContains("new description", changed, "<div class=\"accordion-body\">" + TestData.MARK + " simulation</div>");
        assertContains("accordion stays", changed, "data-bs-target=\"#collapseFour\"");
        assertContains("other labs stay", changed, "8086 microprocessor-based programming");
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + lab).status);
    }

    /** ECE-VLSI has its own texts: changing its about text leaves the (similar) ECE about page as it is. */
    @Test
    public void eceVlsiTextsAreSeparateFromEce() throws Exception {
        long intro = sectionId("department.ecevlsi.about", "intro");
        assertEquals("department.ecevlsi", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, intro));
        String ece = anonymous().get("/department/ece/about").body();
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("ECE-VLSI texts listed", sub.get("/manage/blocks?page=department.ecevlsi.about"), "About ECE-VLSI: introduction");
        sub.postForm("/manage/sections/save", "id", String.valueOf(intro), "html", "<p>" + TestData.MARK + " mtech</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/ecevlsi/about");
        assertContains("new text", changed, "<p>" + TestData.MARK + " mtech</p>");
        assertFalse(changed.body().contains("is a 2-year program"));
        assertEquals("ECE about unchanged", ece, anonymous().get("/department/ece/about").body());
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + intro).status);
    }

    /** EEE site: SubAdmin changes the lab list; the about apostrophe is an entity now (was mojibake). */
    @Test
    public void eeeTextsAreEditableByTheDepartmentArea() throws Exception {
        long labs = sectionId("department.eee.labs", "main");
        assertEquals("department.eee", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, labs));
        assertContains("apostrophe as entity", anonymous().get("/department/eee/about"), "ignite students&rsquo; imagination");
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("EEE texts listed", sub.get("/manage/blocks?page=department.eee.labs"), "EEE Labs: laboratory list");
        sub.postForm("/manage/sections/save", "id", String.valueOf(labs), "html", "<ol><li>" + TestData.MARK + " eee lab</li></ol>", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/eee/labs");
        assertContains("new lab list", changed, "<li>" + TestData.MARK + " eee lab</li>");
        assertFalse(changed.body().contains("Basic Electrical Engineering Lab"));
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + labs).status);
    }

    /** Food Processing: the vision page (today only a heading) can be filled in through the screen. */
    @Test
    public void foodProcessingVisionCanBeFilledIn() throws Exception {
        long vision = sectionId("department.fp.vision", "main");
        assertEquals("department.fp", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, vision));
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("FP texts listed", sub.get("/manage/blocks?page=department.fp.vision"), "Food Processing Vision &amp; Mission: text");
        sub.postForm("/manage/sections/save", "id", String.valueOf(vision), "html",
                body(vision) + "<p>" + TestData.MARK + " fp vision</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/fp/vision");
        assertContains("heading kept", changed, "Vision of the Department");
        assertContains("new text", changed, "<p>" + TestData.MARK + " fp vision</p>");
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + vision).status);
    }

    /** Manufacturing: SubAdmin changes the vision; the faculty cards stay in code (no text on that page). */
    @Test
    public void manufacturingTextsAreEditableButFacultyCardsStayInCode() throws Exception {
        long vision = sectionId("department.manufacturing.vision", "main");
        assertEquals("department.manufacturing", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, vision));
        assertEquals(0L, (long) jdbc.queryForObject("select count(*) from page_section where page_key = 'department.manufacturing.faculty'", Long.class));
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("texts listed", sub.get("/manage/blocks?page=department.manufacturing.vision"), "Manufacturing Vision &amp; Mission: text");
        sub.postForm("/manage/sections/save", "id", String.valueOf(vision), "html", "<p>" + TestData.MARK + " manufacturing</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/manufacturing/vision");
        assertContains("new text", changed, "<p>" + TestData.MARK + " manufacturing</p>");
        assertFalse(changed.body().contains("To groom motivated"));
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + vision).status);
    }

    /** Mechanical &amp; Smart Manufacturing has its own texts: changing its vision leaves the (same) Manufacturing vision as it is. */
    @Test
    public void msmTextsAreSeparateFromManufacturing() throws Exception {
        long vision = sectionId("department.mechandsmartmanufacturing.vision", "main");
        assertEquals("department.mechandsmartmanufacturing",
                jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, vision));
        String manufacturing = anonymous().get("/department/manufacturing/vision").body();
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("texts listed", sub.get("/manage/blocks?page=department.mechandsmartmanufacturing.vision"),
                "Mechanical &amp; Smart Manufacturing Vision &amp; Mission: text");
        sub.postForm("/manage/sections/save", "id", String.valueOf(vision), "html", "<p>" + TestData.MARK + " msm</p>", "visible", "on");
        assertContains("new text", anonymous().get("/department/mechandsmartmanufacturing/vision"), "<p>" + TestData.MARK + " msm</p>");
        assertEquals("Manufacturing unchanged", manufacturing, anonymous().get("/department/manufacturing/vision").body());
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + vision).status);
    }

    /** Mechanical: the about page lives at /department/mechanical/mech, so its texts use that page key. */
    @Test
    public void mechanicalAboutTextUsesTheMechPageKey() throws Exception {
        long intro = sectionId("department.mechanical.mech", "intro");
        assertEquals("department.mechanical", jdbc.queryForObject("select area_code from page_section where section_id = ?", String.class, intro));
        String before = anonymous().get("/department/mechanical/mech").body();
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertEquals("/manage/blocks?page=department.mechanical.mech",
                sub.get("/manage/blocks?url=/department/mechanical/mech").redirectPath());
        assertContains("text listed", sub.get("/manage/blocks?page=department.mechanical.mech"), "About Mechanical: introduction");
        sub.postForm("/manage/sections/save", "id", String.valueOf(intro), "html", "<p>" + TestData.MARK + " mech</p>", "visible", "on");
        WebSession.Response changed = anonymous().get("/department/mechanical/mech");
        assertContains("new text", changed, "<p>" + TestData.MARK + " mech</p>");
        assertContains("notices still there", changed, "View All");
        Long firstLog = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(intro));
        sub.postForm("/manage/sections/restore", "id", String.valueOf(intro), "logId", String.valueOf(firstLog), "before", "1");
        assertEquals("original restored byte for byte", before, anonymous().get("/department/mechanical/mech").body());
        assertEquals(403, loginAs(TestData.FACULTY).get("/manage/sections?id=" + intro).status);
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
