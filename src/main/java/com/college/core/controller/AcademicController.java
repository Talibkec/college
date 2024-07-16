package com.college.core.controller;

import com.college.core.entity.*;
import com.college.service.HolidayService;
import com.college.service.LabDocumentService;
import com.college.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping(value = "academic")
public class AcademicController {
    @Autowired
    HolidayService holidayService;
    @Autowired
    LabDocumentService labDocumentService;
    @Autowired
    UserService userService;
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


        boolean accessAllow = false;
        if(authentication != null){
            String loggedInusername = authentication.getName();
            User loggedInUser = userService.findByUsername(loggedInusername);
            Set<Role> role = loggedInUser.getRoles();

            for ( Role r: role) {
                System.out.println(r.getName());
                if(Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")){
                    accessAllow = true;
                }
            }

        }

        System.out.println("in download method");
        ModelAndView mv = new ModelAndView();
        List<Holiday> allDocumentList = holidayService.getAllDownload();
        mv.setViewName("academic/holidays.jsp");
        //System.out.println(" document list id " + allDocumentList.get(0).getId());
        for (Holiday download : allDocumentList) {
            System.out.println(download.getFileName());
        }
        mv.addObject("allDocuments" , allDocumentList);
        mv.addObject("showDeleteBtn" , accessAllow);

        return mv;
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("academic/holidays.jsp");
//        return mv;
    }


    @RequestMapping(value = "lab")
    public ModelAndView getLabDocuments(Authentication authentication) {

        boolean accessAllow = false;
        if(authentication != null){
            String loggedInusername = authentication.getName();
            User loggedInUser = userService.findByUsername(loggedInusername);
            Set<Role> role = loggedInUser.getRoles();

            for ( Role r: role) {
                System.out.println(r.getName());
                if(Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")){
                    accessAllow = true;
                }
            }

        }

        System.out.println("in download method");
        ModelAndView mv = new ModelAndView();
        List<LabDocument> allDocumentList = labDocumentService.getAllDownload();
        mv.setViewName("academic/labs.jsp");
        //System.out.println(" document list id " + allDocumentList.get(0).getId());
        for (LabDocument download : allDocumentList) {
            System.out.println(download.getFileName());
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
