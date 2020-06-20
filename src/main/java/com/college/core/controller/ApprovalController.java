package com.college.core.controller;

import com.college.core.entity.Faculty;
import com.college.core.entity.FacultyKeyPropValues;
import com.college.core.entity.FacultyKeyProps;
import com.college.repository.FacultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "approval")
public class ApprovalController {

    @Autowired
    FacultyRepository facultyRepository;


    @RequestMapping(value = "akuapproval")
    public ModelAndView getAkuApproval() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/approval/akuapproval.jsp");
        return mv;
    }

    @RequestMapping(value = "aicteapproval")
    public ModelAndView getAicteApproval() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/approval/aicteapproval.jsp");
        return mv;
    }

    @RequestMapping(value = "addFaculty", method = RequestMethod.GET)
    public void addFaculty() {
        FacultyKeyPropValues facultyKeyPropValues = new FacultyKeyPropValues();
        FacultyKeyProps facultyKeyProps = new FacultyKeyProps();
        facultyKeyPropValues.setKeyPropVal("Design and Development of College Website");
        facultyKeyPropValues.setFacultyKeyProps(facultyKeyProps);
        facultyKeyProps.getKeyPropVals().add(facultyKeyPropValues);
        facultyKeyPropValues = new FacultyKeyPropValues();
        facultyKeyPropValues.setKeyPropVal("Cultural & Sports");
        facultyKeyPropValues.setFacultyKeyProps(facultyKeyProps);
        facultyKeyProps.getKeyPropVals().add(facultyKeyPropValues);
        facultyKeyProps.setKeyPropertyName("Administrative Responsibilities");
        Faculty faculty = new Faculty();
        facultyKeyProps.setFaculty(faculty);
        faculty.getFacultyKeyProps().add(facultyKeyProps);
       // faculty.setFacultyName("fname");
        facultyRepository.save(faculty);
        facultyRepository.findOne(1l);
        System.out.println();
    }

}
