package com.college.core.model;

import java.sql.Blob;

public class FacultyDTO {
    private Long facultyId;
    private String facultyName;
    private String facultyDepartment;
    private Long departmentId;
    private String facultyEmail;
    private Long facultyNumber;
    private Blob facultyPhoto;

    public String getFacultyEmail() {return facultyEmail;}

    public void setFacultyEmail(String facultyEmail) {this.facultyEmail = facultyEmail;}

    public Long getFacultyNumber() {return facultyNumber; }

    public void setFacultyNumber(Long facultyNumber) {this.facultyNumber = facultyNumber; }

    public Blob getFacultyPhoto() {return facultyPhoto; }

    public void setFacultyPhoto(Blob facultyPhoto) {this.facultyPhoto = facultyPhoto; }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getFacultyDepartment() {
        return facultyDepartment;
    }

    public void setFacultyDepartment(String facultyDepartment) {
        this.facultyDepartment = facultyDepartment;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }


}
