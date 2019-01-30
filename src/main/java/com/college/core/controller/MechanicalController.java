package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="department/mechanical")
public class MechanicalController {
    @RequestMapping(value="mech")
    public ModelAndView getAbout(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/mech");
        return mv;
    }
    @RequestMapping(value="vision")
    public ModelAndView getVision(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/vision");
        return mv;
    }
    @RequestMapping(value="faculty")
    public ModelAndView getFaculty(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/faculty");
        return mv;
    }
    @RequestMapping(value="students")
    public ModelAndView getStudents(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/students");
        return mv;
    }
    @RequestMapping(value="notice")
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/notice");
        return mv;
    }
    @RequestMapping(value="labs")
    public ModelAndView getLabs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/labs");
        return mv;
    }
    @RequestMapping(value="question")
    public ModelAndView getQuestions(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/question");
        return mv;
    }
    @RequestMapping(value="awards")
    public ModelAndView getAwards(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/awards");
        return mv;
    }
    @RequestMapping(value="programs")
    public ModelAndView getPrograms(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/programs");
        return mv;
    }
    @RequestMapping(value="weekly")
    public ModelAndView getWeekly(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/weekly");
        return mv;
    }
    @RequestMapping(value="course")
    public ModelAndView getCourse(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/course");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/attendance");
        return mv;
    }
    @RequestMapping(value="srk")
    public ModelAndView getSrk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/srk");
        return mv;
    }
    @RequestMapping(value="jk")
    public ModelAndView getJk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/jk");
        return mv;
    }
    @RequestMapping(value="mk")
    public ModelAndView getMk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/mk");
        return mv;
    }
    @RequestMapping(value="ajk")
    public ModelAndView getAjk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/ajk");
        return mv;
    }
    @RequestMapping(value="smnk")
    public ModelAndView getSmnk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/smnk");
        return mv;
    }
    @RequestMapping(value="ap")
    public ModelAndView getAp(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/ap");
        return mv;
    }
    @RequestMapping(value="kks")
    public ModelAndView getKks(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/kks");
        return mv;
    }
    
    
}
