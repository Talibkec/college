package com.college.core.model;

import java.sql.Blob;
import java.util.Date;

public class FacultyDocumentsDTO {
    private Long id;
    private String headLine;
    private String uploadedBy;
    private String Uploadfilename;
    private Date date;
    private byte[] document;

    public byte[] getDocument() {
        return document;
    }
    public void setDocument(byte[] document) {
        this.document = document;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getUploadfilename() {
        return Uploadfilename;
    }

    public void setUploadfilename(String uploadfilename) {
        Uploadfilename = uploadfilename;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


}
