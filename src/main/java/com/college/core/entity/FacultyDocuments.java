package com.college.core.entity;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name="FACULTYDOCUMENTS")
public class FacultyDocuments {
    private Long id;
    private String headLine;
    private String uploadedBy;
    private String Uploadfilename;
    private Date date;
    @Lob
    private byte[] document;
    @Column(name="F_DOCUMENT")
    public byte[] getDocument() {
        return document;
    }
    public void setDocument(byte[] document) {
        this.document = document;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @Column(name="F_HEADLINE")
    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }
    @Column(name="F_UPLOADEDBY")
    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }
    @Column(name="F_UPLOADFILENAME")
    public String getUploadfilename() {
        return Uploadfilename;
    }

    public void setUploadfilename(String uploadfilename) {
        Uploadfilename = uploadfilename;
    }
    @Temporal(TemporalType.DATE)
    @Column(name ="F_UPLOADDATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
