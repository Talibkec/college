package com.college.upgradetest;

import com.college.core.tags.PageKeys;
import org.junit.After;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Page blocks: headings, subheadings, texts, images and documents added to pages (/manage/blocks, &lt;kec:blocks/&gt;,
 * /content-file/{id}). Every block a test creates is deleted through the screen afterwards.
 */
public class PageBlocksTest extends AbstractAppTest {

    private static final String PAGE = "about.vision";
    private static final byte[] PDF = "%PDF-1.4 page block test".getBytes(StandardCharsets.US_ASCII);
    private static final byte[] DOCX = new byte[]{'P', 'K', 3, 4, 20, 0, 6, 0, 8, 0};

    private static byte[] png() throws java.io.IOException {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        javax.imageio.ImageIO.write(new java.awt.image.BufferedImage(40, 30, java.awt.image.BufferedImage.TYPE_INT_RGB), "png", out);
        return out.toByteArray();
    }

    private static Map<String, String> fields(String... nameValues) {
        Map<String, String> m = new LinkedHashMap<>();
        for (int i = 0; i < nameValues.length; i += 2) m.put(nameValues[i], nameValues[i + 1]);
        return m;
    }

    private static Map<String, WebSession.FilePart> file(String name, String type, byte[] bytes) {
        return Collections.singletonMap("file", new WebSession.FilePart(name, type, bytes));
    }

    private static final Map<String, WebSession.FilePart> NO_FILE = Collections.emptyMap();

    private WebSession.Response add(WebSession s, String page, Map<String, String> f, Map<String, WebSession.FilePart> files) throws Exception {
        Map<String, String> all = new LinkedHashMap<>();
        all.put("page", page);
        all.putAll(f);
        return s.postMultipart("/manage/blocks/save", all, files);
    }

    private Long blockId(String heading) {
        List<Long> ids = jdbc.queryForList("select block_id from page_block where heading = ? order by block_id", Long.class, heading);
        return ids.isEmpty() ? null : ids.get(0);
    }

    private Long fileOf(Long blockId) {
        return jdbc.queryForObject("select file_id from page_block where block_id = ?", Long.class, blockId);
    }

