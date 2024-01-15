package com.college.core.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Entity
@Table(name="Placements")
public class Placements {
    private Long placementId;
    private String studentName;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "placementId")
    @SequenceGenerator(name = "placementId", sequenceName = "placementId")
    public Long getPlacementId() {
        return placementId;
    }

    public void setPlacementId(Long placementId) {
        this.placementId = placementId;
    }
    @Column(name="student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    @Column(name="session")
    public String getStudentSession() {
        return studentSession;
    }

    public void setStudentSession(String studentSession) {
        this.studentSession = studentSession;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    @Column(name="department_id")
    public Long getDepartmentId() {
        return departmentId;
    }


    @Column(name="registration_no")
    public Long getReigstrationNo() {
        return reigstrationNo;
    }

    public void setReigstrationNo(Long reigstrationNo) {
        this.reigstrationNo = reigstrationNo;
    }
    @Column(name="company_name")
    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    private String studentSession;
    private Long departmentId;
    private Long reigstrationNo;
    private String companyName;

//    private List<FacultyKeyProps> facultyKeyProps = new ArrayList<>(0);

}
