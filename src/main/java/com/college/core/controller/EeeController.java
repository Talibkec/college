package com.college.core.controller;

import com.college.FacultyHelper;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value="department/eee")

public class EeeController {

    @Autowired
    NoticeBoardService noticeBoardService;
    @Autowired
    FacultyHelper facultyHelper;
    @RequestMapping(value = {"about"}, method = RequestMethod.GET)

    public ModelAndView getAbout(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("Role",ControllerUtility.getRole());
        List<NoticeBoardDTO> eecNotices = noticeBoardService.getEecNotices();
        mv.addObject("noticeList",eecNotices);
        mv.setViewName("department/eee/about");
        return mv;
    }
    @RequestMapping(value="vision")
    public ModelAndView getVision(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/vision");
        return mv;
    }
    @RequestMapping(value="faculty")
    public ModelAndView getFaculty(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/faculty");
        return mv;
    }
    @RequestMapping(value="students")
    public ModelAndView getStudents(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/students");
        return mv;
    }
    @RequestMapping(value="notice")
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/notice");
        return mv;
    }
    @RequestMapping(value="labs")
    public ModelAndView getLabs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/labs");
        return mv;
    }
    @RequestMapping(value="question")
    public ModelAndView getQuestions(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/question");
        return mv;
    }
    @RequestMapping(value="awards")
    public ModelAndView getAwards(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/awards");
        return mv;
    }
    @RequestMapping(value="programs")
    public ModelAndView getPrograms(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/programs");
        return mv;
    }
    @RequestMapping(value="weekly")
    public ModelAndView getWeekly(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/weekly");
        return mv;
    }
    @RequestMapping(value="course")
    public ModelAndView getCourse(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/course");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/eee/attendance");
        return mv;
    }
    @RequestMapping(value="principal")
    public ModelAndView getprincipal(){
        ModelAndView mv = facultyHelper.getFacultyDetails("principal");
        mv.setViewName("department/eee/principal");
        return mv;
    }
    @RequestMapping(value="rk")
    public ModelAndView getRK(){
        ModelAndView mv = facultyHelper.getFacultyDetails("ram");
        mv.setViewName("department/eee/rk");
        return mv;
    }
    @RequestMapping(value="aks")
    public ModelAndView getAmitKumar(){
        ModelAndView mv = facultyHelper.getFacultyDetails("amit");
        mv.setViewName("department/eee/aks");
        return mv;
    }
    @RequestMapping(value="ak")
    public ModelAndView getAK(){
        ModelAndView mv = facultyHelper.getFacultyDetails("abhijeet.kumar");
        mv.setViewName("department/eee/ak");
        return mv;
    }
    @RequestMapping(value="aa")
    public ModelAndView getAA(){
        ModelAndView mv = facultyHelper.getFacultyDetails("alauddinansari0432");
        mv.setViewName("department/eee/aa");
        return mv;
    }
    @RequestMapping(value="sa")
    public ModelAndView getSA(){
        ModelAndView mv = facultyHelper.getFacultyDetails("sajjad.ahmad");
        mv.setViewName("department/eee/sa");
        return mv;
    }
    @RequestMapping(value="ss")
    public ModelAndView getSS(){
        ModelAndView mv = facultyHelper.getFacultyDetails("saketsaurabh");
        mv.setViewName("department/eee/ss");
        return mv;
    }
    @RequestMapping(value="tr")
    public ModelAndView getTR(){
        ModelAndView mv = facultyHelper.getFacultyDetails("tarique");
        mv.setViewName("department/eee/tr");
        return mv;
    }
    @RequestMapping(value="rdr")
    public ModelAndView getRDR(){
        ModelAndView mv = facultyHelper.getFacultyDetails("rajeev");
        mv.setViewName("department/eee/rdr");
        return mv;
    }
    @RequestMapping(value="nk")
    public ModelAndView getNK(){
        ModelAndView mv = facultyHelper.getFacultyDetails("navin");
        mv.setViewName("department/eee/nk");
        return mv;
    }
}
