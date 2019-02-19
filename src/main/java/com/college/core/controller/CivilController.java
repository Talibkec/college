package com.college.core.controller;

import com.college.FacultyHelper;
import com.college.core.entity.NoticeBoard;
import org.springframework.beans.factory.annotation.Autowired;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping(value = "department/civil")


public class CivilController {

    @Autowired
    public NoticeBoardService noticeBoardService;
    @Autowired
    public FacultyHelper facultyHelper;
    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView getAbout(){
        ModelAndView modalAndView=new ModelAndView();
        modalAndView.addObject("Role", ControllerUtility.getRole());
        List<NoticeBoardDTO> civilNotices= noticeBoardService.getCivilNotices();
        modalAndView.addObject("noticeList", civilNotices);
        modalAndView.setViewName("department/civil/about");
        return modalAndView;
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
        ModelAndView mv =facultyHelper.getFacultyDetails("talktorashid");
        mv.setViewName("department/civil/rm");
        return mv;
    }
    @RequestMapping(value="rps")
    public ModelAndView getRps(){
        ModelAndView mv = facultyHelper.getFacultyDetails("rpsingh");
        mv.setViewName("department/civil/rps");
        return mv;
    }
    @RequestMapping(value="akg")
    public ModelAndView getAkg(){
        ModelAndView mv = facultyHelper.getFacultyDetails("anilanu");
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
