package com.college.core.controller;

import com.college.FacultyHelper;
import com.college.KECDateHelper;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value="department/ash")

public class AppliedController {

    @Autowired
    NoticeBoardService noticeBoardService;
    @Autowired
    FacultyHelper facultyHelper;

    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView getAbout(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("UserName", ControllerUtility.getUserName());
        List<NoticeBoardDTO> appliedScienceNotices = noticeBoardService.getApplidScienceNotices(new PageRequest(0, 10));
        ControllerUtility.getNoticelist(appliedScienceNotices);

        mv.addObject("noticeList",appliedScienceNotices);

        mv.addObject("noticeList", getNoticeList(appliedScienceNotices, false));
        mv.addObject("scrollingNoticeList", getNoticeList(appliedScienceNotices, true));



        mv.setViewName("department/ash/about.jsp");
        return mv;
    }



    private List<NoticeBoardDTO> getNoticeList(List<NoticeBoardDTO> list, boolean scrollable) {
        List<NoticeBoardDTO> scrollingNotices = new ArrayList<>();

        for(NoticeBoardDTO dto : list){
            dto.setFileType(("."+ FilenameUtils.getExtension(dto.getUploadedFileName())));
            dto.setNoticeAge(KECDateHelper.getNoticeAge(dto));
            if(scrollable){
                if(dto.getIsScrollable() != null &&  dto.getIsScrollable() == 1)
                    scrollingNotices.add(dto);
            }
            else if(!scrollable){
                if(dto.getIsScrollable()== null )
                    scrollingNotices.add(dto);

            }
        }

        return  scrollingNotices;
    }




    @RequestMapping(value="vision")
    public ModelAndView getVision(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/vision.jsp");
        return mv;
    }
    @RequestMapping(value="faculty")
    public ModelAndView getFaculty(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/faculty.jsp");
        return mv;
    }
    @RequestMapping(value="students")
    public ModelAndView getStudents(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/students.jsp");
        return mv;
    }
    @RequestMapping(value={"notice"}, method =  RequestMethod.GET)
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        List<NoticeBoardDTO> appliedScienceNotices = noticeBoardService.getApplidScienceNotices(new PageRequest(0, 10));
        ControllerUtility.getNoticelist(appliedScienceNotices);
        mv.addObject("noticeList",appliedScienceNotices);
        mv.addObject("pageSize",0);
        mv.setViewName("department/ash/notice.jsp");
        return mv;
    }
    @RequestMapping(value="labs")
    public ModelAndView getLabs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/labs.jsp");
        return mv;
    }
    @RequestMapping(value="question")
    public ModelAndView getQuestions(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/question.jsp");
        return mv;
    }
    @RequestMapping(value="awards")
    public ModelAndView getAwards(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/awards.jsp");
        return mv;
    }
    @RequestMapping(value="programs")
    public ModelAndView getPrograms(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/programs.jsp");
        return mv;
    }
    @RequestMapping(value="weekly")
    public ModelAndView getWeekly(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/weekly.jsp");
        return mv;
    }
    @RequestMapping(value="course")
    public ModelAndView getCourse(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/course.jsp");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/ash/attendance.jsp");
        return mv;
    }
    @RequestMapping(value="pk")
    public ModelAndView getPk(){
        ModelAndView mv=facultyHelper.getFacultyDetails("pramod");
        mv.setViewName("department/ash/pk.jsp");
        return mv;
    }
    @RequestMapping(value="vk")
    public ModelAndView getVk(){
        ModelAndView mv=facultyHelper.getFacultyDetails("vipin");
        mv.setViewName("department/ash/vk.jsp");
        return mv;
    }
    @RequestMapping(value="bnm")
    public ModelAndView getBnm(){
        ModelAndView mv=facultyHelper.getFacultyDetails("bnmahto");
        mv.setViewName("department/ash/bnm.jsp");
        return mv;
    }
    @RequestMapping(value="snk")
    public ModelAndView getSnk(){
        ModelAndView mv=facultyHelper.getFacultyDetails("sunil");
        mv.setViewName("department/ash/snk.jsp");
        return mv;
    }
}
