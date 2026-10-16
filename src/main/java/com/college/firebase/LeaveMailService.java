package com.college.firebase;

import com.college.core.config.KecProperties;
import com.college.core.controller.firebase.CalendarService;
import com.college.core.controller.firebase.LeaveRequestDetail;
import com.college.core.controller.firebase.LeaveStatusDetail;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeUtility;
import org.openpdf.text.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.file.Files;

/**
 * Leave mails for the attendance app: the leave application PDF (/rl) and the leave status mail plus the Google
 * Calendar event (/rs). Request data stays in local variables; every PDF uses its own temp file.
 */
@Service
public class LeaveMailService {

    private static final Logger logger = LoggerFactory.getLogger(LeaveMailService.class);

    private final FirebaseDocumentHelper firebaseDocumentHelper;
    private final JavaMailSender mailSender;
    private final CalendarService calendarService;
    private final KecProperties kecProperties;

    public LeaveMailService(FirebaseDocumentHelper firebaseDocumentHelper, JavaMailSender mailSender,
                            CalendarService calendarService, KecProperties kecProperties) {
        this.firebaseDocumentHelper = firebaseDocumentHelper;
        this.mailSender = mailSender;
        this.calendarService = calendarService;
        this.kecProperties = kecProperties;
    }

    /** Builds the leave application PDF and mails it to the applicant. */
    public void sendLeaveApplication(LeaveRequestDetail leaveRequestDetail) {
        File pdf = AttendanceReportService.createTempFile("RequestLeave", ".pdf");
        try {
            try {
                firebaseDocumentHelper.getLeaveInfo(pdf, leaveRequestDetail);
            } catch (DocumentException e) {
                logger.error("Unable to generate the leave application PDF", e);
            } catch (IOException e) {
                logger.error("Unable to write the leave application PDF", e);
            }
            String email = leaveRequestDetail.getEmail();
            MimeMessagePreparator preparator = mimeMessage -> {
                logger.debug("Preparing the leave application mail");
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress(kecProperties.mail().from()));
                mimeMessage.setSubject("Leave Request.");
                mimeMessage.setText("Please find the attached leave request.");
                mimeMessage.addHeader("Content-Type", "application/pdf");
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment(MimeUtility.encodeText("LeaveMail.pdf"), new ByteArrayResource(Files.readAllBytes(pdf.toPath())));
                helper.setText("Please find the attached leave application.", true);
            };
            try {
                mailSender.send(preparator);
                logger.info("Leave application mail sent");
            } catch (MailException ex) {
                logger.error("Could not send the leave application mail", ex);
            }
        } finally {
            AttendanceReportService.deleteQuietly(pdf);
        }
    }

    /** Mails the new status of a leave request to the applicant. */
    public void sendLeaveStatus(LeaveStatusDetail leaveStatusDetail) {
        String email = leaveStatusDetail.getEmail();
        String subject = "Leave Request Status";
        String mailBody = "Sir," + "\n\n" + "The " + leaveStatusDetail.getLeaveType() + " which you have requested on "
                + leaveStatusDetail.getRequestDate() + " ,between " + leaveStatusDetail.getStartDate() + " and "
                + leaveStatusDetail.getEndDate() + " has been " + leaveStatusDetail.getStatus();
        MimeMessagePreparator preparator = mimeMessage -> {
            logger.debug("Preparing the leave status mail");
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setFrom(new InternetAddress(kecProperties.mail().from()));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(mailBody);
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setText(mailBody, true);
        };
        try {
            mailSender.send(preparator);
            logger.info("Leave status mail sent");
        } catch (MailException ex) {
            logger.error("Could not send the leave status mail", ex);
        }
    }

    /**
     * Adds the leave to the college calendar (09:00-17:00 on the start/end dates).
     * Note: the time zone offset is -07:00 as in the original code (not IST); see the upgrade notes.
     */
    public void addEventToCalendar(LeaveStatusDetail leaveStatusDetail) throws IOException {
        String summary = leaveStatusDetail.getName() + " is on Leave today";
        String description = leaveStatusDetail.getName() + " is on leave from " + leaveStatusDetail.getStartDate()
                + " to " + leaveStatusDetail.getEndDate();
        Event event = new Event()
                .setSummary(summary)
                .setLocation("Katihar, Bihar, India")
                .setDescription(description);
        event.setStart(new EventDateTime().setDateTime(new DateTime(leaveStatusDetail.getStartDate() + "T09:00:00-07:00")));
        event.setEnd(new EventDateTime().setDateTime(new DateTime(leaveStatusDetail.getEndDate() + "T17:00:00-07:00")));

        Calendar service = calendarService.configure();
        try {
            event = service.events().insert(kecProperties.calendar().calendarId(), event).execute();
        } catch (SocketTimeoutException e) {
            logger.error("Timeout while creating the calendar event", e);
        }
        logger.info("Calendar event created: {}", event.getHtmlLink());
    }
}
