package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "facilities")

public class FacilitiesController {

    @RequestMapping(value = "bank")
    public ModelAndView getBank() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("facilities/bank.jsp");
        return mv;
    }
    @RequestMapping(value = "downloads")
    public ModelAndView getdownloads() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("facilities/downloads.jsp");
        return mv;
    }
    @RequestMapping(value = "centrallibrary")
    public ModelAndView getCentralLibrary() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/centrallibrary.jsp");
        return mv;
    }

    @RequestMapping(value = "club")
    public ModelAndView getClub() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/club.jsp");
        return mv;
    }

    @RequestMapping(value = "contactus")
    public ModelAndView getContactUs() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/contactus.jsp");
        return mv;
    }

    @RequestMapping(value = "csecenter")
    public ModelAndView getCseCenter() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/csecenter.jsp");
        return mv;
    }

    @RequestMapping(value = "guesthouse")
    public ModelAndView getGuestHouse() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/guesthouse.jsp");
        return mv;
    }

    @RequestMapping(value = "gymnasium")
    public ModelAndView getGymnasium() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/gymnasium.jsp");
        return mv;
    }

    @RequestMapping(value = "healthcenter")
    public ModelAndView getHealthCenter() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/healthcenter.jsp");
        return mv;
    }

    @RequestMapping(value = "hostels")
    public ModelAndView getHostels() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/hostels.jsp");
        return mv;
    }

    @RequestMapping(value = "placementbrochure")
    public ModelAndView getplacementbrochure() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/placementbrochure.jsp");
        return mv;
    }

    @RequestMapping(value = "sportsfacilities")
    public ModelAndView getSportsFacilities() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/sportsfacilities.jsp");
        return mv;
    }

    @RequestMapping(value = "startupcell")
    public ModelAndView getStartUpCell() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/startupcell.jsp");
        return mv;
    }

    @RequestMapping(value = "usefulllink")
    public ModelAndView getUseFullLink() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/usefullink.jsp");
        return mv;
    }

    @RequestMapping(value = "wifi")
    public ModelAndView getWifi() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/wifi.jsp");
        return mv;
    }

    @RequestMapping(value = "medicalfacilities")
    public ModelAndView getMedicalFacilities() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/medicalfacilities.jsp");
        return mv;
    }
}
