package com.college.core.controller.firebase;

import com.college.firebase.FirebaseDocumentHelper;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Controller
public class LeaveMail {
    public String email = "";
    public String department = "";
    public String startDate = "";
    public String endDate = "";
    public String requestDate = "";
    public String name = "";
    public String leaveType = "";
    public String subject = "";
    public String emailContent = "";

    @Autowired
    FirebaseDocumentHelper firebaseDocumentHelper;

    @Autowired
    private JavaMailSender mailSender;

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
        System.out.println("Leave Application is being called with following parameters. " + params );
        File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
        File fileWithAbsolutePath = new File(tempDirectory.getAbsolutePath() + "/RequestLeave.pdf");
        try {
            firebaseDocumentHelper.getLeaveInfo(fileWithAbsolutePath,leaveRequestDetail);
        } catch (DocumentException e) {
            System.out.println("Unable to Generate the report.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Unable to write into file.");
            e.printStackTrace();
        }
        String email = "ankitdutta170@gmail.com";
        sendMailWithAttachments(email, fileWithAbsolutePath);
        return "";
    }
    private void sendMailWithAttachments( String email, File fileWithAbsolutePath) {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                System.out.println("send Mail with Attachment method being called");
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
            System.out.println("Message sent");
            if(FileSystemUtils.deleteRecursively(fileWithAbsolutePath)){
                System.out.println("Report deleted.");
            }
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
            //fileWithAbsolutePath.delete();
        }
    }
}
