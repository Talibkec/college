package com.college.core.model;

import javax.persistence.Lob;

public class AchievementDTO {
    private Long achievementSlideId;
    private String achievementCaption;
    private String fileName;
    private String fileType;
    @Lob
    private byte[] achievementImage;

    private int displayFlag;

    // Getter and setter
    public int getDisplayFlag() {
        return displayFlag;
    }

    public void setDisplayFlag(int displayFlag) {
        this.displayFlag = displayFlag;
    }
    public Long getAchievementSlideId() {
        return achievementSlideId;
    }

    public void setAchievementSlideId(Long achievementSlideId) {
        this.achievementSlideId = achievementSlideId;
    }

    public String getAchievementCaption() {
        return achievementCaption;
    }

    public void setAchievementCaption(String achievementCaption) {
        this.achievementCaption = achievementCaption;
    }

    public byte[] getAchievementImage() {
        return achievementImage;
    }

    public void setAchievementImage(byte[] achievementImage) {
        this.achievementImage = achievementImage;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }


}
