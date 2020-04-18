package com.college.firebase;

import com.college.core.controller.firebase.DocUtils;
import com.college.core.controller.firebase.FacultyGenerateReport;
import com.college.core.controller.firebase.FacultyReportDetail;
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

    public Map<String, Map<String, Boolean>> getReportInfo(DataSnapshot document, File fileWithAbsolutePath, FacultyReportDetail facultyReportDetail) {

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

        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        DocUtils.setDocumentHeader(doc, facultyReportDetail);
        Integer noOfDays = reportInfo.keySet().size();
        Set days = reportInfo.keySet();
        Iterator iterator = days.iterator();
        Table table = new Table(noOfDays + 1);
        setRowHeader(iterator, table);
        Set<String> regNos = reportInfo.get(facultyReportDetail.getStartDate()).keySet();
        Iterator<String> regNoIterator = regNos.iterator();
        printAttendanceList(table, regNoIterator, reportInfo);
        doc.add(table);
        doc.close();
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return reportInfo;
    }

    private void printAttendanceList(Table table, Iterator<String> regNoIterator, Map<String, Map<String, Boolean>> reportInfo) {
        Cell cell = null;
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
                cell.add(status).setTextAlignment(TextAlignment.CENTER);
                table.addCell(cell);
            }
        }
    }

    private void setRowHeader(Iterator iterator, Table table) {
        Cell cell = new Cell();
        cell.add("Date/Reg No.");
        table.addCell(cell);
        while (iterator.hasNext()) {
            String date = (String) iterator.next();
            cell = new Cell();
            cell.add(date);
            table.addCell(cell);
        }
    }

    private void fillDummyDataForTesting(Map<String, Map<String, Boolean>> reportInfo) {
        for(int i = 1; i <= 30; i++){
            Map<String , Boolean> map = reportInfo.get("2020-04-02");
            reportInfo.put("Date" + i, map);
        }
    }
}
