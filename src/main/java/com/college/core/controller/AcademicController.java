package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "academic")
public class AcademicController {
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

    @RequestMapping(value = "calender")
    public ModelAndView getCalender() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/calender.jsp");
        return mv;
    }

    @RequestMapping(value = "holidays")
    public ModelAndView getHolidays() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("academic/holidays.jsp");
        return mv;
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

    @RequestMapping(value = "universityresult")
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
