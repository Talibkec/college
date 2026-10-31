package com.college.upgradetest;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Creates and removes the test rows. Everything the tests create starts with {@link #MARK}
 * (user names, faculty names, notice headlines, course names, ...), so cleanup can never hit real data.
 */
public class TestData {

    public static final String MARK = "zzupgtest";
    public static final String PASSWORD = "Upgrade#Test1";
    /** Department used for test faculty (CSE). */
    public static final long DEPT_ID = 1L;

    public static final TestUser ADMIN = new TestUser(MARK + "_admin", 1L);
    public static final TestUser FACULTY = new TestUser(MARK + "_faculty", 2L);
    public static final TestUser SM = new TestUser(MARK + "_sm", 3L);
    public static final TestUser HOD = new TestUser(MARK + "_hod", 4L);
    public static final TestUser SK = new TestUser(MARK + "_sk", 5L);
    /** Website roles of the dynamic-content phase (rows from docs/sql/dynamic/10_roles_seed.sql). */
    public static final TestUser TPO = new TestUser(MARK + "_tpo", "TPO");
    public static final TestUser SUBADMIN = new TestUser(MARK + "_subadmin", "SubAdmin");
    public static final TestUser[] ALL = {ADMIN, FACULTY, SM, HOD, SK, TPO, SUBADMIN};

    /** Official e-mail of the test faculty (used by the password-reset flow). */
    public static final String FACULTY_EMAIL = MARK + "_faculty@keck.ac.in";

    private final JdbcTemplate jdbc;

    public TestData(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void createUsers() {
        String hash = new BCryptPasswordEncoder().encode(PASSWORD);
        for (TestUser u : ALL) {
            jdbc.update("insert into test_user (username, password) values (?, ?)", u.username, hash);
            long id = jdbc.queryForObject("select id from test_user where username = ?", Long.class, u.username);
            jdbc.update("insert into user_role (user_id, role_id) values (?, ?)", id, roleId(u));
        }
        jdbc.update("insert into faculty (dept_id, f_mnumber, f_name, f_officialemail, f_personalemail, username) "
                        + "values (?, ?, ?, ?, ?, ?)",
                DEPT_ID, 9000000001L, MARK + " Faculty Member", FACULTY_EMAIL, MARK + "_faculty@example.com", FACULTY.username);
    }

    private long roleId(TestUser u) {
        if (u.roleName == null) {
            return u.roleId;
        }
        java.util.List<Long> ids = jdbc.queryForList("select id from test_role where name = ?", Long.class, u.roleName);
        if (ids.isEmpty()) {
            throw new IllegalStateException("Role " + u.roleName + " missing: apply docs/sql/dynamic/*.sql to the local database "
                    + "(see docs/sql/dynamic/README.md)");
        }
        return ids.get(0);
    }

    public long userId(String username) {
        return jdbc.queryForObject("select id from test_user where username = ?", Long.class, username);
    }

    public long facultyId(String username) {
        return jdbc.queryForObject("select faculty_id from faculty where username = ?", Long.class, username);
    }

    /** Removes every row created by the tests. Safe to run repeatedly. */
    public void cleanup() {
        String like = MARK + "%";
        jdbc.update("delete from password_reset_token where user_id in (select id from test_user where username like ?)", like);
        jdbc.update("delete from faculty_keyprop_values where key_property_id in (select key_property_id from faculty_key_props "
                + "where faculty_id in (select faculty_id from faculty where username like ?))", like);
        jdbc.update("delete from faculty_key_props where faculty_id in (select faculty_id from faculty where username like ?)", like);
        jdbc.update("delete from facultydocuments where f_uploadedby like ?", like);
        jdbc.update("delete from faculty where username like ? or f_name like ?", like, like);
        jdbc.update("delete from user_role where user_id in (select id from test_user where username like ?)", like);
        jdbc.update("delete from test_user where username like ?", like);
        jdbc.update("delete from noticeboard where nb_headline like ? or nb_username like ?", like, like);
        jdbc.update("delete from no_of_seat where branch_name like ?", like);
        jdbc.update("delete from placements where student_name like ?", like);
        jdbc.update("delete from tpo_coordinator where student_name like ?", like);
        jdbc.update("delete from staff where name like ? or username like ?", like, like);
        jdbc.update("delete from holiday where file_name like ?", like);
        jdbc.update("delete from download where file_name like ?", like);
        jdbc.update("delete from image_slide where caption like ?", like);
        // rows of RefactoringSafetyTest
        jdbc.update("delete from aicte_documents where title like ?", like);
        jdbc.update("delete from responsibility_doc where file_name like ?", like);
        jdbc.update("delete from lab_document where file_name like ?", like);
        jdbc.update("delete from gallery_image where caption like ?", like);
        jdbc.update("delete from achievement_slide where achievement_caption like ?", like);
        jdbc.update("delete from administration where f_dutyassigned like ?", like);
        // dynamic-content tables (docs/sql/dynamic)
        String contains = "%" + MARK + "%"; // e.g. area 'department.zzupgtest_site'
        jdbc.update("delete from content_change_log where changed_by like ? or entity_id like ?", like, contains);
        jdbc.update("delete from content_area_role where area_code like ?", contains);
        jdbc.update("delete from content_area where area_code like ?", contains);
        jdbc.update("update department_site set hod_username = null where hod_username like ?", like);
        jdbc.update("delete from department_site where site_code like ?", like);
        jdbc.update("delete from link_item where label like ?", like);
        jdbc.update("delete from page_block where created_by like ?", like);
        jdbc.update("delete from course_file where created_by like ?", like);
        jdbc.update("delete from content_file where uploaded_by like ?", like);
        // switches created only by LinksTest (main_menu's switch comes from the seed and is kept)
        jdbc.update("delete from site_setting where value_type = 'switch' and setting_key <> 'links.main_menu.enabled' and updated_by like ?", like);
        // SiteSettingsTest restores the values; forget that a test user saved them
        jdbc.update("update site_setting set updated_by = null, updated_at = null where updated_by like ?", like);
        jdbc.update("update page_section set updated_by = null, updated_at = null where updated_by like ?", like);
    }

    public static class TestUser {
        public final String username;
        public final long roleId;
        /** Set for roles without a fixed id (looked up by name); then {@link #roleId} is unused. */
        public final String roleName;

        TestUser(String username, long roleId) {
            this.username = username;
            this.roleId = roleId;
            this.roleName = null;
        }

        TestUser(String username, String roleName) {
            this.username = username;
            this.roleId = -1;
            this.roleName = roleName;
        }
    }
}
