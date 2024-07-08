package com.college.core.model;


import com.college.core.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StaffDTO {
    private Long staffId;

    private String staffName;
//    private Long departmentId;
    private Long staffMobNo;
    private byte[] staffPhoto;
    private String imageURL;

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getStaffMobNo() {
        return staffMobNo;
    }

    public void setStaffMobNo(Long staffMobNo) {
        this.staffMobNo = staffMobNo;
    }

    public String getStaffOfficialEmail() {
        return staffOfficialEmail;
    }

    public void setStaffOfficialEmail(String staffOfficialEmail) {
        this.staffOfficialEmail = staffOfficialEmail;
    }

    public String getStaffPersonalEmail() {
        return staffPersonalEmail;
    }

    public void setStaffPersonalEmail(String staffPersonalEmail) {
        this.staffPersonalEmail = staffPersonalEmail;
    }

    public byte[] getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(byte[] staffPhoto) {
        this.staffPhoto = staffPhoto;
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

    private String staffOfficialEmail;
    private String staffPersonalEmail;
//    private String imageURL;


    private User user;
    private String fileType;

//    private List<FacultyKeyPropsDTO> facultyKeyProps = new ArrayList<>(0);


}
