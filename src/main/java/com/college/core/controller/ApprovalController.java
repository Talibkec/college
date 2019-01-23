package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value ="approval" )
public class ApprovalController {

    @RequestMapping(value = "akuapproval")
    public ModelAndView getAkuApproval(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/approval/akuapproval");
        return mv;
    }
    @RequestMapping(value = "aicteapproval")
    public ModelAndView getAicteApproval(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/approval/aicteapproval");
        return mv;
    }
}
