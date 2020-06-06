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
import org.springframework.web.bind.annotation.*;

import java.net.SocketTimeoutException;
import java.security.GeneralSecurityException;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.EventAttendee;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.text.ParseException;
import java.util.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
        

import java.io.File;
import java.io.IOException;

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
    public String LeaveStatusMailController(@RequestParam("params") String params ) throws  IOException{
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
        String email = "ankitdutta170@gmail.com";
        sendLeaveStatusMail(email,leaveStatusDetail);
        
        addEventToCalendar(leaveStatusDetail);
        
        return "";
    }
    @ExceptionHandler(IOException.class)
    public void addEventToCalendar(LeaveStatusDetail leaveStatusDetail) throws IOException
    {
        String summary = leaveStatusDetail.getName()+" is on Leave today";
        String description = leaveStatusDetail.getName()+ " is on leave from "+leaveStatusDetail.getStartDate()+" to "+leaveStatusDetail.getEndDate();
        Event event = new Event()
                .setSummary(summary)
                .setLocation("Katihar, Bihar, India")
                .setDescription(description);

        DateTime startDateTime = new DateTime(leaveStatusDetail.getStartDate()+"T09:00:00-07:00");
        EventDateTime start = new EventDateTime()
                .setDateTime(startDateTime);
                
        event.setStart(start);

        DateTime endDateTime = new DateTime(leaveStatusDetail.getEndDate()+"T17:00:00-07:00");
        EventDateTime end = new EventDateTime()
                .setDateTime(endDateTime);
                
        event.setEnd(end);
        
        Calendar service = null;
        service = new CalendarService().configure();
        String calendarId = "onlineattendance@keck.ac.in";
        try {
                event = service.events().insert(calendarId, event).execute();
        }
        catch (SocketTimeoutException e){
            System.out.print(e.getStackTrace());
        }
        System.out.printf("Event created: %s\n", event.getHtmlLink());
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
