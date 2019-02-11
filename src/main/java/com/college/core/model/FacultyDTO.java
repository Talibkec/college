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
    private String facultyEmail;
    private Long facultyMobNo;
    private Blob facultyPhoto;
    private User user;
    private Set<Property> facultyProperties = new HashSet<>(0);

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

    public String getFacultyEmail() {
        return facultyEmail;
    }

    public void setFacultyEmail(String facultyEmail) {
        this.facultyEmail = facultyEmail;
    }

    public Long getFacultyMobNo() {
        return facultyMobNo;
    }

    public void setFacultyMobNo(Long facultyMobNo) {
        this.facultyMobNo = facultyMobNo;
    }

    public Blob getFacultyPhoto() {
        return facultyPhoto;
    }

    public void setFacultyPhoto(Blob facultyPhoto) {
        this.facultyPhoto = facultyPhoto;
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

}
