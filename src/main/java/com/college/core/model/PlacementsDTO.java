package com.college.core.model;


import com.college.core.entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlacementsDTO {
    private Long placementId;
    private String studentName;

    public Long getPlacementId() {
        return placementId;
    }

    public void setPlacementId(Long placementId) {
        this.placementId = placementId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSession() {
        return studentSession;
    }

    public void setStudentSession(String studentSession) {
        this.studentSession = studentSession;
    }



    public Long getReigstrationNo() {
        return reigstrationNo;
    }

    public void setReigstrationNo(Long reigstrationNo) {
        this.reigstrationNo = reigstrationNo;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    //    private Long departmentId;
    private String studentSession;
    private Long departmentId;
    private Long reigstrationNo;
    private String companyName;


}
