package com.college.firebase;

import com.google.firebase.database.DataSnapshot;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

@Configuration
public class FirebaseDocumentHelper {

    private Map<String, Boolean> hc;

    public Map<String, Map<String, Boolean>> getReportInfo(DataSnapshot document, File fileWithAbsolutePath) {

        Map<String, Map<String, Boolean>> reportInfo = new HashMap<>();
        if (document != null) {

            for (DataSnapshot child : document.getChildren()) {
                DataSnapshot attendanceList = child.child("attendanceList");
                String date = (String) child.child("date").getValue();
                if (attendanceList != null) {
                    Map<String, Boolean> attendanceListVal = (Map<String, Boolean>) attendanceList.getValue();
                    reportInfo.put(date, attendanceListVal);
                }
            }
        }

        PdfWriter writer = null;
        try {
            writer = new PdfWriter(fileWithAbsolutePath.getAbsolutePath() +  "/AttendanceReport.pdf");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        Integer noOfDays = reportInfo.keySet().size();
        Set days = reportInfo.keySet();
        Iterator iterator = days.iterator();
        Table table = new Table(noOfDays + 1);
        Cell cell = new Cell();
        cell.add("Date/Reg No.");
        table.addCell(cell);
        String  dateInfo = "";
        while(iterator.hasNext()){
            String date  = (String) iterator.next();
            dateInfo = date;
            cell = new Cell();
            cell.add(date);
            table.addCell(cell);
        }
        Set<String> regNos = reportInfo.get(dateInfo).keySet();
        Iterator<String> regNoIterator = regNos.iterator();
        while(regNoIterator.hasNext()){
            String regNo = regNoIterator.next();
            cell = new Cell();
            cell.add(regNo);
            table.addCell(cell);
            Iterator<String> attendanceDateIt = reportInfo.keySet().iterator();
            while(attendanceDateIt.hasNext()){
                String date = attendanceDateIt.next();
                cell = new Cell();
                String status = reportInfo.get(date).get(regNo) ? "P" : "A";
                cell.add(status.toString());
                table.addCell(cell);
            }
        }

        doc.add(table);
        doc.close();
        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reportInfo;
    }
}
