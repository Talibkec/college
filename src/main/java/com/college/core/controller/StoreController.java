package com.college.core.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value="store")
public class StoreController {
    @RequestMapping(value="addproduct")
    public ModelAndView getAddproduct(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("store/addproduct.jsp");
        return mv;
    }

}
