package com.college.core.entity;

import jakarta.persistence.*;
import java.util.Date;

/** The course file of one subject of a department site (table {@code course_file}); the file is a {@link ContentFile}. */
@Entity
@Table(name = "course_file")
public class CourseFile {
    private Long courseFileId;
    private String siteCode;
    private int semester;
    private String subjectCode;
    private String subjectName;
    private String facultyName;
    private String academicSession;
    private Long fileId;
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
    @Column(name = "course_file_id")
    public Long getCourseFileId() {
        return courseFileId;
    }

    public void setCourseFileId(Long courseFileId) {
        this.courseFileId = courseFileId;
    }

    @Column(name = "site_code")
    public String getSiteCode() {
        return siteCode;
    }

    public void setSiteCode(String siteCode) {
        this.siteCode = siteCode;
    }

    @Column(name = "semester")
    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Column(name = "subject_code")
    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    @Column(name = "subject_name")
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Column(name = "faculty_name")
    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    @Column(name = "academic_session")
    public String getAcademicSession() {
        return academicSession;
    }

    public void setAcademicSession(String academicSession) {
        this.academicSession = academicSession;
    }

    @Column(name = "file_id")
    public Long getFileId() {
        return fileId;
    }

    public void setFileId(Long fileId) {
        this.fileId = fileId;
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

    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /** Name, type and size of the file (filled in by the service, not a column). */
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

    @Transient
    public boolean isPdf() {
        return "application/pdf".equals(fileType);
    }

    /** "pdf", "word", "excel" or "powerpoint" (icon and colour on the page). */
    @Transient
    public String getFileKind() {
        if (fileType == null || isPdf()) return "pdf";
        if (fileType.contains("word")) return "word";
        if (fileType.contains("sheet") || fileType.contains("excel")) return "excel";
        return "powerpoint";
    }

    /** "PDF", "DOCX", ... from the file name. */
    @Transient
    public String getFileExtension() {
        if (fileName == null || fileName.lastIndexOf('.') < 0) return "";
        return fileName.substring(fileName.lastIndexOf('.') + 1).toUpperCase(java.util.Locale.ROOT);
    }

    /** URL of the file, e.g. /course-file/12/EE301_Course_File.pdf. */
    @Transient
    public String getFileUrl() {
        if (courseFileId == null) return null;
        return "/course-file/" + courseFileId + (fileName == null ? "" : "/" + fileName.replace(' ', '_'));
    }

    /** File size for people: "830 KB", "2.4 MB". */
    @Transient
    public String getFileSizeText() {
        if (fileSize >= 1024 * 1024) return String.format("%.1f MB", fileSize / (1024.0 * 1024.0));
        return Math.max(1, fileSize / 1024) + " KB";
    }
}
