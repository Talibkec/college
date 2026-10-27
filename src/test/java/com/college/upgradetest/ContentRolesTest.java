package com.college.upgradetest;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Website roles and the permission matrix (docs/DYNAMIC_CONTENT_FINDINGS.md §1.3): landing pages, the /manage
 * dashboard, the Admin screens /auth/roles and /auth/roles/areas, department scope. Needs docs/sql/dynamic applied.
 */
public class ContentRolesTest extends AbstractAppTest {

    private static final String AREA = TestData.MARK + "_area";
    private static final String SITE = TestData.MARK + "_site";
    private static final String OTHER_SITE = TestData.MARK + "_other";
    private static final String HOD_SITE = TestData.MARK + "_hodsite";

    private static String tile(String areaCode) {
        return "data-area=\"" + areaCode + "\"";
    }

    private List<String> roleNames(String username) {
        return jdbc.queryForList("select r.name from test_role r join user_role ur on ur.role_id = r.id "
                + "join test_user u on u.id = ur.user_id where u.username = ? order by r.name", String.class, username);
    }

    @Test
    public void landingPagesPerRole() throws Exception {
        WebSession.Response login = new WebSession(port).login(TestData.TPO.username, TestData.PASSWORD);
        assertEquals(302, login.status);
        assertEquals("only-website-role users land on /manage", "/manage", login.redirectPath());

        // header "Dashboard" link: the user's own landing page (B22), unchanged landing pages for the existing roles
        assertEquals("/auth/uploadDashboard", loginAs(TestData.ADMIN).get("/mydashboard").redirectPath());
        assertEquals("/hod/hodDashboard", loginAs(TestData.HOD).get("/mydashboard").redirectPath());
        assertEquals("/facultyDetails?facultyId=" + data.facultyId(TestData.FACULTY.username),
                loginAs(TestData.FACULTY).get("/mydashboard").redirectPath());
        assertEquals("/manage", loginAs(TestData.TPO).get("/mydashboard").redirectPath());

        assertTrue(anonymous().get("/mydashboard").redirectPath().endsWith("/login"));
        assertTrue(anonymous().get("/manage").redirectPath().endsWith("/login"));

        WebSession.Response home = loginAs(TestData.HOD).get("/");
        assertContains("header dashboard link", home, "href=\"/mydashboard\"");
        assertFalse("no Admin-only dashboard link for HOD", home.body().contains("href=\"/auth/uploadDashboard\""));
    }

    @Test
    public void dashboardShowsOnlyTheAreasOfTheUser() throws Exception {
        WebSession.Response tpo = loginAs(TestData.TPO).get("/manage");
        assertCompletePage("TPO /manage", tpo);
        assertContains("TPO edits T&P", tpo, tile("tp"));
        assertContains("TPO adds to the gallery", tpo, tile("gallery"));
        assertContains("TPO adds placement notices", tpo, tile("notices"));
        assertFalse("TPO has no RTI", tpo.body().contains(tile("rti")));
        assertFalse("TPO has no system", tpo.body().contains(tile("system")));
        assertFalse("no admin links for TPO", tpo.body().contains("href=\"/auth/roles\""));

        WebSession.Response sub = loginAs(TestData.SUBADMIN).get("/manage");
        assertContains("SubAdmin", sub, tile("rti"));
        assertContains("SubAdmin", sub, tile("department.cse"));
        assertFalse("system is Admin only", sub.body().contains(tile("system")));

        WebSession.Response admin = loginAs(TestData.ADMIN).get("/manage");
        assertContains("Admin", admin, tile("system"));
        assertContains("Admin", admin, tile("department.fp"));
        assertContains("Admin", admin, "href=\"/auth/roles\"");

        assertEquals("a user without any area", 403, loginAs(TestData.SM).get("/manage").status);
    }

