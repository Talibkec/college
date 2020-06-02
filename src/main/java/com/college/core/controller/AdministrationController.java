package com.college.core.controller;

import com.college.core.entity.Faculty;
import com.college.core.model.AdministrationDTO;
import com.college.core.model.FacultyDTO;
import com.college.service.AdministrationService;
import com.college.service.FacultyService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public  class AdministrationController {
    @Autowired

    AdministrationService administrationService;
    @Autowired
    FacultyService facultyService;
    private final Logger logger = LoggerFactory.getLogger(FileUploadCintroller.class);
    @ResponseBody
    @RequestMapping(value = "/api/uploadAdministration", method = RequestMethod.POST)
    public ResponseEntity<?> uploadAdministration(
            @RequestParam("facultyEmail") String facultyEmail,
            @RequestParam("updateDutyAssigned") String updateDutyAssigned, @RequestParam("roleValue") String roleValue) {
        AdministrationDTO administrationDTO = new AdministrationDTO();
        // administrationDTO.getFaculty().setFacultyName(facultyName);
        administrationDTO.setDutyAssigned(updateDutyAssigned);
        administrationDTO.setRole(roleValue);
        Faculty faculty = facultyService.searchByEmail(facultyEmail);
        administrationDTO.setFaculty(faculty);
        administrationService.saveAdministration(administrationDTO);
        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);
    }
    @RequestMapping(value = "/auth/uploadfile/updateAdministration")
    public ModelAndView updateAdministration() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("updateAdministration.jsp");
        return mv;
    }
}