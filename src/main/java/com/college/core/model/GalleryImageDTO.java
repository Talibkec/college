package com.college.core.model;

import javax.persistence.Lob;

public class GalleryImageDTO {

    private Long galleryImageId;
    private String caption;
    @Lob
    private byte[] image;
    private String fileType;
    private String fileName;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getGalleryImageId() {
        return galleryImageId;
    }

    public void setGalleryImageId(Long galleryImageId) {
        this.galleryImageId = galleryImageId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }




}
