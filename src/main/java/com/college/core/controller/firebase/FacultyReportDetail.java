package com.college.core.controller.firebase;

public class FacultyReportDetail {

    private String semester;
    private  String startDate;
    private  String endDate;
    private  String facultyName;
   // private  String dept;
    private  String facultyEmail;
    private  String subject;

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

   /* public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }*/


}
