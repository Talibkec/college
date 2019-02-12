package com.college.core.controller;

import com.college.FacultyHelper;
import com.college.core.model.*;
import com.college.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="department/cse")

public class CseController {

    @Autowired
    public NoticeBoardService noticeBoardService;
    @Autowired
    FacultyHelper facultyHelper;

    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView getAbout() {
        ModelAndView modalAndView = new ModelAndView();
        modalAndView.addObject("Role", ControllerUtility.getRole());
        List<NoticeBoardDTO> cseNotices = noticeBoardService.getCseNotices();
        modalAndView.addObject("noticeList", cseNotices);
        modalAndView.setViewName("department/cse/about");
        return modalAndView;
    }

    @RequestMapping(value="vision")
    public ModelAndView getVision(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/vision");
        return mv;
    }
    @RequestMapping(value="faculty")
    public ModelAndView getFaculty(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/faculty");
        return mv;
    }
    @RequestMapping(value="students")
    public ModelAndView getStudents(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/students");
        return mv;
    }
    @RequestMapping(value="notice")
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/notice");
        return mv;
    }
    @RequestMapping(value="labs")
    public ModelAndView getLabs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/labs");
        return mv;
    }
    @RequestMapping(value="question")
    public ModelAndView getQuestions(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/question");
        return mv;
    }
    @RequestMapping(value="awards")
    public ModelAndView getAwards(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/awards");
        return mv;
    }
    @RequestMapping(value="programs")
    public ModelAndView getPrograms(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/programs");
        return mv;
    }
    @RequestMapping(value="weekly")
    public ModelAndView getWeekly(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/weekly");
        return mv;
    }
    @RequestMapping(value="course")
    public ModelAndView getCourse(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/course");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/attendance");
        return mv;
    }
    @RequestMapping(value="subodh")
    public ModelAndView getSubodh(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/subodh");
        return mv;
    }@RequestMapping(value="dky")
    public ModelAndView getDky(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/dky");
        return mv;
    }
    @RequestMapping(value="shweta")
    public ModelAndView getShweta(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/shweta");
        return mv;
    }
    @RequestMapping(value="snehakumari")
    public ModelAndView getSnehakumari(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/snehakumari");
        return mv;
    }
    @RequestMapping(value="sjk")
    public ModelAndView getSjk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/sjk");
        return mv;
    }
    @RequestMapping(value="mta")
    public ModelAndView getMta(){
        ModelAndView mv= facultyHelper.getFacultyDetails("Talib");
        mv.setViewName("department/cse/mta");
        return mv;
    }
    @RequestMapping(value="pks")
    public ModelAndView getPks(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/pks");
        return mv;
    }

    @RequestMapping(value="cmm")
    public ModelAndView getCmm(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/cmm");
        return mv;
    }
    @RequestMapping(value="snjk")
    public ModelAndView getSnjk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/snjk");
        return mv;
    }
    @RequestMapping(value="shk")
    public ModelAndView getShk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/cse/shk");
        return mv;
    }


    
}
