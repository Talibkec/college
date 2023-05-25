package com.college.core.controller;

import com.college.core.entity.Faculty;
import com.college.core.entity.FacultyKeyPropValues;
import com.college.core.entity.FacultyKeyProps;
import com.college.core.model.AicteDocumentsDTO;
import com.college.core.model.ImageSlideDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.repository.FacultyRepository;
import com.college.service.AllDocuments;
import com.college.service.AllDocumentsServiceImpl;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping(value = "approval")
public class ApprovalController {
    @Autowired
    AllDocuments allDocuments;
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
        System.out.println("in aicte approval method");
        ModelAndView mv = new ModelAndView();
        List<AicteDocumentsDTO> allDocumentList = allDocuments.getAllDocuments();
        mv.addObject("allDocuments" , allDocumentList);
        mv.setViewName("/approval/aicteapproval.jsp");
        return mv;
    }
    @RequestMapping(value = "document/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable("id") Long documentId) throws IOException {
        System.out.println("User tried to download document with id :- " + documentId);
        AicteDocumentsDTO aicteDocumentsDTO = allDocuments.getAicteDocument(documentId);
        String filename = aicteDocumentsDTO.getFileName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/" + aicteDocumentsDTO.getFileType()));
        headers.add("content-disposition", "inline;filename="+filename);
        if (aicteDocumentsDTO.getImage() != null) {
            InputStream in = new ByteArrayInputStream(aicteDocumentsDTO.getImage());
            return  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);

        }
        return null;

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
