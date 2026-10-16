package com.college.upgradetest;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Pages embed the site's own PDFs with relative paths (approved change: formerly the Google Docs viewer on
 * http://keck.ac.in, which showed the production file and could not work locally). Same-origin framing must be allowed.
 */
public class EmbeddedPdfTest extends AbstractAppTest {

    @Test
    public void pagesEmbedLocalPdfsInsteadOfTheGoogleViewer() throws Exception {
        String[][] pages = {
                {"/approval/akuapproval", "/jsp/approval/aku.pdf"},
                {"/approval/mandatorydisclosure", "/jsp/approval/aku.pdf"},
                {"/rti/rtiact", "/wp-content/uploads/2018/rti-act-2.pdf"},
                {"/rti/rtiapplication", "/wp-content/uploads/2018/RTI-application-format.pdf"},
                {"/trainingplacement/placementbrochure", "/jsp/trainingplacement/brochure.pdf"},
                {"/trainingplacement/rulesoftrainingplacement", "/jsp/trainingplacement/sample.pdf"},
        };
        for (String[] p : pages) {
            WebSession.Response page = anonymous().get(p[0]);
            assertEquals(p[0], 200, page.status);
            assertFalse(p[0] + " still uses the Google viewer", page.body().contains("drive.google.com/viewerng"));
            assertTrue(p[0] + " embeds " + p[1], page.body().contains("<object data=\"" + p[1] + "\""));
            assertEquals("SAMEORIGIN", page.frameOptions);

            WebSession.Response pdf = anonymous().get(p[1]);
            assertEquals(p[1], 200, pdf.status);
            assertTrue(p[1] + " is a PDF: " + pdf.contentType, pdf.contentType.startsWith("application/pdf"));
            assertEquals("SAMEORIGIN", pdf.frameOptions);
        }
    }
}
