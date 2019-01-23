package com.college.sms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@PropertySource("classpath:SMS.properties")
@ConfigurationProperties
public class MessageSender {

    private final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    @Value("${KEC_WEB_TEAM}")
    private String kecWebTeam;

    @Value("${ALL_STUDENTS}")
    private String allStudents;

    @Value("${ALL_PARENTS}")
    private String allParents;

    @Value("${ALL_FACULTIES}")
    private String allFaculties;

    public StringBuilder sendSMSGroup(String smsGroup, String msg, String numberInput){

        if("KECWebTeam".equalsIgnoreCase(smsGroup)){
            if(kecWebTeam != null && kecWebTeam != "") {
                String template = "Dear Web Team Members ";
                String message = sendSMS(getArrayList(kecWebTeam), template, msg);
                logger.info("Message = " + message);
            }
        }
        else if("allStudents".equalsIgnoreCase(smsGroup)){
            if(allStudents != null && allStudents != "") {
                String template = "Dear Students ";
                String message = sendSMS(getArrayList(allStudents),template, msg);
                logger.info("Message = " + message);
            }
        }

        else if("allParents".equalsIgnoreCase(smsGroup)){
            if(allParents != null && allParents != "") {
                String template = "Dear Parents ";
                String message = sendSMS(getArrayList(allParents), template, msg);
                logger.info("Message = " + message);
            }
        }

        else if("allFaculties".equalsIgnoreCase(smsGroup)){
            if(allFaculties != null && allFaculties != "") {
                String template = "Dear Faculties ";
                String message = sendSMS(getArrayList(allFaculties), template, msg);
                logger.info("Message = " + message);
            }
        }
        else if("individual".equalsIgnoreCase(smsGroup)){
            List<String> number = new ArrayList<>(1);
            number.add(numberInput);
            String template = "Dear Students ";
            String message = sendSMS(number, template, msg);
            logger.info("Message = " + message);
        }
        return  null;
    }
    public boolean isValid(String s)
    {
        Pattern p = Pattern.compile("(0/91)?[7-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }

    private List<String> getArrayList(String kecWebTeam) {
        return  Arrays.asList(kecWebTeam.split("\\s*,\\s*"));
    }

    public String sendSMS(List<String> nums, String template, String msg){
        StringBuilder sb = new StringBuilder();
        for (String number : nums) {
            try {
                // Construct data
                String apiKey = "apikey=" + "DQNOrMbdD7g-cJHqexVHwXXxvssb1R1UbxddcccxLz";
                String message = "&message=" + template + msg ;
                String sender = "&sender=" + "KECTXT";
                String numbers = "&numbers=" + "91" + number;
                //String test = "&test=" + "true";
                HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
                String data = apiKey + numbers + message + sender /*+test*/;
                conn.setDoOutput(true);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
                conn.getOutputStream().write(data.getBytes("UTF-8"));
                final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = rd.readLine()) != null) {
                    sb.append(line);
                }
                rd.close();

                //return sb.toString();
            } catch (Exception e) {
                logger.error("Error SMS " + e);
                //return "Error " + e;
            }
        }
        return sb.toString();
    }


}
