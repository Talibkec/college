package com.college.core.entity;


import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Entity
@Table(name="Staff")
public class Staff {
    private Long staffId;

    private String staffName;
    private String staffOfficialEmail;
    private String staffPersonalEmail;
    private Long staffMobNo;
    private String fileType;
    private User user;
//    private List<FacultyKeyProps> facultyKeyProps = new ArrayList<>(0);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "staffId")
    @SequenceGenerator(name = "staffId", sequenceName = "staffId")
    public Long getStaffId() {
        return staffId;
    }

    //Setter and getter for Faculty ID
    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }


    @Column(name="mobile_no")
    public Long getStaffMobNo() {return staffMobNo;}

    public void setStaffMobNo(Long staffMobNo) {this.staffMobNo = staffMobNo; }

    @Lob
    private byte[] staffPhoto;
    @Column(name="photo", columnDefinition="mediumblob")
    public byte[] getStaffPhoto() {
        return staffPhoto;
    }

    public void setStaffPhoto(byte[] staffPhoto) {
        this.staffPhoto = staffPhoto;
    }


    /*-------------------------------------------------------------*/

    //Setter and getter for Faculty Name
    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    @Column(name = "name")

    public String getStaffName() {
        return staffName;
    }
    /*-------------------------------------------------------------*/

    //Setter and getter for Department Id
//    public void setDepartmentId(Long departmentId) {
//        this.departmentId = departmentId;
//    }
//    @Column(name = "dept_Id")
//    public Long getDepartmentId() {
//        return departmentId;
//    }

    /*-------------------------------------------------------------*/
    /*-------------------------------------------------------------*/
    //Setter and Getter for Faculty Official Email
    @Column(name ="official_email")

    public String getStaffOfficialEmail() {
        return staffOfficialEmail;
    }

    public void setStaffOfficialEmail(String staffOfficialEmail) {
        this.staffOfficialEmail = staffOfficialEmail;
    }
    /*--------------------------------------------------------------*/

    /*-------------------------------------------------------------*/
    //Setter and Getter for Faculty Personal Email
    @Column(name="personal_mail")
    public String getStaffPersonalEmail() {return staffPersonalEmail; }

    public void setStaffPersonalEmail(String staffPersonalEmail){this.staffPersonalEmail = staffPersonalEmail; }


    /*-------------------------------------------------------------*/
//    @OneToMany(mappedBy = "staff", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
//    @Fetch(FetchMode.SELECT)
//    @OrderBy("key_props_order")
//    public List<FacultyKeyProps> getFacultyKeyProps() {
//        return facultyKeyProps;
//    }
//
//    public void setFacultyKeyProps(List<FacultyKeyProps> facultyKeyProps) {
//        this.facultyKeyProps = facultyKeyProps;
//    }

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
