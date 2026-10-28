package com.college.upgradetest;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Faculty profile: document upload/download/delete, profile photo, profile edit with key properties. */
public class FacultySelfServiceTest extends AbstractAppTest {

    private static final byte[] DOC = "%PDF-1.4 faculty upgrade test document".getBytes(StandardCharsets.US_ASCII);

    private static Map<String, String> profileFields(String isProfilePic, String header) {
        Map<String, String> m = new LinkedHashMap<>();
        m.put("facultydocumentsHeader", header);
        m.put("isProfilePic", isProfilePic);
        m.put("facultyPersonalEmail", "");
        m.put("facultyOfficialEmail", "");
        m.put("facultyMobNo", "9000000001");
        m.put("facultyName", "");
        return m;
    }

    @Test
    public void documentUploadDownloadDelete() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY);
        String header = TestData.MARK + " publication " + System.currentTimeMillis();
        WebSession.Response up = faculty.postMultipart("/uploadfile/facultyFileUpload", profileFields("No", header),
                Collections.singletonMap("facultydocumentsFile", new WebSession.FilePart("paper.pdf", "application/pdf", DOC)));
        assertEquals("upload: " + up, 200, up.status);

        List<Long> ids = jdbc.queryForList("select id from facultydocuments where f_headline = ?", Long.class, header);
        assertEquals("document row", 1, ids.size());
        long docId = ids.get(0);
        assertEquals(TestData.FACULTY.username, jdbc.queryForObject("select f_uploadedby from facultydocuments where id = ?", String.class, docId));

        long facultyId = data.facultyId(TestData.FACULTY.username);
        assertContains("profile lists document", anonymous().get("/facultyDetails?facultyId=" + facultyId), header);
        WebSession.Response download = anonymous().get("/" + docId + "/documents");
        assertEquals(200, download.status);
        assertArrayEquals(DOC, download.bytes);

        faculty.get("/common/deleteFacultyDoc/" + docId);
        assertEquals(0, jdbc.queryForList("select id from facultydocuments where id = ?", Long.class, docId).size());
    }

    @Test
    public void profilePhotoUploadIsServed() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY);
        java.io.ByteArrayOutputStream png = new java.io.ByteArrayOutputStream();
        javax.imageio.ImageIO.write(new java.awt.image.BufferedImage(40, 50, java.awt.image.BufferedImage.TYPE_INT_RGB), "png", png);

        WebSession.Response up = faculty.postMultipart("/uploadfile/facultyFileUpload", profileFields("Yes", "photo"),
                Collections.singletonMap("facultydocumentsFile", new WebSession.FilePart("me.png", "image/png", png.toByteArray())));
        assertEquals("upload: " + up, 200, up.status);

        long facultyId = data.facultyId(TestData.FACULTY.username);
        assertEquals("png", jdbc.queryForObject("select f_filetype from faculty where faculty_id = ?", String.class, facultyId));
        WebSession.Response img = anonymous().get("/" + facultyId + "/image");
        assertEquals(200, img.status);
        assertArrayEquals(png.toByteArray(), img.bytes);
        assertContains("faculty list shows photo", anonymous().get("/faculty?deptno=" + TestData.DEPT_ID), "data:image/png;base64,");
    }

    @Test
    public void profileEditSavesNameAndKeyProperties() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY);
        long facultyId = data.facultyId(TestData.FACULTY.username);
        String newName = TestData.MARK + " Faculty Member";       // unchanged name keeps other tests stable
        String propName = TestData.MARK + " Research Area";
        String propValue = TestData.MARK + " Distributed Systems";
        // same JSON the edit page's JavaScript (js/editfacultydetails.js) sends
        String details = "{\"facultyKeyProps\":[{\"keyPropertyName\":\"" + propName + "\",\"keyPropertyId\":-9999,"
                + "\"keyPropsOrder\":1,\"keyPropVals\":[{\"keyPropVal\":\"" + propValue + "\",\"keyPropValuesId\":-9999,"
                + "\"keyValueOrder\":1}]}],\"facultyId\":-9999,\"facultyName\":\"\",\"departmentId\":-9999,"
                + "\"facultyMobNo\":-9999,\"facultyOfficialEmail\":\"\",\"facultyPersonalEmail\":\"\",\"imageURL\":\"\","
                + "\"facultyPhoto\":[],\"user\":{},\"fileType\":\"\"}";
        Map<String, String> f = new LinkedHashMap<>();
        f.put("facultyName", newName);
        f.put("facultyMobNo", "9000000099");
        f.put("facultyOfficialEmail", TestData.FACULTY_EMAIL);
        f.put("facultyPersonalEmail", "");
        f.put("facultyId", String.valueOf(facultyId));
        f.put("newProps", "{\"" + propName + "\":[\"" + propValue + "\"]}");
        f.put("oldProps", "{}");
        f.put("oldKeyOrder", "");
        f.put("newKeyOrder", propName);
        f.put("facultyDetails", details);

        WebSession.Response r = faculty.postMultipart("/uploadfile/editfacultydetails", f,
                Collections.<String, WebSession.FilePart>emptyMap());
        assertEquals("edit: " + r, 200, r.status);
        assertEquals(Long.valueOf(9000000099L), jdbc.queryForObject("select f_mnumber from faculty where faculty_id = ?", Long.class, facultyId));
        assertEquals(1, jdbc.queryForList("select key_property_id from faculty_key_props where faculty_id = ? and key_property_name = ?",
                Long.class, facultyId, propName).size());

        WebSession.Response profile = anonymous().get("/facultyDetails?facultyId=" + facultyId);
        assertContains("profile shows key property", profile, propName);
        assertContains("profile shows key property value", profile, propValue);
        assertCompletePage("edit page", faculty.get("/uploadfile/editfacultydetails?facultyId=" + facultyId));
    }

    @Test
    public void facultyCannotOpenAdminPages() throws Exception {
        assertEquals(403, loginAs(TestData.FACULTY).get("/auth/uploadfile/addfaculty").status);
    }

    @Test
    public void autocompleteFindsFaculty() throws Exception {
        // search by full name (header search box), returns {"name":..,"id":..}
        WebSession.Response r = anonymous().get("/faculty/searchFacultyNames?facultyName=" + TestData.MARK + "+Faculty+Member");
        assertEquals(200, r.status);
        assertTrue(r.toString(), r.body().contains("\"id\":" + data.facultyId(TestData.FACULTY.username)));
    }

    @Test
    public void uploadPagesPostWithTheirScriptAndFilesOpenWithTheirExtension() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY);
        for (String pic : new String[]{"No", "Yes"}) {
            WebSession.Response page = faculty.get("/uploadfile/facultyFileUpload?isProfilePic=" + pic);
            assertCompletePage("upload page " + pic, page);
            assertContains("submit handled by the script", page, "id=\"btnSubmit\"");
            assertContains("script", page, "<script src=\"/js/facultyfileUpload.js\"></script>");
        }
        // the photo page sends no mobile number any more
        java.io.ByteArrayOutputStream png = new java.io.ByteArrayOutputStream();
        javax.imageio.ImageIO.write(new java.awt.image.BufferedImage(8, 8, java.awt.image.BufferedImage.TYPE_INT_RGB), "png", png);
        Map<String, String> photo = new LinkedHashMap<>(profileFields("Yes", "NotAvailable"));
        photo.remove("facultyMobNo");
        assertEquals(200, faculty.postMultipart("/uploadfile/facultyFileUpload", photo,
                Collections.singletonMap("facultydocumentsFile", new WebSession.FilePart("me.png", "image/png", png.toByteArray()))).status);
        long facultyId = data.facultyId(TestData.FACULTY.username);
        WebSession.Response img = anonymous().get("/" + facultyId + "/image.png");
        assertEquals(200, img.status);
        assertTrue(img.contentType, img.contentType.startsWith("image/png"));
        assertArrayEquals(png.toByteArray(), img.bytes);

        // a document opens with its extension as before the upgrade (Spring Boot 1.3 suffix matching)
        String header = TestData.MARK + " ext doc " + System.currentTimeMillis();
        faculty.postMultipart("/uploadfile/facultyFileUpload", profileFields("No", header),
                Collections.singletonMap("facultydocumentsFile", new WebSession.FilePart("paper.pdf", "application/pdf", DOC)));
        long docId = jdbc.queryForObject("select id from facultydocuments where f_headline = ?", Long.class, header);
        assertContains("profile links with extension", anonymous().get("/facultyDetails?facultyId=" + facultyId), "/" + docId + "/documents.pdf");
        WebSession.Response pdf = anonymous().get("/" + docId + "/documents.pdf");
        assertEquals(200, pdf.status);
        assertTrue(pdf.contentType, pdf.contentType.startsWith("application/pdf"));
        assertArrayEquals(DOC, pdf.bytes);
        WebSession.Response other = anonymous().get("/" + docId + "/documents.html");
        assertEquals(200, other.status);
        assertTrue("unknown extensions are downloads, never HTML: " + other.contentType, other.contentType.startsWith("application/octet-stream"));

        // a link needs no file
        String linkHeader = TestData.MARK + " ext link " + System.currentTimeMillis();
        Map<String, String> link = new LinkedHashMap<>(profileFields("No", linkHeader));
        link.put("isLink", "true");
        link.put("linkAddress", "https://example.com/paper");
        assertEquals(200, faculty.postMultipart("/uploadfile/facultyFileUpload", link, Collections.<String, WebSession.FilePart>emptyMap()).status);
        assertContains("link listed", anonymous().get("/facultyDetails?facultyId=" + facultyId), "https://example.com/paper");

        // home page slider images (existing rows) open with their extension again
        List<Long> slides = jdbc.queryForList("select image_slide_id from image_slide where image is not null and file_type = 'JPG' limit 1", Long.class);
        if (!slides.isEmpty()) {
            WebSession.Response slide = anonymous().get("/" + slides.get(0) + "/slideImage.JPG");
            assertEquals(200, slide.status);
            assertTrue(slide.contentType, slide.contentType.startsWith("image/jpeg"));
        }
    }

    @Test
    public void facultyUploadsANoticeForTheOwnDepartmentAndFilesUpTo5MB() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY); // department 1 = CSE
        WebSession.Response page = faculty.get("/uploadNoticeFaculty");
        assertCompletePage("notice page", page);
        assertContains("own department", page, "href=\"/department/cse/notice\"");

        String header = TestData.MARK + " dept notice " + System.currentTimeMillis();
        Map<String, String> f = new LinkedHashMap<>();
        f.put("noticeHeader", header);
        f.put("news", "false");
        f.put("date", "28/10/2026");
        WebSession.Response ok = faculty.postMultipart("/user/api/uploadDepartmentNotice", f,
                Collections.singletonMap("noticeFile", new WebSession.FilePart("n.pdf", "application/pdf", DOC)));
        assertEquals(ok.toString(), 200, ok.status);
        assertEquals("CSENotice", jdbc.queryForObject("select nb_noticetype from noticeboard where nb_headline = ?", String.class, header));
        assertContains("department notice page", anonymous().get("/department/cse/notice"), header);
        long noticeId = jdbc.queryForObject("select id from noticeboard where nb_headline = ?", Long.class, header);
        assertContains("CSE page links relative", anonymous().get("/department/cse/notice"), "href=\"notice/" + noticeId + "\"");
        // every form in which the pages link a notice opens the PDF
        for (String url : new String[]{"/notice/" + noticeId, "/department/cse/notice/" + noticeId,
                "/" + noticeId + "/notice.pdf", "/" + noticeId + "/notice"}) {
            WebSession.Response pdf = anonymous().get(url);
            assertEquals(url, 200, pdf.status);
            assertTrue(url + " " + pdf.contentType, pdf.contentType.startsWith("application/pdf"));
            assertArrayEquals(url, DOC, pdf.bytes);
        }

        WebSession.Response notPdf = faculty.postMultipart("/user/api/uploadDepartmentNotice", f,
                Collections.singletonMap("noticeFile", new WebSession.FilePart("n.pdf", "application/pdf", "<html>".getBytes(StandardCharsets.US_ASCII))));
        assertEquals(400, notPdf.status);
        assertEquals("HOD without the Faculty role", 403, loginAs(TestData.HOD).postMultipart("/user/api/uploadDepartmentNotice", f,
                Collections.singletonMap("noticeFile", new WebSession.FilePart("n.pdf", "application/pdf", DOC))).status);

        // profile uploads: up to 5 MB
        byte[] big = new byte[5 * 1024 * 1024 + 1];
        System.arraycopy(DOC, 0, big, 0, DOC.length);
        WebSession.Response tooBig = faculty.postMultipart("/uploadfile/facultyFileUpload", profileFields("No", TestData.MARK + " big"),
                Collections.singletonMap("facultydocumentsFile", new WebSession.FilePart("big.pdf", "application/pdf", big)));
        assertEquals(400, tooBig.status);
        byte[] mb = java.util.Arrays.copyOf(big, 1024 * 1024);
        assertEquals(200, faculty.postMultipart("/uploadfile/facultyFileUpload", profileFields("No", TestData.MARK + " 1mb"),
                Collections.singletonMap("facultydocumentsFile", new WebSession.FilePart("mb.pdf", "application/pdf", mb))).status);
    }
}
