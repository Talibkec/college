package com.college.upgradetest;

import com.college.core.controller.firebase.CalendarService;
import com.college.core.controller.firebase.DocUtils;
import com.college.core.controller.firebase.FacultyReportDetail;
import com.college.core.controller.firebase.LeaveRequestDetail;
import com.google.api.services.calendar.Calendar;
import com.google.firebase.FirebaseApp;
import org.openpdf.text.Document;
import org.openpdf.text.Phrase;
import org.openpdf.text.pdf.PdfPCell;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfReader;
import org.openpdf.text.pdf.PdfWriter;
import org.openpdf.text.pdf.parser.PdfTextExtractor;
import org.junit.Assume;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;

import jakarta.mail.Multipart;
import jakarta.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URLEncoder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Context wiring and the external integrations. Mail and SMS are recorded (see {@link TestDoublesConfig}).
 * Not called: {@code /rn} (live Firebase query) and {@code /rs} (live Google Calendar insert; calls System.exit on
 * failure). Both are in the manual checklist.
 */
public class IntegrationsTest extends AbstractAppTest {

    /** kec.paths.pdf-logo of the tests (AbstractAppTest); PDFs with the logo can only be built where it exists. */
    private static final File KEC_LOGO = new File("src/main/webapp/sites/default/files/compressed/logo.png");

    @Autowired
    private CalendarService calendarService;

    @Autowired
    private ApplicationContext context;

    @Test
    public void contextLoadsWithTestDoublesAndFirebase() {
        assertSame("mail is replaced by the recorder", mail, context.getBean(JavaMailSender.class));
        assertSame("SMS is replaced by the recorder", sms, context.getBean(com.college.sms.MessageSender.class));
        assertFalse("FirebaseSetup initialised the Firebase app from the service-account JSON", FirebaseApp.getApps().isEmpty());
    }

    /** Same PDF calls (OpenPDF, formerly iText) as the attendance report / leave application, without the logo (see {@link #KEC_LOGO}). */
    @Test
    public void pdfGenerationProducesReadablePdf() throws Exception {
        FacultyReportDetail report = new com.google.gson.Gson().fromJson(
                "{\"startDate\":\"2099-01-01\",\"endDate\":\"2099-01-31\",\"dept\":\"Computer Sc. & Engineering\","
                        + "\"semester\":\"7\",\"subject\":\"Upgrade Testing\",\"facultyName\":\"Test Faculty\"}",
                FacultyReportDetail.class);
        LeaveRequestDetail leave = new com.google.gson.Gson().fromJson(
                "{\"name\":\"Test Faculty\",\"department\":\"CSE\",\"leaveType\":\"Casual Leave\",\"startDate\":\"2099-02-01\","
                        + "\"endDate\":\"2099-02-02\",\"requestDate\":\"2099-01-30\",\"subject\":\"\",\"emailContent\":\"\"}",
                LeaveRequestDetail.class);

        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, out);
        doc.open();
        doc.add(DocUtils.attTitle());
        doc.add(DocUtils.deptTitle(report));
        doc.add(DocUtils.semTitle(report));
        doc.add(DocUtils.subTitle(report));
        PdfPTable table = new PdfPTable(3);
        for (String c : new String[]{"Date/Reg No.", "2099-01-01", "Count", "99001", "P", "1"}) {
            table.addCell(new PdfPCell(new Phrase(c)));
        }
        doc.add(table);
        doc.add(DocUtils.facultySign());
        doc.newPage();
        doc.add(DocUtils.addContent());
        doc.add(DocUtils.addSubject(leave));
        doc.add(DocUtils.addGreet());
        doc.add(DocUtils.addBody(leave));
        doc.add(DocUtils.facultyInfo(leave));
        doc.close();

