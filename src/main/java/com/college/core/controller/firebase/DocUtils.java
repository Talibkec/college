package com.college.core.controller.firebase;

import com.college.firebase.FirebaseDocumentHelper;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.property.TabAlignment;
import com.itextpdf.layout.property.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.MalformedURLException;

public class DocUtils {

    @Autowired
    FacultyGenerateReport facultyGenerateReport;


    //KEC Logo
    public Image kecLogo (){
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
    public Paragraph dashedLine(){
        String line = "________________________________";
        Paragraph clgNamePara = new Paragraph(line).setTextAlignment(TextAlignment.CENTER).setFontSize(25F);
        return  clgNamePara;
    }


    // Title of PDF Report file
    public Paragraph attendanceTitle(){
        String attendanceTitle = "Attendance Report ";
        Paragraph attndancePara = new Paragraph(attendanceTitle).setTextAlignment(TextAlignment.CENTER).setFontSize(20F);
        return  attndancePara;
    }


    //Name of Department and Faculty Name
    public Paragraph deptTitle(){
        String deptTitle ="Department - " +facultyGenerateReport.dept;//Get Department Name
        String facultyName = "Faculty Name - "+facultyGenerateReport.facultyName;//Get Faculty Name
        Paragraph deptPara = new Paragraph(deptTitle);//Print Department Name
        deptPara.add(new Tab());//Tab Alignment
        deptPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        deptPara.add(facultyName);// Print Faculty's Name
         return  deptPara;
    }

    //Semester and Start Date
    public Paragraph semTitle(){
        String semTitle = "Semester - " +facultyGenerateReport.semester;
        String startDate = "Start Date - "+facultyGenerateReport.startDate;
        Paragraph semPara = new Paragraph(semTitle);
        semPara.add(new Tab());
        semPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        semPara.add(startDate);
        return  semPara;
    }

    //Subject and End Date
    public  Paragraph subTitle(){
        String subTitle = "Subject - " + facultyGenerateReport.sub;
        String endDate = "End Date - "+facultyGenerateReport.endDate;
        Paragraph subPara = new Paragraph(subTitle);
        subPara.add(new Tab());
        subPara.addTabStops(new TabStop(1000, TabAlignment.RIGHT));
        subPara.add(endDate);
        return  subPara;
    }

}
