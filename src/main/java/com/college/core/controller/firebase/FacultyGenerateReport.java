package com.college.core.controller.firebase;

import com.college.firebase.FirebaseDocumentHelper;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import com.itextpdf.text.DocumentException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
public class FacultyGenerateReport {

   public String dept = "";
    public String sub = "";
    public String startDate = "";
    public String semester ="";
    public String endDate = "";
    public  String facultyName="";
    @Autowired
    FirebaseDocumentHelper firebaseDocumentHelper;

    @Autowired
    private JavaMailSender mailSender;

    @ResponseBody
    @RequestMapping(value = "/rn", method = RequestMethod.GET)
    public String FirebaseReportController(@RequestParam("params") String params ) {
        //params = "{\"startDate\":\"2020-08-29\",\"endDate\":\"2020-09-04\",\"dept\":\"Computer Sc. & Engineering\",\"semester\":\"7\",\"subject\":\"Artificial Intelligence\",\"facultyName\":\"Md Talib Ahmad\",\"facultyEmail\":\"apcbadal@gmail.com\"}";
        Gson gson =new Gson();
        FacultyReportDetail facultyReportDetail = gson.fromJson(params,FacultyReportDetail.class);
        sub = facultyReportDetail.getSubject();
        semester = facultyReportDetail.getSemester();
        startDate =facultyReportDetail.getStartDate();
        endDate=facultyReportDetail.getEndDate();
        facultyName = facultyReportDetail.getFacultyName();
        dept = facultyReportDetail.getDept();
        System.out.println("Report Generation is being called with following parameters. " + params );
        Query query = FirebaseDatabase.getInstance()
                .getReference("attendance").orderByChild("date").startAt(startDate).endAt(endDate);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                System.out.println("Firebase Data received : " + dataSnapshot.getChildren());
                Map<String, Map<String, Boolean>> reportInfo = null;
                File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
                File fileWithAbsolutePath = new File(tempDirectory.getAbsolutePath() + "/AttendanceReport.pdf");
                try {
                    reportInfo = firebaseDocumentHelper.getReportInfo(dataSnapshot,fileWithAbsolutePath, facultyReportDetail);
                } catch (DocumentException e) {
                    System.out.println("Unable to Generate the report.");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.out.println("Unable to write into file.");
                    e.printStackTrace();
                }
                catch(Exception ex){
                    System.out.println("Error while generating report");
                    ex.printStackTrace();
                }
                String email ="mdtalibahmad@gmail.com";//facultyReportDetail.getFacultyEmail();
                sendMailWithAttachments(email, fileWithAbsolutePath, reportInfo);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                System.out.println("Firebase call failed , did not received response." + error.getMessage() + error.getDetails());
            }
        });
        return "";
    }

    private void sendMailWithAttachments( String email, File fileWithAbsolutePath,  Map<String, Map<String, Boolean>> reportInfo) {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                System.out.println("sendMailwithAttachment method being called");
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress("test@keck.ac.in"));
                mimeMessage.setSubject("KEC, Attendance Report.");
                mimeMessage.setText("Please find the attached attendance report.");
                //mimeMessage.addHeader("Content-Type", "application/pdf");
                FileSystemResource file = new FileSystemResource(new File(fileWithAbsolutePath.getAbsolutePath()));
                FileSystemResource csvFile = new FileSystemResource(getCSVFile(reportInfo));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment(MimeUtility.encodeText("AttendanceReport.pdf"), new ByteArrayResource(IOUtils.toByteArray(file.getInputStream())));
                helper.addAttachment(MimeUtility.encodeText("AttendanceReport.csv"), new ByteArrayResource(IOUtils.toByteArray(csvFile.getInputStream())));
                helper.setText("Please find the attached attendance report.", true);

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

    public File getCSVFile(Map<String, Map<String, Boolean>> reportInfo) throws FileNotFoundException {
        List<String[]> dataLines = getData(reportInfo);

        File csvOutputFile = new File("test.csv");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
                    .map(this::convertToCSV)
                    .forEach(pw::println);
        }

        return csvOutputFile;

    }

    private List<String[]> getData(Map<String, Map<String, Boolean>> reportInfo) {
        List<String[]> data = new ArrayList<>();
        List<String> dates = new ArrayList<>(reportInfo.keySet());
        Collections.sort(dates);
        List<String> regNos = new ArrayList<>(reportInfo.get(dates.get(0)).keySet());
        Collections.sort(regNos);
        dates.add(0, "");
        String[] arr = dates.stream().toArray(String[] ::new);
        data.add(arr);
        dates.remove(0);
        for(String regNum : regNos){
            List<String> list = new ArrayList<>();
            list.add(regNum);
            Integer count = 0;

            for(String date: dates){
                String status = "A";
                if(reportInfo.get(date).get(regNum)){
                    status = "P";
                    count++;
                }
                list.add(status);
            }
            list.add(count.toString());
            arr = list.stream().toArray(String[] ::new);
            data.add(arr);
        }
        List<String> dayCount = getDayCount(regNos, dates, reportInfo);
        dayCount.add(0, "");
        arr = dayCount.stream().toArray(String[] ::new);
        data.add(arr);
        return data;
    }

    private List<String> getDayCount(List<String> regNos, List<String> dates, Map<String, Map<String, Boolean>> reportInfo) {
        List<String> dayCount = new ArrayList<>();
        for(String date : dates){
            Integer count = 0;
            for(String regnum: regNos){
                if(reportInfo.get(date).get(regnum)){
                    count++;
                }
            }
            dayCount.add(count.toString());
        }
        return  dayCount;
    }

    public String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    public String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

}
