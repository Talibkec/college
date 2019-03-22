package com.college.core.controller;

import com.college.core.model.NoticeBoardDTO;
import com.college.service.NoticeBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("category")
public class notice {
    @Autowired
    NoticeBoardService noticeBoardService;
    @RequestMapping(value = "notices")
    public ModelAndView getNotices() {
        ModelAndView mv = new ModelAndView();
        List<NoticeBoardDTO> list = noticeBoardService.getAllNotice(new PageRequest(0, 10));
        ControllerUtility.getNoticelist(list);
        mv.addObject("noticeList",list);
        mv.setViewName("category/notices.jsp");
        return mv;
    }

    @RequestMapping(value = "kecwebteam")
    public ModelAndView getKecwebteam() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category/kecwebteam.jsp");
        return mv;
    }
    @RequestMapping(value = "department")
    public ModelAndView getDepartment() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category/department.jsp");
        return mv;
    }
}