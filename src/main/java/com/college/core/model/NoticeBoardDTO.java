package com.college.core.model;

import java.util.Date;

public class NoticeBoardDTO {
    private Long id;
    private String uploadedBy;
    private String headLine;
    private String noticeType;
    private String uploadedFileName;
    private Date date;
    private Integer isScrollable;
    private String fileType;
    private byte[] notice;

    public String getFileType() {return fileType;}

    public void setFileType(String fileType) { this.fileType = fileType; }



    public byte[] getNotice() { return notice; }

    public void setNotice(byte[] notice) { this.notice = notice; }



    private Integer noticeAge;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }

    public Integer getIsScrollable() {
        return isScrollable;
    }

    public void setIsScrollable(Integer isScrollable) {
        this.isScrollable = isScrollable;
    }



    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
