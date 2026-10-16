package com.college.upgradetest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * What users see on errors, as before the upgrade: unknown URLs show the site's 404 page (error-404.jsp),
 * standard MVC errors (e.g. a missing request parameter) answer with the status and an empty body.
 */
public class ErrorResponsesTest extends AbstractAppTest {

    private static final String PAGE_404_TEXT = "We are sorry the page you request cannot be found.";

    @Test
    public void unknownUrlShowsTheSite404Page() throws Exception {
        WebSession.Response r = anonymous().get("/no/such/page");
        assertEquals(404, r.status);
        assertContains("404 page", r, PAGE_404_TEXT);
    }

    @Test
    public void missingStaticFileShowsTheSite404Page() throws Exception {
        WebSession.Response r = anonymous().get("/css/no-such-file.css");
        assertEquals(404, r.status);
        assertContains("404 page", r, PAGE_404_TEXT);
    }

    @Test
    public void missingRequestParameterGivesEmpty400() throws Exception {
        // the header menu links /academic/lab?id=1 although the controller expects dept_id
        WebSession.Response r = anonymous().get("/academic/lab?id=1");
        assertEquals(400, r.status);
        assertTrue("no error body (was empty before the upgrade): " + r, r.body().isEmpty());
    }

    @Test
    public void controllerExceptionGivesEmpty200() throws Exception {
        // CustomExceptionHandler handles every controller exception without writing a response (original behaviour)
        WebSession.Response missingNotice = anonymous().get("/notice/999999999");
        assertEquals(200, missingNotice.status);
        assertTrue("empty body: " + missingNotice, missingNotice.body().isEmpty());
        WebSession.Response clientAbort = anonymous().get("/exception");
        assertEquals(200, clientAbort.status);
        assertTrue("empty body: " + clientAbort, clientAbort.body().isEmpty());
    }

    @Test
    public void accessDeniedShowsThe403Status() throws Exception {
        assertEquals(403, loginAs(TestData.FACULTY).get("/auth/uploadDashboard").status);
    }
}
