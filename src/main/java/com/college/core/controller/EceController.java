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
@RequestMapping(value = "department/ece")
public class EceController {

        @Autowired
        NoticeBoardService noticeBoardService;
        @Autowired
        FacultyHelper facultyHelper;

        @RequestMapping(value = {"about"}, method = RequestMethod.GET)

        public ModelAndView getAbout() {
            ModelAndView mv = new ModelAndView();
            mv.addObject("Role", ControllerUtility.getRole());
            mv.addObject("UserName", ControllerUtility.getUserName());
            List<NoticeBoardDTO> eecNotices = noticeBoardService.getEecNotices(new PageRequest(0, 10));
            ControllerUtility.getNoticelist(eecNotices);


            mv.addObject("noticeList", eecNotices);

            mv.addObject("noticeList", getNoticeList(eecNotices, false));
            mv.addObject("scrollingNoticeList", getNoticeList(eecNotices, true));


            mv.setViewName("department/ece/about.jsp");
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


        @RequestMapping(value = "vision")
        public ModelAndView getVision() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/vision.jsp");
            return mv;
        }



        @RequestMapping(value = "students")
        public ModelAndView getStudents() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/students.jsp");
            return mv;
        }

        @RequestMapping(value = {"notice"}, method = RequestMethod.GET)
        public ModelAndView getNotice() {
            ModelAndView mv = new ModelAndView();
            List<NoticeBoardDTO> eecNotices = noticeBoardService.getEecNotices(new PageRequest(0, 10));
            ControllerUtility.getNoticelist(eecNotices);
            mv.addObject("noticeList", eecNotices);
            mv.addObject("pageSize", 0);
            mv.setViewName("department/ece/notice.jsp");
            return mv;
        }

        @RequestMapping(value = "labs")
        public ModelAndView getLabs() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/labs.jsp");
            return mv;
        }

        @RequestMapping(value = "question")
        public ModelAndView getQuestions() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/question.jsp");
            return mv;
        }

        @RequestMapping(value = "awards")
        public ModelAndView getAwards() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/awards.jsp");
            return mv;
        }

        @RequestMapping(value = "programs")
        public ModelAndView getPrograms() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/programs.jsp");
            return mv;
        }

        @RequestMapping(value = "weekly")
        public ModelAndView getWeekly() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/weekly.jsp");
            return mv;
        }

        @RequestMapping(value = "course")
        public ModelAndView getCourse() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/course.jsp");
            return mv;
        }

        @RequestMapping(value = "attendance")
        public ModelAndView getAttendance() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ece/attendance.jsp");
            return mv;
        }

        @RequestMapping(value = "principal")
        public ModelAndView getprincipal() {
            ModelAndView mv = facultyHelper.getFacultyDetails("principal");
            mv.setViewName("department/ece/principal.jsp");
            return mv;
        }

        @RequestMapping(value = "rk")
        public ModelAndView getRK() {
            ModelAndView mv = facultyHelper.getFacultyDetails("ram");
            mv.setViewName("department/ece/rk.jsp");
            return mv;
        }

        @RequestMapping(value = "aks")
        public ModelAndView getAmitKumar() {
            ModelAndView mv = facultyHelper.getFacultyDetails("amit");
            mv.setViewName("department/ece/aks.jsp");
            return mv;
        }

        @RequestMapping(value = "ak")
        public ModelAndView getAK() {
            ModelAndView mv = facultyHelper.getFacultyDetails("abhijeet.kumar");
            mv.setViewName("department/ece/ak.jsp");
            return mv;
        }

        @RequestMapping(value = "aa")
        public ModelAndView getAA() {
            ModelAndView mv = facultyHelper.getFacultyDetails("alauddinansari0432");
            mv.setViewName("department/ece/aa.jsp");
            return mv;
        }

        @RequestMapping(value = "sa")
        public ModelAndView getSA() {
            ModelAndView mv = facultyHelper.getFacultyDetails("sajjad.ahmad");
            mv.setViewName("department/ece/sa.jsp");
            return mv;
        }

        @RequestMapping(value = "ss")
        public ModelAndView getSS() {
            ModelAndView mv = facultyHelper.getFacultyDetails("saketsaurabh");
            mv.setViewName("department/ece/ss.jsp");
            return mv;
        }

        @RequestMapping(value = "tr")
        public ModelAndView getTR() {
            ModelAndView mv = facultyHelper.getFacultyDetails("tarique");
            mv.setViewName("department/ece/tr.jsp");
            return mv;
        }

        @RequestMapping(value = "rdr")
        public ModelAndView getRDR() {
            ModelAndView mv = facultyHelper.getFacultyDetails("rajeev");
            mv.setViewName("department/ece/rdr.jsp");
            return mv;
        }

        @RequestMapping(value = "nk")
        public ModelAndView getNK() {
            ModelAndView mv = facultyHelper.getFacultyDetails("navin");
            mv.setViewName("department/ece/nk.jsp");
            return mv;
        }
}
