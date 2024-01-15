package com.college.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name="gallery_image")
public class GalleryImage {

    private Long galleryImageId;
    private String caption;
    private byte[] image;
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    private  String fileType;
    @Lob


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "galleryImageId", sequenceName = "galleryImageId")
    public Long getGalleryImageId() {
        return galleryImageId;
    }

    public void setGalleryImageId(Long galleryImageId) {
        this.galleryImageId = galleryImageId;
    }


    @Column(name = "CAPTION")
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
    @Column(name = "IMAGE", columnDefinition = "mediumblob")
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