    @After
    public void deleteTestBlocks() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        for (Map<String, Object> row : jdbc.queryForList("select block_id, page_key from page_block where created_by like ?", TestData.MARK + "%")) {
            admin.postForm("/manage/blocks/delete", "page", (String) row.get("page_key"), "blockId", String.valueOf(row.get("block_id")));
        }
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from page_block where created_by like ?", Integer.class, TestData.MARK + "%"));
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from content_file where uploaded_by like ?", Integer.class, TestData.MARK + "%"));
    }

    @Test
    public void pagesShowNothingUntilContentIsAdded() throws Exception {
        // an About page without blocks (the local copy may hold content added while verifying by hand)
        String empty = jdbc.queryForList("select k from (select 'about.history' k union all select 'about.affiliation' "
                + "union all select 'about.principal' union all select 'about.vision') pages "
                + "where k not in (select page_key from page_block) order by k", String.class).stream().findFirst()
                .orElseThrow(() -> new AssertionError("every About page has blocks: remove some in /manage/blocks"));
        String url = PageKeys.url(empty);
        assertFalse("anonymous: no added content, no edit link", anonymous().get(url).body().contains("page-blocks"));
        WebSession.Response sub = loginAs(TestData.SUBADMIN).get(url);
        assertContains("editor sees the edit link", sub, "href=\"/manage/blocks?page=" + empty + "\"");
        assertFalse("TPO does not edit About", loginAs(TestData.TPO).get(url).body().contains("page-blocks"));
        assertContains("TPO edits T&P pages", loginAs(TestData.TPO).get("/trainingplacement/aboutplacement"),
                "href=\"/manage/blocks?page=trainingplacement.aboutplacement\"");
        assertContains("dashboard link", loginAs(TestData.SUBADMIN).get("/manage"), "href=\"/manage/blocks\"");
    }

    @Test
    public void subAdminAddsHeadingsTextImageAndDocuments() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertCompletePage("edit screen", sub.get("/manage/blocks?page=" + PAGE + "&add=text"));
        assertEquals("/manage/blocks?saved=1&page=" + PAGE,
                add(sub, PAGE, fields("type", "heading", "heading", TestData.MARK + " Heading", "visible", "on"), NO_FILE).redirectPath());
        add(sub, PAGE, fields("type", "subheading", "heading", TestData.MARK + " Sub", "visible", "on"), NO_FILE);
        add(sub, PAGE, fields("type", "text", "html", "<p>" + TestData.MARK + " body <strong>bold</strong></p><script>alert(1)</script>",
                "visible", "on"), NO_FILE);
        byte[] image = png();
        add(sub, PAGE, fields("type", "image", "heading", TestData.MARK + " Photo", "display", "small", "visible", "on"),
                file("campus photo.png", "image/png", image));
        add(sub, PAGE, fields("type", "document", "heading", TestData.MARK + " Brochure", "display", "embed", "visible", "on"),
                file("brochure.pdf", "application/pdf", PDF));
        add(sub, PAGE, fields("type", "document", "heading", TestData.MARK + " Timetable", "visible", "on"),
                file("timetable.docx", "application/octet-stream", DOCX));

        String page = anonymous().get("/about/vision").body();
        assertTrue(page.contains("<h2 class=\"text-2xl font-semibold text-gray-800 mt-6 mb-3\">" + TestData.MARK + " Heading</h2>"));
        assertTrue(page.contains("<h3 class=\"text-xl font-semibold text-gray-700 mt-4 mb-2\">" + TestData.MARK + " Sub</h3>"));
        assertTrue(page.contains(TestData.MARK + " body <strong>bold</strong>"));
        assertFalse("script removed", page.contains("<script>alert(1)"));
        Long imageFile = fileOf(blockId(TestData.MARK + " Photo"));
        assertTrue(page.contains("src=\"/content-file/" + imageFile + "/campus_photo.png\""));
        assertTrue(page.contains("max-w-xs"));
        Long pdfFile = fileOf(blockId(TestData.MARK + " Brochure"));
        assertTrue("pdf shown in the page", page.contains("<object data=\"/content-file/" + pdfFile + "/brochure.pdf\" type=\"application/pdf\""));
        Long docxFile = fileOf(blockId(TestData.MARK + " Timetable"));
        assertTrue("docx as link", page.contains("href=\"/content-file/" + docxFile + "/timetable.docx\""));
        assertFalse("no embedded docx", page.contains("<object data=\"/content-file/" + docxFile));
        assertTrue("order", page.indexOf(TestData.MARK + " Heading") < page.indexOf(TestData.MARK + " Sub")
                && page.indexOf(TestData.MARK + " Sub") < page.indexOf(TestData.MARK + " body"));

        WebSession.Response img = anonymous().get("/content-file/" + imageFile);
        assertEquals(200, img.status);
        assertTrue(img.contentType.startsWith("image/png"));
        assertArrayEquals(image, img.bytes);
        WebSession.Response pdf = anonymous().get("/content-file/" + pdfFile + "/brochure.pdf");
        assertTrue(pdf.contentType.startsWith("application/pdf"));
        assertTrue(pdf.contentDisposition, pdf.contentDisposition.startsWith("inline"));
        WebSession.Response docx = anonymous().get("/content-file/" + docxFile);
        assertTrue(docx.contentDisposition, docx.contentDisposition.startsWith("attachment"));
        assertTrue(jdbc.queryForObject("select count(*) from content_change_log where entity = 'page_block' and action = 'create' "
                + "and changed_by = ?", Integer.class, TestData.SUBADMIN.username) >= 6);
    }

    @Test
    public void linksAreAddedWithACheckedAddress() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("link form", sub.get("/manage/blocks?page=" + PAGE + "&add=link"), "name=\"link\"");
        assertEquals("/manage/blocks?saved=1&page=" + PAGE, add(sub, PAGE, fields("type", "link", "heading", TestData.MARK + " AICTE",
                "link", " https://www.aicte-india.org/ ", "visible", "on"), NO_FILE).redirectPath());
        add(sub, PAGE, fields("type", "link", "heading", TestData.MARK + " History", "link", "/about/history", "visible", "on"), NO_FILE);
        assertContains("bad address refused", add(sub, PAGE, fields("type", "link", "heading", TestData.MARK + " Bad",
                "link", "javascript:alert(1)", "visible", "on"), NO_FILE), "Please enter a web address");
        assertEquals(null, blockId(TestData.MARK + " Bad"));

        String page = anonymous().get("/about/vision").body();
        assertTrue(page.contains("<a href=\"https://www.aicte-india.org/\" class=\"text-blue-600 hover:underline\" target=\"_blank\" rel=\"noopener\">"
                + TestData.MARK + " AICTE</a>"));
        assertTrue(page.contains("<a href=\"/about/history\" class=\"text-blue-600 hover:underline\" >" + TestData.MARK + " History</a>"));
    }

    @Test
    public void reorderHideAndDelete() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        add(sub, PAGE, fields("type", "heading", "heading", TestData.MARK + " First", "visible", "on"), NO_FILE);
        add(sub, PAGE, fields("type", "image", "heading", TestData.MARK + " Second", "visible", "on"), file("a.png", "image/png", png()));
        Long second = blockId(TestData.MARK + " Second");
        Long file = fileOf(second);
        sub.postForm("/manage/blocks/move", "page", PAGE, "blockId", String.valueOf(second), "direction", "-1");
        String page = anonymous().get("/about/vision").body();
        assertTrue("moved up", page.indexOf(TestData.MARK + " Second") < page.indexOf(TestData.MARK + " First"));

        // hidden: not on the page, and its file is only visible to editors
        add(sub, PAGE, fields("blockId", String.valueOf(second), "heading", TestData.MARK + " Second"), NO_FILE);
        assertFalse(anonymous().get("/about/vision").body().contains(TestData.MARK + " Second"));
        assertEquals(404, anonymous().get("/content-file/" + file).status);
        assertEquals(200, sub.get("/content-file/" + file).status);
        assertNotNull("file kept when changing without a new file", fileOf(second));

        sub.postForm("/manage/blocks/delete", "page", PAGE, "blockId", String.valueOf(second));
        assertNull(blockId(TestData.MARK + " Second"));
        assertEquals("file deleted with its block", 0, (int) jdbc.queryForObject("select count(*) from content_file where file_id = ?", Integer.class, file));
    }

    @Test
    public void unsafeOrWrongFilesAreRejected() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        byte[] svg = "<svg xmlns=\"http://www.w3.org/2000/svg\"><script>alert(1)</script></svg>".getBytes(StandardCharsets.UTF_8);
        WebSession.Response r = add(sub, PAGE, fields("type", "image", "heading", TestData.MARK + " svg", "visible", "on"), file("x.svg", "image/svg+xml", svg));
        assertCompletePage("form again", r);
        assertContains("error", r, "id=\"error\"");
        add(sub, PAGE, fields("type", "document", "heading", TestData.MARK + " fake", "visible", "on"),
                file("fake.pdf", "application/pdf", "hello, not a pdf".getBytes(StandardCharsets.US_ASCII)));
        add(sub, PAGE, fields("type", "document", "heading", TestData.MARK + " html", "visible", "on"),
                file("page.html", "text/html", "<html><script>x()</script></html>".getBytes(StandardCharsets.US_ASCII)));
        add(sub, PAGE, fields("type", "image", "heading", TestData.MARK + " pdf as image", "visible", "on"), file("x.png", "image/png", PDF));
        add(sub, PAGE, fields("type", "heading", "heading", "", "visible", "on"), NO_FILE);
        add(sub, PAGE, fields("type", "bogus", "heading", TestData.MARK + " bogus", "visible", "on"), NO_FILE);
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from page_block where created_by like ?", Integer.class, TestData.MARK + "%"));
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from content_file where uploaded_by like ?", Integer.class, TestData.MARK + "%"));
    }

    @Test
    public void onlyEditorsOfThePageMayAddContent() throws Exception {
        WebSession tpo = loginAs(TestData.TPO);
        assertEquals(403, tpo.get("/manage/blocks?page=" + PAGE).status);
        assertEquals(403, add(tpo, PAGE, fields("type", "heading", "heading", TestData.MARK + " tpo", "visible", "on"), NO_FILE).status);
        assertEquals(302, add(tpo, "trainingplacement.aboutplacement", fields("type", "heading", "heading", TestData.MARK + " tpo ok",
                "visible", "on"), NO_FILE).status);
        assertNotNull(blockId(TestData.MARK + " tpo ok"));
        assertNull(blockId(TestData.MARK + " tpo"));
        assertTrue(anonymous().get("/manage/blocks?page=" + PAGE).redirectPath().endsWith("/login"));
        assertEquals(403, loginAs(TestData.ADMIN).get("/manage/blocks?page=store.x").status);

        // open a page by its address
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertEquals("/manage/blocks?page=about.vision", sub.get("/manage/blocks?url=http://www.keck.ac.in/about/vision").redirectPath());
        assertContains("unknown page", sub.get("/manage/blocks?url=/store/x"), "id=\"error\"");
    }

    @Test
    public void facultyAddsToOwnDepartmentAndChangesOnlyOwnBlocks() throws Exception {
        WebSession faculty = loginAs(TestData.FACULTY); // test faculty belongs to department 1 (CSE)
        WebSession sub = loginAs(TestData.SUBADMIN);
        add(sub, "department.cse.labs", fields("type", "heading", "heading", TestData.MARK + " by subadmin", "visible", "on"), NO_FILE);
        assertEquals(302, add(faculty, "department.cse.labs", fields("type", "text", "html", "<p>" + TestData.MARK + " by faculty</p>",
                "visible", "on"), NO_FILE).status);
        assertContains("page", anonymous().get("/department/cse/labs"), TestData.MARK + " by faculty");
        Long subBlock = blockId(TestData.MARK + " by subadmin");
        assertEquals("not own block", 403, faculty.postForm("/manage/blocks/delete", "page", "department.cse.labs",
                "blockId", String.valueOf(subBlock)).status);
        assertEquals(403, faculty.get("/manage/blocks?page=department.cse.labs&edit=" + subBlock).status);
        assertEquals("other department", 403, add(faculty, "department.civil.labs", fields("type", "heading", "heading",
                TestData.MARK + " civil", "visible", "on"), NO_FILE).status);
        Long own = jdbc.queryForObject("select block_id from page_block where created_by = ? and page_key = 'department.cse.labs'",
                Long.class, TestData.FACULTY.username);
        assertEquals(302, faculty.postForm("/manage/blocks/delete", "page", "department.cse.labs", "blockId", String.valueOf(own)).status);
        assertNotNull(subBlock);
    }
}