    @Test
    public void websiteRolesCannotUseTheAdminScreens() throws Exception {
        long facultyUserId = data.userId(TestData.FACULTY.username);
        WebSession tpo = loginAs(TestData.TPO);
        assertEquals(403, tpo.get("/auth/roles").status);
        assertEquals(403, tpo.get("/auth/roles/areas").status);
        assertEquals(403, tpo.postForm("/auth/roles/user/" + facultyUserId, "role", "TPO").status);
        assertEquals(403, loginAs(TestData.HOD).get("/auth/roles").status);
        assertEquals(403, loginAs(TestData.SUBADMIN).get("/auth/roles").status);
        assertFalse(roleNames(TestData.FACULTY.username).contains("TPO"));
    }

    @Test
    public void adminAssignsAndRemovesWebsiteRoles() throws Exception {
        long facultyUserId = data.userId(TestData.FACULTY.username);
        WebSession admin = loginAs(TestData.ADMIN);
        assertCompletePage("user list", admin.get("/auth/roles?q=" + TestData.MARK));
        assertContains("user list", admin.get("/auth/roles?q=" + TestData.MARK), TestData.FACULTY.username);
        assertContains("user form", admin.get("/auth/roles/user/" + facultyUserId), "value=\"Librarian\"");

        // Admin is not assignable here: it is ignored; Faculty (existing role) is kept
        WebSession.Response save = admin.postForm("/auth/roles/user/" + facultyUserId, "role", "Librarian", "role", "Admin");
        assertEquals(302, save.status);
        assertTrue(save.redirectPath().startsWith("/auth/roles?saved="));
        assertEquals(java.util.Arrays.asList("Faculty", "Librarian"), roleNames(TestData.FACULTY.username));
        assertEquals("change logged", 1, (int) jdbc.queryForObject("select count(*) from content_change_log where entity = 'user_role' "
                + "and entity_id = ? and new_value = 'Librarian' and changed_by = ?", Integer.class,
                TestData.FACULTY.username, TestData.ADMIN.username));

        // the faculty member still lands on the profile and now also edits the library page
        WebSession faculty = loginAs(TestData.FACULTY);
        assertContains("faculty with Librarian", faculty.get("/manage"), tile("library"));

        assertEquals(302, admin.postForm("/auth/roles/user/" + facultyUserId).status);
        assertEquals(java.util.Collections.singletonList("Faculty"), roleNames(TestData.FACULTY.username));
    }

    @Test
    public void adminAssignsAndRemovesTheHodOfABranch() throws Exception {
        jdbc.update("insert into department_site (site_code, dept_id, full_name, short_name, menu_group, about_path, hod_username, sort_order) "
                + "values (?, 999999, 'Test branch', 'TB', 'UG', '/x', 'nobody', 9990)", HOD_SITE);
        long facultyUserId = data.userId(TestData.FACULTY.username);
        WebSession admin = loginAs(TestData.ADMIN);
        WebSession.Response form = admin.get("/auth/roles/user/" + facultyUserId);
        assertCompletePage("user form", form);
        assertContains("branch list", form, "name=\"hodSite\" value=\"" + HOD_SITE + "\"");
        assertContains("current HOD", form, "HOD: nobody");

        // ticking a branch gives the HOD role and replaces the previous HOD of that branch
        WebSession.Response save = admin.postForm("/auth/roles/user/" + facultyUserId, "hodSection", "1", "hodSite", HOD_SITE);
        assertEquals(302, save.status);
        assertEquals(java.util.Arrays.asList("Faculty", "HOD"), roleNames(TestData.FACULTY.username));
        assertEquals(TestData.FACULTY.username, jdbc.queryForObject("select hod_username from department_site where site_code = ?",
                String.class, HOD_SITE));
        assertEquals("change logged", 1, (int) jdbc.queryForObject("select count(*) from content_change_log where entity = 'department_site_hod' "
                + "and entity_id = ? and old_value = 'nobody' and changed_by = ?", Integer.class, HOD_SITE, TestData.ADMIN.username));
        assertContains("HOD of the branch", loginAs(TestData.FACULTY).get("/hod/hodDashboard"), "href=\"/manage\"");

        // a post without the HOD part (old form) leaves the HOD role alone
        admin.postForm("/auth/roles/user/" + facultyUserId, "role", "Librarian");
        assertEquals(java.util.Arrays.asList("Faculty", "HOD", "Librarian"), roleNames(TestData.FACULTY.username));

        // unticking HOD and the branch takes both away
        admin.postForm("/auth/roles/user/" + facultyUserId, "hodSection", "1");
        assertEquals(java.util.Collections.singletonList("Faculty"), roleNames(TestData.FACULTY.username));
        assertEquals(null, jdbc.queryForObject("select hod_username from department_site where site_code = ?", String.class, HOD_SITE));
    }

