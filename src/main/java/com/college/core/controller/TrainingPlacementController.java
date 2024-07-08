package com.college.core.controller;

import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.core.model.PlacementsDTO;
import com.college.core.model.StaffDTO;
import com.college.service.PlacementsService;
import com.college.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "trainingplacement")
public class TrainingPlacementController {
    @Autowired
    UserService userService;
    @Autowired
    PlacementsService placementsService;
    @RequestMapping(value = "placementbrochure")
    public ModelAndView getplacementbrochure() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/trainingplacement/placementbrochure.jsp");
        return mv;
    }
    private int getUserType(String loggedInusername) {
        User loggedInUser = userService.findByUsername(loggedInusername);
        Set<Role> role = loggedInUser.getRoles();
        for (Role r : role) {
            if("Admin".equalsIgnoreCase(r.getName())){
                return 1;
            }
        }
        return 0;
    }
    @RequestMapping(value = "placedstudent")
    public ModelAndView getPlacedStudent(Authentication authentication) {
        ModelAndView mv = new ModelAndView();
        List<PlacementsDTO> placementsDTOList =  placementsService.getAllPlacements();
        int userType = 0;

        if (authentication != null) {
            String loggedInusername = authentication.getName();
            userType = getUserType(loggedInusername);
        }

        mv.addObject("placementList", placementsDTOList);
        mv.addObject("userType", userType);
        mv.setViewName("/trainingplacement/placedstudent.jsp");
        return mv;
    }

    @RequestMapping(value = "deletePlacement")
    public void deleteStaff(@RequestParam("placementId") Long placementId , Authentication authentication , HttpServletResponse res) {
        int accessAllow = 0;
        String loggedInusername = "";
        if (authentication != null) {
            loggedInusername = authentication.getName();
            accessAllow = getUserType(loggedInusername);
        }
        PlacementsDTO placementsDTO = placementsService.getPlacementsById(placementId);
        if (accessAllow > 0 ) {
            placementsService.deletePlacements(placementId);
        }
        try {
            res.sendRedirect("placedstudent");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
