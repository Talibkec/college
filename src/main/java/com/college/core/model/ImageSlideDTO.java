package com.college.core.model;

import javax.persistence.Lob;

public class ImageSlideDTO {
    private Long imageSlideId;
    private String caption;
    @Lob
    private byte[] image;

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    private String fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;

    public Long getImageSlideId() {
        return imageSlideId;
    }

    public void setImageSlideId(Long imageSlideId) {
        this.imageSlideId = imageSlideId;
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
