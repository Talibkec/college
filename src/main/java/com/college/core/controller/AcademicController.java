package com.college.core.controller;

import com.college.core.model.LabDocumentDTO;
import com.college.core.model.HolidayDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.college.service.HolidayService;
import com.college.service.LabDocumentService;
import com.college.service.AccessService;
import com.college.service.LabDocumentAccess;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;

@Controller
@RequestMapping(value = "academic")
public class AcademicController {
    private static final Logger logger = LoggerFactory.getLogger(AcademicController.class);

    private final HolidayService holidayService;
    private final LabDocumentService labDocumentService;
    private final AccessService accessService;
    private final LabDocumentAccess labDocumentAccess;

    public AcademicController(HolidayService holidayService,
            LabDocumentService labDocumentService,
            AccessService accessService,
            LabDocumentAccess labDocumentAccess) {
        this.labDocumentAccess = labDocumentAccess;
        this.holidayService = holidayService;
        this.labDocumentService = labDocumentService;
        this.accessService = accessService;
    }

    @RequestMapping(value = "admission")
    public ModelAndView getAdmission() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/admission.jsp");
        return mv;
    }

    @RequestMapping(value = "regulations")
    public ModelAndView getRegulations() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/regulations.jsp");
        return mv;
    }
    @RequestMapping(value = "adminsValue")
    public ModelAndView getAdmins() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/administration.jsp");
        return mv;
    }

    @RequestMapping(value = "calender")
    public ModelAndView getCalender() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/calender.jsp");
        return mv;
    }

    @RequestMapping(value = "holidays")
    public ModelAndView getHolidays(Authentication authentication) {


        boolean accessAllow = accessService.isAdminOrHod(authentication);

        logger.debug("Listing documents");
        ModelAndView mv = new ModelAndView();
        List<HolidayDTO> allDocumentList = holidayService.getAllDownload();
        mv.setViewName("academic/holidays.jsp");
        //System.out.println(" document list id " + allDocumentList.get(0).getId());
        for (HolidayDTO download : allDocumentList) {
            logger.debug("Document: {}", download.getFileName());
        }
        mv.addObject("allDocuments" , allDocumentList);
        mv.addObject("showDeleteBtn" , accessAllow);

        return mv;
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("academic/holidays.jsp");
//        return mv;
    }


    @RequestMapping(value = "lab")
    public ModelAndView getLabDocuments(@RequestParam("dept_id") Integer deptId, Authentication authentication) {

        // upload/delete only for Admin and the HOD of this department (LabDocumentAccess)
        boolean accessAllow = labDocumentAccess.canManage(authentication, deptId);

        logger.debug("Listing documents");
        ModelAndView mv = new ModelAndView();
        List<LabDocumentDTO> allDocumentList = labDocumentService.getAllLabDocByDepId(deptId);
        mv.setViewName("academic/labs.jsp");
        //System.out.println(" document list id " + allDocumentList.get(0).getId());
        for (LabDocumentDTO download : allDocumentList) {
            logger.debug("Document: {}", download.getFileName());
        }
        mv.addObject("allDocuments" , allDocumentList);
        mv.addObject("showDeleteBtn" , accessAllow);

        return mv;
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("academic/holidays.jsp");
//        return mv;
    }


    @RequestMapping(value = "attendance")
    public ModelAndView getAttendance() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/attendance.jsp");
        return mv;
    }

    @RequestMapping(value = "notice")
    public ModelAndView getNotice() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/notice.jsp");
        return mv;
    }

    @RequestMapping(value = "paymenttutorial")
    public ModelAndView getPayment() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/paymenttutorial.jsp");
        return mv;
    }

    @RequestMapping(value = "rules")
    public ModelAndView getRules() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/rules.jsp");
        return mv;
    }

    @RequestMapping(value = "academiccouncil")
    public ModelAndView getAcademiccouncil() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/academiccouncil.jsp");
        return mv;
    }

    @RequestMapping(value = "antiragging")
    public ModelAndView getAntiragging() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/antiragging.jsp");
        return mv;
    }

    @RequestMapping(value = "syllabus")
    public ModelAndView getSyllabus() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/syllabus.jsp");
        return mv;
    }

    @RequestMapping(value = "universityresults")
    public ModelAndView getUniversityresult() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/universityresult.jsp");
        return mv;
    }

    @RequestMapping(value = "mentors")
    public ModelAndView getMentors() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/mentors.jsp");
        return mv;
    }


}
