package com.college.core.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "aicte_documents")
public class AicteDocuments {

    @Id
    @SequenceGenerator(name = "aicteDocumentsId", sequenceName = "aicteDocumentsId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    @Lob
    private byte[] image;
    private String fileName;
    private String fileType;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }



    @Column(columnDefinition="mediumblob")
    public byte[] getImage() {
        return image;
    }


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


    public Long getId() {
        return id;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }


    public void setId(Long id) {
        this.id = id;
    }


}
