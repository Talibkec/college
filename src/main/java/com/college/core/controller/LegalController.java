package com.college.core.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "legal")
public class LegalController {

    @RequestMapping(value = "contactus")
    public ModelAndView contactUs() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("contactus.jsp");
        return mv;
    }


    @RequestMapping(value = "privacypolicy")
    public ModelAndView privacyPolicy() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("privacypolicy.jsp");
        return mv;
    }
    @RequestMapping(value = "feestructure")
    public ModelAndView feeStructure() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("feestructure.jsp");
        return mv;
    }

    @RequestMapping(value = "termandcondition")
    public ModelAndView termAndCodition() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("termandcondition.jsp");
        return mv;
    }


    @RequestMapping(value = "productandservices")
    public ModelAndView productAndServides() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("productandservice.jsp");
        return mv;
    }

    @RequestMapping(value = "refundandcancellation")
    public ModelAndView refundAndCancellation() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("refundandcancellation.jsp");
        return mv;
    }

}
