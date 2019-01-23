package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="department/civil")
public class CivilController {
    @RequestMapping(value="about")
    public ModelAndView getAbout(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/about");
        return mv;
    }
    @RequestMapping(value="vision")
    public ModelAndView getVision(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/vision");
        return mv;
    }
    @RequestMapping(value="faculty")
    public ModelAndView getFaculty(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/faculty");
        return mv;
    }
    @RequestMapping(value="students")
    public ModelAndView getStudents(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/students");
        return mv;
    }
    @RequestMapping(value="notice")
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/notice");
        return mv;
    }
    @RequestMapping(value="labs")
    public ModelAndView getLabs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/labs");
        return mv;
    }
    @RequestMapping(value="question")
    public ModelAndView getQuestions(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/question");
        return mv;
    }
    @RequestMapping(value="awards")
    public ModelAndView getAwards(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/awards");
        return mv;
    }
    @RequestMapping(value="programs")
    public ModelAndView getPrograms(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/programs");
        return mv;
    }
    @RequestMapping(value="weekly")
    public ModelAndView getWeekly(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/weekly");
        return mv;
    }
    @RequestMapping(value="course")
    public ModelAndView getCourse(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/course");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/attendance");
        return mv;
    }
    @RequestMapping(value="rm")
    public ModelAndView getRm(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/rm");
        return mv;
    }
    @RequestMapping(value="rps")
    public ModelAndView getRps(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/rps");
        return mv;
    }
    @RequestMapping(value="akg")
    public ModelAndView getAkg(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/akg");
        return mv;
    }
    @RequestMapping(value="ar")
    public ModelAndView getAr(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/ar");
        return mv;
    }
    @RequestMapping(value="abhi")
    public ModelAndView getbhi(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/abhi");
        return mv;
    }

}
