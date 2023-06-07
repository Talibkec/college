package com.college.core.controller;

import com.college.KECDateHelper;
import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.orm.hibernate3.SpringSessionContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
//@RequestMapping("admin")
public class AdminController {
    @Autowired
    NoticeBoardService noticeBoardService;
    @RequestMapping(value = "/auth/uploadDashboard")
    public ModelAndView getUploadDashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.addObject("Role", ControllerUtility.getRole());
        mv.addObject("noticeList", getNoticeList(list, false));
        mv.setViewName("admin/UploadDashboard.jsp");
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

    @RequestMapping(value = "/hod/hodDashboard")
    public ModelAndView getHodDashboard() {
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ModelAndView mv = new ModelAndView();
        mv.setViewName("admin/HodDashboard.jsp");
        return mv;

    }
    @RequestMapping(value = "/auth/uploadAICTEapproval")
    public ModelAndView uploadAICTEapproval() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadAICTEapproval.jsp");
        return model;

    }
    @RequestMapping(value = "/auth/uploadDownload")
    public ModelAndView uploadDownload() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadDownload.jsp");
        return model;

    }
}
