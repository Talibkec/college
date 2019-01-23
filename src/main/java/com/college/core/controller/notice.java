package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("category")
public class notice {
    @RequestMapping(value = "notices")
    public ModelAndView getNotices() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category/notices");
        return mv;
    }

    @RequestMapping(value = "kecwebteam")
    public ModelAndView getKecwebteam() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category/kecwebteam");
        return mv;
    }
    @RequestMapping(value = "department")
    public ModelAndView getDepartment() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("category/department");
        return mv;
    }
}