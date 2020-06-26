package com.college.core.entity;

import javax.persistence.*;


@Entity
@Table(name = "DEPARTMENT")
public class Department {
    private Long departmentId;
    private String departmentName;
    private String departmentHod;

    public Department(){

    }

    public  Department(String departmentHod){
        this.departmentHod = departmentHod;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "departmentId")
    @SequenceGenerator(name = "departmentId", sequenceName = "departmentId")
    @Column(name = "dept_Id")
    public Long getDepartmentId() {
        return departmentId;
    }

    //Setter and getter for Department ID
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    /*-------------------------------------------------------------*/


    //Setter and getter for Faculty Name
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Column(name = "dept_Name")
    public String getDepartmentName() {
        return departmentName;
    }

    /*-------------------------------------------------------------*/


    //Setter and getter for Faculty Name
    public void setDepartmentHod(String departmentHod) {
        this.departmentHod = departmentHod;
    }

    @Column(name = "dept_hod")
    public String getDepartmentHod() {
        return departmentHod;
    }

    /*-------------------------------------------------------------*/


}
