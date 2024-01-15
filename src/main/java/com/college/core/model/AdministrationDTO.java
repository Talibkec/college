package com.college.core.model;

import com.college.core.entity.Faculty;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class AdministrationDTO {

    private Long adminRoleId;
    private String role;
    private Faculty faculty;
    private String dutyAssigned;
    private String designation;

    public Long getAdminRoleId() {
        return adminRoleId;
    }

    public void setAdminRoleId(Long adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDutyAssigned() {
        return dutyAssigned;
    }

    public void setDutyAssigned(String dutyAssigned) {
        this.dutyAssigned = dutyAssigned;
    }


    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }



    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }


    public Faculty getFaculty() {
        return faculty;
    }

}
