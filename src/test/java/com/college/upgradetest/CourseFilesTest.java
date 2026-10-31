package com.college.upgradetest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Course files of the department sites (decision 2026-10-31, docs/sql/dynamic/17_course_file.sql):
 * /department/&lt;site&gt;/course (editable paragraph, then the files by semester), /manage/course-files (HOD of the
 * own department, Admin), /course-file/{id}. The test HOD is made HOD of CSE for the test; every course file a test
 * creates is deleted afterwards.
 */
public class CourseFilesTest extends AbstractAppTest {

    private static final byte[] PDF = "%PDF-1.4 course file test".getBytes(StandardCharsets.US_ASCII);
    private static final byte[] DOCX = new byte[]{'P', 'K', 3, 4, 20, 0, 6, 0, 8, 0};
    private static final String[] SITES = {"ash", "civil", "cse", "ece", "ecevlsi", "eee", "fp", "manufacturing",
            "mechandsmartmanufacturing", "mechanical"};

    private String hodOfCse;

    @Before
    public void makeTestHodHodOfCse() {
        hodOfCse = jdbc.queryForObject("select hod_username from department_site where site_code = 'cse'", String.class);
        jdbc.update("update department_site set hod_username = ? where site_code = 'cse'", TestData.HOD.username);
    }

    @After
    public void deleteTestCourseFiles() {
        jdbc.update("update department_site set hod_username = ? where site_code = 'cse'", hodOfCse);
        jdbc.update("delete from content_file where file_id in (select file_id from course_file where created_by like ?)", TestData.MARK + "%");
        jdbc.update("delete from course_file where created_by like ?", TestData.MARK + "%");
        jdbc.update("delete from content_file where uploaded_by like ? and area_code like 'department.%'", TestData.MARK + "%");
    }

    private static Map<String, String> fields(String... nameValues) {
        Map<String, String> m = new LinkedHashMap<>();
        for (int i = 0; i < nameValues.length; i += 2) m.put(nameValues[i], nameValues[i + 1]);
        return m;
    }

    private static Map<String, WebSession.FilePart> file(String name, String type, byte[] bytes) {
        return Collections.singletonMap("file", new WebSession.FilePart(name, type, bytes));
    }

    private Long courseFileId(String subject) {
        return jdbc.queryForObject("select course_file_id from course_file where subject_name = ?", Long.class, subject);
    }

    /** Every department site: complete page with its sidebar, whose "Course File" entry now opens the course page. */
    @Test
    public void everyCoursePageHasItsSidebarParagraphAndFileList() throws Exception {
        for (String site : SITES) {
            WebSession.Response page = anonymous().get("/department/" + site + "/course");
            assertCompletePage(site, page);
            assertContains(site + " sidebar", page, "role=\"complementary\"");
            assertContains(site + " paragraph", page, "The course file is the complete teaching record of a subject");
            assertContains(site + " file list", page, "id=\"course-files\"");
            assertFalse(site + " no manage button for visitors", page.body().contains("manage-course-files"));
            assertFalse(site + " Course File no longer opens Program Activities",
                    page.body().contains("/programs\" class=\"sf-depth-2\">Course File"));
        }
        assertContains("EEE sidebar", anonymous().get("/department/eee/course"), "href=\"/department/eee/course\" class=\"sf-depth-2\">Course File");
        for (String path : new String[]{"/department/eee/programs", "/department/eee/question", "/department/ece/question",
                "/department/ecevlsi/question"}) {
            WebSession.Response page = anonymous().get(path);
            assertCompletePage(path + " no longer truncated", page);
            assertContains(path + " sidebar", page, "role=\"complementary\"");
        }
    }

