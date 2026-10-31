package com.college.core.controller;

import com.college.core.entity.ContentFile;
import com.college.core.entity.CourseFile;
import com.college.core.tags.FileTypes;
import com.college.service.CourseFileService;
import com.college.service.PageBlockService;
import org.springframework.http.CacheControl;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * Course files of the department sites: the public page {@code /department/<site>/course} (editable paragraph, then the
 * files by semester), the HOD's screen {@code /manage/course-files} and the files {@code /course-file/{id}/{name}}.
 */
@Controller
public class CourseFileController {
    private static final String ALL_DEPTS =
            "cse|civil|mechanical|ece|eee|ecevlsi|ash|fp|manufacturing|mechandsmartmanufacturing";

    private final CourseFileService courseFileService;
    private final PageBlockService pageBlockService;

    public CourseFileController(CourseFileService courseFileService, PageBlockService pageBlockService) {
        this.courseFileService = courseFileService;
        this.pageBlockService = pageBlockService;
    }

    /** Any method, like the other static department pages before. */
    @RequestMapping("/department/{dept:" + ALL_DEPTS + "}/course")
    public ModelAndView page(@PathVariable("dept") String dept, Authentication authentication) {
        ModelAndView mv = new ModelAndView("department/" + dept + "/course.jsp");
        Map<Integer, List<CourseFile>> semesters = courseFileService.visibleBySemester(dept);
        int count = 0;
        for (List<CourseFile> files : semesters.values()) {
            count += files.size();
        }
        mv.addObject("courseSite", dept);
        mv.addObject("courseSemesters", semesters);
        mv.addObject("courseFileCount", count);
        mv.addObject("courseCanManage", courseFileService.canManage(authentication, dept));
        mv.addObject("courseTextEditor", pageBlockService.access(authentication, "department." + dept + ".course") != null);
        return mv;
    }

    /** Without {@code dept}: the departments to choose from (or straight to the only one). */
    @GetMapping("/manage/course-files")
    public ModelAndView manage(Authentication authentication, HttpServletResponse response,
                               @RequestParam(value = "dept", required = false) String dept,
                               @RequestParam(value = "edit", required = false) Long edit,
                               @RequestParam(value = "saved", required = false) String saved) throws IOException {
        List<String> sites = courseFileService.manageableSites(authentication);
        if (sites.isEmpty() || (dept != null && !sites.contains(dept))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        if (dept == null && sites.size() == 1) {
            return new ModelAndView("redirect:/manage/course-files?dept=" + sites.get(0));
        }
        CourseFile editFile = null;
        if (dept != null && edit != null) {
            editFile = courseFileService.courseFile(edit);
            if (editFile == null || !editFile.getSiteCode().equals(dept)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN);
                return null;
            }
        }
        return view(dept, sites, editFile, null, saved != null);
    }

    @PostMapping("/manage/course-files/save")
    public ModelAndView save(Authentication authentication, HttpServletResponse response,
                             @RequestParam("dept") String dept,
                             @RequestParam(value = "id", required = false) Long id,
                             @RequestParam(value = "semester", required = false) Integer semester,
                             @RequestParam(value = "subjectCode", required = false) String subjectCode,
                             @RequestParam(value = "subjectName", required = false) String subjectName,
                             @RequestParam(value = "facultyName", required = false) String facultyName,
                             @RequestParam(value = "academicSession", required = false) String academicSession,
                             @RequestParam(value = "visible", required = false) String visible,
                             @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
        CourseFile existing = courseFileService.courseFile(id);
        if (!courseFileService.canManage(authentication, dept)
                || (id != null && (existing == null || !existing.getSiteCode().equals(dept)))) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        boolean hasFile = file != null && !file.isEmpty();
        String error = courseFileService.save(dept, id, semester, subjectCode, subjectName, facultyName, academicSession,
                visible != null, hasFile ? file.getOriginalFilename() : null, hasFile ? file.getBytes() : null);
        if (error != null) {
            CourseFile entered = new CourseFile(); // only for showing the form again; the stored row is not touched
            if (existing != null) {
                entered.setCourseFileId(existing.getCourseFileId());
                entered.setFileId(existing.getFileId());
            }
            entered.setSemester(semester == null ? 0 : semester);
            entered.setSubjectCode(subjectCode);
            entered.setSubjectName(subjectName);
            entered.setFacultyName(facultyName);
            entered.setAcademicSession(academicSession);
            entered.setVisible(visible != null);
            return view(dept, courseFileService.manageableSites(authentication), entered, error, false);
        }
        return new ModelAndView("redirect:/manage/course-files?saved=1&dept=" + dept);
    }

    @PostMapping("/manage/course-files/delete")
    public String delete(Authentication authentication, HttpServletResponse response, @RequestParam("dept") String dept,
                         @RequestParam("id") Long id) throws IOException {
        CourseFile courseFile = courseFileService.courseFile(id);
        if (courseFile == null || !courseFile.getSiteCode().equals(dept) || !courseFileService.canManage(authentication, dept)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN);
            return null;
        }
        courseFileService.delete(courseFile);
        return "redirect:/manage/course-files?saved=1&dept=" + dept;
    }

    @GetMapping({"/course-file/{id}", "/course-file/{id}/{name}"})
    public ResponseEntity<byte[]> file(@PathVariable("id") Long id, Authentication authentication) {
        ContentFile file = courseFileService.servableFile(authentication, id);
        if (file == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        boolean inline = FileTypes.inline(file.getContentType());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(file.getContentType()));
        headers.setContentDisposition((inline ? ContentDisposition.inline() : ContentDisposition.attachment())
                .filename(file.getFileName(), StandardCharsets.UTF_8).build());
        headers.set("X-Content-Type-Options", "nosniff");
        // the HOD may replace the file of a course file under the same id: always ask the server again
        headers.setCacheControl(CacheControl.noCache());
        return new ResponseEntity<>(file.getData(), headers, HttpStatus.OK);
    }

    private ModelAndView view(String dept, List<String> sites, CourseFile editFile, String error, boolean saved) {
        ModelAndView mv = new ModelAndView("manage/courseFiles.jsp");
        mv.addObject("sites", sites);
        mv.addObject("siteNames", courseFileService.siteNames());
        mv.addObject("dept", dept);
        if (dept != null) {
            mv.addObject("files", courseFileService.allFiles(dept));
        }
        mv.addObject("editFile", editFile);
        mv.addObject("semesterCount", CourseFileService.SEMESTERS);
        mv.addObject("error", error);
        mv.addObject("saved", saved);
        return mv;
    }
}
