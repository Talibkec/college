package com.college.upgradetest;

import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Profile editor (2026-10-28): designation, headings (add, rename, reorder, delete), who may save (the faculty member
 * and Admin only) and the content blocks of the profile page.
 */
public class FacultyProfileEditTest extends AbstractAppTest {

    private long facultyId;

    @Before
    public void ids() {
        facultyId = data.facultyId(TestData.FACULTY.username);
    }

    /** One heading as the edit page's script sends it: {id or null, title, [value id or null, text]...}. */
    private static String heading(Long id, String title, Object... values) {
        StringBuilder vals = new StringBuilder();
        for (int i = 0; i < values.length; i += 2) {
            if (vals.length() > 0) vals.append(',');
            vals.append("{\"keyPropValuesId\":").append(values[i]).append(",\"keyPropVal\":\"").append(values[i + 1])
                    .append("\",\"keyValueOrder\":").append(i / 2 + 1).append('}');
        }
        return "{\"keyPropertyId\":" + id + ",\"keyPropertyName\":\"" + title + "\",\"keyPropVals\":[" + vals + "]}";
    }

    private WebSession.Response save(WebSession s, long id, String designation, String mobile, String... headings) throws Exception {
        Map<String, String> f = new LinkedHashMap<>();
        f.put("facultyId", String.valueOf(id));
        f.put("facultyName", "");
        f.put("facultyDesignation", designation);
        f.put("facultyOfficialEmail", "");
        f.put("facultyPersonalEmail", "");
        f.put("facultyMobNo", mobile);
        f.put("facultyDetails", "{\"facultyKeyProps\":[" + String.join(",", headings) + "]}");
        return s.postMultipart("/uploadfile/editfacultydetails", f, Collections.<String, WebSession.FilePart>emptyMap());
    }

    private List<Map<String, Object>> stored(long id) {
        return jdbc.queryForList("select p.key_property_id, p.key_property_name, p.key_props_order, v.key_prop_values_id, "
                + "v.key_prop_val, v.key_value_order from faculty_key_props p left join faculty_keyprop_values v "
                + "on v.key_property_id = p.key_property_id where p.faculty_id = ? order by p.key_props_order, v.key_value_order", id);
    }

    private Long headingId(String title) {
        return jdbc.queryForObject("select key_property_id from faculty_key_props where faculty_id = ? and key_property_name = ?",
                Long.class, facultyId, title);
    }

    private Long valueId(String text) {
        return jdbc.queryForObject("select v.key_prop_values_id from faculty_keyprop_values v join faculty_key_props p "
                + "on p.key_property_id = v.key_property_id where p.faculty_id = ? and v.key_prop_val = ?", Long.class, facultyId, text);
    }

