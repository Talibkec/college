package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.entity.ContentFile;
import com.college.core.entity.CourseFile;
import com.college.core.entity.DepartmentSite;
import com.college.core.tags.FileTypes;
import com.college.repository.ContentFileRepository;
import com.college.repository.CourseFileRepository;
import com.college.repository.DepartmentSiteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Course files of the department sites (table {@code course_file}, decision 2026-10-31): shown on
 * {@code /department/<site>/course}; Admin manages all departments, an HOD only the own one, i.e. when the HOD may edit
 * that department's pages (department_site.hod_username or the HOD's own faculty department), like the lab documents.
 */
@Service
public class CourseFileService {
    private static final Logger logger = LoggerFactory.getLogger(CourseFileService.class);
    static final String ENTITY = "course_file";
    static final long MAX_FILE_SIZE = 20L * 1024 * 1024;
    public static final int SEMESTERS = 8;
    /** The department sites, as in DepartmentController. */
    public static final List<String> SITES = Arrays.asList("cse", "civil", "mechanical", "ece", "eee", "ecevlsi", "ash",
            "fp", "manufacturing", "mechandsmartmanufacturing");

    private final CourseFileRepository courseFileRepository;
    private final ContentFileRepository contentFileRepository;
    private final DepartmentSiteRepository departmentSiteRepository;
    private final PageBlockService pageBlockService;
    private final ContentChangeLogService contentChangeLogService;

    public CourseFileService(CourseFileRepository courseFileRepository, ContentFileRepository contentFileRepository,
                             DepartmentSiteRepository departmentSiteRepository, PageBlockService pageBlockService,
                             ContentChangeLogService contentChangeLogService) {
        this.courseFileRepository = courseFileRepository;
        this.contentFileRepository = contentFileRepository;
        this.departmentSiteRepository = departmentSiteRepository;
        this.pageBlockService = pageBlockService;
        this.contentChangeLogService = contentChangeLogService;
    }

    public boolean canManage(Authentication authentication, String site) {
        if (authentication == null || site == null || !SITES.contains(site)) return false;
        if (has(authentication, "Admin")) return true;
        return has(authentication, "HOD") && "E".equals(pageBlockService.access(authentication, "department." + site + ".course"));
    }

    /** The departments whose course files the user may manage, in the order of the department list. */
    public List<String> manageableSites(Authentication authentication) {
        List<String> sites = new ArrayList<>();
        for (String site : SITES) {
            if (canManage(authentication, site)) sites.add(site);
        }
        return sites;
    }

    /** site code -> full name ("Electrical & Electronics Engg."), for the screens. */
    public Map<String, String> siteNames() {
        Map<String, String> names = new LinkedHashMap<>();
        try {
            for (DepartmentSite site : departmentSiteRepository.findAllByOrderBySortOrderAsc()) {
                names.put(site.getSiteCode(), site.getFullName() + " (" + site.getShortName() + ")");
            }
        } catch (DataAccessException e) {
            logger.warn("department_site not readable: {}", e.getMostSpecificCause().getMessage());
        }
        for (String site : SITES) {
            names.putIfAbsent(site, site);
        }
        return names;
    }

    /** Visible course files of a department by semester (only semesters that have files); empty if the table is missing. */
    public Map<Integer, List<CourseFile>> visibleBySemester(String site) {
        Map<Integer, List<CourseFile>> semesters = new TreeMap<>();
        try {
            for (CourseFile file : allFiles(site)) {
                if (file.isVisible()) semesters.computeIfAbsent(file.getSemester(), k -> new ArrayList<>()).add(file);
            }
        } catch (DataAccessException e) {
            logger.warn("course_file not readable, course pages show no files: {}", e.getMostSpecificCause().getMessage());
        }
        return semesters;
    }

    /** All course files of a department (also hidden ones), with file name/type/size. */
    public List<CourseFile> allFiles(String site) {
        List<CourseFile> files = courseFileRepository.findBySiteCodeOrderBySemesterAscSubjectCodeAscSubjectNameAsc(site);
        Set<Long> ids = new HashSet<>();
        for (CourseFile file : files) {
            ids.add(file.getFileId());
        }
        if (ids.isEmpty()) return files;
        Map<Long, Object[]> info = new HashMap<>();
        for (Object[] row : contentFileRepository.findInfo(ids)) {
            info.put((Long) row[0], row);
        }
        for (CourseFile file : files) {
            Object[] row = info.get(file.getFileId());
            if (row != null) {
                file.setFileName((String) row[1]);
                file.setFileType((String) row[2]);
                file.setFileSize((Long) row[3]);
            }
        }
        return files;
    }

    public CourseFile courseFile(Long id) {
        return id == null ? null : courseFileRepository.findById(id).orElse(null);
    }

