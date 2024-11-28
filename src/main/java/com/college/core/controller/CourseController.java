package com.college.core.controller;

import com.college.core.model.CourseDTO;

import com.college.service.CourseService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/auth/course", method = RequestMethod.GET)
    public ModelAndView Courses() {

        ModelAndView mv = new ModelAndView();

        mv.addObject("Role", ControllerUtility.getRole());
        mv.setViewName("/coursecreator.jsp");

        return mv;
    }

    @RequestMapping(value = "/auth/uploadfile/coursecreator", method = RequestMethod.POST)
    public ResponseEntity<String> addcourseseat(
            @RequestParam("branchName") String branchName,
            @RequestParam("regularSeat") Long regularSeat,
            @RequestParam("lateralSeat") Long lateralSeat
    ) {
        CourseDTO courseDTO = new CourseDTO();
        courseDTO.setBranchName(branchName);
        courseDTO.setRegularSeat(regularSeat);
        courseDTO.setLateralSeat(lateralSeat);

        courseService.saveCourse(courseDTO);

        return new ResponseEntity<>("Course added successfully", HttpStatus.OK);
    }


    @RequestMapping(value = "/academic/admission", method = RequestMethod.GET)
    public ModelAndView getCourses() {

        List<CourseDTO> courses = courseService.getAllCourses();


        ModelAndView mv = new ModelAndView();


        mv.addObject("courses", courses);

        mv.addObject("Role", ControllerUtility.getRole());
        mv.setViewName("/academic/admission.jsp");

        return mv;
    }

    @RequestMapping(value = "/auth/course/{id}", method = RequestMethod.GET)
    public void deleteCourse(@PathVariable("id") Long courseId, HttpServletResponse res) throws IOException {
        courseService.deleteCourse(courseId);

        res.sendRedirect("/auth/course");
    }

//    @RequestMapping(value = "/auth/course/{id}", method = RequestMethod.POST)
//    public ResponseEntity<String> updateCourse(
//            @PathVariable("id") Long courseId,
//            @RequestParam("branchName") String branchName,
//            @RequestParam("regularSeat") Long regularSeat,
//            @RequestParam("lateralSeat") Long lateralSeat
//    ) {
//        CourseDTO courseDTO = new CourseDTO();
//        courseDTO.setBranchName(branchName);
//        courseDTO.setRegularSeat(regularSeat);
//        courseDTO.setLateralSeat(lateralSeat);
//
//        courseService.updateCourse(courseId, courseDTO);
//
//        return new ResponseEntity<>("Course updated successfully", HttpStatus.OK);
//    }


}
