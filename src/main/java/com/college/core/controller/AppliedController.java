package com.college.core.controller;

import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="department/ash")

public class AppliedController {

    @Autowired
    NoticeBoardService noticeBoardService;

    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView getAbout(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        List<NoticeBoardDTO> appliedScienceNotices = noticeBoardService.getApplidScienceNotices();
        mv.addObject("noticeList",appliedScienceNotices);
        mv.setViewName("department/ash/about");
        return mv;
    }
    @RequestMapping(value="vision")
    public ModelAndView getVision(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/vision");
        return mv;
    }
    @RequestMapping(value="faculty")
    public ModelAndView getFaculty(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/faculty");
        return mv;
    }
    @RequestMapping(value="students")
    public ModelAndView getStudents(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/students");
        return mv;
    }
    @RequestMapping(value="notice")
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/notice");
        return mv;
    }
    @RequestMapping(value="labs")
    public ModelAndView getLabs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/labs");
        return mv;
    }
    @RequestMapping(value="question")
    public ModelAndView getQuestions(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/question");
        return mv;
    }
    @RequestMapping(value="awards")
    public ModelAndView getAwards(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/awards");
        return mv;
    }
    @RequestMapping(value="programs")
    public ModelAndView getPrograms(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/programs");
        return mv;
    }
    @RequestMapping(value="weekly")
    public ModelAndView getWeekly(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/weekly");
        return mv;
    }
    @RequestMapping(value="course")
    public ModelAndView getCourse(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/course");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/attendance");
        return mv;
    }
    @RequestMapping(value="pk")
    public ModelAndView getPk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/pk");
        return mv;
    }
    @RequestMapping(value="vk")
    public ModelAndView getVk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/vk");
        return mv;
    }
    @RequestMapping(value="bnm")
    public ModelAndView getBnm(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/bnm");
        return mv;
    }
    @RequestMapping(value="snk")
    public ModelAndView getSnk(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/snk");
        return mv;
    }
}
