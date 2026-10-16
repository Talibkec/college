package com.college.service;

import com.college.core.config.KecProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/** Mails a grievance query from the public grievance form to the principal. */
@Service
public class GrievanceService {

    private static final Logger logger = LoggerFactory.getLogger(GrievanceService.class);

    private final JavaMailSender mailSender;
    private final KecProperties kecProperties;

    public GrievanceService(JavaMailSender mailSender, KecProperties kecProperties) {
        this.mailSender = mailSender;
        this.kecProperties = kecProperties;
    }

    /** Sends the query; a mail failure is logged and otherwise ignored (the form always reports success). */
    public void sendQuery(String name, String email, String phone, String query, String registration) {
        SimpleMailMessage emailObj = new SimpleMailMessage();
        emailObj.setSubject("New Grievence Query Received");
        emailObj.setText("Grievence details are \n" +
                "\nName:- " + name +
                "\nRegistration:- " + registration +
                "\nPhone no :- " + phone +
                "Email:- " + email +
                "\nQuery:-\n " + query
        );
        emailObj.setTo(kecProperties.mail().grievanceTo());
        emailObj.setFrom(kecProperties.mail().from());
        try {
            mailSender.send(emailObj);
        } catch (Exception e) {
            logger.error("Could not send the grievance mail", e);
        }
    }
}
