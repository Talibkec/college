package com.college.core.model;

import javax.persistence.Lob;
import java.util.Date;

public class DownloadDTO {
    private long id;
    private String fileName;
    private String fileType;

    public DownloadDTO(long id, String fileName, String uploadedBy, Date date, byte[] uploadedFile) {
        this.id = id;
        this.fileName = fileName;
        this.uploadedBy = uploadedBy;
        this.date = date;
        this.uploadedFile = uploadedFile;
    }

    public DownloadDTO() {
    }

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

    public byte[] getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(byte[] uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    private String uploadedBy;
    private Date date;
    private byte[] uploadedFile;

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileType() {
        return fileType;
    }
}