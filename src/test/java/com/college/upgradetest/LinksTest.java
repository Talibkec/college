package com.college.upgradetest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Menus and link lists (steps 4-5): /manage/links and the &lt;kec:links&gt; lists in header, footer and sidebars.
 * Test entries are labelled zzupgtest...; they and the switches are reset through the screen afterwards.
 */
public class LinksTest extends AbstractAppTest {

    private static final String LABEL = TestData.MARK + " link";

    private Long linkId(String label) {
        List<Long> ids = jdbc.queryForList("select link_id from link_item where label = ? order by link_id", Long.class, label);
        return ids.isEmpty() ? null : ids.get(0);
    }

    private static final String[] SWITCHES = {"links.main_menu.enabled", "links.top_bar.enabled"};
    /** The switch rows as they were before the test (the local DB is the user's working copy); null = no row. */
    private final java.util.Map<String, java.util.Map<String, Object>> switchesBefore = new java.util.HashMap<>();

    @Before
    public void rememberTheSwitches() throws Exception {
        for (String key : SWITCHES) {
            List<java.util.Map<String, Object>> row = jdbc.queryForList("select * from site_setting where setting_key = ?", key);
            switchesBefore.put(key, row.isEmpty() ? null : row.get(0));
        }
        // the tests start from the seed's defaults
        WebSession admin = loginAs(TestData.ADMIN);
        admin.postForm("/manage/links/enabled", "list", "main_menu", "enabled", "no");
        admin.postForm("/manage/links/enabled", "list", "top_bar", "enabled", "yes");
    }

    /** Puts the switch rows back exactly (value, updated_by, updated_at), so the test cleanup leaves them alone. */
    private void restoreTheSwitches() {
        for (String key : SWITCHES) {
            java.util.Map<String, Object> row = switchesBefore.get(key);
            jdbc.update("delete from site_setting where setting_key = ?", key);
            if (row != null) {
                jdbc.update("insert into site_setting (setting_key, setting_value, value_type, area_code, description, updated_by, updated_at) "
                        + "values (?, ?, ?, ?, ?, ?, ?)", key, row.get("setting_value"), row.get("value_type"), row.get("area_code"),
                        row.get("description"), row.get("updated_by"), row.get("updated_at"));
            }
        }
    }

