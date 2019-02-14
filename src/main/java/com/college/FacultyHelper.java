package com.college;

import com.college.core.controller.ControllerUtility;
import com.college.core.model.FacultyDTO;
import com.college.core.model.FacultyDocumentsDTO;
import com.college.core.model.RequestDTO;
import com.college.service.FacultyDocumentsService;
import com.college.service.FacultyService;
import com.college.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class FacultyHelper {

    @Autowired
    FacultyService facultyService;
    @Autowired
    FacultyDocumentsService facultyDocumentsService;

    @Autowired
    RequestService requestService;

    public  ModelAndView getFacultyDetails(String userName) {
        ModelAndView mv = new ModelAndView();
        String loggedInUser = ControllerUtility.getUserName();
        String role = ControllerUtility.getRole();
        List<FacultyDocumentsDTO> allFacultyDocuments  = facultyDocumentsService.getFacultyDocuments(userName);
        List<RequestDTO> requests = new ArrayList<>();
        FacultyDTO facultyDTO = facultyService.getFaculty(userName);
        if(userName.equalsIgnoreCase(loggedInUser)) {
            requests = requestService.getFacultyRequest(facultyDTO.getFacultyId());
        }
        mv.addObject("facultyId", facultyDTO.getFacultyId());
        mv.addObject("facultyEmail" ,facultyDTO.getFacultyEmail());
        mv.addObject("facultyMobNo", facultyDTO.getFacultyMobNo());
        mv.addObject("facultyName", facultyDTO.getFacultyName());
        if( facultyDTO.getFileType() != null)
            mv.addObject("fileExtension", "." + facultyDTO.getFileType());
        mv.addObject("facultyDocument", allFacultyDocuments );
        mv.addObject("Role", role);
        mv.addObject("UserName", loggedInUser);
        mv.addObject("requests", requests);
        return  mv;
    }
}
