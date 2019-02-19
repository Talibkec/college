package com.college.core.model;

import com.college.core.entity.Property;
import com.college.core.entity.User;

import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;

public class FacultyDTO {
    private Long facultyId;
    private String facultyName;
    private Long departmentId;
    private Long facultyMobNo;
    private String facultyOfficialEmail;
    private String facultyPersonalEmail;

    private byte[] facultyPhoto;
    private User user;
    private String fileType;
    private Set<Property> facultyProperties = new HashSet<>(0);

    public String getFacultyOfficialEmail() {return facultyOfficialEmail;}

    public void setFacultyOfficialEmail(String facultyOfficialEmail) {this.facultyOfficialEmail = facultyOfficialEmail;}

    public String getFacultyPersonalEmail() {return facultyPersonalEmail; }

    public void setFacultyPersonalEmail(String facultyPersonalEmail) {this.facultyPersonalEmail = facultyPersonalEmail; }

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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }



    public Long getFacultyMobNo() {
        return facultyMobNo;
    }

    public void setFacultyMobNo(Long facultyMobNo) {
        this.facultyMobNo = facultyMobNo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Property> getFacultyProperties() {
        return facultyProperties;
    }

    public void setFacultyProperties(Set<Property> facultyProperties) {
        this.facultyProperties = facultyProperties;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public byte[] getFacultyPhoto() {
        return facultyPhoto;
    }

    public void setFacultyPhoto(byte[] facultyPhoto) {
        this.facultyPhoto = facultyPhoto;
    }


}
