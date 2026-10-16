package com.college.core.model;

/** One photo of the static photo gallery (file under /wp-content/uploads/2018/ and its caption). */
public class GalleryPhotoDTO {

    private final String fileName;
    private final String caption;

    public GalleryPhotoDTO(String fileName, String caption) {
        this.fileName = fileName;
        this.caption = caption;
    }

    public String getFileName() {
        return fileName;
    }

    public String getCaption() {
        return caption;
    }
}