        PdfReader reader = new PdfReader(out.toByteArray());
        assertEquals(2, reader.getNumberOfPages());
        String page1 = new PdfTextExtractor(reader).getTextFromPage(1);
        String page2 = new PdfTextExtractor(reader).getTextFromPage(2);
        reader.close();
        assertTrue(page1, page1.contains("Attendance Report"));
        assertTrue(page1, page1.contains("Computer Sc. & Engineering"));
        assertTrue(page1, page1.contains("99001"));
        assertTrue(page2, page2.contains("Casual Leave"));
        assertTrue(page2, page2.contains("KEC, Katihar"));
    }

    /** Full /rl flow: leave application PDF (incl. logo) mailed as attachment. Needs {@link #KEC_LOGO}. */
    @Test
    public void leaveRequestEndpointMailsPdf() throws Exception {
        Assume.assumeTrue("skipped: " + KEC_LOGO + " not present on this machine (PDF generation needs it)", KEC_LOGO.isFile());
        String params = "{\"email\":\"" + TestData.MARK + "@example.com\",\"department\":\"CSE\",\"startDate\":\"2099-02-01\","
                + "\"endDate\":\"2099-02-02\",\"requestDate\":\"2099-01-30\",\"name\":\"Test Faculty\",\"leaveType\":\"Casual Leave\","
                + "\"subject\":\"\",\"emailContent\":\"\"}";
        WebSession.Response r = anonymous().get("/rl?params=" + URLEncoder.encode(params, "UTF-8"));
        assertEquals(200, r.status);
        assertEquals("one leave mail", 1, mail.mimeMessages.size());
        MimeMessage m = mail.mimeMessages.get(0);
        assertEquals(TestData.MARK + "@example.com", m.getAllRecipients()[0].toString());
        Multipart mp = (Multipart) m.getContent();
        boolean pdf = false;
        for (int i = 0; i < mp.getCount(); i++) {
            pdf |= "LeaveMail.pdf".equals(mp.getBodyPart(i).getFileName());
        }
        assertTrue("PDF attachment", pdf);
    }

    /** Builds the Google Calendar client from the P12 service-account key (no network call). */
    @Test
    public void googleCalendarClientCanBeConfigured() {
        Calendar client = calendarService.configure();
        assertNotNull(client);
        assertEquals("Google Calendar API", client.getApplicationName());
    }

    /** A calendar misconfiguration fails the request instead of stopping the server (was System.exit(1)). */
    @Test
    public void calendarMisconfigurationThrowsInsteadOfExiting() {
        com.college.core.config.KecProperties p = kecProperties;
        com.college.core.config.KecProperties broken = new com.college.core.config.KecProperties(p.mail(), p.paths(), p.sms(),
                p.firebase(), new com.college.core.config.KecProperties.GoogleCalendar(p.calendar().serviceAccount(),
                "/no-such-key.p12", p.calendar().calendarId()), p.report());
        try {
            new CalendarService(broken).configure();
            org.junit.Assert.fail("expected IllegalStateException");
        } catch (IllegalStateException expected) {
            assertTrue(expected.getMessage(), expected.getMessage().contains("Google Calendar"));
        }
    }

    /** CSV attachment of the attendance report: same layout as the former test.csv file. */
    @Test
    public void attendanceCsvLayout() {
        java.util.Map<String, java.util.Map<String, Boolean>> report = new java.util.TreeMap<>();
        report.put("2099-01-02", new java.util.TreeMap<>(java.util.Map.of("R1", true, "R2,x", false)));
        report.put("2099-01-01", new java.util.TreeMap<>(java.util.Map.of("R1", false, "R2,x", true)));
        String nl = System.lineSeparator();
        String expected = ",2099-01-01,2099-01-02" + nl
                + "R1,A,P,1" + nl
                + "\"R2,x\",P,A,1" + nl
                + ",1,1" + nl;
        assertEquals(expected, new String(attendanceReportService.getCsv(report), java.nio.charset.Charset.defaultCharset()));
    }

    /** Concurrent leave applications each get their own PDF (shared fields and a fixed temp file mixed them up). */
    @Test
    public void concurrentLeaveApplicationsGetTheirOwnPdf() throws Exception {
        Assume.assumeTrue("skipped: " + KEC_LOGO + " missing", KEC_LOGO.isFile());
        java.util.List<Thread> threads = new java.util.ArrayList<>();
        for (String name : new String[]{"Alpha Applicant", "Beta Applicant", "Gamma Applicant", "Delta Applicant"}) {
            String params = "{\"email\":\"" + TestData.MARK + "@example.com\",\"department\":\"CSE\",\"startDate\":\"2099-02-01\","
                    + "\"endDate\":\"2099-02-02\",\"requestDate\":\"2099-01-30\",\"name\":\"" + name + "\",\"leaveType\":\"Casual Leave\","
                    + "\"subject\":\"\",\"emailContent\":\"\"}";
            Thread t = new Thread(() -> {
                try {
                    anonymous().get("/rl?params=" + URLEncoder.encode(params, "UTF-8"));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
            threads.add(t);
            t.start();
        }
        for (Thread t : threads) t.join();
        assertEquals(4, mail.mimeMessages.size());
        java.util.Set<String> names = new java.util.HashSet<>();
        for (MimeMessage m : mail.mimeMessages) {
            Multipart mp = (Multipart) m.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                if ("LeaveMail.pdf".equals(mp.getBodyPart(i).getFileName())) {
                    PdfReader reader = new PdfReader(mp.getBodyPart(i).getInputStream());
                    String text = new PdfTextExtractor(reader).getTextFromPage(1);
                    reader.close();
                    for (String n : new String[]{"Alpha", "Beta", "Gamma", "Delta"}) {
                        if (text.contains(n + " Applicant")) names.add(n);
                    }
                }
            }
        }
        assertEquals("each mail carries its own applicant's PDF", 4, names.size());
    }

    @Autowired
    private com.college.core.config.KecProperties kecProperties;

    @Autowired
    private com.college.firebase.AttendanceReportService attendanceReportService;
}
