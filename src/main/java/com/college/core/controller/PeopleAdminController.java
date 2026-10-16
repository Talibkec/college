package com.college.core.controller;

import com.college.core.model.PlacementsDTO;
import com.college.core.model.StaffDTO;
import com.college.core.model.TpoDTO;
import com.college.service.FacultyProfileService;
import com.college.service.PlacementsService;
import com.college.service.StaffService;
import com.college.service.TpoCoordinatorService;
import com.college.service.UploadService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin pages that add faculty, staff, placed students and TPO student coordinators.
 * (Moved unchanged out of store/CommonResourceController.)
 */
@Controller
public class PeopleAdminController {

    private final FacultyProfileService facultyProfileService;
    private final StaffService staffService;
    private final PlacementsService placementsService;
    private final TpoCoordinatorService tpoCoordinatorService;

    public PeopleAdminController(FacultyProfileService facultyProfileService,
            StaffService staffService,
            PlacementsService placementsService,
            TpoCoordinatorService tpoCoordinatorService) {
        this.facultyProfileService = facultyProfileService;
        this.staffService = staffService;
        this.placementsService = placementsService;
        this.tpoCoordinatorService = tpoCoordinatorService;
    }

    @RequestMapping(value = "/auth/uploadfile/addfaculty", method = RequestMethod.GET)
    public ModelAndView addFaculty() {
        return formPage("addfaculty.jsp");
    }

    @RequestMapping(value = "/auth/uploadfile/addstaff", method = RequestMethod.GET)
    public ModelAndView addStaff() {
        return formPage("addstaff.jsp");
    }

    @RequestMapping(value = "/auth/uploadfile/addtpocoordinator", method = RequestMethod.GET)
    public ModelAndView tpoCoordinator() {
        return formPage("addtpocoordinator.jsp");
    }

    @RequestMapping(value = "/auth/uploadfile/addPlacements", method = RequestMethod.GET)
    public ModelAndView addPlacements() {
        return formPage("addPlacements.jsp");
    }

    private static ModelAndView formPage(String view) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", "No any message for now");
        mv.setViewName(view);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/addfaculty", method = RequestMethod.POST)
    public String addFaculty(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("deptId") Long deptId,
            @RequestParam("userName") String username) {
        return facultyProfileService.addFaculty(facultyPersonalEmail, facultyOfficialEmail, facultyMobNo, facultyName,
                deptId, username);
    }

    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/addstaff", method = RequestMethod.POST)
    public String addStaff(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("slideImage") MultipartFile uploadfile) {
        if (uploadfile.isEmpty()) {
            return "Please select a file.";
        }
        StaffDTO staffDTO = new StaffDTO();
        staffDTO.setFileType(FilenameUtils.getExtension(uploadfile.getOriginalFilename()));
        staffDTO.setStaffPhoto(UploadService.readBytes(uploadfile, "staff photo"));
        staffDTO.setStaffOfficialEmail(facultyOfficialEmail);
        staffDTO.setStaffPersonalEmail(facultyPersonalEmail);
        staffDTO.setStaffMobNo(facultyMobNo);
        staffDTO.setStaffName(facultyName);
        staffService.saveStaff(staffDTO);
        return "Staff added successfully  ";
    }

    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/addPlacements", method = RequestMethod.POST)
    public String addPlacements(
            @RequestParam("studentName") String studentName,
            @RequestParam("companyName") String companyName,
            @RequestParam("studentSession") String studentSession,
            @RequestParam("deptId") Long deptId,
            @RequestParam("registrationNo") Long registrationNo) {
        PlacementsDTO placementsDTO = new PlacementsDTO();
        placementsDTO.setStudentName(studentName);
        placementsDTO.setCompanyName(companyName);
        placementsDTO.setStudentSession(studentSession);
        placementsDTO.setReigstrationNo(registrationNo);
        placementsDTO.setDepartmentId(deptId);
        placementsService.savePlacements(placementsDTO);
        return "Placement added successfully  ";
    }

    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/addtpocoordinator", method = RequestMethod.POST)
    public String addTpoCoordinator(
            @RequestParam("studentName") String studentName,
            @RequestParam("mobile") Long mobile,
            @RequestParam("email") String email,
            @RequestParam("studentSession") String studentSession,
            @RequestParam("deptId") Long deptId,
            @RequestParam("registrationNo") Long registrationNo) {
        TpoDTO tpoDTO = new TpoDTO();
        tpoDTO.setStudentName(studentName);
        tpoDTO.setMobile(mobile);
        tpoDTO.setEmail(email);
        tpoDTO.setStudentSession(studentSession);
        tpoDTO.setRegistrationNo(registrationNo);
        tpoDTO.setDepartmentId(deptId);
        tpoCoordinatorService.saveTpoCoordinator(tpoDTO);
        return "TPO added successfully  ";
    }

    @RequestMapping(value = "/auth/tpoCoordinator/{id}", method = RequestMethod.GET)
    public void tpoCoordinatorDelete(@PathVariable("id") Long tpoCoordinatorId, HttpServletResponse res) throws IOException {
        tpoCoordinatorService.deleteTpoCoordinator(tpoCoordinatorId);
        res.sendRedirect("/");
    }
}
