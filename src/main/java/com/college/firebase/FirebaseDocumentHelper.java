package com.college.firebase;

import com.college.core.controller.firebase.DocUtils;
import com.college.core.controller.firebase.FacultyReportDetail;
import com.college.core.controller.firebase.LeaveRequestDetail;
import com.google.firebase.database.DataSnapshot;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.StringUtils;
import io.grpc.netty.shaded.io.netty.util.internal.StringUtil;
import org.springframework.context.annotation.Configuration;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
public class FirebaseDocumentHelper {

    public Map<String, Map<String, Boolean>> getReportInfo(DataSnapshot document, File fileWithAbsolutePath, FacultyReportDetail facultyReportDetail) throws DocumentException, IOException {
        Map<String, Map<String, Boolean>> reportInfo = new TreeMap<>();
        if (document != null) {
            for (DataSnapshot child : document.getChildren()) {
                DataSnapshot attendanceList = child.child("attendanceList");
                String db_department = (String) child.child("department").getValue();
                String db_semester = (String) child.child("semester").getValue();
                String db_subject = (String) child.child("subject").getValue();
                String date = (String) child.child("date").getValue();
                Boolean haveValidInfo = StringUtil.isNullOrEmpty(db_department) ||
                                        StringUtil.isNullOrEmpty(db_semester) ||
                                        StringUtil.isNullOrEmpty(db_subject);
                if(!haveValidInfo && (
                            db_department.equalsIgnoreCase(facultyReportDetail.getDept()) &&
                            db_subject.equalsIgnoreCase(facultyReportDetail.getSubject()) &&
                            db_semester.equalsIgnoreCase(facultyReportDetail.getSemester())
                        )){
                if (attendanceList != null) {
                    Map<String, Boolean> attendanceListVal = (Map<String, Boolean>) attendanceList.getValue();
                    TreeMap<String, Boolean> treeMap = new TreeMap<>();
                    treeMap.putAll(attendanceListVal);
                    reportInfo.put(date, treeMap);
                } }
            }
        }
        //fillDummyDataForTesting(reportInfo);
        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, out);
        Integer noOfDays = reportInfo.keySet().size();
        Integer columnPerPage = 10;
        Integer pageSize = noOfDays / columnPerPage;
        doc.open();
        DocUtils.setDocumentHeader(doc, facultyReportDetail);
        getPdfPTable(facultyReportDetail, reportInfo, doc, columnPerPage, pageSize );
        doc.add(DocUtils.facultySign());

       if(reportInfo.size() <= 0) {
            doc.add(new Paragraph(new Phrase("No. record found for this details")));
            doc.close();
        }

       if(doc.isOpen()){
           doc.close();
       }
        OutputStream os = new FileOutputStream(fileWithAbsolutePath.getAbsoluteFile());
        out.writeTo(os);
        os.close();
        out.close();
        return reportInfo;
    }
    public void getLeaveInfo(File fileWithAbsolutePath, LeaveRequestDetail leaveRequestDetail) throws DocumentException, IOException {
        Document doc = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter.getInstance(doc, out);
        doc.open();
        if(leaveRequestDetail.getSubject().isEmpty() || leaveRequestDetail.getEmailContent().isEmpty()) {
            DocUtils.setDefaultLeaveApplication(doc, leaveRequestDetail);
        }
        else{
            DocUtils.setCustomLeaveApplication(doc,leaveRequestDetail);
        }
        if(doc.isOpen()){
            doc.close();
        }
        OutputStream os = new FileOutputStream(fileWithAbsolutePath.getAbsoluteFile());
        out.writeTo(os);
        os.close();
        out.close();
    }
    private void getPdfPTable(FacultyReportDetail facultyReportDetail, Map<String, Map<String, Boolean>> reportInfo,
                              Document doc, Integer columnPerPage, Integer pageSize) throws DocumentException {

        Integer noOfDays = reportInfo.keySet().size();
        Set<String> days = reportInfo.keySet();
        Integer offset = 0;
        Integer pageNo = 0;
        List<String> arrayOfdates = days.stream().collect(Collectors.toList());

        while( pageNo < pageSize){
            PdfPTable table = new PdfPTable(columnPerPage + 1);

            setRowHeader(arrayOfdates, table, offset, columnPerPage);
            Set<String> regNos = reportInfo.get(facultyReportDetail.getStartDate()).keySet();
            Iterator<String> regNoIterator = regNos.iterator();
            printAttendanceList(table, regNoIterator, reportInfo, offset, columnPerPage, arrayOfdates);
            doc.add(table);
            offset += columnPerPage;
            pageNo++;
        }
        if(noOfDays % columnPerPage != 0){
            columnPerPage = noOfDays %columnPerPage;
            PdfPTable table = new PdfPTable(columnPerPage + 1);
            setRowHeader(arrayOfdates, table, offset, columnPerPage);
            Set<String> regNos = reportInfo.get(facultyReportDetail.getStartDate()).keySet();
            Iterator<String> regNoIterator = regNos.iterator();
            printAttendanceList(table, regNoIterator, reportInfo, offset, columnPerPage, arrayOfdates);
            doc.add(table);
        }
        return ;
    }

    private void printAttendanceList(PdfPTable table, Iterator<String> regNoIterator, Map<String, Map<String, Boolean>> reportInfo,
                                     Integer offset, Integer colPPage, List<String> arrayOfDates) {
        while (regNoIterator.hasNext()) {
            Integer colNum = offset;
            String regNo = regNoIterator.next();
            PdfPCell cell = new PdfPCell(new Phrase(regNo));
            table.addCell(cell);
            while (colNum < offset + colPPage) {
                String date = arrayOfDates.get(colNum++);
                String status = reportInfo.get(date).get(regNo) ? "P" : "A";
                cell = new PdfPCell(new Phrase(status));
                table.addCell(cell);
            }
        }
    }

    private void setRowHeader(List<String> arraysOdDates, PdfPTable table, Integer offset, Integer columnPPage) {
        Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        Integer colCount = offset;
        PdfPCell cell = new PdfPCell( new Phrase("Date/Reg No.", headFont));
        table.addCell(cell);
        while (colCount < offset + columnPPage) {
            String date = arraysOdDates.get(colCount++);
            cell = new PdfPCell(new Phrase(date, headFont));
            table.addCell(cell);
        }
        return ;
    }

    private void fillDummyDataForTesting(Map<String, Map<String, Boolean>> reportInfo) {
        for(int i = 1; i <= 30; i++){
            Map<String , Boolean> map = reportInfo.get("2020-04-02");
            reportInfo.put("Date" + i, map);
        }
    }
}
