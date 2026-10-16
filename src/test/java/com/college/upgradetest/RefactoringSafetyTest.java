package com.college.upgradetest;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Refactoring safety net (added before the refactoring phase, recorded on the unrefactored code): pins the exact
 * responses (status, content type, body) and stored rows of the upload/save endpoints that the refactoring touches
 * and that the upgrade tests did not cover.
 */
public class RefactoringSafetyTest extends AbstractAppTest {

    private static final byte[] PDF = "%PDF-1.4 refactoring test document".getBytes(StandardCharsets.US_ASCII);
    private static final String TEXT_PLAIN = "text/plain;charset=UTF-8";

    private static byte[] png(int width, int height) throws java.io.IOException {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        javax.imageio.ImageIO.write(new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB), "png", out);
        return out.toByteArray();
    }

    private static Map<String, String> fields(String... nameValues) {
        Map<String, String> m = new LinkedHashMap<>();
        for (int i = 0; i < nameValues.length; i += 2) m.put(nameValues[i], nameValues[i + 1]);
        return m;
    }

    private static Map<String, WebSession.FilePart> file(String field, String name, String type, byte[] bytes) {
        return Collections.singletonMap(field, new WebSession.FilePart(name, type, bytes));
    }

    private Long id(String sql, Object... args) {
        List<Long> ids = jdbc.queryForList(sql, Long.class, args);
        return ids.isEmpty() ? null : ids.get(0);
    }

    private static void assertResponse(String what, WebSession.Response r, int status, String contentType, String body) {
        assertEquals(what + " status: " + r, status, r.status);
        assertEquals(what + " content type: " + r, contentType, r.contentType);
        assertEquals(what + " body", body, r.body());
    }

    @Test
    public void facultyUploadsNoticeAndSlide() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY);
        String headline = TestData.MARK + " faculty notice " + System.currentTimeMillis();
        WebSession.Response up = faculty.postMultipart("/user/api/upload",
                fields("noticeHeader", headline, "noticeType", "CSENotice", "link", "", "isScrollable", "true", "date", "01/02/2099"),
                file("noticeFile", TestData.MARK + "-fac.pdf", "application/pdf", PDF));
        assertResponse("faculty notice upload", up, 200, TEXT_PLAIN,
                "/wp-content/uploads/notice/" + TestData.MARK + "-fac.pdf," + headline + ",CSENotice");
        Long id = id("select id from noticeboard where nb_headline = ?", headline);
        assertNotNull("notice row", id);
        assertEquals(TestData.FACULTY.username, jdbc.queryForObject("select nb_username from noticeboard where id = ?", String.class, id));
        assertEquals("2099-02-01", jdbc.queryForObject("select date_format(nb_uploaddate, '%Y-%m-%d') from noticeboard where id = ?", String.class, id));
        assertEquals(Integer.valueOf(1), jdbc.queryForObject("select nb_scrollable from noticeboard where id = ?", Integer.class, id));
        assertTrue("dept notice page", anonymous().get("/department/cse/notice").body().contains(headline));
        assertArrayEquals(PDF, anonymous().get("/notice/" + id).bytes);

        String caption = TestData.MARK + " fac slide " + System.currentTimeMillis();
        WebSession.Response wrongSize = faculty.postMultipart("/user/api/uploadslideimage", fields("caption", caption),
                file("slideImage", "s.png", "image/png", png(100, 100)));
        assertResponse("faculty slide wrong size", wrongSize, 400, TEXT_PLAIN, "Kindly Resize Your image in 2000 * 550 format");
        WebSession.Response slide = faculty.postMultipart("/user/api/uploadslideimage", fields("caption", caption),
                file("slideImage", "s.png", "image/png", png(2000, 550)));
        assertResponse("faculty slide", slide, 200, TEXT_PLAIN, "Document uploaded successfully");
        assertNotNull("slide row", id("select image_slide_id from image_slide where caption = ?", caption));
    }

    @Test
    public void adminNoticeUploadResponseAndInvalidDate() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String headline = TestData.MARK + " admin notice " + System.currentTimeMillis();
        WebSession.Response up = admin.postMultipart("/auth/api/upload",
                fields("noticeHeader", headline, "noticeType", "Tendor", "link", "http://example.com/x", "isScrollable", "false", "date", "not a date"),
                file("noticeFile", TestData.MARK + "-t.pdf", "application/pdf", PDF));
        assertResponse("admin notice upload", up, 200, TEXT_PLAIN,
                "/wp-content/uploads/notice/" + TestData.MARK + "-t.pdf," + headline + ",Tendor");
        Long id = id("select id from noticeboard where nb_headline = ?", headline);
        assertNotNull(id);
        assertNull("unparseable date is stored as NULL", jdbc.queryForObject("select nb_uploaddate from noticeboard where id = ?", String.class, id));
        assertEquals("http://example.com/x", jdbc.queryForObject("select link from noticeboard where id = ?", String.class, id));
    }

    /** Notice dates are parsed leniently (SimpleDateFormat default): days roll over, trailing text is ignored. */
    @Test
    public void noticeDateParsingIsLenient() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String[][] cases = {{"31/02/2099", "2099-03-03"}, {"1/2/2099xyz", "2099-02-01"}};
        for (String[] c : cases) {
            String headline = TestData.MARK + " date " + c[0] + " " + System.nanoTime();
            admin.postMultipart("/auth/api/upload",
                    fields("noticeHeader", headline, "noticeType", "Notice", "link", "", "isScrollable", "false", "date", c[0]),
                    file("noticeFile", "d.pdf", "application/pdf", PDF));
            assertEquals(c[0], c[1], jdbc.queryForObject(
                    "select date_format(nb_uploaddate, '%Y-%m-%d') from noticeboard where nb_headline = ?", String.class, headline));
        }
    }

    @Test
    public void hodNoticeResponseAndDepartmentPage() throws Exception {
        WebSession hod = loginAs(TestData.HOD);
        String headline = TestData.MARK + " hod dept notice " + System.currentTimeMillis();
        WebSession.Response up = hod.postMultipart("/hod/api/upload",
                fields("noticeHeader", headline, "noticeType", "CSENotice", "isScrollable", "true", "date", ""),
                file("noticeFile", TestData.MARK + "-h.pdf", "application/pdf", PDF));
        assertResponse("hod notice upload", up, 200, TEXT_PLAIN,
                "/wp-content/uploads/notice/" + TestData.MARK + "-h.pdf," + headline + ",CSENotice");
        assertTrue("cse about page shows scrolling dept notice", anonymous().get("/department/cse/about").body().contains(headline));
        assertTrue("cse notice page", anonymous().get("/department/cse/notice").body().contains(headline));
    }

    @Test
    public void documentUploadsStoreRowsAndShowOnPages() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        long t = System.currentTimeMillis();

        String aicte = TestData.MARK + "-aicte-" + t;
        assertResponse("aicte upload", admin.postMultipart("/auth/api/uploadAICTEapproval", fields("caption", aicte),
                file("slideImage", "a.pdf", "application/pdf", PDF)), 200, TEXT_PLAIN, "Document uploaded successfully");
        Long aicteId = id("select id from aicte_documents where title = ?", aicte);
        assertNotNull(aicteId);
        assertTrue(anonymous().get("/approval/aicteapproval").body().contains(aicte));
        assertArrayEquals(PDF, anonymous().get("/approval/document/" + aicteId).bytes);
        admin.get("/approval/deletedocument/" + aicteId);
        assertNull(id("select id from aicte_documents where id = ?", aicteId));

        String download = TestData.MARK + "-download-" + t;
        assertResponse("download upload", admin.postMultipart("/auth/api/uploadDownload", fields("caption", download),
                file("slideImage", "d.pdf", "application/pdf", PDF)), 200, TEXT_PLAIN, "Document uploaded successfully");
        Long downloadId = id("select id from download where file_name = ? and doc_type = 'download'", download);
        assertNotNull(downloadId);
        assertTrue(anonymous().get("/facilities/downloads").body().contains(download));
        assertArrayEquals(PDF, anonymous().get("/facilities/document/" + downloadId).bytes);
        admin.get("/facilities/deletedocument/" + downloadId);
        assertNull(id("select id from download where id = ?", downloadId));

        String nba = TestData.MARK + "-nba-" + t;
        WebSession.Response nbaUp = admin.postMultipart("/auth/api/nba/uploadNBA", fields("caption", nba),
                file("slideImage", "n.pdf", "application/pdf", PDF));
        assertEquals(200, nbaUp.status);
        assertNotNull(id("select id from download where file_name = ? and doc_type = 'nba'", nba));
        assertTrue(anonymous().get("/nba/nba").body().contains(nba));

        String resp = TestData.MARK + "-resp-" + t;
        assertResponse("responsibility upload", admin.postMultipart("/auth/api/uploadResponsibilityDoc", fields("caption", resp),
                file("slideImage", "r.pdf", "application/pdf", PDF)), 200, TEXT_PLAIN, "Responsibility Doc uploaded successfully");
        Long respId = id("select id from responsibility_doc where file_name = ?", resp);
        assertNotNull(respId);
        assertTrue(anonymous().get("/about/administration").body().contains(resp));
        assertArrayEquals(PDF, anonymous().get("/approval/responsibilityDoc/" + respId).bytes);
        admin.get("/approval/deleteResponsibilityDoc/" + respId);
        assertNull(id("select id from responsibility_doc where id = ?", respId));

        String lab = TestData.MARK + "-lab-" + t;
        WebSession hod = loginAs(TestData.HOD);
        assertResponse("lab upload", hod.postMultipart("/auth/api/uploadLabDoc", fields("caption", lab, "deptId", "1"),
                file("slideImage", "l.pdf", "application/pdf", PDF)), 200, TEXT_PLAIN, "Lab Doc uploaded successfully");
        Long labId = id("select id from lab_document where file_name = ?", lab);
        assertNotNull(labId);
        assertEquals(Long.valueOf(1), id("select dep_id from lab_document where id = ?", labId));
        assertTrue(anonymous().get("/academic/lab?dept_id=1").body().contains(lab));
        assertArrayEquals(PDF, anonymous().get("/approval/lab/" + labId).bytes);
        hod.get("/approval/deleteLab/" + labId);
        assertNull(id("select id from lab_document where id = ?", labId));

        assertResponse("empty caption", admin.postMultipart("/auth/api/uploadHoliday", fields("caption", ""),
                file("slideImage", "h.pdf", "application/pdf", PDF)), 400, TEXT_PLAIN, "Please provide a year");
        assertResponse("empty file", admin.postMultipart("/auth/api/uploadDownload", fields("caption", "x"),
                file("slideImage", "h.pdf", "application/pdf", new byte[0])), 400, TEXT_PLAIN, "Please select a file.");
    }

    @Test
    public void galleryAndAchievementImages() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        long t = System.currentTimeMillis();
        byte[] image = png(30, 20);

        String gallery = TestData.MARK + " gallery " + t;
        WebSession.Response g = admin.postMultipart("/auth/api/uploadgalleryimage", fields("caption", gallery),
                file("galleryImage", "g.png", "image/png", image));
        assertEquals(200, g.status);
        assertEquals(TEXT_PLAIN, g.contentType);
        assertTrue(g.body(), g.body().startsWith("/wp-content/uploads/notice/g.png," + gallery + ","));
        Long galleryId = id("select gallery_image_id from gallery_image where caption = ?", gallery);
        assertNotNull(galleryId);
        assertArrayEquals(image, anonymous().get("/" + galleryId + "/galleryImage").bytes);
        assertEquals(302, admin.get("/auth/galleryImageDelete/" + galleryId).status);
        assertNull(id("select gallery_image_id from gallery_image where gallery_image_id = ?", galleryId));

        String achievement = TestData.MARK + " achievement " + t;
        WebSession.Response wrong = admin.postMultipart("/auth/api/achievementSlideImage", fields("caption", achievement, "displayFlag", "0"),
                file("slideImage", "a.png", "image/png", image));
        assertEquals(400, wrong.status);
        assertTrue(wrong.body(), wrong.body().startsWith("Please resize your image to "));
        assertNull(id("select achievement_slide_id from achievement_slide where achievement_caption = ?", achievement));
    }

    @Test
    public void administrationRoleAddAndDelete() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String duty = TestData.MARK + " duty " + System.currentTimeMillis();
        WebSession.Response r = admin.postForm("/api/uploadAdministration", "facultyEmail", TestData.FACULTY_EMAIL,
                "designation", "Assistant Professor", "role", "Coordinator", "updateDutyAssigned", duty);
        Long roleId = id("select admin_role_id from administration where f_dutyassigned = ?", duty);
        assertNotNull("administration row: " + r, roleId);
        assertEquals(Long.valueOf(data.facultyId(TestData.FACULTY.username)), id("select faculty_id from administration where admin_role_id = ?", roleId));
        assertEquals("redirect after save: " + r, "/", r.redirectPath());
        // the roles are not shown on any page; delete redirects to the home page
        assertEquals("/", admin.get("/auth/deleteAdminRole/" + roleId).redirectPath());
        assertNull(id("select admin_role_id from administration where admin_role_id = ?", roleId));
    }

    @Test
    public void adminAddsAndDeletesStaff() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String name = TestData.MARK + " Staff " + System.currentTimeMillis();
        WebSession.Response r = admin.postMultipart("/auth/uploadfile/addstaff",
                fields("facultyPersonalEmail", TestData.MARK + "_st@example.com", "facultyOfficialEmail", TestData.MARK + "_st@keck.ac.in",
                        "facultyMobNo", "9000000007", "facultyName", name),
                file("slideImage", "p.png", "image/png", png(10, 10)));
        assertEquals("add staff: " + r, 200, r.status);
        Long staffId = id("select staff_id from staff where name = ?", name);
        assertNotNull("staff row", staffId);
        assertTrue(anonymous().get("/about/staff").body().contains(name));
        WebSession.Response del = admin.get("/about/deleteStaff?staffId=" + staffId);
        assertEquals(302, del.status);
        assertNull(id("select staff_id from staff where staff_id = ?", staffId));
    }

    @Test
    public void hodAddsFacultyResponses() throws Exception {
        WebSession hod = loginAs(TestData.HOD);
        String username = TestData.MARK + "_hodfac";
        WebSession.Response bad = hod.postForm("/hod/uploadfile/addfaculty", "facultyPersonalEmail", "", "facultyOfficialEmail", username + "@gmail.com",
                "facultyMobNo", "9000000008", "facultyName", TestData.MARK + " Hod Fac", "deptId", "1", "userName", username);
        assertEquals(200, bad.status);
        assertTrue(bad.body(), bad.body().contains("Official email doesn't belong to keck.ac.in"));
        assertNull(id("select faculty_id from faculty where username = ?", username));

        WebSession.Response ok = hod.postForm("/hod/uploadfile/addfaculty", "facultyPersonalEmail", "", "facultyOfficialEmail", username + "@keck.ac.in",
                "facultyMobNo", "9000000008", "facultyName", TestData.MARK + " Hod Fac", "deptId", "1", "userName", username);
        assertEquals(200, ok.status);
        assertNotNull("faculty row: " + ok, id("select faculty_id from faculty where username = ?", username));

        WebSession.Response dup = hod.postForm("/hod/uploadfile/addfaculty", "facultyPersonalEmail", "", "facultyOfficialEmail", username + "@keck.ac.in",
                "facultyMobNo", "9000000008", "facultyName", TestData.MARK + " Hod Fac", "deptId", "1", "userName", username);
        assertTrue(dup.body(), dup.body().contains("Faculty already registered with given username :-  " + username));
    }

    @Test
    public void hodInChargeAssignsRoleAndDepartmentHod() throws Exception {
        String before = jdbc.queryForObject("select dept_hod from department where dept_id = 1", String.class);
        long userId = data.userId(TestData.FACULTY.username);
        try {
            WebSession admin = loginAs(TestData.ADMIN);
            WebSession.Response r = admin.postForm("/auth/hod/saveRole", "facultyDetails",
                    "{\"userId\":" + userId + ",\"departmentId\":1,\"facultyName\":\"" + TestData.MARK + " Faculty Member\"}");
            assertResponse("save role", r, 200, TEXT_PLAIN, "Successfully assigned the role ");
            assertEquals(TestData.MARK + " Faculty Member", jdbc.queryForObject("select dept_hod from department where dept_id = 1", String.class));
            assertEquals(new HashSet<>(Arrays.asList(2L, 4L)), new HashSet<>(jdbc.queryForList(
                    "select role_id from user_role where user_id = ?", Long.class, userId)));
        } finally {
            jdbc.update("update department set dept_hod = ? where dept_id = 1", before);
            jdbc.update("delete from user_role where user_id = ? and role_id = 4", userId);
        }
    }

    @Test
    public void registrationCreatesUserAndRejectsInvalidInput() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String username = TestData.MARK + "_reg";
        WebSession.Response invalid = admin.postForm("/auth/registration", "username", username, "password", "short", "passwordConfirm", "short", "role", "2");
        assertEquals("invalid registration: " + invalid, 404, invalid.status); // returns the view "registration", which has no JSP
        assertNull(id("select id from test_user where username = ?", username));

        WebSession.Response ok = admin.postForm("/auth/registration", "username", username, "password", "Long#Password1",
                "passwordConfirm", "Long#Password1", "role", "1");
        assertEquals(302, ok.status);
        assertEquals("/", ok.redirectPath());
        Long userId = id("select id from test_user where username = ?", username);
        assertNotNull(userId);
        assertEquals(Long.valueOf(1), id("select role_id from user_role where user_id = ?", userId));
        WebSession.Response login = anonymous().login(username, "Long#Password1");
        assertEquals(302, login.status);
        assertEquals("/auth/uploadDashboard", login.redirectPath());
    }

    @org.springframework.beans.factory.annotation.Autowired
    private com.college.core.config.KecProperties kecProperties;

    /** Values that were hard-coded before the refactoring (kec.* properties must keep them). */
    @Test
    public void applicationSettingsKeepTheOldValues() throws Exception {
        assertEquals("test@keck.ac.in", kecProperties.mail().from());
        assertEquals("principal@keck.ac.in", kecProperties.mail().grievanceTo());
        assertEquals("/wp-content/uploads/notice/", kecProperties.paths().noticeUploadDir());
        assertEquals("/sites/default/files/compressed/", kecProperties.paths().homePageImageDir());
        assertEquals("https://api.textlocal.in/send/?", kecProperties.sms().url());
        assertEquals("KECTXT", kecProperties.sms().sender());

        anonymous().get("/approval/raisequery?name=" + TestData.MARK + "&email=a%40example.com&phone=1&query=q&registration=1");
        assertEquals("grievance sender", "test@keck.ac.in", mail.simpleMessages.get(0).getFrom());
    }

    @Test
    public void passwordResetLinkWithExpiredOrForeignTokenIsRejected() throws Exception {
        long facultyUser = data.userId(TestData.FACULTY.username);
        long adminUser = data.userId(TestData.ADMIN.username);
        jdbc.update("insert into password_reset_token (expiry_date, token, user_id) values (date_sub(now(), interval 1 day), ?, ?)",
                TestData.MARK + "-expired", facultyUser);
        jdbc.update("insert into password_reset_token (expiry_date, token, user_id) values (date_add(now(), interval 1 day), ?, ?)",
                TestData.MARK + "-valid", facultyUser);

        WebSession.Response expired = anonymous().get("/user/changePassword?id=" + facultyUser + "&token=" + TestData.MARK + "-expired");
        assertEquals(302, expired.status);
        assertEquals("/login?lang=en", expired.redirectPath());

        WebSession.Response foreign = anonymous().get("/user/changePassword?id=" + adminUser + "&token=" + TestData.MARK + "-valid");
        assertEquals(302, foreign.status);
        assertEquals("/login?lang=en", foreign.redirectPath());

        WebSession.Response valid = anonymous().get("/user/changePassword?id=" + facultyUser + "&token=" + TestData.MARK + "-valid");
        assertEquals(302, valid.status);
        assertEquals("/jsp/updatePassword.jsp?lang=en", valid.redirectPath());
    }

    /** Anonymous GET that inserts a blank faculty row (security problem, kept until the security follow-up). */
    @Test
    public void approvalAddFacultyInsertsPlaceholderRow() throws Exception {
        long maxBefore = jdbc.queryForObject("select coalesce(max(faculty_id), 0) from faculty", Long.class);
        try {
            WebSession.Response r = anonymous().get("/approval/addFaculty");
            assertEquals("response: " + r, 404, r.status);
            List<Long> ids = jdbc.queryForList("select faculty_id from faculty where faculty_id > ? and f_name is null and username is null", Long.class, maxBefore);
            assertEquals(1, ids.size());
            List<Map<String, Object>> props = jdbc.queryForList(
                    "select p.key_property_name, v.key_prop_val from faculty_key_props p join faculty_keyprop_values v on v.key_property_id = p.key_property_id "
                            + "where p.faculty_id = ? order by v.key_prop_values_id", ids.get(0));
            assertEquals(2, props.size());
            assertEquals("Administrative Responsibilities", props.get(0).get("key_property_name"));
            assertEquals("Design and Development of College Website", props.get(0).get("key_prop_val"));
            assertEquals("Cultural & Sports", props.get(1).get("key_prop_val"));
        } finally {
            String blank = "select faculty_id from faculty where faculty_id > " + maxBefore + " and f_name is null and username is null and dept_id is null";
            jdbc.update("delete from faculty_keyprop_values where key_property_id in (select key_property_id from faculty_key_props where faculty_id in (" + blank + "))");
            jdbc.update("delete from faculty_key_props where faculty_id in (" + blank + ")");
            jdbc.update("delete from faculty where faculty_id in (select * from (" + blank + ") t)");
        }
    }

    /** Department sites: only the listed pages exist, about/notice are GET-only, static pages accept any method. */
    @Test
    public void departmentUrlsAndMethods() throws Exception {
        WebSession anon = anonymous();
        assertEquals(404, anon.get("/department/cse/nosuchpage").status);
        assertEquals(404, anon.get("/department/cse/faculty").status);       // "faculty" page exists for mechanical only
        assertEquals(404, anon.get("/department/civil/subodh").status);      // faculty shortcut of another department
        assertEquals(404, anon.get("/department/mechanical/about").status);  // mechanical's about page is /mech
        assertEquals(404, anon.get("/department/nosuchdept/vision").status);
        assertEquals(405, anon.postForm("/department/cse/about").status);
        assertEquals(405, anon.postForm("/department/mechanical/mech").status);
        assertEquals(405, anon.postForm("/department/ece/notice").status);
        assertCompletePage("POST static page", anon.postForm("/department/cse/vision"));
        assertCompletePage("POST mechanical faculty page", anon.postForm("/department/mechanical/faculty"));
        assertCompletePage("trailing slash", anon.get("/department/eee/labs/"));
        assertEquals(404, anon.postForm("/department/cse/dky").status);       // faculty shortcut view is missing (broken today)
    }

    @Test
    public void userNameAutocompleteFindsTestUsers() throws Exception {
        WebSession.Response r = loginAs(TestData.FACULTY).get("/user/userNameAutocomplete?userName=" + TestData.MARK);
        assertEquals(200, r.status);
        for (TestData.TestUser u : TestData.ALL) {
            assertTrue(r.body(), r.body().contains("\"" + u.username + "\""));
        }
    }
}