    @Test
    public void permissionMatrixAndDepartmentScope() throws Exception {
        jdbc.update("insert into content_area (area_code, label, site_code, sort_order) values (?, 'Test area', null, 9990)", AREA);
        jdbc.update("insert into content_area (area_code, label, site_code, sort_order) values (?, 'Test dept', ?, 9991)",
                "department." + SITE, SITE);
        jdbc.update("insert into content_area (area_code, label, site_code, sort_order) values (?, 'Other dept', ?, 9992)",
                "department." + OTHER_SITE, OTHER_SITE);
        jdbc.update("insert into department_site (site_code, dept_id, full_name, short_name, menu_group, about_path, hod_username) "
                + "values (?, ?, 'Test', 'T', 'UG', '/x', ?)", SITE, TestData.DEPT_ID, TestData.HOD.username);
        jdbc.update("insert into department_site (site_code, dept_id, full_name, short_name, menu_group, about_path, hod_username) "
                + "values (?, 999999, 'Other', 'O', 'UG', '/y', 'nobody')", OTHER_SITE);

        WebSession admin = loginAs(TestData.ADMIN);
        assertCompletePage("matrix", admin.get("/auth/roles/areas"));
        WebSession.Response save = admin.postForm("/auth/roles/areas",
                "cell:" + AREA + "|TPO", "E",
                "cell:system|TPO", "E",                         // Admin-only area: ignored
                "cell:department." + SITE + "|HOD", "E",
                "cell:department." + SITE + "|Faculty", "A",
                "cell:department." + OTHER_SITE + "|HOD", "E",
                "cell:department." + OTHER_SITE + "|Faculty", "A");
        assertEquals("/auth/roles/areas?saved=1", save.redirectPath());
        assertEquals("all", jdbc.queryForObject("select scope from content_area_role where area_code = ? and role_name = 'TPO'", String.class, AREA));
        assertEquals("own_department", jdbc.queryForObject("select scope from content_area_role where area_code = ? and role_name = 'HOD'",
                String.class, "department." + SITE));
        assertEquals(0, (int) jdbc.queryForObject("select count(*) from content_area_role where area_code = 'system'", Integer.class));
        assertTrue(jdbc.queryForObject("select count(*) from content_change_log where entity = 'content_area_role' and entity_id = ?",
                Integer.class, AREA + "|TPO") > 0);

        assertContains("TPO got the new area", loginAs(TestData.TPO).get("/manage"), tile(AREA));

        WebSession.Response hod = loginAs(TestData.HOD).get("/manage");
        assertContains("HOD of the test site", hod, tile("department." + SITE));
        assertFalse("HOD of another site", hod.body().contains(tile("department." + OTHER_SITE)));
        WebSession.Response faculty = loginAs(TestData.FACULTY).get("/manage");
        assertContains("faculty of dept " + TestData.DEPT_ID, faculty, tile("department." + SITE));
        assertFalse("faculty of another dept", faculty.body().contains(tile("department." + OTHER_SITE)));

        admin.postForm("/auth/roles/areas", "cell:" + AREA + "|TPO", "");
        assertFalse("removed again", loginAs(TestData.TPO).get("/manage").body().contains(tile(AREA)));
    }

    @Test
    public void hodDashboardLinksToWebsiteContent() throws Exception {
        // HOD adds to the gallery and to NBA (seed of the permission matrix)
        assertContains("HOD dashboard", loginAs(TestData.HOD).get("/hod/hodDashboard"), "href=\"/manage\"");
        assertContains("Admin sidebar", loginAs(TestData.ADMIN).get("/auth/uploadDashboard"), "href=\"/auth/roles\"");
    }
}
