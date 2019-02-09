package com.college.core.entity;


import javax.persistence.*;
import java.sql.Blob;


@Entity
@Table(name="FACULTY")
public class Faculty {
    private Long facultyId;
    private String facultyName;
    private String facultyDepartment;
    private Long departmentId;
    private String facultyEmail;
    private Long facultyNumber;
    private Blob facultyPhoto;

    @Column(name="F_EMAIL")
    public String getFacultyEmail() {return facultyEmail;}

    public void setFacultyEmail(String facultyEmail) {this.facultyEmail = facultyEmail;}
    @Column(name="F_NUMBER")
    public Long getFacultyNumber() {return facultyNumber;}

    public void setFacultyNumber(Long facultyNumber) {this.facultyNumber = facultyNumber; }
    @Column(name="F_PHOTO")
    public Blob getFacultyPhoto() {return facultyPhoto; }

    public void setFacultyPhoto(Blob facultyPhoto) {this.facultyPhoto = facultyPhoto;}

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "facultyId")
    @SequenceGenerator(name = "facultyId", sequenceName = "facultyId")
    @ManyToMany
    @JoinTable(name="faculty_property", joinColumns = @JoinColumn(name="faculty_Id"), inverseJoinColumns = @JoinColumn(name="property_Id"))
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
