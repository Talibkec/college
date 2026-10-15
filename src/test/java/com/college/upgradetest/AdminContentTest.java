package com.college.upgradetest;

import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/** Admin create/list/download/delete flows, multipart uploads, grievance mail and SMS (mail/SMS are recorded). */
public class AdminContentTest extends AbstractAppTest {

    private static final byte[] PDF = "%PDF-1.4 upgrade test document".getBytes(StandardCharsets.US_ASCII);

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
        java.util.List<Long> ids = jdbc.queryForList(sql, Long.class, args);
        return ids.isEmpty() ? null : ids.get(0);
    }

    @Test
    public void noticeUploadListDownloadDelete() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String headline = TestData.MARK + " notice " + System.currentTimeMillis();

        WebSession.Response up = admin.postMultipart("/auth/api/upload",
                fields("noticeHeader", headline, "noticeType", "Notice", "link", "", "isScrollable", "true", "date", ""),
                file("noticeFile", TestData.MARK + "-notice.pdf", "application/pdf", PDF));
        assertEquals("upload: " + up, 200, up.status);
        assertContains("upload response", up, headline);
        Long id = id("select id from noticeboard where nb_headline = ?", headline);
        assertTrue("notice row created", id != null);
        assertEquals(TestData.ADMIN.username, jdbc.queryForObject("select nb_username from noticeboard where id = ?", String.class, id));

        assertContains("notices page", anonymous().get("/category/notices"), headline);
        assertContains("home page", anonymous().get("/"), headline);
        WebSession.Response download = anonymous().get("/notice/" + id);
        assertEquals(200, download.status);
        assertArrayEquals("downloaded notice file", PDF, download.bytes);

        WebSession.Response del = admin.get("/auth/delNotice/" + id);
        assertEquals(302, del.status);
        assertFalse("notice deleted", anonymous().get("/category/notices").body().contains(headline));
        assertEquals(null, id("select id from noticeboard where id = ?", id));
    }

    @Test
    public void anonymousCannotUploadNotices() throws Exception {
        String headline = TestData.MARK + " anon notice";
        WebSession.Response up = anonymous().postMultipart("/auth/api/upload",
                fields("noticeHeader", headline, "noticeType", "Notice", "link", "", "isScrollable", "false", "date", ""),
                file("noticeFile", TestData.MARK + "-anon.pdf", "application/pdf", PDF));
        assertEquals(302, up.status);
        assertTrue(up.redirectPath().endsWith("/login"));
        assertEquals(null, id("select id from noticeboard where nb_headline = ?", headline));
    }

    @Test
    public void hodUploadsDepartmentNotice() throws Exception {
        WebSession hod = loginAs(TestData.HOD);
        String headline = TestData.MARK + " hod notice " + System.currentTimeMillis();
        WebSession.Response up = hod.postMultipart("/hod/api/upload",
                fields("noticeHeader", headline, "noticeType", "CSENotice", "isScrollable", "false", "date", ""),
                file("noticeFile", TestData.MARK + "-hod.pdf", "application/pdf", PDF));
        assertEquals("upload: " + up, 200, up.status);
        assertTrue("hod notice row", id("select id from noticeboard where nb_headline = ?", headline) != null);
    }

    @Test
    public void slideImageUploadServeDelete() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String caption = TestData.MARK + " slide " + System.currentTimeMillis();
        byte[] slide = png(2000, 550); // home slider images must be exactly 2000x550 (flag 0)
        WebSession.Response up = admin.postMultipart("/auth/api/uploadslideimage",
                fields("caption", caption, "flag", "0"),
                file("slideImage", TestData.MARK + "-slide.png", "image/png", slide));
        assertEquals("upload: " + up, 200, up.status);
        Long id = id("select image_slide_id from image_slide where caption = ?", caption);
        assertTrue("slide row", id != null);

        WebSession.Response img = anonymous().get("/" + id + "/slideImage");
        assertEquals(200, img.status);
        assertArrayEquals(slide, img.bytes);

        assertEquals(302, admin.get("/auth/deleteSlideImage/" + id).status);
        assertEquals(null, id("select image_slide_id from image_slide where image_slide_id = ?", id));
    }

    @Test
    public void holidayDocumentUploadDownloadDelete() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String fileName = TestData.MARK + "-holiday-" + System.currentTimeMillis(); // the caption is stored as file_name
        WebSession.Response up = admin.postMultipart("/auth/api/uploadHoliday",
                fields("caption", fileName), file("slideImage", "holidays.pdf", "application/pdf", PDF));
        assertEquals("upload: " + up, 200, up.status);
        Long id = id("select id from holiday where file_name = ?", fileName);
        assertTrue("holiday row", id != null);
        assertContains("holidays page", anonymous().get("/academic/holidays"), "/approval/holiday/" + id);

        WebSession.Response download = anonymous().get("/approval/holiday/" + id);
        assertEquals(200, download.status);
        assertArrayEquals(PDF, download.bytes);

        // anonymous delete is refused, admin delete works
        anonymous().get("/approval/deleteHoliday/" + id);
        assertTrue("anonymous must not delete", id("select id from holiday where id = ?", id) != null);
        admin.get("/approval/deleteHoliday/" + id);
        assertEquals(null, id("select id from holiday where id = ?", id));
    }

    /** commons-fileupload had no size limit; servlet multipart defaults to 1MB, so the app sets 20MB/25MB. */
    @Test
    public void largeUploadAboveOneMegabyteIsAccepted() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String fileName = TestData.MARK + "-large-" + System.currentTimeMillis();
        byte[] big = new byte[5 * 1024 * 1024];
        java.util.Arrays.fill(big, (byte) 'x');
        WebSession.Response up = admin.postMultipart("/auth/api/uploadHoliday",
                fields("caption", fileName), file("slideImage", "big.pdf", "application/pdf", big));
        assertEquals("5MB upload: " + up, 200, up.status);
        Long id = id("select id from holiday where file_name = ?", fileName);
        assertTrue("holiday row", id != null);
        assertEquals(big.length, anonymous().get("/approval/holiday/" + id).bytes.length);
    }

    @Test
    public void courseSeatsCreateListDelete() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String branch = TestData.MARK + " branch " + System.currentTimeMillis();
        WebSession.Response add = admin.postForm("/auth/uploadfile/coursecreator",
                "branchName", branch, "regularSeat", "60", "lateralSeat", "6");
        assertEquals(200, add.status);
        assertContains("add course", add, "Course added successfully");
        Long id = id("select course_id from no_of_seat where branch_name = ?", branch);
        assertTrue("course row", id != null);
        assertContains("admission page", anonymous().get("/academic/admission"), branch);

        WebSession.Response del = admin.get("/auth/course/" + id);
        assertEquals(302, del.status);
        assertEquals("/auth/course", del.redirectPath());
        assertEquals(null, id("select course_id from no_of_seat where course_id = ?", id));
    }

    @Test
    public void placementAddListDelete() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String student = TestData.MARK + " Student " + System.currentTimeMillis();
        admin.postForm("/auth/uploadfile/addPlacements", "studentName", student, "companyName", "Upgrade Corp",
                "studentSession", "2099-2103", "deptId", "1", "registrationNo", "99001");
        Long id = id("select placement_id from placements where student_name = ?", student);
        assertTrue("placement row", id != null);
        assertContains("placed students page", anonymous().get("/trainingplacement/placedstudent"), student);

        anonymous().get("/trainingplacement/deletePlacement?placementId=" + id);
        assertTrue("anonymous must not delete", id("select placement_id from placements where placement_id = ?", id) != null);
        WebSession.Response del = admin.get("/trainingplacement/deletePlacement?placementId=" + id);
        assertEquals(302, del.status);
        assertEquals(null, id("select placement_id from placements where placement_id = ?", id));
    }

    @Test
    public void tpoCoordinatorAddListDelete() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String student = TestData.MARK + " Coordinator " + System.currentTimeMillis();
        admin.postForm("/auth/uploadfile/addtpocoordinator", "studentName", student, "mobile", "9000000002",
                "email", TestData.MARK + "@example.com", "studentSession", "2099-2103", "deptId", "1", "registrationNo", "99002");
        Long id = id("select coordinator_id from tpo_coordinator where student_name = ?", student);
        assertTrue("coordinator row", id != null);
        assertContains("coordinators page", anonymous().get("/trainingplacement/studentplacementcoordinator"), student);

        admin.get("/auth/tpoCoordinator/" + id);
        assertEquals(null, id("select coordinator_id from tpo_coordinator where coordinator_id = ?", id));
    }

    @Test
    public void adminAddsFacultyWhoCanLogInAndIsListed() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String username = TestData.MARK + "_newfac";
        String name = TestData.MARK + " New Faculty";
        admin.postForm("/auth/uploadfile/addfaculty", "facultyPersonalEmail", username + "@example.com",
                "facultyOfficialEmail", username + "@keck.ac.in", "facultyMobNo", "9000000003",
                "facultyName", name, "deptId", "1", "userName", username);
        Long facultyId = id("select faculty_id from faculty where username = ?", username);
        assertTrue("faculty row", facultyId != null);
        assertEquals("faculty role", Long.valueOf(2), jdbc.queryForObject(
                "select ur.role_id from user_role ur join test_user u on u.id = ur.user_id where u.username = ?", Long.class, username));

        // the new account logs in with password = user name (existing behaviour)
        WebSession.Response login = anonymous().login(username, username);
        assertEquals(302, login.status);
        assertEquals("/facultyDetails?facultyId=" + facultyId, login.redirectPath());

        assertContains("faculty list", anonymous().get("/faculty?deptno=1"), name);
        assertContains("faculty profile", anonymous().get("/facultyDetails?facultyId=" + facultyId), name);
        assertContains("autocomplete", anonymous().get("/faculty/facultyNameAutocompletes?facultyName=" + TestData.MARK), name);

        WebSession.Response del = admin.get("/deleteFaculty?facultyId=" + facultyId + "&deptno=1");
        assertEquals(302, del.status);
        assertEquals("/faculty?deptno=1", del.redirectPath());
        assertEquals(null, id("select faculty_id from faculty where faculty_id = ?", facultyId));
    }

    @Test
    public void addFacultyRejectsNonCollegeEmail() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        String username = TestData.MARK + "_badmail";
        admin.postForm("/auth/uploadfile/addfaculty", "facultyPersonalEmail", "", "facultyOfficialEmail", username + "@gmail.com",
                "facultyMobNo", "9000000004", "facultyName", TestData.MARK + " Bad Mail", "deptId", "1", "userName", username);
        assertEquals(null, id("select faculty_id from faculty where username = ?", username));
    }

    @Test
    public void grievanceQueryIsMailedToPrincipal() throws Exception {
        WebSession.Response r = anonymous().get("/approval/raisequery?name=" + TestData.MARK
                + "&email=student%40example.com&phone=9000000005&query=upgrade+test+query&registration=99003");
        assertEquals(200, r.status);
        assertContains("grievance response", r, "grievance");
        assertEquals(1, mail.simpleMessages.size());
        SimpleMailMessage m = mail.simpleMessages.get(0);
        assertEquals("principal@keck.ac.in", m.getTo()[0]);
        assertTrue(m.getText(), m.getText().contains("upgrade test query"));
    }

    @Test
    public void bulkSmsGoesThroughMessageSender() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        WebSession.Response r = admin.postForm("/auth/sendMessage", "smsGroup", "individual",
                "message", TestData.MARK + " sms", "numberInput", "9000000006");
        assertCompletePage("sms page after send", r);
        assertEquals("one SMS recorded: " + sms.sent, 1, sms.sent.size());
        assertTrue(sms.sent.get(0), sms.sent.get(0).contains("9000000006"));
    }
}
