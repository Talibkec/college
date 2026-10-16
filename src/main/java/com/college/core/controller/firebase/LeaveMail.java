package com.college.core.controller.firebase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.college.firebase.FirebaseDocumentHelper;
import com.google.gson.Gson;
import org.openpdf.text.DocumentException;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeUtility;
import java.io.File;
import java.io.IOException;

@Controller
public class LeaveMail {
    private static final Logger logger = LoggerFactory.getLogger(LeaveMail.class);

    public String email = "";
    public String department = "";
    public String startDate = "";
    public String endDate = "";
    public String requestDate = "";
    public String name = "";
    public String leaveType = "";
    public String subject = "";
    public String emailContent = "";

    private final FirebaseDocumentHelper firebaseDocumentHelper;
    private final JavaMailSender mailSender;

    public LeaveMail(FirebaseDocumentHelper firebaseDocumentHelper, JavaMailSender mailSender) {
        this.firebaseDocumentHelper = firebaseDocumentHelper;
        this.mailSender = mailSender;
    }

    @ResponseBody
    @RequestMapping(value = "/rl", method = RequestMethod.GET)
    public String LeaveMailController(@RequestParam("params") String params ) {
        Gson gson= new Gson();
        LeaveRequestDetail leaveRequestDetail = gson.fromJson(params,LeaveRequestDetail.class);
        email = leaveRequestDetail.getEmail();
        startDate = leaveRequestDetail.getStartDate();
        endDate = leaveRequestDetail.getEndDate();
        requestDate = leaveRequestDetail.getRequestDate();
        name = leaveRequestDetail.getName();
        leaveType = leaveRequestDetail.getLeaveType();
        subject = leaveRequestDetail.getSubject();
        emailContent = leaveRequestDetail.getEmailContent();
        logger.debug("Leave application requested with parameters {}", params);
        File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
        File fileWithAbsolutePath = new File(tempDirectory.getAbsolutePath() + "/RequestLeave.pdf");
        try {
            firebaseDocumentHelper.getLeaveInfo(fileWithAbsolutePath,leaveRequestDetail);
        } catch (DocumentException e) {
            logger.error("Unable to generate the leave application PDF", e);
        } catch (IOException e) {
            logger.error("Unable to write the leave application PDF", e);
        }
        String email = leaveRequestDetail.getEmail();
        sendMailWithAttachments(email, fileWithAbsolutePath);
        return "";
    }
    private void sendMailWithAttachments( String email, File fileWithAbsolutePath) {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                logger.debug("Preparing the leave application mail");
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

                mimeMessage.setFrom(new InternetAddress("test@keck.ac.in"));
                mimeMessage.setSubject("Leave Request.");
                mimeMessage.setText("Please find the attached leave request.");
                mimeMessage.addHeader("Content-Type", "application/pdf");

                FileSystemResource file = new FileSystemResource(new File(fileWithAbsolutePath.getAbsolutePath()));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment(MimeUtility.encodeText("LeaveMail.pdf"), new ByteArrayResource(IOUtils.toByteArray(file.getInputStream())));
                helper.setText("Please find the attached leave application.", true);

            }
        };
        try {
            mailSender.send(preparator);
            logger.info("Leave application mail sent");
            if(FileSystemUtils.deleteRecursively(fileWithAbsolutePath)){
                logger.debug("Leave application file deleted");
            }
        }
        catch (MailException ex) {
            // simply log it and go on...
            logger.error("Could not send the leave application mail", ex);
            //fileWithAbsolutePath.delete();
        }
    }
}
