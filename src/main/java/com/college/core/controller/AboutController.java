package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;

@Controller

@RequestMapping("about")
public class AboutController {

    @RequestMapping(value = "history")
    public ModelAndView getHistory() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/history");
        return mv;

    }

    @RequestMapping(value="vision")
    public ModelAndView getVision() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/vision");
        return mv;
    }
    @RequestMapping(value="principal")
    public ModelAndView getPrincipal() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/principal");
        return mv;
    }
    @RequestMapping(value="allotment")
    public ModelAndView getAllotment(){
        ModelAndView mv =new ModelAndView();
        mv.setViewName("about/allotment");
        return mv;
    }

    @RequestMapping(value="affiliation")
        public ModelAndView getAffiliation()
    {
        ModelAndView mv =new ModelAndView();
        mv.setViewName("about/affiliation");
        return mv;

    }
  @RequestMapping(value="administration")
    public ModelAndView getAdministration()
  {
      ModelAndView mv = new ModelAndView();
      mv.setViewName("about/administration");
      return mv;
  }
    @RequestMapping(value="staff")
    public ModelAndView getStaff()
    {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/staff");
        return mv;
    }

}



