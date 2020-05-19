package com.college.core.entity;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name="FACULTY")
public class Faculty {
    private Long facultyId;
    private Long departmentId;
    private String facultyName;
    private String facultyOfficialEmail;
    private String facultyPersonalEmail;
    private Long facultyMobNo;
    private String fileType;
    private User user;
    private Set<FacultyKeyProps> facultyKeyProps = new HashSet<>(0);

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


    @Column(name="F_MNUMBER")
    public Long getFacultyMobNo() {return facultyMobNo;}

    public void setFacultyMobNo(Long facultyMobNo) {this.facultyMobNo = facultyMobNo; }

    @Lob
    private byte[] facultyPhoto;
    @Column(name="f_photo", columnDefinition="mediumblob")
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
    /*-------------------------------------------------------------*/
    //Setter and Getter for Faculty Official Email
    @Column(name ="F_OFFICIALEMAIL")

    public String getFacultyOfficialEmail() {
        return facultyOfficialEmail;
    }

    public void setFacultyOfficialEmail(String facultyOfficialEmail) {
        this.facultyOfficialEmail = facultyOfficialEmail;
    }
    /*--------------------------------------------------------------*/

    /*-------------------------------------------------------------*/
    //Setter and Getter for Faculty Personal Email
    @Column(name="F_PERSONALEMAIL")
    public String getFacultyPersonalEmail() {return facultyPersonalEmail; }

    public void setFacultyPersonalEmail(String facultyPersonalEmail){this.facultyPersonalEmail = facultyPersonalEmail; }


    /*-------------------------------------------------------------*/
    @OneToMany(mappedBy = "faculty", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<FacultyKeyProps> getFacultyKeyProps() {
        return facultyKeyProps;
    }

    public void setFacultyKeyProps(Set<FacultyKeyProps> facultyKeyProps) {
        this.facultyKeyProps = facultyKeyProps;
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
