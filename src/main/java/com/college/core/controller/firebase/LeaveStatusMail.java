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
public class LeaveStatusMail {
    public String leaveId = "";
    public String startDate = "";
    public String endDate = "";
    public String leaveType = "";
    public String name = "";
    public String status = "";
    public String email = "";
    public String requestDate = "";
    
    @Autowired
    private JavaMailSender mailSender;
    
    @ResponseBody
    @RequestMapping(value = "/rs", method = RequestMethod.GET)
    public String LeaveStatusMailController(@RequestParam("params") String params ){
        Gson gson= new Gson();
        LeaveStatusDetail leaveStatusDetail = gson.fromJson(params,LeaveStatusDetail.class);
        leaveId = leaveStatusDetail.getLeaveId();
        startDate = leaveStatusDetail.getStartDate();
        endDate = leaveStatusDetail.getEndDate();
        leaveType = leaveStatusDetail.getLeaveType();
        name = leaveStatusDetail.getName();
        email = leaveStatusDetail.getEmail();
        status = leaveStatusDetail.getStatus();
        requestDate = leaveStatusDetail.getRequestDate();
        System.out.println("Leave Status is being called with following parameters. " + params );
        File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
        String email = leaveStatusDetail.getEmail();
        sendLeaveStatusMail(email,leaveStatusDetail);
        return "";
    }
    private void  sendLeaveStatusMail(String email, LeaveStatusDetail leaveStatusDetail){
        String subject = "Leave Request Status";
        String mailBody = "Sir,"+"\n\n"+"The "+leaveStatusDetail.getLeaveType()+" which you have requested on "+leaveStatusDetail.getRequestDate()+" ,between "+leaveStatusDetail.getStartDate()+" and "+leaveStatusDetail.getEndDate()+" has been "+leaveStatusDetail.getStatus();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                System.out.println("send Mail with Status method being called");
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

                mimeMessage.setFrom(new InternetAddress("test@keck.ac.in"));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(mailBody);

                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                
                helper.setText(mailBody, true);
            }
        };
        try {
            mailSender.send(preparator);
            System.out.println("Message sent");
           
        }
        catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
            //fileWithAbsolutePath.delete();
        }
    }
}
