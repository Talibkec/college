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
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class FacultyHelper {

    @Autowired
    FacultyService facultyService;
    @Autowired
    FacultyDocumentsService facultyDocumentsService;

    @Autowired
    RequestService requestService;

    public List<FacultyDTO> getFacultyByDeptNo(Long deptno) {
        return facultyService.getFacultyByDeptNo(deptno);
    }

    public ModelAndView getFacultyDetails(String userName) {
        ModelAndView mv = new ModelAndView();
        String loggedInUser = ControllerUtility.getUserName();
        List<String> role = ControllerUtility.getRole();
        List<FacultyDocumentsDTO> allFacultyDocuments = facultyDocumentsService.getFacultyDocuments(userName);
        List<RequestDTO> requests = new ArrayList<>();
        FacultyDTO facultyDTO = facultyService.getFaculty(userName);
        if (userName.equalsIgnoreCase(loggedInUser)) {
            requests = requestService.getFacultyRequest(facultyDTO.getFacultyId());
        }
        mv.addObject("facultyId", facultyDTO.getFacultyId());
        mv.addObject("facultyOfficialEmail", facultyDTO.getFacultyOfficialEmail());
        mv.addObject("facultyPersonalEmail", facultyDTO.getFacultyPersonalEmail());
        mv.addObject("facultyMobNo", facultyDTO.getFacultyMobNo());
        mv.addObject("facultyName", facultyDTO.getFacultyName());
        mv.addObject("facultyProp", facultyDTO.getFacultyKeyProps());

        if (facultyDTO.getFileType() != null)
            mv.addObject("fileExtension", "." + facultyDTO.getFileType());
        mv.addObject("facultyDocument", allFacultyDocuments);
        mv.addObject("Role", role);
        mv.addObject("UserName", loggedInUser);
        mv.addObject("requests", requests);
        return mv;
    }

    public List<String> facultyNames(List<FacultyDTO> faculties) {
        List<String> names = new ArrayList<>();
        for (FacultyDTO facultyDTO : faculties) {
            names.add(facultyDTO.getFacultyName());
        }
        return names;
    }

    public ModelAndView getFacultyDetailsbyId(Long facultyId) {
        ModelAndView mv = new ModelAndView();
        String userName = ControllerUtility.getUserName();
        List<String> role = ControllerUtility.getRole();
        Map<Long, List<Map<String, String>>> facultyNameByDeptNo = facultyService.getFacultyNameByDeptNo();
        FacultyDTO facultyDTO = facultyService.getFacultyById(facultyId);
        List<Map<String, String>> facultyNames = facultyNameByDeptNo.get(facultyDTO.getDepartmentId());
        List<FacultyDocumentsDTO> documents = facultyDocumentsService.getFacultyDocuments(facultyDTO.getUser().getUsername());
        mv.addObject("facultyDetails",facultyDTO);
        mv.addObject("facultyDocument", documents);
        mv.addObject("facultyNameList", facultyNames);
        mv.addObject("fUserName",facultyDTO.getUser().getUsername());
        mv.addObject("loggedInUser", userName);
        mv.addObject("Role", role);


        return mv;
    }


}
