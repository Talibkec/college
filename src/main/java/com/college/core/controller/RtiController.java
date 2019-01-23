package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "rti")

public class RtiController {

    @RequestMapping(value = "publicinformationcell")
    public ModelAndView getPublicInformationCell(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/rti/publicinformationcell");
        return mv;
    }

    @RequestMapping(value = "rtiact")
    public ModelAndView getRtiAct(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("/rti/rtiact");
        return mv;
    }

    @RequestMapping(value = "rtiapplication")
    public ModelAndView getRtiApplication(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("/rti/rtiapplication");
        return mv;
    }

    @RequestMapping(value = "rtistatus")
    public ModelAndView getRtiStatus(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("/rti/rtistatus");
        return mv;
    }

}
