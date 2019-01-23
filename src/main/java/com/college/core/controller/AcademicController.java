package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="academic")
public class AcademicController {
    @RequestMapping(value="admission")
    public ModelAndView getAdmission(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/admission");
        return mv;
    }
    @RequestMapping(value="regulations")
    public ModelAndView getRegulations(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/regulations");
        return mv;
    }
    @RequestMapping(value="calender")
    public ModelAndView getCalender(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/calender");
        return mv;
    }
    @RequestMapping(value="holidays")
    public ModelAndView getHolidays(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/holidays");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/attendance");
        return mv;
    }
    @RequestMapping(value="notice")
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/notice");
        return mv;
    }
    @RequestMapping(value="rules")
    public ModelAndView getRules(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/rules");
        return mv;
    }
    @RequestMapping(value="academiccouncil")
    public ModelAndView getAcademiccouncil(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/academiccouncil");
        return mv;
    }
    @RequestMapping(value="antiragging")
    public ModelAndView getAntiragging(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/antiragging");
        return mv;
    }
    @RequestMapping(value="syllabus")
    public ModelAndView getSyllabus(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/syllabus");
        return mv;
    }
    @RequestMapping(value="universityresult")
    public ModelAndView getUniversityresult(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/universityresult");
        return mv;
    }
    @RequestMapping(value="mentors")
    public ModelAndView getMentors(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("academic/mentors");
        return mv;
    }



}
