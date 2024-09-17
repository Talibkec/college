package com.college.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "TpoCoordinator")
public class TpoCoordinator {

    private Long coordinatorId;
    private String studentName;
    private Long mobile;
    private String email;
    private String studentSession;
    private Long departmentId;
    private Long registrationNo;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "coordinatorId")
    @SequenceGenerator(name = "coordinatorId", sequenceName = "coordinatorId")
    public Long getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(Long coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    @Column(name = "student_name", nullable = false)
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

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "session", nullable = false)
    public String getStudentSession() {
        return studentSession;
    }

    public void setStudentSession(String studentSession) {
        this.studentSession = studentSession;
    }

    @Column(name = "department_id", nullable = false)
    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name = "registration_no", nullable = false)
    public Long getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(Long registrationNo) {
        this.registrationNo = registrationNo;
    }
}

