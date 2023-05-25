package com.college.core.controller;

import com.college.core.entity.Administration;
import com.college.core.model.AdministrationDTO;
import com.college.service.AdministrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import java.util.List;

@Controller

@RequestMapping("about")
public class AboutController {
    @Autowired
    AdministrationService administrationService;
    @RequestMapping(value = "history")
    public ModelAndView getHistory() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/history.jsp");
        return mv;

    }

    @RequestMapping(value = "vision")
    public ModelAndView getVision() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/vision.jsp");
        return mv;
    }

    @RequestMapping(value = "principal")
    public ModelAndView getPrincipal() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/principal.jsp");
        return mv;
    }

    @RequestMapping(value = "allotment")
    public ModelAndView getAllotment() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/allotment.jsp");
        return mv;
    }

    @RequestMapping(value = "affiliation")
    public ModelAndView getAffiliation() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/affiliation.jsp");
        return mv;

    }

    @RequestMapping(value = "administration")
    public ModelAndView getAdministration() {
        ModelAndView mv = new ModelAndView();
        //List<String> role= ControllerUtility.getRole();
        //List<AdministrationDTO> administration =administrationService.getAllAdministration();
        //mv.addObject("adminsDetails",administration);
        //mv.addObject("Role", role);
        mv.setViewName("about/administration.jsp");
        return mv;
    }


    @RequestMapping(value = "staff")
    public ModelAndView getStaff() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("about/staff.jsp");
        return mv;
    }

}



