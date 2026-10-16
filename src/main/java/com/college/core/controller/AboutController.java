package com.college.core.controller;

import com.college.core.model.ResponsibilityDocDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.college.core.model.StaffDTO;
import com.college.service.AdministrationService;
import com.college.service.ResponsibilityDocService;
import com.college.service.FacultyProfileService;
import com.college.service.StaffService;
import com.college.service.AccessService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
@RequestMapping("about")
public class AboutController {
    private static final Logger logger = LoggerFactory.getLogger(AboutController.class);
    private final AccessService accessService;
    private final ResponsibilityDocService responsibilityDocService;
    private final StaffService staffService;
    private final AdministrationService administrationService;

    public AboutController(AccessService accessService,
            ResponsibilityDocService responsibilityDocService,
            StaffService staffService,
            AdministrationService administrationService) {
        this.accessService = accessService;
        this.responsibilityDocService = responsibilityDocService;
        this.staffService = staffService;
        this.administrationService = administrationService;
    }

    @RequestMapping(value = "history")
    public ModelAndView getHistory() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/history.jsp");
        return mv;
    }

    @RequestMapping(value = "vision")
    public ModelAndView getVision() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/vision.jsp");
        return mv;
    }

    @RequestMapping(value = "principal")
    public ModelAndView getPrincipal() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/principal.jsp");
        return mv;
    }

    @RequestMapping(value = "allotment")
    public ModelAndView getAllotment() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/allotment.jsp");
        return mv;
    }

    @RequestMapping(value = "affiliation")
    public ModelAndView getAffiliation() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/affiliation.jsp");
        return mv;

    }

    @RequestMapping(value = "administration")
    public ModelAndView getResponsibilityDoc(Authentication authentication) {

        boolean accessAllow = accessService.isAdminOrHod(authentication);

        logger.debug("Listing documents");
        ModelAndView mv = new ModelAndView();
        List<ResponsibilityDocDTO> allDocumentList = responsibilityDocService.getAllDownload();
        mv.setViewName("facilities/responsibilityDoc.jsp");
        //System.out.println(" document list id " + allDocumentList.get(0).getId());
        for (ResponsibilityDocDTO download : allDocumentList) {
            logger.debug("Document: {}", download.getFileName());
        }
        mv.addObject("allDocuments" , allDocumentList);
        mv.addObject("showDeleteBtn" , accessAllow);

        return mv;
    }


    @RequestMapping(value = "staff")
    public ModelAndView getStaff(Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        // userType will be
        // 0 :- Normal User
        //1:- Admin
        int userType = accessService.isAdmin(authentication) ? 1 : 0;
        List<StaffDTO> staffList = staffService.getAllStaff();
        for (StaffDTO dto : staffList) {
            dto.setImageURL(FacultyProfileService.photoDataUrl(dto.getStaffPhoto(), dto.getFileType()));
        }

        mv.addObject("staffList", staffList);
        mv.addObject("userType", userType);
        mv.setViewName("about/staff.jsp");
        return mv;
    }
    @RequestMapping(value = "deleteStaff")
    public void deleteStaff(@RequestParam("staffId") Long staffId, Authentication authentication, HttpServletResponse res) {
        int accessAllow = accessService.isAdmin(authentication) ? 1 : 0;
        StaffDTO staffDTO = staffService.getStaffById(staffId);
        if (accessAllow > 0 ) {
            staffService.deleteStaff(staffId);
        }
        try {
            res.sendRedirect("/about/staff");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}



