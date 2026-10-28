package com.college.service;

import com.college.core.entity.Faculty;
import com.college.repository.FacultyRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Notices of a faculty member (/uploadNoticeFaculty): always for the notice page of the faculty member's own
 * department (the types the department notice pages and the HOD upload use), never the college-wide notice board.
 */
@Service
public class FacultyNoticeService {
    public static final long MAX_BYTES = 5L * 1024 * 1024;

    /** faculty.dept_id → [notice type prefix, department site code, name]. */
    private static final Map<Long, String[]> DEPARTMENTS = new HashMap<>();

    static {
        DEPARTMENTS.put(1L, new String[]{"CSE", "cse", "Computer Science & Engineering"});
        DEPARTMENTS.put(2L, new String[]{"CIVIL", "civil", "Civil Engineering"});
        DEPARTMENTS.put(3L, new String[]{"MECH", "mechanical", "Mechanical Engineering"});
        DEPARTMENTS.put(4L, new String[]{"ELEC", "eee", "Electrical & Electronics Engineering"});
        DEPARTMENTS.put(5L, new String[]{"ASH", "ash", "Applied Science & Humanities"});
        DEPARTMENTS.put(6L, new String[]{"ELEC", "ece", "Electronics & Communication Engineering"});
        DEPARTMENTS.put(7L, new String[]{"MECH", "mechandsmartmanufacturing", "Mechanical & Smart Manufacturing"});
    }

    private final FacultyRepository facultyRepository;
    private final UploadService uploadService;

    public FacultyNoticeService(FacultyRepository facultyRepository, UploadService uploadService) {
        this.facultyRepository = facultyRepository;
        this.uploadService = uploadService;
    }

    /** [notice type prefix, site code, name] of the user's department, or null (no faculty record / unknown department). */
    public String[] department(String userName) {
        Faculty faculty = userName == null ? null : facultyRepository.findFacltyByUserName(userName);
        return faculty == null || faculty.getDepartmentId() == null ? null : DEPARTMENTS.get(faculty.getDepartmentId());
    }

    /** Saves the notice; returns the error message or null. */
    public String save(String userName, String header, boolean news, boolean scrollable, String date, MultipartFile file) {
        String[] department = department(userName);
        if (department == null) return "Your account has no department; please ask the Admin.";
        if (header == null || header.trim().isEmpty()) return "Please enter a headline.";
        if (header.trim().length() > 255) return "The headline may have at most 255 characters.";
        if (file == null || file.isEmpty()) return "Please choose the PDF file.";
        if (file.getSize() > MAX_BYTES) return "The file is larger than 5 MB.";
        try {
            byte[] head = file.getInputStream().readNBytes(5);
            if (!"%PDF-".equals(new String(head, java.nio.charset.StandardCharsets.US_ASCII))) {
                return "Only PDF files can be uploaded as notices.";
            }
        } catch (IOException e) {
            return "The file could not be read.";
        }
        uploadService.saveNotice(userName, header.trim(), department[0] + (news ? "News" : "Notice"), null, scrollable, file, date);
        return null;
    }
}
