package com.college.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NOTICEBOARD")
public class NoticeBoard {
    private Long id;
    private String uploadedBy;
    private String headLine;
    private String noticeType;
    private String uploadedFileName;
    private Date date;
    private Integer isScrollable;

    public NoticeBoard() {

    }

    public NoticeBoard(Long id, String headLine, String noticeType, String uploadedFileName, Date date, Integer isScrollable) {
        this.id = id;
        this.headLine = headLine;
        this.noticeType = noticeType;
        this.uploadedFileName = uploadedFileName;
        this.date = date;
        this.isScrollable = isScrollable;
    }

    @Lob
    private byte[] notice;

    @Column(name = "NB_FILE", columnDefinition = "mediumblob")
    public byte[] getNotice() {
        return notice;
    }

    public void setNotice(byte[] notice) {
        this.notice = notice;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @Column(name = "NB_USERNAME")
    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    @Column(name = "NB_HEADLINE")
    public String getHeadLine() {
        return headLine;
    }

    public void setHeadLine(String headLine) {
        this.headLine = headLine;
    }

    @Column(name = "NB_NOTICETYPE")
    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    @Column(name = "NB_FILENAME")
    public String getUploadedFileName() {
        return uploadedFileName;
    }

    public void setUploadedFileName(String uploadedFileName) {
        this.uploadedFileName = uploadedFileName;
    }


    @Temporal(TemporalType.DATE)
    @Column(name = "NB_UPLOADDATE")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "NB_SCROLLABLE")
    public Integer getIsScrollable() {
        return isScrollable;
    }

    public void setIsScrollable(Integer isScrollable) {
        this.isScrollable = isScrollable;
    }

}
