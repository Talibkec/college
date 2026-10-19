package com.college.upgradetest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Site settings (step 3): /manage/settings and the &lt;kec:setting&gt; texts in header, footer and contact pages.
 * Changes real rows of site_setting and restores them afterwards (through the screen, so the cache is reset too).
 */
public class SiteSettingsTest extends AbstractAppTest {

    private String originalPhone;
    private String originalCredit;

    private String value(String key) {
        return jdbc.queryForObject("select setting_value from site_setting where setting_key = ?", String.class, key);
    }

    @Before
    public void rememberValues() {
        originalPhone = value("contact.phone");
        originalCredit = value("footer.credit");
    }

    @After
    public void restoreValues() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        admin.postForm("/manage/settings", "value:contact.phone", originalPhone, "value:footer.credit", originalCredit);
        assertEquals(originalPhone, value("contact.phone"));
        assertEquals(originalCredit, value("footer.credit"));
    }

    @Test
    public void subAdminChangesThePhoneOnAllPages() throws Exception {
        WebSession sub = loginAs(TestData.SUBADMIN);
        WebSession.Response screen = sub.get("/manage/settings?area=site");
        assertCompletePage("settings screen", screen);
        assertContains("settings screen", screen, "name=\"value:contact.phone\"");
        assertContains("dashboard link", sub.get("/manage"), "/manage/settings?area=site");

        WebSession.Response save = sub.postForm("/manage/settings", "area", "site", "value:contact.phone", "0123-456789");
        assertEquals("/manage/settings?saved=1&area=site", save.redirectPath());
        assertEquals("0123-456789", value("contact.phone"));
        assertContains("footer", anonymous().get("/"), "Ph.: 0123-456789</span>");
        assertContains("refund page", anonymous().get("/legal/refundandcancellation"), "<li>Phone: 0123-456789</li>");
        assertContains("grievance", anonymous().get("/approval/grievance"), "href=\"tel:0123-456789\"");
        assertEquals(1, (int) jdbc.queryForObject("select count(*) from content_change_log where entity = 'site_setting' "
                + "and entity_id = 'contact.phone' and new_value = '0123-456789' and changed_by = ?", Integer.class,
                TestData.SUBADMIN.username));

        // "use the page's original text": NULL in the table, the JSP's built-in text is shown
        sub.postForm("/manage/settings", "value:contact.phone", "0123-456789", "original:contact.phone", "on");
        assertNull(value("contact.phone"));
        assertContains("built-in text", anonymous().get("/"), "Ph.: 06452-239122</span>");
    }

    @Test
    public void valuesAreEscapedButCharacterReferencesStay() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        admin.postForm("/manage/settings", "value:footer.credit", "<b>Web</b> & \"Team\" &copy;");
        WebSession.Response home = anonymous().get("/");
        assertContains("escaped", home, "<p>&lt;b&gt;Web&lt;/b&gt; & &#34;Team&#34; &copy;</p>");
        assertFalse(home.body().contains("<b>Web</b>"));
    }

    @Test
    public void invalidValuesAreNotSaved() throws Exception {
        WebSession admin = loginAs(TestData.ADMIN);
        WebSession.Response r = admin.postForm("/manage/settings", "value:contact.phone", "call me", "value:footer.credit", "changed");
        assertCompletePage("form shown again", r);
        assertContains("error", r, "id=\"errors\"");
        assertContains("entered value kept", r, "value=\"call me\"");
        assertEquals("nothing saved", originalPhone, value("contact.phone"));
        assertEquals("nothing saved", originalCredit, value("footer.credit"));
    }

    @Test
    public void onlyEditorsOfTheSiteAreaMayChangeSettings() throws Exception {
        WebSession tpo = loginAs(TestData.TPO);
        assertEquals(403, tpo.get("/manage/settings").status);
        assertEquals(403, tpo.postForm("/manage/settings", "value:contact.phone", "111").status);
        assertFalse(tpo.get("/manage").body().contains("/manage/settings"));
        assertTrue(anonymous().postForm("/manage/settings", "value:contact.phone", "111").redirectPath().endsWith("/login"));
        assertEquals(originalPhone, value("contact.phone"));
    }
}
