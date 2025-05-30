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
@RequestMapping(value = "department/ecevlsi")
public class EceVlsiController {

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


            mv.setViewName("department/ecevlsi/about.jsp");
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
            mv.setViewName("department/ecevlsi/vision.jsp");
            return mv;
        }



        @RequestMapping(value = "students")
        public ModelAndView getStudents() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/students.jsp");
            return mv;
        }

        @RequestMapping(value = {"notice"}, method = RequestMethod.GET)
        public ModelAndView getNotice() {
            ModelAndView mv = new ModelAndView();
            List<NoticeBoardDTO> eecNotices = noticeBoardService.getEecNotices(new PageRequest(0, 10));
            ControllerUtility.getNoticelist(eecNotices);
            mv.addObject("noticeList", eecNotices);
            mv.addObject("pageSize", 0);
            mv.setViewName("department/ecevlsi/notice.jsp");
            return mv;
        }

        @RequestMapping(value = "labs")
        public ModelAndView getLabs() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/labs.jsp");
            return mv;
        }

        @RequestMapping(value = "question")
        public ModelAndView getQuestions() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/question.jsp");
            return mv;
        }

        @RequestMapping(value = "awards")
        public ModelAndView getAwards() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/awards.jsp");
            return mv;
        }

        @RequestMapping(value = "programs")
        public ModelAndView getPrograms() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/programs.jsp");
            return mv;
        }

        @RequestMapping(value = "weekly")
        public ModelAndView getWeekly() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/weekly.jsp");
            return mv;
        }

        @RequestMapping(value = "course")
        public ModelAndView getCourse() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/course.jsp");
            return mv;
        }

        @RequestMapping(value = "attendance")
        public ModelAndView getAttendance() {
            ModelAndView mv = new ModelAndView();
            mv.setViewName("department/ecevlsi/attendance.jsp");
            return mv;
        }

        @RequestMapping(value = "principal")
        public ModelAndView getprincipal() {
            ModelAndView mv = facultyHelper.getFacultyDetails("principal");
            mv.setViewName("department/ecevlsi/principal.jsp");
            return mv;
        }

        @RequestMapping(value = "rk")
        public ModelAndView getRK() {
            ModelAndView mv = facultyHelper.getFacultyDetails("ram");
            mv.setViewName("department/ecevlsi/rk.jsp");
            return mv;
        }

        @RequestMapping(value = "aks")
        public ModelAndView getAmitKumar() {
            ModelAndView mv = facultyHelper.getFacultyDetails("amit");
            mv.setViewName("department/ecevlsi/aks.jsp");
            return mv;
        }

        @RequestMapping(value = "ak")
        public ModelAndView getAK() {
            ModelAndView mv = facultyHelper.getFacultyDetails("abhijeet.kumar");
            mv.setViewName("department/ecevlsi/ak.jsp");
            return mv;
        }

        @RequestMapping(value = "aa")
        public ModelAndView getAA() {
            ModelAndView mv = facultyHelper.getFacultyDetails("alauddinansari0432");
            mv.setViewName("department/ecevlsi/aa.jsp");
            return mv;
        }

        @RequestMapping(value = "sa")
        public ModelAndView getSA() {
            ModelAndView mv = facultyHelper.getFacultyDetails("sajjad.ahmad");
            mv.setViewName("department/ecevlsi/sa.jsp");
            return mv;
        }

        @RequestMapping(value = "ss")
        public ModelAndView getSS() {
            ModelAndView mv = facultyHelper.getFacultyDetails("saketsaurabh");
            mv.setViewName("department/ecevlsi/ss.jsp");
            return mv;
        }

        @RequestMapping(value = "tr")
        public ModelAndView getTR() {
            ModelAndView mv = facultyHelper.getFacultyDetails("tarique");
            mv.setViewName("department/ecevlsi/tr.jsp");
            return mv;
        }

        @RequestMapping(value = "rdr")
        public ModelAndView getRDR() {
            ModelAndView mv = facultyHelper.getFacultyDetails("rajeev");
            mv.setViewName("department/ecevlsi/rdr.jsp");
            return mv;
        }

        @RequestMapping(value = "nk")
        public ModelAndView getNK() {
            ModelAndView mv = facultyHelper.getFacultyDetails("navin");
            mv.setViewName("department/ecevlsi/nk.jsp");
            return mv;
        }
}
