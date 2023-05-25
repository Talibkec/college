package com.college.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "trainingplacement")
public class TrainingPlacementController {

    @RequestMapping(value = "placementbrochure")
    public ModelAndView getplacementbrochure() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/trainingplacement/placementbrochure.jsp");
        return mv;
    }

    @RequestMapping(value = "rulesoftrainingplacement")
    public ModelAndView getRulesOfTrainingPlacement() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/trainingplacement/rulesoftrainingplacement.jsp");
        return mv;
    }

    @RequestMapping(value = "studentplacementcoordinator")
    public ModelAndView getStudentPlacementCoordinator() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/trainingplacement/studentplacementcoordinator.jsp");
        return mv;
    }

    @RequestMapping(value = "tipsforresume")
    public ModelAndView getTipsForResume() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/trainingplacement/tipsforresume.jsp");
        return mv;
    }

    @RequestMapping(value = "aboutplacement")
    public ModelAndView getAboutPlacement() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/trainingplacement/aboutplacement.jsp");
        return mv;
    }
}
