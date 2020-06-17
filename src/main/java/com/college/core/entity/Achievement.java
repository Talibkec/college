package com.college.core.entity;

import javax.persistence.*;

@Entity
@Table(name = "achievement_slide")
public class Achievement {
    private Long achievementSlideId;
    private String achievementCaption;
    private byte[] achievementImage;
    private String fileName;
    private String fileType;
    @Lob
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }

    public String getFileType() { return fileType; }
    public void setFileType(String fileType) { this.fileType = fileType; }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "achievementSlideId", sequenceName = "achievementSlideId")
    public Long getAchievementSlideId() {return achievementSlideId;}

    public void setAchievementSlideId(Long achievementSlideId) {this.achievementSlideId = achievementSlideId;}
    @Column(name="ACHIEVEMENT_CAPTION")
    public String getAchievementCaption() {return achievementCaption; }

    public void setAchievementCaption(String achievementCaption) {this.achievementCaption = achievementCaption; }
    @Column(name = "ACHIEVEMENT_IMAGE", columnDefinition = "mediumblob")
    public byte[] getAchievementImage() { return achievementImage; }

    public void setAchievementImage(byte[] achievementImage) { this.achievementImage = achievementImage; }



}