    @After
    public void resetThroughTheScreen() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        for (String[] row : jdbc.queryForList("select list_code, link_id from link_item where label like ? order by parent_id is null",
                TestData.MARK + "%").stream().map(m -> new String[]{(String) m.get("list_code"), String.valueOf(m.get("link_id"))}).toArray(String[][]::new)) {
            admin.postForm("/manage/links/delete", "list", row[0], "linkId", row[1]);
        }
        restoreTheSwitches();
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from link_item where label like ?", Integer.class, TestData.MARK + "%"));
    }

    @Test
    public void subAdminAddsAFooterLinkAndASidebarEntry() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        assertCompletePage("links screen", sub.get("/manage/links?list=footer"));
        assertContains("dashboard link", sub.get("/manage"), "href=\"/manage/links\"");

        WebSession.Response save = sub.postForm("/manage/links/save", "list", "footer", "label", LABEL, "url", "/legal/privacypolicy",
                "visible", "on");
        assertEquals("/manage/links?saved=1&list=footer", save.redirectPath());
        assertContains("footer", anonymous().get("/"), "<p class=\"p-1 block-title\"> &raquo; " + LABEL + "</p>");
        assertEquals(1, (int) jdbc.queryForObject("select count(*) from content_change_log where entity = 'link_item' "
                + "and action = 'create' and changed_by = ? and new_value = ?", Integer.class, TestData.SUBADMIN.username,
                LABEL + " -> /legal/privacypolicy"));

        sub.postForm("/manage/links/save", "list", "rti_sidebar", "label", LABEL + " rti", "url", "https://rti.gov.in",
                "openNewTab", "on", "visible", "on");
        Long id = linkId(LABEL + " rti");
        assertContains("sidebar", anonymous().get("/rti/rtiact"), "<a href=\"https://rti.gov.in\" class=\"sf-depth-2\" target=\"_blank\">" + LABEL + " rti</a>");

        // move to the top: becomes the first ("active") entry
        for (int i = 0; i < 5; i++) sub.postForm("/manage/links/move", "list", "rti_sidebar", "linkId", String.valueOf(id), "direction", "-1");
        assertContains("first entry", anonymous().get("/rti/rtiact"), "<li class=\"first leaf\"><a href=\"https://rti.gov.in\" class=\"sf-depth-2 active\"");

        // hidden entries are not shown
        sub.postForm("/manage/links/save", "list", "rti_sidebar", "linkId", String.valueOf(id), "label", LABEL + " rti",
                "url", "https://rti.gov.in");
        assertFalse(anonymous().get("/rti/rtiact").body().contains(LABEL + " rti"));
    }

    @Test
    public void unsafeOrInvalidLinksAreRejected() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        for (String url : new String[]{"javascript:alert(1)", "//evil.example", "data:text/html,x", "ftp://x"}) {
            WebSession.Response r = admin.postForm("/manage/links/save", "list", "footer", "label", LABEL, "url", url, "visible", "on");
            assertCompletePage("form shown again for " + url, r);
            assertContains("error", r, "id=\"error\"");
        }
        assertNull(linkId(LABEL));
        admin.postForm("/manage/links/save", "list", "footer", "label", "<b>" + LABEL + "</b>", "url", "/x\"onmouseover=\"y", "visible", "on");
        assertNull("quote in URL rejected", linkId("<b>" + LABEL + "</b>"));
        admin.postForm("/manage/links/save", "list", "footer", "label", LABEL + " <b>x</b>", "url", "/legal/feestructure", "visible", "on");
        assertContains("label escaped", anonymous().get("/"), LABEL + " &lt;b&gt;x&lt;/b&gt;</p>");
    }

    @Test
    public void mainMenuIsSwitchedOffUntilAnAdminSwitchesItOn() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        WebSession.Response home = anonymous().get("/");
        assertContains("built-in desktop menu", home, "id=\"menu-1131-1\" class=\"main-nav\"");

        WebSession.Response preview = admin.get("/manage/links/preview");
        assertCompletePage("preview", preview);
        assertFalse("preview uses the generated menu", preview.body().contains("id=\"menu-1131-1\""));
        assertContains("preview: department columns", preview, "<li class=\"headli\">Under Graduates</li>");
        assertContains("preview: mobile groups", preview, "<ul class=\"submenu2\">");
        assertContains("preview: logout link kept (admin is logged in)", preview, "href=\"/logout\"");

        // a sub-entry added to "About KEC" shows in the preview only while the menu is switched off
        Long about = jdbc.queryForObject("select link_id from link_item where list_code = 'main_menu' and parent_id is null "
                + "and url = '/about/history'", Long.class);
        admin.postForm("/manage/links/save", "list", "main_menu", "parentId", String.valueOf(about), "label", LABEL,
                "url", "/about/staff", "visible", "on");
        assertContains("preview", admin.get("/manage/links/preview"), ">" + LABEL + "</a>");
        assertFalse("live page unchanged", anonymous().get("/").body().contains(LABEL));

        admin.postForm("/manage/links/enabled", "list", "main_menu", "enabled", "yes");
        WebSession.Response live = anonymous().get("/");
        assertCompletePage("home with the database menu", live);
        assertContains("live menu", live, ">" + LABEL + "</a>");
        assertFalse(live.body().contains("id=\"menu-1131-1\""));
        assertCompletePage("another page", anonymous().get("/rti/rtiact"));

        admin.postForm("/manage/links/enabled", "list", "main_menu", "enabled", "no");
        assertContains("back to the built-in menu", anonymous().get("/"), "id=\"menu-1131-1\" class=\"main-nav\"");
    }

    @Test
    public void deletingAMenuEntryDeletesItsSubEntries() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        admin.postForm("/manage/links/save", "list", "main_menu", "label", LABEL + " parent", "url", "/", "visible", "on");
        Long parent = linkId(LABEL + " parent");
        assertNotNull(parent);
        admin.postForm("/manage/links/save", "list", "main_menu", "parentId", String.valueOf(parent), "label", LABEL + " child",
                "url", "/about/history", "visible", "on");
        assertNotNull(linkId(LABEL + " child"));
        admin.postForm("/manage/links/delete", "list", "main_menu", "linkId", String.valueOf(parent));
        assertNull(linkId(LABEL + " parent"));
        assertNull(linkId(LABEL + " child"));
    }

    @Test
    public void switchedOffListShowsTheBuiltInLinks() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        admin.postForm("/manage/links/save", "list", "top_bar", "label", LABEL, "url", "/", "visible", "on");
        assertContains("from the database", anonymous().get("/"), "&raquo; " + LABEL + "</a>");
        admin.postForm("/manage/links/enabled", "list", "top_bar", "enabled", "no");
        WebSession.Response home = anonymous().get("/");
        assertFalse(home.body().contains(LABEL));
        assertContains("built-in top bar", home, "<li class=\"quicknav\"><a href=\"/category/kecwebteam\" target=\"_blank\"> &raquo; KEC Web Team</a></li>");
    }

    @Test
    public void onlyMenuEditorsMayChangeLinks() throws Exception {
        WebSession tpo = loginAs(TestData.TPO);
        assertEquals(403, tpo.get("/manage/links").status);
        assertEquals(403, tpo.get("/manage/links/preview").status);
        assertEquals(403, tpo.postForm("/manage/links/save", "list", "footer", "label", LABEL, "url", "/").status);
        assertEquals(403, tpo.postForm("/manage/links/enabled", "list", "main_menu", "enabled", "yes").status);
        assertEquals(403, loginAs(TestData.ADMIN).get("/manage/links?list=unknown").status);
        assertTrue(anonymous().get("/manage/links").redirectPath().endsWith("/login"));
        assertNull(linkId(LABEL));
        assertEquals("no", jdbc.queryForObject("select setting_value from site_setting where setting_key = 'links.main_menu.enabled'", String.class));
    }

    @Test
    public void aMainMenuEntryCreatesANewPageWithSidebarAndContent() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        String url = "/page/" + TestData.MARK + "-new";
        assertEquals("no page without a menu entry", 404, anonymous().get(url).status);
        assertContains("hint", sub.get("/manage/links?list=main_menu"), "id=\"new-page-hint\"");

        sub.postForm("/manage/links/save", "list", "main_menu", "label", LABEL + " top", "url", url, "visible", "on");
        Long top = linkId(LABEL + " top");
        WebSession.Response page = anonymous().get(url);
        assertCompletePage("new page", page);
        assertContains("title", page, "id=\"page-title\">" + LABEL + " top</h2>");
        assertFalse("no sidebar without sub-entries", page.body().contains("id=\"page-sidebar\""));
        assertFalse("nothing to edit for visitors", page.body().contains("page-blocks-add"));

        // a sub-entry: own page, both pages get the sidebar
        sub.postForm("/manage/links/save", "list", "main_menu", "label", LABEL + " sub", "url", url + "-sub",
                "parentId", String.valueOf(top), "visible", "on");
        WebSession.Response subPage = anonymous().get(url + "-sub");
        assertContains("sub title", subPage, "id=\"page-title\">" + LABEL + " sub</h2>");
        assertContains("sidebar heading", subPage, ">" + LABEL + " top</h2>");
        assertContains("active entry", subPage, "href=\"" + url + "-sub\" class=\"sf-depth-2 text-blue-500 hover:underline active font-bold\"");
        assertContains("sidebar on the top page", anonymous().get(url), "id=\"page-sidebar\"");

        // editors get the add buttons; added content shows on the page
        WebSession.Response asEditor = sub.get(url);
        assertContains("add buttons", asEditor, "/manage/blocks?page=page." + TestData.MARK + "-new&add=heading");
        java.util.Map<String, String> block = new java.util.LinkedHashMap<>();
        block.put("page", "page." + TestData.MARK + "-new");
        block.put("type", "heading");
        block.put("heading", LABEL + " heading");
        block.put("visible", "on");
        sub.postMultipart("/manage/blocks/save", block, java.util.Collections.emptyMap());
        assertContains("block", anonymous().get(url), LABEL + " heading");
        assertEquals("Faculty may not edit it", 403, loginAs(TestData.FACULTY).get("/manage/blocks?page=page." + TestData.MARK + "-new").status);

        // hidden or deleted entry: page gone
        sub.postForm("/manage/links/save", "list", "main_menu", "linkId", String.valueOf(top), "label", LABEL + " top", "url", url);
        assertEquals(404, anonymous().get(url).status);
        assertEquals("children of a hidden entry are hidden", 404, anonymous().get(url + "-sub").status);
        jdbc.update("delete from page_block where page_key = ?", "page." + TestData.MARK + "-new");
    }
}
