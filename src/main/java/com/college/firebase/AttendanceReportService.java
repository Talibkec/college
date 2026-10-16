package com.college.firebase;

import com.college.core.config.KecProperties;
import com.college.core.controller.firebase.FacultyReportDetail;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
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
import java.io.UncheckedIOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Attendance report for the attendance app (/rn): reads the attendance records between two dates from Firebase,
 * builds a PDF and a CSV report and mails both to kec.report.attendance-mail-to.
 *
 * <p>All request data stays in local variables and every report uses its own temp file (the controller used to
 * keep the request in shared fields and write fixed files, which mixed up concurrent requests).
 */
@Service
public class AttendanceReportService {

    private static final Logger logger = LoggerFactory.getLogger(AttendanceReportService.class);

    private final FirebaseDocumentHelper firebaseDocumentHelper;
    private final JavaMailSender mailSender;
    private final KecProperties kecProperties;

    public AttendanceReportService(FirebaseDocumentHelper firebaseDocumentHelper, JavaMailSender mailSender,
                                   KecProperties kecProperties) {
        this.firebaseDocumentHelper = firebaseDocumentHelper;
        this.mailSender = mailSender;
        this.kecProperties = kecProperties;
    }

    /** Starts the Firebase query; the report is built and mailed asynchronously when the data arrives. */
    public void requestReport(FacultyReportDetail facultyReportDetail) {
        Query query = FirebaseDatabase.getInstance()
                .getReference("attendance").orderByChild("date")
                .startAt(facultyReportDetail.getStartDate()).endAt(facultyReportDetail.getEndDate());
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                logger.debug("Firebase data received: {} entries", dataSnapshot.getChildrenCount());
                buildAndMail(dataSnapshot, facultyReportDetail);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                logger.error("Firebase query failed: {} {}", error.getMessage(), error.getDetails());
            }
        });
    }

    void buildAndMail(DataSnapshot dataSnapshot, FacultyReportDetail facultyReportDetail) {
        File pdf = createTempFile("AttendanceReport", ".pdf");
        try {
            Map<String, Map<String, Boolean>> reportInfo = null;
            try {
                reportInfo = firebaseDocumentHelper.getReportInfo(dataSnapshot, pdf, facultyReportDetail);
            } catch (DocumentException e) {
                logger.error("Unable to generate the attendance report", e);
            } catch (IOException e) {
                logger.error("Unable to write the attendance report file", e);
            } catch (Exception ex) {
                logger.error("Error while generating the attendance report", ex);
            }
            sendMailWithAttachments(kecProperties.report().attendanceMailTo(), pdf, reportInfo);
        } finally {
            deleteQuietly(pdf);
        }
    }

    private void sendMailWithAttachments(String email, File pdf, Map<String, Map<String, Boolean>> reportInfo) {
        MimeMessagePreparator preparator = mimeMessage -> {
            logger.debug("Preparing the attendance report mail");
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            mimeMessage.setFrom(new InternetAddress(kecProperties.mail().from()));
            mimeMessage.setSubject("KEC, Attendance Report.");
            mimeMessage.setText("Please find the attached attendance report.");
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.addAttachment(MimeUtility.encodeText("AttendanceReport.pdf"), new ByteArrayResource(Files.readAllBytes(pdf.toPath())));
            helper.addAttachment(MimeUtility.encodeText("AttendanceReport.csv"), new ByteArrayResource(getCsv(reportInfo)));
            helper.setText("Please find the attached attendance report.", true);
        };
        try {
            mailSender.send(preparator);
            logger.info("Attendance report mail sent");
        } catch (MailException ex) {
            logger.error("Could not send the attendance report mail", ex);
        }
    }

    /** The CSV report, byte for byte as the former test.csv file (one line per PrintWriter.println). */
    public byte[] getCsv(Map<String, Map<String, Boolean>> reportInfo) {
        StringBuilder csv = new StringBuilder();
        for (String[] line : getData(reportInfo)) {
            csv.append(convertToCSV(line)).append(System.lineSeparator());
        }
        return csv.toString().getBytes(Charset.defaultCharset());
    }

    private List<String[]> getData(Map<String, Map<String, Boolean>> reportInfo) {
        List<String[]> data = new ArrayList<>();
        List<String> dates = new ArrayList<>(reportInfo.keySet());
        Collections.sort(dates);
        List<String> regNos = new ArrayList<>(reportInfo.get(dates.get(0)).keySet());
        Collections.sort(regNos);
        dates.add(0, "");
        String[] arr = dates.stream().toArray(String[]::new);
        data.add(arr);
        dates.remove(0);
        for (String regNum : regNos) {
            List<String> list = new ArrayList<>();
            list.add(regNum);
            Integer count = 0;
            for (String date : dates) {
                String status = "A";
                if (reportInfo.get(date).get(regNum)) {
                    status = "P";
                    count++;
                }
                list.add(status);
            }
            list.add(count.toString());
            arr = list.stream().toArray(String[]::new);
            data.add(arr);
        }
        List<String> dayCount = getDayCount(regNos, dates, reportInfo);
        dayCount.add(0, "");
        arr = dayCount.stream().toArray(String[]::new);
        data.add(arr);
        return data;
    }

    private List<String> getDayCount(List<String> regNos, List<String> dates, Map<String, Map<String, Boolean>> reportInfo) {
        List<String> dayCount = new ArrayList<>();
        for (String date : dates) {
            Integer count = 0;
            for (String regnum : regNos) {
                if (reportInfo.get(date).get(regnum)) {
                    count++;
                }
            }
            dayCount.add(count.toString());
        }
        return dayCount;
    }

    private String convertToCSV(String[] data) {
        return Stream.of(data)
                .map(this::escapeSpecialCharacters)
                .collect(Collectors.joining(","));
    }

    private String escapeSpecialCharacters(String data) {
        String escapedData = data.replaceAll("\\R", " ");
        if (data.contains(",") || data.contains("\"") || data.contains("'")) {
            data = data.replace("\"", "\"\"");
            escapedData = "\"" + data + "\"";
        }
        return escapedData;
    }

    static File createTempFile(String prefix, String suffix) {
        try {
            return File.createTempFile(prefix, suffix);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    static void deleteQuietly(File file) {
        if (file != null && !file.delete() && file.exists()) {
            logger.warn("Could not delete the temp file {}", file);
        }
    }
}
