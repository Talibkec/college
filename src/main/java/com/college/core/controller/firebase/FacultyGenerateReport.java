package com.college.core.controller.firebase;

import com.college.firebase.FirebaseDocumentHelper;
import com.google.firebase.database.*;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.File;
import java.util.Map;

@Controller
public class FacultyGenerateReport {

    public String dept = "Computer Sc. & Engineering";
    public String sub = "Major Projct";
    public String startDate = "2020-04-01";
    public String endDate = "2020-04-25";
    @Autowired
    FirebaseDocumentHelper firebaseDocumentHelper;

    @Autowired
    private JavaMailSender mailSender;

    @ResponseBody
    @RequestMapping(value = "/rn", method = RequestMethod.GET)
    public String FirebaseReportController(/*@RequestParam("param") String params */ ) {

        String dept = "Computer Sc. & Engineering";
        String sub = "Major Projct";
        String startDate = "2020-04-01";
        String endDate = "2020-04-05";

        Query query = FirebaseDatabase.getInstance()
                .getReference("attendance").orderByChild("date").startAt(startDate).endAt(endDate);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                File tempDirectory = new File(System.getProperty("java.io.tmpdir"));
                File fileWithAbsolutePath = new File(tempDirectory.getAbsolutePath());
                Map<String, Map<String, Boolean>> reportInfo = firebaseDocumentHelper.getReportInfo(dataSnapshot,fileWithAbsolutePath);

                String email = "mdtalibahmad@gmail.com";
                sendMailWithAttachments(email, fileWithAbsolutePath);
            }

            @Override
            public void onCancelled(DatabaseError error) {
            }
        });
        return "";
    }

    private void sendMailWithAttachments( String email, File fileWithAbsolutePath) {
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
            public void prepare(MimeMessage mimeMessage) throws Exception
            {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
                mimeMessage.setFrom(new InternetAddress("test@keck.ac.in"));
                mimeMessage.setSubject("KEC, Attendance Report.");
                mimeMessage.setText("Please find the attached attendance report.");
                mimeMessage.addHeader("Content-Type", "application/pdf");

                FileSystemResource file = new FileSystemResource(new File(fileWithAbsolutePath.getAbsolutePath() +  "/AttendanceReport.pdf"));
                MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
                helper.addAttachment(MimeUtility.encodeText("AttendanceReport.pdf"), new ByteArrayResource(IOUtils.toByteArray(file.getInputStream())));
                helper.setText("Please find the attached attendance report.", true);
                //helper.addAttachment("AttendanceReport.pdf", file);

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
