package com.college.core.model;

import javax.persistence.Column;

public class TpoDTO {

    private Long coordinatorId;
    private String studentName;
    private Long mobile;
    private String email;
    private String studentSession;
    private Long departmentId;
    private Long registrationNo;

    // Getters and Setters
    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    @Column(name = "mobile", nullable = false)
    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentSession() {
        return studentSession;
    }

    public void setStudentSession(String studentSession) {
        this.studentSession = studentSession;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getRegistrationNo() {
        return registrationNo;
    }
    public void setRegistrationNo(Long registrationNo) {
        this.registrationNo = registrationNo;
    }

}

