package com.college.core.controller;

import com.college.FacultyHelper;
import com.college.KECDateHelper;
import com.college.core.model.FacultyDTO;
import com.college.service.FacultyService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "department/civil")


public class CivilController {

    @Autowired
    public NoticeBoardService noticeBoardService;
    @Autowired
    public FacultyHelper facultyHelper;
    @Autowired
    public FacultyService facultyService;
    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView getAbout(){
        ModelAndView modalAndView=new ModelAndView();
        modalAndView.addObject("Role", ControllerUtility.getRole());
        modalAndView.addObject("UserName", ControllerUtility.getUserName());
        List<NoticeBoardDTO> civilNotices= noticeBoardService.getCivilNotices(new PageRequest(0, 10));


        List<NoticeBoardDTO> blinkingMessage= noticeBoardService.getBlinkingMessage(new PageRequest(0, 10));



        modalAndView.addObject("noticeList", civilNotices);
        ControllerUtility.getNoticelist(civilNotices);

        modalAndView.addObject("noticeList", getNoticeList(civilNotices, false));
        modalAndView.addObject("scrollingNoticeList", getNoticeList(civilNotices, true));

        modalAndView.addObject("blinkMessage", blinkingMessage);




        modalAndView.setViewName("department/civil/about.jsp");
        return modalAndView;
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
        mv.setViewName("department/civil/vision.jsp");
        return mv;
    }
    @RequestMapping(value="faculty")
    public ModelAndView getFaculty(){
        ModelAndView mv=new ModelAndView();
        Long deptno = 2L;
        List<FacultyDTO> facultyList = facultyService.getFacultyByDeptNo(deptno);
        mv.addObject("facultyList",facultyList);
        mv.setViewName("department/civil/faculty.jsp");
        return mv;
    }
    @RequestMapping(value="students")

    public ModelAndView getStudents(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/students.jsp");
        return mv;
    }
    @RequestMapping(value={"notice"}, method = RequestMethod.GET)
    public ModelAndView getNotice(){
        ModelAndView mv=new ModelAndView();
        List<NoticeBoardDTO> civilNotices= noticeBoardService.getCivilNotices(new PageRequest(0, 10));
        mv.addObject("noticeList", civilNotices);
        mv.addObject("pageSize", 0);
        ControllerUtility.getNoticelist(civilNotices);
        mv.setViewName("department/civil/notice.jsp");
        return mv;
    }
    @RequestMapping(value="labs")
    public ModelAndView getLabs(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/labs.jsp");
        return mv;
    }
    @RequestMapping(value="question")
    public ModelAndView getQuestions(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/question.jsp");
        return mv;
    }
    @RequestMapping(value="awards")
    public ModelAndView getAwards(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/awards.jsp");
        return mv;
    }
    @RequestMapping(value="programs")
    public ModelAndView getPrograms(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/programs.jsp");
        return mv;
    }
    @RequestMapping(value="weekly")
    public ModelAndView getWeekly(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/weekly.jsp");
        return mv;
    }
    @RequestMapping(value="course")
    public ModelAndView getCourse(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/course.jsp");
        return mv;
    }
    @RequestMapping(value="attendance")
    public ModelAndView getAttendance(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("department/civil/attendance.jsp");
        return mv;
    }
    @RequestMapping(value="rm")
    public ModelAndView getRm(){
        ModelAndView mv =facultyHelper.getFacultyDetails("talktorashid");
        mv.setViewName("department/civil/rm.jsp");
        return mv;
    }
    @RequestMapping(value="rps")
    public ModelAndView getRps(){
        ModelAndView mv = facultyHelper.getFacultyDetails("rpsingh");
        mv.setViewName("department/civil/rps.jsp");
        return mv;
    }
    @RequestMapping(value="akg")
    public ModelAndView getAkg() {
        ModelAndView mv = facultyHelper.getFacultyDetails("anilanu");
        mv.setViewName("department/civil/akg.jsp");
        return mv;
    }
    @RequestMapping(value="abhi")
    public ModelAndView getbhi(){
        ModelAndView mv = facultyHelper.getFacultyDetails("abhishek");
        mv.setViewName("department/civil/abhi.jsp");
        return mv;
    }
    @RequestMapping(value="sss")
    public ModelAndView getsss(){
        ModelAndView mv = facultyHelper.getFacultyDetails("shashank");
        mv.setViewName("department/civil/sss.jsp");
        return mv;
    }
    @RequestMapping(value="ar")
    public ModelAndView getar(){
        ModelAndView mv = facultyHelper.getFacultyDetails("aale");
        mv.setViewName("department/civil/ar.jsp");
        return mv;
    }
    @RequestMapping(value="kkk")
    public ModelAndView getkkk(){
        ModelAndView mv = facultyHelper.getFacultyDetails("krishna");
        mv.setViewName("department/civil/kkk.jsp");
        return mv;
    }



}
