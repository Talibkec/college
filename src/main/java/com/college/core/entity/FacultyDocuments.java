package com.college.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "FACULTYDOCUMENTS")
public class FacultyDocuments {
    private Long id;
    private String headLine;
    private String uploadedBy;
    private String uploadedFileName;
    private Date date;
    private Boolean link;
    private String linkAddress;

    public FacultyDocuments() {

    }

    public FacultyDocuments(Long id, String headLine, String uploadedFileName, Date date, Boolean link, String linkAddress) {
        this.id = id;
        this.headLine = headLine;
        this.uploadedFileName = uploadedFileName;
        this.date = date;
        this.link = link;
        this.linkAddress = linkAddress;
    }

    @Lob
    private byte[] document;

    @Column(name = "F_DOCUMENT", columnDefinition = "mediumblob")
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

    @Column(name = "F_HEADLINE")
    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    @Column(name = "F_UPLOADEDBY")
    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Column(name = "F_UPLOADFILENAME")
    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "F_UPLOADDATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "IS_LINK")
    public Boolean getLink() {
        return link;
    }

    public void setLink(Boolean link) {
        this.link = link;
    }

    @Column(name = "LINK_ADDRESS")
    public String getLinkAddress() {
        return linkAddress;
    }

    public void setLinkAddress(String linkAddress) {
        this.linkAddress = linkAddress;
    }

}
