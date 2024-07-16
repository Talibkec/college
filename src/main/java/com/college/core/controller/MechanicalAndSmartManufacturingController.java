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
@RequestMapping(value = "department/mechandsmartmanufacturing")

public class MechanicalAndSmartManufacturingController {
    @Autowired
    public NoticeBoardService noticeBoardService;
    @Autowired
    public FacultyHelper facultyHelper;

    @RequestMapping(value = "about", method = RequestMethod.GET)
    public ModelAndView getAbout() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("UserName", ControllerUtility.getUserName());
        List<NoticeBoardDTO> mechNotices = noticeBoardService.getMechNotices(new PageRequest(0, 10));

        mv.addObject("noticeList", mechNotices);
        ControllerUtility.getNoticelist(mechNotices);

        mv.addObject("noticeList", getNoticeList(mechNotices, false));
        mv.addObject("scrollingNoticeList", getNoticeList(mechNotices, true));


        mv.setViewName("department/mechandsmartmanufacturing/about.jsp");
        return mv;
    }


    private List<NoticeBoardDTO> getNoticeList(List<NoticeBoardDTO> list, boolean scrollable) {
        List<NoticeBoardDTO> scrollingNotices = new ArrayList<>();

        for (NoticeBoardDTO dto : list) {
            dto.setFileType(("." + FilenameUtils.getExtension(dto.getUploadedFileName())));
            dto.setNoticeAge(KECDateHelper.getNoticeAge(dto));
            if (scrollable) {
                if (dto.getIsScrollable() != null && dto.getIsScrollable() == 1)
                    scrollingNotices.add(dto);
            } else if (!scrollable) {
                if (dto.getIsScrollable() == null)
                    scrollingNotices.add(dto);

            }
        }

        return scrollingNotices;
    }


    @RequestMapping(value = {"notice"}, method = RequestMethod.GET)
    public ModelAndView getNotice() {
        ModelAndView mv = new ModelAndView();
        List<NoticeBoardDTO> mechNotices = noticeBoardService.getMechNotices(new PageRequest(0, 10));
        ControllerUtility.getNoticelist(mechNotices);
        mv.addObject("pageSize", 0);
        mv.addObject("noticeList", mechNotices);
        mv.setViewName("department/mechandsmartmanufacturing/notice.jsp");
        return mv;
    }

    @RequestMapping(value = "vision")
    public ModelAndView getVision() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/vision.jsp");
        return mv;
    }

    @RequestMapping(value = "faculty")
    public ModelAndView getFaculty() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/faculty.jsp");
        return mv;
    }

    @RequestMapping(value = "students")
    public ModelAndView getStudents() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/students.jsp");
        return mv;
    }

    @RequestMapping(value = "labs")
    public ModelAndView getLabs() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/labs.jsp");
        return mv;
    }

    @RequestMapping(value = "question")
    public ModelAndView getQuestions() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/question.jsp");
        return mv;
    }

    @RequestMapping(value = "awards")
    public ModelAndView getAwards() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/awards.jsp");
        return mv;
    }

    @RequestMapping(value = "programs")
    public ModelAndView getPrograms() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/programs.jsp");
        return mv;
    }

    @RequestMapping(value = "weekly")
    public ModelAndView getWeekly() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/weekly.jsp");
        return mv;
    }

    @RequestMapping(value = "course")
    public ModelAndView getCourse() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/course.jsp");
        return mv;
    }

    @RequestMapping(value = "attendance")
    public ModelAndView getAttendance() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/attendance.jsp");
        return mv;
    }

    @RequestMapping(value = "staff")
    public ModelAndView getStaff() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/mechandsmartmanufacturing/staff.jsp");
        return mv;
    }

    @RequestMapping(value = "srk")
    public ModelAndView getSrk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("suryakumar");
        mv.setViewName("department/mechandsmartmanufacturing/srk.jsp");
        return mv;
    }

    @RequestMapping(value = "jk")
    public ModelAndView getJk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("jayant906");
        mv.setViewName("department/mechandsmartmanufacturing/jk.jsp");
        return mv;
    }

    @RequestMapping(value = "mk")
    public ModelAndView getMk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("mukeshme24");
        mv.setViewName("department/mechandsmartmanufacturing/mk.jsp");
        return mv;
    }

    @RequestMapping(value = "ajk")
    public ModelAndView getAjk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("kumarajay");
        mv.setViewName("department/mechandsmartmanufacturing/ajk.jsp");
        return mv;
    }

    @RequestMapping(value = "smnk")
    public ModelAndView getSmnk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("sumankumar");
        mv.setViewName("department/mechandsmartmanufacturing/smnk.jsp");
        return mv;
    }

    @RequestMapping(value = "ap")
    public ModelAndView getAp() {
        ModelAndView mv = facultyHelper.getFacultyDetails("arbind");
        mv.setViewName("department/mechandsmartmanufacturing/ap.jsp");
        return mv;
    }

    @RequestMapping(value = "kks")
    public ModelAndView getKks() {
        ModelAndView mv = facultyHelper.getFacultyDetails("kanchan");
        mv.setViewName("department/mechandsmartmanufacturing/kks.jsp");
        return mv;
    }



}
