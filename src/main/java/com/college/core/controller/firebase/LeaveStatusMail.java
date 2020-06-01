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
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.CalendarListEntry;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
        

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
    public String LeaveStatusMailController(@RequestParam("params") String params ) throws  IOException, ParseException{
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
    public void addEventToCalendar(LeaveStatusDetail leaveStatusDetail) throws IOException, ParseException
    {
        Event event = new Event();
        Calendar service =null;

        event.setSummary("Calendar Testing");
        event.setLocation("India");
        event.setDescription("Desired description");

        /*ArrayList<EventAttendee> attendees = new ArrayList<EventAttendee>();
        attendees.add(new EventAttendee().setEmail("ankitdutta170@gmail.com"));
        // ...
        event.setAttendees(attendees);*/


        // set the number of days
        String startDate = leaveStatusDetail.getStartDate();
        String endDate = leaveStatusDetail.getEndDate();
        String temp[];
        String delimeter ="-";
        temp = startDate.split(delimeter);
        int startYear = Integer.parseInt(temp[0]) ;
        int startMonth = Integer.parseInt(temp[1]);
        int startDay = Integer.parseInt(temp[2]);
        java.util.Calendar startCal = java.util.Calendar.getInstance();
        startCal.set(java.util.Calendar.YEAR, startYear);
        startCal.set(java.util.Calendar.MONTH, startMonth);
        startCal.set(java.util.Calendar.DATE, startDay);
        startCal.set(java.util.Calendar.HOUR_OF_DAY, 7);
        startCal.set(java.util.Calendar.MINUTE, 0);
        Date getStartDate = startCal.getTime();
        
        temp = endDate.split(delimeter);
        int endYear = Integer.parseInt(temp[0]) ;
        int endMonth = Integer.parseInt(temp[1]);
        int endDay = Integer.parseInt(temp[2]);
        java.util.Calendar endCal = java.util.Calendar.getInstance();
        endCal.set(java.util.Calendar.YEAR, endYear);
        endCal.set(java.util.Calendar.MONTH, endMonth);
        endCal.set(java.util.Calendar.DATE, endDay);
        endCal.set(java.util.Calendar.HOUR_OF_DAY, 18);
        endCal.set(java.util.Calendar.MINUTE, 0);
        Date getEndDate = startCal.getTime();
        
        
        DateTime start = new DateTime(getStartDate);
        event.setStart(new EventDateTime().setDateTime(start));
        DateTime end = new DateTime(getEndDate);
        event.setEnd(new EventDateTime().setDateTime(end));

        service = new CalendarService().configure();
        Event createdEvent = service.events().insert("primary", event).execute();

        System.out.println("Data is :"+createdEvent.getId());
        CalendarListEntry calendarListEntry = service.calendarList().get("calendarId").execute();

        System.out.println(calendarListEntry.getSummary());
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
