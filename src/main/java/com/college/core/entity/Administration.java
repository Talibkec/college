package com.college.core.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="ADMINISTRATION")
public class Administration {

    private Long adminRoleId;
    private Faculty faculty;
    private String role;
    private String designation;
    private String dutyAssigned;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "adminRoleId")
    @SequenceGenerator(name = "adminRoleId", sequenceName = "adminRoleId")
    public Long getAdminRoleId() { return adminRoleId; }

    public void setAdminRoleId(Long adminRoleId) {
        this.adminRoleId = adminRoleId;
    }

    @Column(name = "F_ROLE")
    public String getrole() { return role; }

    public void setRole(String role) {
        this.role = role;
    }

    @Column(name = "F_DUTYASSIGNED")
    public String getDutyAssigned() {
        return dutyAssigned;
    }

    public void setDutyAssigned(String dutyAssigned) {
        this.dutyAssigned = dutyAssigned;
    }

    @Column(name = "F_DESIGNATION")
    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "facultyId")
    public Faculty getFaculty() {
        return faculty;
    }
}