    @Test
    public void facultyEditsDesignationAndHeadings() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY);
        String a = TestData.MARK + " Qualifications", b = TestData.MARK + " Research";
        assertEquals(200, save(faculty, facultyId, "Associate Professor", "9000000001",
                heading(null, a, null, "M.Tech", null, "B.Tech"), heading(null, b, null, "Networks")).status);
        assertEquals("Associate Professor", jdbc.queryForObject("select f_designation from faculty where faculty_id = ?", String.class, facultyId));
        assertContains("profile designation", anonymous().get("/facultyDetails?facultyId=" + facultyId), "Associate Professor <br/>");
        assertContains("list designation", anonymous().get("/faculty?deptno=" + TestData.DEPT_ID), "<strong>Designation:</strong> Associate Professor</p>");

        // rename, reorder headings and values, delete a value, add a value; same row ids are kept
        Long idA = headingId(a), idB = headingId(b), mtech = valueId("M.Tech"), btech = valueId("B.Tech");
        assertEquals(200, save(faculty, facultyId, "Associate Professor", "9000000001",
                heading(idB, b + " Areas", null, "Graph Theory"),
                heading(idA, a, btech, "B.Tech (KEC)", mtech, "M.Tech")).status);
        List<Map<String, Object>> rows = stored(facultyId);
        assertEquals(3, rows.size());
        assertEquals(b + " Areas", rows.get(0).get("key_property_name"));
        assertEquals(idB.longValue(), ((Number) rows.get(0).get("key_property_id")).longValue());
        assertEquals("Graph Theory", rows.get(0).get("key_prop_val"));
        assertEquals(a, rows.get(1).get("key_property_name"));
        assertEquals("B.Tech (KEC)", rows.get(1).get("key_prop_val"));
        assertEquals(btech.longValue(), ((Number) rows.get(1).get("key_prop_values_id")).longValue());
        assertEquals("M.Tech", rows.get(2).get("key_prop_val"));
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from faculty_keyprop_values v join faculty_key_props p "
                + "on p.key_property_id = v.key_property_id where p.faculty_id = ? and v.key_prop_val = 'Networks'", Integer.class, facultyId));

        // delete a heading with its values; empty designation = default text again
        assertEquals(200, save(faculty, facultyId, "", "9000000001", heading(idA, a, mtech, "M.Tech")).status);
        assertEquals(1, stored(facultyId).size());
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from faculty_keyprop_values where key_property_id = ?", Integer.class, idB));
        assertNull(jdbc.queryForObject("select f_designation from faculty where faculty_id = ?", String.class, facultyId));
        assertContains("default designation", anonymous().get("/facultyDetails?facultyId=" + facultyId), "Assistant Professor <br/>");

        WebSession.Response page = faculty.get("/uploadfile/editfacultydetails?facultyId=" + facultyId);
        assertCompletePage("editor", page);
        assertContains("headings for the script", page, "\"title\":\"" + TestData.MARK + " Qualifications\"");
    }

    @Test
    public void invalidInputIsRejectedAndHtmlIsSanitised() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY);
        WebSession.Response bad = save(faculty, facultyId, "", "12ab", heading(null, "x", null, "y"));
        assertEquals(400, bad.status);
        assertTrue(bad.body(), bad.body().contains("mobile number"));
        assertEquals(400, save(faculty, facultyId, "", "9000000001", heading(null, " ", null, "y")).status);

        String title = TestData.MARK + " Links";
        assertEquals(200, save(faculty, facultyId, "", "9000000001",
                heading(null, title, null, "<script>alert(1)</script><a href='https://orcid.org/x'>ORCID</a>")).status);
        String stored = jdbc.queryForObject("select v.key_prop_val from faculty_keyprop_values v join faculty_key_props p "
                + "on p.key_property_id = v.key_property_id where p.faculty_id = ? and p.key_property_name = ?", String.class, facultyId, title);
        assertFalse(stored, stored.contains("script"));
        assertTrue(stored, stored.contains("href=\"https://orcid.org/x\""));
    }

    @Test
    public void onlyTheFacultyMemberAndAdminMayChangeTheProfile() throws Exception {
        // another faculty member's profile with a heading
        jdbc.update("insert into faculty (dept_id, f_name, username) values (?, ?, ?)", TestData.DEPT_ID,
                TestData.MARK + " Other Member", TestData.HOD.username);
        long other = data.facultyId(TestData.HOD.username);
        jdbc.update("insert into faculty_key_props (key_property_name, faculty_id, key_props_order) values (?, ?, 1)",
                TestData.MARK + " Other heading", other);
        Long otherHeading = jdbc.queryForObject("select key_property_id from faculty_key_props where faculty_id = ?", Long.class, other);

        WebSession faculty = loginAs(TestData.FACULTY);
        assertEquals("other profile", 403, save(faculty, other, "Professor", "", heading(null, "x", null, "y")).status);
        assertContains("edit page of another profile", faculty.get("/uploadfile/editfacultydetails?facultyId=" + other), "Access denied");
        assertFalse(faculty.get("/facultyDetails?facultyId=" + other).body().contains("editfacultydetails?facultyId=" + other));

        // a heading id of another profile does not move or change that heading
        assertEquals(200, save(faculty, facultyId, "", "9000000001", heading(otherHeading, TestData.MARK + " Taken", null, "v")).status);
        assertEquals(TestData.MARK + " Other heading", jdbc.queryForObject("select key_property_name from faculty_key_props "
                + "where key_property_id = ?", String.class, otherHeading));
        assertEquals(other, (long) jdbc.queryForObject("select faculty_id from faculty_key_props where key_property_id = ?", Long.class, otherHeading));

        for (TestData.TestUser user : new TestData.TestUser[]{TestData.SM, TestData.SUBADMIN}) {
            assertEquals(user.username, 403, save(loginAs(user), facultyId, "Professor", "", heading(null, "x", null, "y")).status);
        }
        WebSession.Response anon = save(anonymous(), facultyId, "Professor", "", heading(null, "x", null, "y"));
        assertTrue("anonymous: " + anon.status, anon.status == 302 || anon.status == 403);
        assertNull(jdbc.queryForObject("select f_designation from faculty where faculty_id = ?", String.class, facultyId));

        // Admin may correct the profile and sees the edit link
        WebSession admin = loginAs(TestData.ADMIN);
        assertContains("admin edit link", admin.get("/facultyDetails?facultyId=" + facultyId), "id=\"admin-profile-edit\"");
        assertCompletePage("admin editor", admin.get("/uploadfile/editfacultydetails?facultyId=" + facultyId));
        assertEquals(200, save(admin, facultyId, "Professor", "9000000001", heading(null, TestData.MARK + " By admin", null, "ok")).status);
        assertEquals("Professor", jdbc.queryForObject("select f_designation from faculty where faculty_id = ?", String.class, facultyId));
        jdbc.update("update faculty set f_designation = null where faculty_id = ?", facultyId);
    }

    @Test
    public void facultyAddsContentToTheOwnProfilePageOnly() throws Exception {
        String key = "faculty." + facultyId;
        WebSession faculty = loginAs(TestData.FACULTY);
        assertContains("add buttons on own profile", faculty.get("/facultyDetails?facultyId=" + facultyId),
                "/manage/blocks?page=" + key + "&add=heading");
        assertFalse("visitors", anonymous().get("/facultyDetails?facultyId=" + facultyId).body().contains("page-blocks-add"));
        assertFalse("HOD", loginAs(TestData.HOD).get("/facultyDetails?facultyId=" + facultyId).body().contains("page-blocks-add"));
        assertEquals("HOD may not edit", 403, loginAs(TestData.HOD).get("/manage/blocks?page=" + key).status);
        assertEquals("SubAdmin may not edit", 403, loginAs(TestData.SUBADMIN).get("/manage/blocks?page=" + key).status);

        Map<String, String> block = new LinkedHashMap<>();
        block.put("page", key);
        block.put("type", "heading");
        block.put("heading", TestData.MARK + " Awards");
        block.put("visible", "on");
        WebSession.Response r = faculty.postMultipart("/manage/blocks/save", block, Collections.<String, WebSession.FilePart>emptyMap());
        assertTrue(r.toString(), r.isRedirect());
        assertContains("block on the profile", anonymous().get("/facultyDetails?facultyId=" + facultyId), TestData.MARK + " Awards</h2>");
        assertEquals("faculty." + facultyId, jdbc.queryForObject("select area_code from page_block where page_key = ?", String.class, key));
        assertEquals("/manage/blocks?page=" + key, faculty.get("/manage/blocks?url=" + java.net.URLEncoder.encode(
                "/facultyDetails?facultyId=" + facultyId, "UTF-8")).redirectPath());
        assertCompletePage("admin may edit", loginAs(TestData.ADMIN).get("/manage/blocks?page=" + key));
    }
}
