package com.college.firebase;

import com.college.core.controller.firebase.FacultyGenerateReport;
import com.google.firebase.database.DataSnapshot;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.property.TabAlignment;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.*;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.Document;

@Configuration
public class FirebaseDocumentHelper {

    private Map<String, Boolean> hc;

    public Map<String, Map<String, Boolean>> getReportInfo(DataSnapshot document, File fileWithAbsolutePath) {

        Map<String, Map<String, Boolean>> reportInfo = new TreeMap<>();
        String db_department = new String();
        String db_semester = new String();
        String db_subject = new String();

        if (document != null) {
            for (DataSnapshot child : document.getChildren()) {
                DataSnapshot attendanceList = child.child("attendanceList");
                db_department = (String) child.child("department").getValue();
                db_semester = (String) child.child("semester").getValue();
                db_subject = (String) child.child("subject").getValue();
                String date = (String) child.child("date").getValue();
                if (attendanceList != null) {
                    Map<String, Boolean> attendanceListVal = (Map<String, Boolean>) attendanceList.getValue();
                    TreeMap<String, Boolean> treeMap = new TreeMap<>();
                    treeMap.putAll(attendanceListVal);
                    reportInfo.put(date, treeMap);
                }
            }
        }
        fillDummyDataForTesting(reportInfo);
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(fileWithAbsolutePath.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        FacultyGenerateReport facultyGenerateReport = new FacultyGenerateReport();

        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        ImageData data = null;
        try {
            data = ImageDataFactory.create("http://localhost/sites/default/files/logo.jpeg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image = new Image(data);
        String clgName = "________________________________";
        String reportTitle = "Attendance Report ";
        String deptTitle = "Department - " + db_department;
        String semTitle = "Semester - " + db_semester;
        String subTitle = "Subject - " + db_subject;
        String facultyName = "Faculty Name - "+facultyGenerateReport.facultyName;
        String startDate = facultyGenerateReport.startDate;
        String endDate = facultyGenerateReport.endDate;
        String signature = "____________________" + "\n" + "Faculty's Signature";
        Integer noOfDays = reportInfo.keySet().size();
        Set days = reportInfo.keySet();
        Iterator iterator = days.iterator();
        Table table = new Table(noOfDays + 1);
        Cell cell = new Cell();
        cell.add("Date/Reg No.");
        table.addCell(cell);
        String dateInfo = "";
        //Arrays.sort(new String[]{dateInfo});
        while (iterator.hasNext()) {
            String date = (String) iterator.next();
            dateInfo = date;
            cell = new Cell();
            cell.add(date);
            table.addCell(cell);
        }
        Set<String> regNos = reportInfo.get(dateInfo).keySet();

        Iterator<String> regNoIterator = regNos.iterator();
        while (regNoIterator.hasNext()) {
            String regNo = regNoIterator.next();
            cell = new Cell();
            cell.add(regNo);
            table.addCell(cell);
            Iterator<String> attendanceDateIt = reportInfo.keySet().iterator();
            int count = 0;
            while (attendanceDateIt.hasNext()) {
                count ++;
                String date = attendanceDateIt.next();

                cell = new Cell();
                String status = reportInfo.get(date).get(regNo) ? "P" : "A";
                cell.add(status.toString()).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
            }
        }
        Paragraph clgNamePara = new Paragraph(clgName).setTextAlignment(TextAlignment.CENTER).setFontSize(25F);
        Paragraph reportPara = new Paragraph(reportTitle).setTextAlignment(TextAlignment.CENTER).setFontSize(20F);
        Paragraph deptPara = new Paragraph(deptTitle);
        deptPara.add(new Tab());
        deptPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        deptPara.add(facultyName);
        Paragraph semPara = new Paragraph(semTitle);
        semPara.add(new Tab());
        semPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        semPara.add(startDate);
        Paragraph subPara = new Paragraph(subTitle);
        subPara.add(new Tab());
        subPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        subPara.add(endDate);
        Paragraph signPara = new Paragraph(signature).setTextAlignment(TextAlignment.RIGHT).setMarginTop(70F);
        doc.add(image);
        doc.add(clgNamePara);

        doc.add(reportPara);
        doc.add(deptPara);
        doc.add(semPara);
        doc.add(subPara);
        doc.add(table);
        doc.add(signPara);
        doc.close();
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return reportInfo;
    }

    private void fillDummyDataForTesting(Map<String, Map<String, Boolean>> reportInfo) {
        for(int i = 1; i <= 30; i++){
            Map<String , Boolean> map = reportInfo.get("2020-04-02");
            reportInfo.put("Date" + i, map);
        }
    }
}
