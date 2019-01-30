package com.college.core.entity;


import javax.persistence.*;


@Entity
@Table(name="FACULTY")
public class Faculty {


    private Long facultyId;
    private String facultyName;
    private String facultyDepartment;
    private Long departmentId;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "facultyId")
    @SequenceGenerator(name = "facultyId", sequenceName = "facultyId")
    @Column(name = "facultyId")
    public Long getFacultyId() {
        return facultyId;
    }



    //Setter and getter for Faculty ID
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    /*-------------------------------------------------------------*/




    //Setter and getter for Faculty Name
    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Column(name = "F_NAME")

    public String getFacultyName() {
        return facultyName;
    }


    /*-------------------------------------------------------------*/




    //Setter and getter for Faculty Department
    public void setFacultyDepartment(String facultyDepartment) {
        this.facultyDepartment = facultyDepartment;
    }
    @Column(name = "F_DEPARTMENT")


    public String getFacultyDepartment () {
        return facultyDepartment;
    }


    /*-------------------------------------------------------------*/


    //Setter and getter for Department Id
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    @Column(name = "dept_Id")
    public Long getDepartmentId() {
        return departmentId;
    }

    /*-------------------------------------------------------------*/
}
