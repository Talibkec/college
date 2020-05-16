package com.college.core.controller.firebase;


import com.itextpdf.layout.element.Tab;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.IOException;

public class DocUtils {


    public static void setDocumentHeader(Document doc, FacultyReportDetail facultyReportDetail) throws DocumentException {


        doc.add(new Paragraph(kecLogo()));
        doc.add(new Paragraph(attTitle()));
        doc.add(new Paragraph(deptTitle(facultyReportDetail)));
        doc.add(new Paragraph(semTitle(facultyReportDetail)));
        doc.add(new Paragraph(subTitle(facultyReportDetail)));

    }

    //Print KEC Logo
public  static Paragraph kecLogo(){
        System.setProperty("http.agent","Chrome");
    String attString ="http://localhost/sites/default/files/compressed/logo.png";
    Image image=null;
    try {
        image =  Image.getInstance(attString);
    } catch (BadElementException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
    image.setAlignment(Element.ALIGN_CENTER);
    Paragraph kecLogo = new Paragraph(32F);
    kecLogo.add(image);

    return  kecLogo;

}

//Print Attendance Title
public static Paragraph attTitle(){
    String attString = "Attendance Report";
    Paragraph attTitle =new Paragraph(10F);
    attTitle.setSpacingAfter(25F);
    attTitle.setFont(FontFactory.getFont(FontFactory.HELVETICA,16F));
    attTitle.setAlignment(Element.ALIGN_CENTER);
    attTitle.add(new Phrase(attString));
    return attTitle;
}

//Print Department Title and Faculty Name
    public  static Paragraph deptTitle(FacultyReportDetail facultyReportDetail){
        Chunk chunk = new Chunk(new VerticalPositionMark());
        String deptString = "Department - " + facultyReportDetail.getDept();
        Paragraph deptTitle = new Paragraph(10F);
        deptTitle.setIndentationLeft(25F);
        deptTitle.setSpacingAfter(10F);
        deptTitle.add(new Phrase(deptString));
        deptTitle.add(new Chunk(chunk));
        deptTitle.add(new Phrase("Faculty Name - "+ facultyReportDetail.getFacultyName()));

        return  deptTitle;
    }

    //Print Semester and Start date
    public  static Paragraph semTitle(FacultyReportDetail facultyReportDetail){
        Chunk chunk = new Chunk(new VerticalPositionMark());
        String semString = "Semester- "+ facultyReportDetail.getSemester();
        Paragraph semTitle =new Paragraph(10F);
        semTitle.setIndentationLeft(25F);
        semTitle.setSpacingAfter(10F);
        semTitle.add(new Phrase(semString));
        semTitle.add(new Chunk(chunk));
        semTitle.add(new Phrase("Start Date - "+ facultyReportDetail.getStartDate()));

        return  semTitle;

    }

//Print Subject Title and End date
    public  static Paragraph subTitle(FacultyReportDetail facultyReportDetail){
       Chunk chunk =new Chunk(new VerticalPositionMark());
       String subString ="Subject - "+facultyReportDetail.getSubject();
       Paragraph subTitle =new Paragraph(10F);
       subTitle.setIndentationLeft(25F);
       subTitle.setSpacingAfter(10F);
       subTitle.add(new Phrase(subString));
       subTitle.add(new Chunk(chunk));
       subTitle.add(new Phrase("End Date - "+facultyReportDetail.getEndDate()));
        return  subTitle;

    }

    //Print Fcaulty's Signature
    public static  Paragraph facultySign(){
        String sign="_____________________"+"\n\n"+"Faculty's Signature";
        Paragraph signPara =new Paragraph(10F);
        signPara.setSpacingBefore(50F);
        signPara.setAlignment(Element.ALIGN_RIGHT);
        signPara.add(sign);
        return signPara;
    }


}
