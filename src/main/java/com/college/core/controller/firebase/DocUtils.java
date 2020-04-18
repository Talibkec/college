package com.college.core.controller.firebase;

import com.college.firebase.FirebaseDocumentHelper;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;

public class DocUtils {


    public static void setDocumentHeader(Document doc, FacultyReportDetail facultyReportDetail){
        String signature = "____________________" + "\n" + "Faculty's Signature";
        Paragraph signPara = new Paragraph(signature).setTextAlignment(TextAlignment.RIGHT).setMarginTop(70F);
        doc.add(kecLogo());
        doc.add(dashedLine());
        doc.add(attendanceTitle());
        doc.add(deptTitle(facultyReportDetail));
        doc.add(semTitle(facultyReportDetail));
        doc.add(subTitle(facultyReportDetail));
        doc.add(signPara);
    }

    //KEC Logo
    public static Image kecLogo(){
        ImageData data=null;
        try {
            data = ImageDataFactory.create("http://localhost/sites/default/files/logo.jpeg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Image image =new Image(data);
        return image;
    }


    // A line after KEC Logo
    public static Paragraph dashedLine(){
        String line = "________________________________";
        Paragraph clgNamePara = new Paragraph(line).setTextAlignment(TextAlignment.CENTER).setFontSize(25F);
        return  clgNamePara;
    }


    // Title of PDF Report file
    public static Paragraph attendanceTitle(){
        String attendanceTitle = "Attendance Report ";
        Paragraph attndancePara = new Paragraph(attendanceTitle).setTextAlignment(TextAlignment.CENTER).setFontSize(20F);
        return  attndancePara;
    }


    //Name of Department and Faculty Name
    public static Paragraph deptTitle( FacultyReportDetail facultyReportDetail){
        String deptTitle ="Department - " + facultyReportDetail;//.dept;//Get Department Name
        String facultyName = "Faculty Name - "+ facultyReportDetail.getFacultyName();//Get Faculty Name
        Paragraph deptPara = new Paragraph(deptTitle);//Print Department Name
        deptPara.add(new Tab());//Tab Alignment
        deptPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        deptPara.add(facultyName);// Print Faculty's Name
         return  deptPara;
    }

    //Semester and Start Date
    public static Paragraph semTitle( FacultyReportDetail facultyReportDetail){
        String semTitle = "Semester - " + facultyReportDetail.getSemester();
        String startDate = "Start Date - "+ facultyReportDetail.getStartDate();
        Paragraph semPara = new Paragraph(semTitle);
        semPara.add(new Tab());
        semPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        semPara.add(startDate);
        return  semPara;
    }

    //Subject and End Date
    public static Paragraph subTitle(FacultyReportDetail facultyReportDetail){
        String subTitle = "Subject - " + facultyReportDetail.getSubject();
        String endDate = "End Date - "+ facultyReportDetail.getEndDate();
        Paragraph subPara = new Paragraph(subTitle);
        subPara.add(new Tab());
        subPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        subPara.add(endDate);
        return  subPara;
    }

}
