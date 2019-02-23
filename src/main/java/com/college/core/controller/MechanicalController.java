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
@RequestMapping(value="department/mechanical")

public class MechanicalController {
    @Autowired
    public NoticeBoardService noticeBoardService;
    @Autowired
    public FacultyHelper facultyHelper;
    @RequestMapping(value = {"mech"}, method = RequestMethod.GET)
    public ModelAndView getAbout(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("Role",ControllerUtility.getRole());
        List<NoticeBoardDTO> mechNotices = noticeBoardService.getMechNotices();
        mv.addObject("noticeList",mechNotices );

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
    @RequestMapping(value="staff")
    public ModelAndView getStaff(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/mechanical/staff");
        return mv;
    }
    @RequestMapping(value="srk")
    public ModelAndView getSrk(){
        ModelAndView mv = facultyHelper.getFacultyDetails("suryakumar");
        mv.setViewName("department/mechanical/srk");
        return mv;
    }
    @RequestMapping(value="jk")
    public ModelAndView getJk(){
        ModelAndView mv=facultyHelper.getFacultyDetails("jayant906");
        mv.setViewName("department/mechanical/jk");
        return mv;
    }
    @RequestMapping(value="mk")
    public ModelAndView getMk(){
        ModelAndView mv=facultyHelper.getFacultyDetails("mukeshme24");
        mv.setViewName("department/mechanical/mk");
        return mv;
    }
    @RequestMapping(value="ajk")
    public ModelAndView getAjk(){
        ModelAndView mv=facultyHelper.getFacultyDetails("kumarajay");
        mv.setViewName("department/mechanical/ajk");
        return mv;
    }
    @RequestMapping(value="smnk")
    public ModelAndView getSmnk(){
        ModelAndView mv=facultyHelper.getFacultyDetails("sumankumar");
        mv.setViewName("department/mechanical/smnk");
        return mv;
    }
    @RequestMapping(value="ap")
    public ModelAndView getAp(){
        ModelAndView mv=facultyHelper.getFacultyDetails("arbind");
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
