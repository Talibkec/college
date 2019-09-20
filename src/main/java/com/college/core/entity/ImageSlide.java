package com.college.core.entity;

import javax.persistence.*;

@Entity
@Table(name="ImageSlide")
public class ImageSlide {
    private Long imageSlideId;
    private String caption;
    @Lob
    private byte[] image;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    private String fileName;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getImageSlideId() {
        return imageSlideId;
    }

    public void setImageSlideId(Long imageSlideId) {
        this.imageSlideId = imageSlideId;
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
