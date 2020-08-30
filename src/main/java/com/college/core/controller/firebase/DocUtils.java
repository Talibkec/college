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
    String attString ="http://localhost:8080/sites/default/files/compressed/logo.png";
    System.out.println("Image string found = " + attString);
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

    public static void setDefaultLeaveApplication(Document doc, LeaveRequestDetail leaveRequestDetail) throws DocumentException {


        doc.add(new Paragraph(kecLogo()));
        doc.add(new Paragraph(addContent()));
        doc.add(new Paragraph(addSubject(leaveRequestDetail)));
        doc.add(new Paragraph(addGreet()));
        doc.add(new Paragraph(addBody(leaveRequestDetail)));
        doc.add(new Paragraph(facultyInfo(leaveRequestDetail)));


    }
    public static void setCustomLeaveApplication(Document doc,LeaveRequestDetail leaveRequestDetail) throws DocumentException {
        doc.add(new Paragraph(kecLogo()));
        doc.add(new Paragraph(addContent()));
        doc.add(new Paragraph(addCustomSubject(leaveRequestDetail)));
        doc.add(new Paragraph(addGreet()));
        doc.add(new Paragraph(addCustomBody(leaveRequestDetail)));
        doc.add(new Paragraph(facultyInfo(leaveRequestDetail)));
    }
    public static Paragraph addCustomSubject(LeaveRequestDetail leaveRequestDetail){
        String subject = "Subject - "+leaveRequestDetail.getSubject();
        Paragraph addSubject =new Paragraph(10F);
        addSubject.setSpacingAfter(25F);
        addSubject.add(new Phrase(subject));
        return  addSubject;

    }
    public static  Paragraph addCustomBody(LeaveRequestDetail leaveRequestDetail){
        String body = leaveRequestDetail.getEmailContent();
        Paragraph addBody = new Paragraph(25F);
        addBody.setSpacingAfter(25F);
        addBody.add(new Phrase(body));
        return  addBody;
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
    //Print Leave Application
    public static Paragraph addContent(){
        String addressingWord = "To,"+"\n"+"The Principal"+"\n"+"KEC, Katihar";
        String designation = "The Principal"+"\n"+"KEC, Katihar";
        /*String collegeName = "KEC, Katihar";
        String subject = "Subject - Regarding leave from"+leaveRequestDetail.getStartDate()+"to"+leaveRequestDetail.getEndDate();
        String greet = "\n"+"Sir/Ma'am";
        String body = "With immense respect, I hereby wish to notify you that I wish to take " +leaveRequestDetail.getLeaveType()+" from "+ leaveRequestDetail.getStartDate()+" to " + leaveRequestDetail.getEndDate() +". On the stated days I will not be able to attend the college because of some reasons";
        String endLine = "\n"+"I wish that you will consider and grant my leave request. For this, I will be grateful of you. Thankyou!";
*/
        Paragraph addAddressing =new Paragraph(25F);
        /*Paragraph addDesignation =new Paragraph(10F);
        Paragraph addCollegeName =new Paragraph(10F);
        Paragraph addSubject =new Paragraph(10F);
        Paragraph addGreet = new Paragraph(10F);
        Paragraph addBody = new Paragraph(10F);
        Paragraph addEndLine = new Paragraph(10F);
*/

        addAddressing.setSpacingAfter(25F);
        addAddressing.add(new Phrase(addressingWord));
        /*addDesignation.setSpacingAfter(25F);
        addDesignation.add(new Phrase(designation));
        addCollegeName.setSpacingAfter(25F);
        addCollegeName.add(new Phrase(collegeName));
        addSubject.setSpacingAfter(25F);
        addSubject.add(new Phrase(subject));
        addGreet.setSpacingAfter(25F);
        addGreet.add(new Phrase((greet)));
        addBody.setSpacingAfter(25F);
        addBody.add(new Phrase(body));
        addEndLine.setSpacingAfter(25F);
        addEndLine.add(new Phrase(endLine));
*/
        /*addAddressing.add(new Paragraph(addDesignation));
        addDesignation.add(new Paragraph(addCollegeName));
        addCollegeName.add(new Paragraph(addSubject));
        addSubject.add(new Paragraph(addGreet));
        addGreet.add(new Paragraph(addBody));
        addBody.add(new Paragraph(addEndLine));
*/
        return addAddressing;

    }
    public static Paragraph addSubject(LeaveRequestDetail leaveRequestDetail){
        String subject = "\n"+"Subject - Regarding "+leaveRequestDetail.getLeaveType()+" from "+leaveRequestDetail.getStartDate()+" to "+leaveRequestDetail.getEndDate();
        Paragraph addSubject =new Paragraph(10F);
        addSubject.setSpacingAfter(25F);
        addSubject.add(new Phrase(subject));
        return  addSubject;

    }
    public static  Paragraph addGreet(){
        String greet = "\n"+"Sir/Ma'am,";
        Paragraph addGreet = new Paragraph(10F);
        addGreet.setSpacingAfter(25F);
        addGreet.add(new Phrase((greet)));
        return addGreet;
    }
    public static  Paragraph addBody(LeaveRequestDetail leaveRequestDetail){
        String body = "With immense respect, I would like to inform you that I would be on " +leaveRequestDetail.getLeaveType()+" from "+ leaveRequestDetail.getStartDate()+" to " + leaveRequestDetail.getEndDate() +" .On the above mentioned dates, I will not be able to attend college due to personal reasons"+"\n\n"+"I wish that you will consider and grant my leave request. For this, I will be grateful of you. "+"\n\n"+"Thankyou!";
        Paragraph addBody = new Paragraph(25F);
        addBody.setSpacingAfter(25F);
        addBody.add(new Phrase(body));
        return  addBody;
    }
    //Print Faculty's Informatiion in Leave Application
    public static  Paragraph facultyInfo(LeaveRequestDetail leaveRequestDetail){
        String sign="_____________________"+"\n\n"+"(Faculty's Signature)"+"\n\n"+leaveRequestDetail.getName()+"\n\n"+"Date- "+leaveRequestDetail.getRequestDate()+"\n\n"+"Department- "+leaveRequestDetail.getDepartment();
        Paragraph signPara =new Paragraph(10F);
        signPara.setSpacingBefore(50F);
        signPara.setAlignment(Element.ALIGN_LEFT);
        signPara.add(sign);
        return signPara;
    }


}
