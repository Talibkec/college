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
@RequestMapping(value = "department/fp")

public class FPController {

    @Autowired
    public NoticeBoardService noticeBoardService;
    @Autowired
    FacultyHelper facultyHelper;


    @RequestMapping(value = {"about"}, method = RequestMethod.GET)
    public ModelAndView getAbout() {
        ModelAndView modalAndView = new ModelAndView();
        modalAndView.addObject("Role", ControllerUtility.getRole());
        modalAndView.addObject("UserName", ControllerUtility.getUserName());
        List<NoticeBoardDTO> fpNotices = noticeBoardService.getfpNotices(PageRequest.of(0, 10));
        ControllerUtility.getNoticelist(fpNotices);


        modalAndView.addObject("noticeList", fpNotices);

        modalAndView.addObject("noticeList", getNoticeList(fpNotices, false));
        modalAndView.addObject("scrollingNoticeList", getNoticeList(fpNotices, true));


        modalAndView.setViewName("department/fp/about.jsp");
        return modalAndView;
    }

    @RequestMapping(value = {"notice"}, method = RequestMethod.GET)
    public ModelAndView getNotice() {
        ModelAndView modalAndView = new ModelAndView();
        List<NoticeBoardDTO> fpNotices = noticeBoardService.getfpNotices(PageRequest.of(0, 10));
        ControllerUtility.getNoticelist(fpNotices);
        modalAndView.addObject("noticeList", fpNotices);


        modalAndView.addObject("pageSize", "0");
        modalAndView.setViewName("department/fp/notice.jsp");
        return modalAndView;
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


    @RequestMapping(value = "vision")
    public ModelAndView getVision() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/vision.jsp");
        return mv;
    }



    @RequestMapping(value = "students")
    public ModelAndView getStudents() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/students.jsp");
        return mv;
    }

    @RequestMapping(value = "labs")
    public ModelAndView getLabs() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/labs.jsp");
        return mv;
    }

    @RequestMapping(value = "question")
    public ModelAndView getQuestions() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/question.jsp");
        return mv;
    }

    @RequestMapping(value = "awards")
    public ModelAndView getAwards() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/awards.jsp");
        return mv;
    }

    @RequestMapping(value = "programs")
    public ModelAndView getPrograms() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/programs.jsp");
        return mv;
    }

    @RequestMapping(value = "weekly")
    public ModelAndView getWeekly() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/weekly.jsp");
        return mv;
    }

    @RequestMapping(value = "course")
    public ModelAndView getCourse() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/course.jsp");
        return mv;
    }

    @RequestMapping(value = "attendance")
    public ModelAndView getAttendance() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("department/fp/attendance.jsp");
        return mv;
    }

    @RequestMapping(value = "subodh")
    public ModelAndView getSubodh() {
        ModelAndView mv = facultyHelper.getFacultyDetails("subodhkumar");
        mv.setViewName("department/fp/subodh.jsp");
        return mv;
    }

    @RequestMapping(value = "dky")
    public ModelAndView getDky() {
        ModelAndView mv = facultyHelper.getFacultyDetails("kumar.dharmveer");
        mv.setViewName("department/fp/dky.jsp");
        return mv;
    }

    @RequestMapping(value = "shweta")
    public ModelAndView getShweta() {
        ModelAndView mv = facultyHelper.getFacultyDetails("shwetakumari");
        mv.setViewName("department/fp/shweta.jsp");
        return mv;
    }

    @RequestMapping(value = "snehakumari")
    public ModelAndView getSnehakumari() {
        ModelAndView mv = facultyHelper.getFacultyDetails("snehakumari");
        mv.setViewName("department/fp/snehakumari.jsp");
        return mv;
    }

    @RequestMapping(value = "sjk")
    public ModelAndView getSjk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("sujeet.fp");
        mv.setViewName("department/fp/sjk.jsp");
        return mv;
    }

    @RequestMapping(value = "mta")
    public ModelAndView getMta() {
        ModelAndView mv = facultyHelper.getFacultyDetails("Talib");
        mv.setViewName("department/fp/mta.jsp");
        return mv;
    }

    @RequestMapping(value = "pks")
    public ModelAndView getPks() {
        ModelAndView mv = facultyHelper.getFacultyDetails("pradeep");
        mv.setViewName("department/fp/pks.jsp");
        return mv;
    }

    @RequestMapping(value = "cmm")
    public ModelAndView getCmm() {
        ModelAndView mv = facultyHelper.getFacultyDetails("chintamani");
        mv.setViewName("department/fp/cmm.jsp");
        return mv;
    }

    @RequestMapping(value = "snjk")
    public ModelAndView getSnjk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("sinjan.kumar");
        mv.setViewName("department/fp/snjk.jsp");
        return mv;
    }

    @RequestMapping(value = "shk")
    public ModelAndView getShk() {
        ModelAndView mv = facultyHelper.getFacultyDetails("sritosh");
        mv.setViewName("department/fp/shk.jsp");
        return mv;
    }


}
