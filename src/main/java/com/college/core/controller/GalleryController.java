package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "gallery")

public class GalleryController {

    @RequestMapping(value = "photogallery")
    public ModelAndView getPhotoGallery(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/gallery/photogallery.jsp");
        return mv;
    }
    @RequestMapping(value = "mediagallery")
    public ModelAndView getMediaGallery(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/gallery/mediagallery.jsp");
        return mv;
        }
}