    /**
     * Creates ({@code id} null) or changes a course file of {@code site}. {@code fileData} may be null when changing
     * (keeps the file). Returns a message for the HOD if the input is not valid; nothing is saved then.
     */
    public String save(String site, Long id, Integer semester, String subjectCode, String subjectName, String facultyName,
                       String academicSession, boolean visible, String fileName, byte[] fileData) {
        CourseFile courseFile = id == null ? new CourseFile() : courseFile(id);
        if (courseFile == null || (id != null && !courseFile.getSiteCode().equals(site))) {
            return "This course file does not exist any more.";
        }
        String name = trim(subjectName);
        String code = trim(subjectCode);
        String faculty = trim(facultyName);
        String session = trim(academicSession);
        boolean hasFile = fileData != null && fileData.length > 0;
        if (semester == null || semester < 1 || semester > SEMESTERS) return "Please choose the semester.";
        if (name == null) return "Please enter the subject name.";
        if (name.length() > 255) return "The subject name may have at most 255 characters.";
        if (code != null && code.length() > 30) return "The subject code may have at most 30 characters.";
        if (faculty != null && faculty.length() > 255) return "The faculty name may have at most 255 characters.";
        if (session != null && session.length() > 20) return "The session may have at most 20 characters (e.g. 2025-26).";
        if (!hasFile && courseFile.getFileId() == null) return "Please choose the course file (PDF, Word, Excel or PowerPoint).";
        String fileType = null;
        if (hasFile && (fileType = FileTypes.document(fileData, fileName)) == null) {
            return "Only PDF and Word/Excel/PowerPoint files (.doc, .docx, .xls, .xlsx, .ppt, .pptx) can be uploaded.";
        }
        if (hasFile && fileData.length > MAX_FILE_SIZE) return "The file may be at most 20 MB.";

        String before = id == null ? null : describe(courseFile);
        String user = ControllerUtility.getPrincipalName();
        Long oldFileId = courseFile.getFileId();
        if (hasFile) {
            ContentFile file = new ContentFile();
            file.setAreaCode("department." + site);
            file.setFileName(FileTypes.cleanName(fileName, "course-file"));
            file.setContentType(fileType);
            file.setFileSize(fileData.length);
            file.setData(fileData);
            file.setUploadedBy(user);
            file.setUploadedAt(new Date());
            courseFile.setFileId(contentFileRepository.save(file).getFileId());
        }
        if (id == null) {
            courseFile.setSiteCode(site);
            courseFile.setCreatedBy(user);
            courseFile.setCreatedAt(new Date());
        }
        courseFile.setSemester(semester);
        courseFile.setSubjectCode(code);
        courseFile.setSubjectName(name);
        courseFile.setFacultyName(faculty);
        courseFile.setAcademicSession(session);
        courseFile.setVisible(visible);
        courseFile.setUpdatedBy(user);
        courseFile.setUpdatedAt(new Date());
        CourseFile saved = courseFileRepository.save(courseFile);
        if (hasFile && oldFileId != null) {
            deleteFileIfUnused(oldFileId);
        }
        contentChangeLogService.record("department." + site, ENTITY, String.valueOf(saved.getCourseFileId()),
                id == null ? "create" : "update", before, describe(saved));
        return null;
    }

    public void delete(CourseFile courseFile) {
        courseFileRepository.delete(courseFile);
        deleteFileIfUnused(courseFile.getFileId());
        contentChangeLogService.record("department." + courseFile.getSiteCode(), ENTITY,
                String.valueOf(courseFile.getCourseFileId()), "delete", describe(courseFile), null);
    }

    /** The file of a course file if this user may see it (visible, or the user manages the department), else null. */
    public ContentFile servableFile(Authentication authentication, Long courseFileId) {
        CourseFile courseFile = courseFile(courseFileId);
        if (courseFile == null || !(courseFile.isVisible() || canManage(authentication, courseFile.getSiteCode()))) {
            return null;
        }
        return contentFileRepository.findById(courseFile.getFileId()).orElse(null);
    }

    private void deleteFileIfUnused(Long fileId) {
        if (fileId != null && courseFileRepository.countByFileId(fileId) == 0) {
            contentFileRepository.deleteById(fileId);
        }
    }

    private static String trim(String value) {
        return value == null || value.trim().isEmpty() ? null : value.trim();
    }

    private static boolean has(Authentication authentication, String role) {
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (role.equals(authority.getAuthority())) return true;
        }
        return false;
    }

    private static String describe(CourseFile f) {
        return "semester " + f.getSemester() + ": " + (f.getSubjectCode() == null ? "" : f.getSubjectCode() + " ")
                + f.getSubjectName() + (f.getFacultyName() == null ? "" : ", " + f.getFacultyName())
                + (f.getAcademicSession() == null ? "" : ", " + f.getAcademicSession())
                + " [file " + f.getFileId() + "]" + (f.isVisible() ? "" : " (hidden)");
    }
}
