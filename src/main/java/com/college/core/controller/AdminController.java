package com.college.core.controller;

import com.college.core.model.NoticeBoardDTO;
import com.college.service.ContentPermissionService;
import com.college.service.FacultyProfileService;
import com.college.service.NoticeBoardService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
//@RequestMapping("admin")
public class AdminController {
    private final FacultyProfileService facultyProfileService;
    private final NoticeBoardService noticeBoardService;
    private final ContentPermissionService contentPermissionService;

    public AdminController(FacultyProfileService facultyProfileService, NoticeBoardService noticeBoardService,
                           ContentPermissionService contentPermissionService) {
        this.facultyProfileService = facultyProfileService;
        this.noticeBoardService = noticeBoardService;
        this.contentPermissionService = contentPermissionService;
    }

    @RequestMapping(value = "/auth/uploadDashboard")
    public ModelAndView getUploadDashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(PageRequest.of(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", ControllerUtility.filterNotices(list, false));
        mv.setViewName("admin/UploadDashboard.jsp");
        return mv;

    }

    @RequestMapping(value = "/auth/uploadLabDoc")
    public ModelAndView uploadLabDoc() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(PageRequest.of(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", ControllerUtility.filterNotices(list, false));
        mv.setViewName("admin/uploadLabDoc.jsp");
        return mv;

    }

    @RequestMapping(value = "/auth/addPlacement")
    public ModelAndView addPlacement() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(PageRequest.of(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", ControllerUtility.filterNotices(list, false));
        mv.setViewName("addPlacement.jsp");
        return mv;

    }

    @RequestMapping(value = "/hod/hodDashboard")
    public ModelAndView getHodDashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(PageRequest.of(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("canManageContent",
                !contentPermissionService.editableAreas(SecurityContextHolder.getContext().getAuthentication()).isEmpty());
        mv.setViewName("admin/HodDashboard.jsp");
        return mv;

    }
    @RequestMapping(value = "/hod/addfaculty")
    public ModelAndView getHodAddFaculty() {
        //List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(PageRequest.of(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/HodAddFaculty.jsp");
        return mv;

    }
    @RequestMapping(value = "/auth/uploadAICTEapproval")
    public ModelAndView uploadAICTEapproval() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadAICTEapproval.jsp");
        return model;

    }
    @ResponseBody
    @RequestMapping(value="/hod/uploadfile/addfaculty",method = RequestMethod.POST)
    public String addFaculty(
            @RequestParam("facultyPersonalEmail") String facultyPersonalEmail,
            @RequestParam("facultyOfficialEmail") String facultyOfficialEmail,
            @RequestParam("facultyMobNo") Long facultyMobNo,
            @RequestParam("facultyName") String facultyName,
            @RequestParam("deptId") Long deptId,
            @RequestParam("userName") String username){
        return facultyProfileService.addFaculty(facultyPersonalEmail, facultyOfficialEmail, facultyMobNo, facultyName,
                deptId, username);
    }
    @RequestMapping(value = "/auth/uploadDownload")
    public ModelAndView uploadDownload() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadDownload.jsp");
        return model;

    }
    @RequestMapping(value = "/auth/uploadResponsibilityDoc")
    public ModelAndView uploadResponsibilityDoc() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadResponsibilityDoc.jsp");
        return model;

    }
    @RequestMapping(value = "/auth/uploadHoliday")
    public ModelAndView uploadHoliday() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadHoliday.jsp");
        return model;

    }
}