    @Test
    public void hodUploadsChangesHidesAndDeletesCourseFilesOfOwnDepartment() throws Exception {
        WebSession hod = loginAs(TestData.HOD);
        String subject = TestData.MARK + " Data Structures";
        assertContains("manage button on own page", hod.get("/department/cse/course"), "href=\"/manage/course-files?dept=cse\"");
        assertEquals("only department: straight to it", "/manage/course-files?dept=cse", hod.get("/manage/course-files").redirectPath());
        assertCompletePage("HOD screen", hod.get("/manage/course-files?dept=cse"));
        assertContains("HOD dashboard tile", hod.get("/hod/hodDashboard"), "href=\"/manage/course-files\"");
        assertContains("content nav", hod.get("/manage"), "href=\"/manage/course-files?dept=cse\"");

        // validation: nothing saved
        assertContains("semester required", hod.postMultipart("/manage/course-files/save",
                fields("dept", "cse", "subjectName", subject, "visible", "on"), file("cf.pdf", "application/pdf", PDF)), "Please choose the semester.");
        assertContains("only documents", hod.postMultipart("/manage/course-files/save",
                fields("dept", "cse", "semester", "3", "subjectName", subject, "visible", "on"),
                file("cf.html", "text/html", "<html><script>alert(1)</script>".getBytes(StandardCharsets.US_ASCII))), "Only PDF and Word");
        assertContains("file required", hod.postMultipart("/manage/course-files/save",
                fields("dept", "cse", "semester", "3", "subjectName", subject, "visible", "on"), Collections.emptyMap()), "Please choose the course file");
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from course_file where created_by like ?", Integer.class, TestData.MARK + "%"));

        // create
        assertEquals("/manage/course-files?saved=1&dept=cse", hod.postMultipart("/manage/course-files/save",
                fields("dept", "cse", "semester", "3", "subjectCode", "105301", "subjectName", subject, "facultyName",
                        TestData.MARK + " Teacher", "academicSession", "2025-26", "visible", "on"),
                file("DS course file.pdf", "application/pdf", PDF)).redirectPath());
        Long id = courseFileId(subject);
        Long firstFile = jdbc.queryForObject("select file_id from course_file where course_file_id = ?", Long.class, id);
        assertEquals("department.cse", jdbc.queryForObject("select area_code from content_file where file_id = ?", String.class, firstFile));
        WebSession.Response page = anonymous().get("/department/cse/course");
        assertContains("shown on the page", page, subject);
        assertContains("semester heading", page, "Semester 3");
        assertContains("code", page, "105301");
        assertContains("file link", page, "href=\"/course-file/" + id + "/DS_course_file.pdf\"");
        assertFalse("empty state gone", page.body().contains("course-files-empty"));
        WebSession.Response pdf = anonymous().get("/course-file/" + id + "/DS_course_file.pdf");
        assertEquals(200, pdf.status);
        assertEquals("application/pdf", pdf.contentType);
        assertArrayEquals(PDF, pdf.bytes);
        assertTrue(pdf.contentDisposition, pdf.contentDisposition.startsWith("inline"));
        assertContains("listed on the HOD screen", hod.get("/manage/course-files?dept=cse"), subject);
        assertContains("edit form", hod.get("/manage/course-files?dept=cse&edit=" + id), "value=\"105301\"");

        // change: new file replaces the old one, which is deleted
        hod.postMultipart("/manage/course-files/save", fields("dept", "cse", "id", String.valueOf(id), "semester", "4",
                "subjectCode", "105401", "subjectName", subject + " II", "visible", "on"), file("ds.docx", "application/octet-stream", DOCX));
        Long secondFile = jdbc.queryForObject("select file_id from course_file where course_file_id = ?", Long.class, id);
        assertNotEquals(firstFile, secondFile);
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from content_file where file_id = ?", Integer.class, firstFile));
        WebSession.Response docx = anonymous().get("/course-file/" + id);
        assertTrue(docx.contentType, docx.contentType.startsWith("application/vnd.openxmlformats-officedocument.wordprocessingml"));
        assertTrue(docx.contentDisposition, docx.contentDisposition.startsWith("attachment"));
        page = anonymous().get("/department/cse/course");
        assertContains("changed", page, subject + " II");
        assertContains("moved", page, "Semester 4");

        // change without a file keeps it; hidden = not on the page, file only for the HOD
        hod.postMultipart("/manage/course-files/save", fields("dept", "cse", "id", String.valueOf(id), "semester", "4",
                "subjectName", subject + " II"), Collections.emptyMap());
        assertEquals(secondFile, jdbc.queryForObject("select file_id from course_file where course_file_id = ?", Long.class, id));
        assertFalse("hidden", anonymous().get("/department/cse/course").body().contains(subject));
        assertEquals(404, anonymous().get("/course-file/" + id).status);
        assertEquals(200, hod.get("/course-file/" + id).status);

        // delete
        assertEquals("/manage/course-files?saved=1&dept=cse", hod.postForm("/manage/course-files/delete", "dept", "cse", "id", String.valueOf(id)).redirectPath());
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from course_file where course_file_id = ?", Integer.class, id));
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from content_file where file_id = ?", Integer.class, secondFile));
        assertEquals(404, anonymous().get("/course-file/" + id).status);
        assertEquals(1, (int) jdbc.queryForObject("select count(*) from content_change_log where entity = 'course_file' "
                + "and entity_id = ? and action = 'delete'", Integer.class, String.valueOf(id)));
    }

    @Test
    public void onlyTheOwnDepartmentsHodAndAdminManageCourseFiles() throws Exception {
        WebSession hod = loginAs(TestData.HOD);
        assertEquals("other department", 403, hod.get("/manage/course-files?dept=eee").status);
        assertEquals("other department", 403, hod.postMultipart("/manage/course-files/save",
                fields("dept", "eee", "semester", "1", "subjectName", TestData.MARK + " eee", "visible", "on"),
                file("x.pdf", "application/pdf", PDF)).status);
        assertFalse("no manage button on another department's page", hod.get("/department/eee/course").body().contains("manage-course-files"));
        assertEquals("faculty", 403, loginAs(TestData.FACULTY).get("/manage/course-files").status);
        assertEquals("SubAdmin edits texts, not course files", 403, loginAs(TestData.SUBADMIN).get("/manage/course-files").status);
        assertEquals("anonymous must log in", "/login", anonymous().get("/manage/course-files").redirectPath());

        WebSession admin = loginAs(TestData.ADMIN);
        assertContains("Admin chooses the department", admin.get("/manage/course-files"), "href=\"/manage/course-files?dept=eee\"");
        admin.postMultipart("/manage/course-files/save", fields("dept", "eee", "semester", "5", "subjectName", TestData.MARK + " Machines",
                "visible", "on"), file("m.pdf", "application/pdf", PDF));
        Long id = courseFileId(TestData.MARK + " Machines");
        assertContains("on the EEE page", anonymous().get("/department/eee/course"), TestData.MARK + " Machines");
        assertFalse("not on CSE's", anonymous().get("/department/cse/course").body().contains(TestData.MARK + " Machines"));
        assertEquals("HOD of CSE cannot delete EEE's", 403, hod.postForm("/manage/course-files/delete", "dept", "eee", "id", String.valueOf(id)).status);
        assertEquals("nor through the own department", 403, hod.postForm("/manage/course-files/delete", "dept", "cse", "id", String.valueOf(id)).status);
        assertEquals(1, (int) jdbc.queryForObject("select count(*) from course_file where course_file_id = ?", Integer.class, id));
    }
}
