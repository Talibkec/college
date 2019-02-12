package com.college.core.entity;


import javax.persistence.*;
import java.sql.Blob;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="FACULTY")
public class Faculty {
    private Long facultyId;
    private String facultyName;
    private Long departmentId;
    private String facultyEmail;
    private Long facultyMobNo;
    @Lob
    private byte[] facultyPhoto;
    private String fileType;
    private User user;
    private Set<Property> facultyProperties = new HashSet<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "facultyId")
    @SequenceGenerator(name = "facultyId", sequenceName = "facultyId")
    public Long getFacultyId() {
        return facultyId;
    }

    //Setter and getter for Faculty ID
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    @Column(name="F_EMAIL")
    public String getFacultyEmail() {return facultyEmail;}

    public void setFacultyEmail(String facultyEmail) {this.facultyEmail = facultyEmail;}
    @Column(name="F_MNUMBER")
    public Long getFacultyMobNo() {return facultyMobNo;}

    public void setFacultyMobNo(Long facultyMobNo) {this.facultyMobNo = facultyMobNo; }
    @Column(name="F_PHOTO")
    public byte[] getFacultyPhoto() {
        return facultyPhoto;
    }

    public void setFacultyPhoto(byte[] facultyPhoto) {
        this.facultyPhoto = facultyPhoto;
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

    //Setter and getter for Department Id
    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
    @Column(name = "dept_Id")
    public Long getDepartmentId() {
        return departmentId;
    }

    /*-------------------------------------------------------------*/

    @ManyToMany
    @JoinTable(name="faculty_property", joinColumns = @JoinColumn(name="faculty_Id"), inverseJoinColumns = @JoinColumn(name="property_Id"))
    public Set<Property> getFacultyProperties() {
        return facultyProperties;
    }

    public void setFacultyProperties(Set<Property> facultyProperties) {
        this.facultyProperties = facultyProperties;
    }

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "F_FILETYPE")
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}
