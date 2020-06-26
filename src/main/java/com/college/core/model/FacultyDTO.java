package com.college.core.model;


import com.college.core.entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FacultyDTO {
    private Long facultyId;
    private String facultyName;
    private Long departmentId;
    private Long facultyMobNo;
    private String facultyOfficialEmail;
    private String facultyPersonalEmail;
    private String imageURL;


    private byte[] facultyPhoto;
    private User user;
    private String fileType;


    private List<FacultyKeyPropsDTO> facultyKeyProps = new ArrayList<>(0);

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

    public List<FacultyKeyPropsDTO> getFacultyKeyProps() {
        return facultyKeyProps;
    }

    public void setFacultyKeyProps(List<FacultyKeyPropsDTO> facultyKeyProps) {
        this.facultyKeyProps = facultyKeyProps;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageURL() {
        return imageURL;
    }
}
