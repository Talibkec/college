package com.college.upgradetest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Page sections (step 6): /manage/sections, sanitising, preview, history/restore and the &lt;kec:section&gt; texts of
 * the About pages. Changes the real "History" section and restores it through the screen afterwards.
 */
public class PageSectionsTest extends AbstractAppTest {

    private long historyId;
    private String original;
    private String originalPage;

    @Before
    public void remember() throws Exception {
        historyId = jdbc.queryForObject("select section_id from page_section where page_key = 'about.history' and section_key = 'main'", Long.class);
        original = body();
        originalPage = anonymous().get("/about/history").body();
    }

    private String body() {
        return jdbc.queryForObject("select body_html from page_section where section_id = ?", String.class, historyId);
    }

    @After
    public void restoreOriginal() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        List<Long> logs = jdbc.queryForList("select log_id from content_change_log where entity = 'page_section' and entity_id = ? "
                + "and changed_by like ? order by log_id", Long.class, String.valueOf(historyId), TestData.MARK + "%");
        if (!logs.isEmpty() && !original.equals(body())) {
            admin.postForm("/manage/sections/restore", "id", String.valueOf(historyId), "logId", String.valueOf(logs.get(0)), "before", "1");
        }
        admin.postForm("/manage/sections/save", "id", String.valueOf(historyId), "html", original, "visible", "on");
        assertEquals(original, body());
        assertEquals(originalPage, anonymous().get("/about/history").body());
    }

    @Test
    public void subAdminChangesTheHistoryTextSafely() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertContains("dashboard link", sub.get("/manage"), "/manage/sections?area=about");
        WebSession.Response screen = sub.get("/manage/sections?id=" + historyId);
        assertCompletePage("editor", screen);
        assertContains("editor shows the text", screen, "Katihar Engineering College");
        assertContains("no history yet", screen, "id=\"no-history\"");

        String html = "<p class=\"text-lg\" onclick=\"steal()\">" + TestData.MARK + " text</p><script>alert(1)</script>"
                + "<a href=\"javascript:alert(2)\">bad</a><a href=\"/about/vision\" target=\"_blank\">good</a>"
                + "<iframe src=\"https://www.youtube.com/embed/abc\"></iframe><iframe src=\"https://evil.example/x\"></iframe>";
        WebSession.Response save = sub.postForm("/manage/sections/save", "id", String.valueOf(historyId), "html", html, "visible", "on");
        assertEquals("/manage/sections?saved=1&id=" + historyId, save.redirectPath());
        String stored = body();
        assertTrue(stored, stored.contains("<p class=\"text-lg\">" + TestData.MARK + " text</p>"));
        assertFalse(stored, stored.contains("script") || stored.contains("onclick") || stored.contains("javascript:") || stored.contains("evil.example"));
        assertTrue(stored, stored.contains("href=\"/about/vision\""));
        assertTrue(stored, stored.contains("src=\"https://www.youtube.com/embed/abc\""));

        WebSession.Response page = anonymous().get("/about/history");
        assertContains("page shows the new text", page, TestData.MARK + " text</p>");
        assertFalse(page.body().contains("<script>alert(1)"));
        assertContains("history", sub.get("/manage/sections?id=" + historyId), "id=\"history\"");
    }

    @Test
    public void previewDoesNotSave() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        WebSession.Response preview = sub.postForm("/manage/sections/preview", "id", String.valueOf(historyId),
                "html", "<p>" + TestData.MARK + " preview</p><script>x()</script>");
        assertCompletePage("preview", preview);
        assertContains("preview", preview, "<p>" + TestData.MARK + " preview</p>");
        assertFalse(preview.body().contains("<script>x()"));
        assertEquals(original, body());
    }

    @Test
    public void restoreTheOriginalTextMakesThePageIdenticalAgain() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        long lastLog = jdbc.queryForObject("select coalesce(max(log_id), 0) from content_change_log", Long.class);
        admin.postForm("/manage/sections/save", "id", String.valueOf(historyId), "html", "<p>" + TestData.MARK + " v1</p>", "visible", "on");
        admin.postForm("/manage/sections/save", "id", String.valueOf(historyId), "html", "<p>" + TestData.MARK + " v2</p>", "visible", "on");
        List<Long> logs = jdbc.queryForList("select log_id from content_change_log where entity = 'page_section' and entity_id = ? "
                + "and log_id > ? order by log_id", Long.class, String.valueOf(historyId), lastLog);
        assertEquals(2, logs.size());
        // "Restore the original text" is offered on the oldest entry of the history
        long oldest = jdbc.queryForObject("select min(log_id) from content_change_log where entity = 'page_section' and entity_id = ?",
                Long.class, String.valueOf(historyId));

        admin.postForm("/manage/sections/restore", "id", String.valueOf(historyId), "logId", String.valueOf(logs.get(0)));
        assertEquals("<p>" + TestData.MARK + " v1</p>", body());

        admin.postForm("/manage/sections/restore", "id", String.valueOf(historyId), "logId", String.valueOf(oldest), "before", "1");
        assertEquals("original restored byte for byte", original, body());
        assertEquals(originalPage, anonymous().get("/about/history").body());
    }

    @Test
    public void hiddenSectionsAreNotShown() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        admin.postForm("/manage/sections/save", "id", String.valueOf(historyId), "html", original);
        assertEquals("text unchanged", original, body());
        WebSession.Response page = anonymous().get("/about/history");
        assertCompletePage("page without the text", page);
        assertFalse(page.body().contains("is one of the seventeen engineering colleges"));
    }

    @Test
    public void principalPhotoComesFromTheSetting() throws Exception {
        assertContains("photo of faculty 11", anonymous().get("/about/principal"), "src=\"/11/image");
        WebSession admin = loginAs(TestData.ADMIN);
        try {
            admin.postForm("/manage/settings", "value:principal.faculty_id", "12");
            assertContains("photo of faculty 12", anonymous().get("/about/principal"), "src=\"/12/image");
        } finally {
            admin.postForm("/manage/settings", "value:principal.faculty_id", "11");
        }
        assertEquals("11", jdbc.queryForObject("select setting_value from site_setting where setting_key = 'principal.faculty_id'", String.class));
    }

    @Test
    public void onlyEditorsOfTheAreaMayChangeSections() throws Exception {
        WebSession tpo = loginAs(TestData.TPO);
        // TPO edits only its own texts (placement brochure, startup cell), never the About texts
        WebSession.Response list = tpo.get("/manage/sections");
        assertEquals(200, list.status);
        assertFalse(list.body().contains("/manage/sections?id=" + historyId + "\""));
        assertEquals(403, tpo.get("/manage/sections?id=" + historyId).status);
        assertEquals(403, tpo.postForm("/manage/sections/save", "id", String.valueOf(historyId), "html", "<p>x</p>", "visible", "on").status);
        assertEquals(403, tpo.postForm("/manage/sections/preview", "id", String.valueOf(historyId), "html", "<p>x</p>").status);
        assertTrue(anonymous().get("/manage/sections").redirectPath().endsWith("/login"));
        assertEquals(original, body());
    }
}
