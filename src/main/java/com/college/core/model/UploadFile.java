package com.college.core.model;

import org.springframework.web.multipart.MultipartFile;

public class UploadFile {

    private String noticeHeadLines;
    private MultipartFile[] files;

    public String getNoticeHeadLines() {
        return noticeHeadLines;
    }

    public void setNoticeHeadLines(String noticeHeadLines) {
        this.noticeHeadLines = noticeHeadLines;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

}
