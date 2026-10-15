package com.college.upgradetest;

import org.junit.Test;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/** Login per role, logout, wrong password and the complete password-reset flow. */
public class LoginAndAccountTest extends AbstractAppTest {

    @Test
    public void eachRoleIsRedirectedToItsLandingPage() throws Exception {
        assertLanding(TestData.ADMIN, "/auth/uploadDashboard");
        assertLanding(TestData.HOD, "/hod/hodDashboard");
        assertLanding(TestData.SM, "/store/smdashboard");
        assertLanding(TestData.SK, "/sk/storekeeper");
        assertLanding(TestData.FACULTY, "/facultyDetails?facultyId=" + data.facultyId(TestData.FACULTY.username));
    }

    private void assertLanding(TestData.TestUser user, String expectedPath) throws Exception {
        WebSession s = anonymous();
        WebSession.Response r = s.login(user.username, TestData.PASSWORD);
        assertEquals("landing of " + user.username + ": " + r, 302, r.status);
        assertEquals("landing of " + user.username, expectedPath, r.redirectPath());
        assertCompletePage("landing page of " + user.username, s.get(expectedPath));
    }

    @Test
    public void facultyLandingPageShowsOwnProfile() throws Exception {
        WebSession s = anonymous();
        WebSession.Response landing = s.getFollow(s.login(TestData.FACULTY.username, TestData.PASSWORD).redirectPath());
        assertCompletePage("faculty profile", landing);
        assertContains("faculty profile", landing, TestData.MARK + " Faculty Member");
    }

    @Test
    public void wrongPasswordIsRejected() throws Exception {
        WebSession.Response r = anonymous().login(TestData.ADMIN.username, "wrong-password");
        assertEquals(302, r.status);
        assertEquals("/login?error", r.redirectPath());
    }

    @Test
    public void loginPageRenders() throws Exception {
        WebSession.Response r = anonymous().get("/login");
        assertCompletePage("login page", r);
        assertContains("login page", r, "name=\"username\"");
        assertContains("login page", r, "name=\"password\"");
    }

    @Test
    public void logoutEndsTheSession() throws Exception {
        WebSession s = loginAs(TestData.ADMIN);
        assertEquals(200, s.get("/auth/uploadDashboard").status);
        WebSession.Response out = s.get("/logout");
        assertEquals(302, out.status);
        assertEquals("/", out.redirectPath());
        WebSession.Response after = s.get("/auth/uploadDashboard");
        assertEquals(302, after.status);
        assertTrue(after.redirectPath(), after.redirectPath().endsWith("/login"));
    }

    @Test
    public void protectedPagesRequireTheRightRole() throws Exception {
        assertEquals(403, loginAs(TestData.FACULTY).get("/auth/uploadDashboard").status);
        assertEquals(403, loginAs(TestData.HOD).get("/auth/uploadDashboard").status);
        assertTrue(anonymous().get("/auth/uploadDashboard").redirectPath().endsWith("/login"));
    }

    /**
     * Forgot password → mail with link → link puts CHANGE_PASSWORD_PRIVILEGE into the session → save new password
     * → login with the new password. (Security 6+ no longer saves a manually set SecurityContext automatically.)
     */
    @Test
    public void passwordResetFlowWorksEndToEnd() throws Exception {
        String newPassword = "Changed#Pass2026";
        try {
            WebSession s = anonymous();
            assertCompletePage("forgot password page", s.get("/forgotPassword"));

            WebSession.Response reset = s.postForm("/user/resetPassword", "email", TestData.FACULTY_EMAIL);
            assertEquals("reset request: " + reset, 200, reset.status);
            assertEquals("one reset mail", 1, mail.simpleMessages.size());
            SimpleMailMessage m = mail.simpleMessages.get(0);
            assertEquals(TestData.FACULTY_EMAIL, m.getTo()[0]);
            Matcher link = Pattern.compile("(/user/changePassword\\?id=\\d+&token=[0-9a-f-]+)").matcher(m.getText());
            assertTrue("reset link in mail: " + m.getText(), link.find());

            WebSession.Response open = s.get(link.group(1));
            assertEquals("open reset link: " + open, 302, open.status);
            assertTrue("redirect to update page: " + open.redirectPath(), open.redirectPath().startsWith("/jsp/updatePassword.jsp"));
            assertCompletePage("update password page", s.get(open.redirectPath()));

            WebSession.Response save = s.postForm("/jsp/user/savePassword", "newPassword", newPassword);
            assertEquals("save password: " + save, 200, save.status);
            assertContains("save password", save, "message");

            WebSession.Response login = anonymous().login(TestData.FACULTY.username, newPassword);
            assertEquals(302, login.status);
            assertTrue("login with new password: " + login, login.redirectPath().startsWith("/facultyDetails"));
        } finally {
            jdbc.update("update test_user set password = ? where username = ?",
                    new BCryptPasswordEncoder().encode(TestData.PASSWORD), TestData.FACULTY.username);
        }
    }

    @Test
    public void resetLinkWithInvalidTokenIsRejected() throws Exception {
        long id = data.userId(TestData.FACULTY.username);
        WebSession.Response r = anonymous().get("/user/changePassword?id=" + id + "&token=not-a-valid-token");
        assertEquals(302, r.status);
        assertTrue(r.redirectPath(), r.redirectPath().startsWith("/login"));
    }
}
