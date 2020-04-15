package com.college.firebase;

import com.google.firebase.database.DataSnapshot;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.*;

import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import org.springframework.context.annotation.Configuration;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

@Configuration
public class FirebaseDocumentHelper {

    private Map<String, Boolean> hc;

    public Map<String, Map<String, Boolean>> getReportInfo(DataSnapshot document) {

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
        String dest = "C:/itextExamples/AttendanceReport.pdf";
        PdfWriter writer = null;
        try {
            writer = new PdfWriter(dest);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document doc = new Document(pdfDoc);
        Table table = new Table(UnitValue.createPointArray(new float[] {1,1,1,1,1,1,1,1,1,1}));
        Table table1 = new Table(UnitValue.createPointArray(new float[] {1,1}));

        Cell cell  = new Cell().add(new Paragraph("Date"));
        table.addCell(cell);

        Map.Entry<String,Map<String,Boolean>> key = reportInfo.entrySet().iterator().next();
        Map<String, Boolean> attendanceList = key.getValue();


        for (Map.Entry<String, Map<String, Boolean>> entry : reportInfo.entrySet()) {


            Cell c1 = new Cell();
            c1.add(entry.getKey());// Print Date
            c1.setTextAlignment(TextAlignment.CENTER);
            table.addCell(c1);



                 for(Map.Entry<String,Boolean> mapEntry:attendanceList.entrySet()){

                        Cell c2 = new Cell();
                        c2.add((mapEntry.getKey()));// Print Registration Number
                        c2.setWidth(25F);
                        c2.setTextAlignment(TextAlignment.CENTER);

                        table1.addCell(c2);

                     if(mapEntry.getValue()==false){
                         Cell c3 =new Cell();
                         c3.add("Absent");// Print absent
                         c3.setWidth(61F);
                         c3.setTextAlignment(TextAlignment.CENTER);
                         table1.addCell(c3);
                     }
                     else{
                         Cell c3 =new Cell(1,78);
                         c3.add("Present");//Print Present
                         c3.setTextAlignment(TextAlignment.CENTER);
                         table1.addCell(c3);
                     }
                 }
        }
        doc.add(table);
        doc.add(table1);

        doc.close();

        return reportInfo;
    }
}
