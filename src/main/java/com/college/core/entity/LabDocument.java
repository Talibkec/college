package com.college.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class LabDocument {
    private long id;
    private String fileName;
    private String uploadedBy;
    private Date date;
    private String fileType;
    private Integer depId;
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Lob
    private byte[] uploadedFile;
    @Column(name = "DN_FILE", columnDefinition = "mediumblob")
    public byte[] getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(byte[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }


    public LabDocument(long id, String fileName, String uploadedBy, Date date , String fileType) {
        this.id = id;
        this.fileName = fileName;
        this.uploadedBy = uploadedBy;
        this.date = date;
        this.fileType=fileType;
    }

    public LabDocument() {
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDepId() {
        return depId;
    }

    public void setDepId(Integer depId) {
        this.depId = depId;
    }
}
