package com.college.core.entity;

import jakarta.persistence.*;
import java.util.Date;

/** A heading, subheading, text, image or document that an editor added to a page (table {@code page_block}). */
@Entity
@Table(name = "page_block")
public class PageBlock {
    public static final String HEADING = "heading";
    public static final String SUBHEADING = "subheading";
    public static final String TEXT = "text";
    public static final String IMAGE = "image";
    public static final String DOCUMENT = "document";

    private Long blockId;
    private String pageKey;
    private String areaCode;
    private String blockType;
    private String heading;
    private String bodyHtml;
    private Long fileId;
    private String display;
    private int sortOrder;
    private boolean visible = true;
    private String createdBy;
    private Date createdAt;
    private String updatedBy;
    private Date updatedAt;
    private String fileName;
    private String fileType;
    private long fileSize;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "block_id")
    public Long getBlockId() {
        return blockId;
    }

    public void setBlockId(Long blockId) {
        this.blockId = blockId;
    }

    @Column(name = "page_key")
    public String getPageKey() {
        return pageKey;
    }

    public void setPageKey(String pageKey) {
        this.pageKey = pageKey;
    }

    @Column(name = "area_code")
    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    @Column(name = "block_type")
    public String getBlockType() {
        return blockType;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    /** Heading text, image caption or document title. */
    @Column(name = "heading")
    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    @Column(name = "body_html")
    public String getBodyHtml() {
        return bodyHtml;
    }

    public void setBodyHtml(String bodyHtml) {
        this.bodyHtml = bodyHtml;
    }

    @Column(name = "file_id")
    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /** image: small | medium | full; document: embed | link. */
    @Column(name = "display")
    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    @Column(name = "sort_order")
    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Column(name = "visible")
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Column(name = "created_by")
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Column(name = "updated_by")
    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /** Name of the attached file (filled by PageBlockService, not mapped). */
    @Transient
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Transient
    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    @Transient
    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    /** True for PDFs (shown inside the page); Office files are offered as a download link. */
    @Transient
    public boolean isPdf() {
        return "application/pdf".equals(fileType);
    }

    /** URL of the attached file, e.g. /content-file/12/Brochure.pdf. */
    @Transient
    public String getFileUrl() {
        if (fileId == null) return null;
        return "/content-file/" + fileId + (fileName == null ? "" : "/" + fileName.replace(' ', '_'));
    }

    /** File size for people: "830 KB", "2.4 MB". */
    @Transient
    public String getFileSizeText() {
        if (fileSize >= 1024 * 1024) return String.format("%.1f MB", fileSize / (1024.0 * 1024.0));
        return Math.max(1, fileSize / 1024) + " KB";
    }
}
