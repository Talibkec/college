package com.college.core.controller;

import com.college.core.entity.*;
import com.college.core.model.AicteDocumentsDTO;
import com.college.core.model.HolidayDTO;
import com.college.core.model.ResponsibilityDocDTO;
import com.college.repository.FacultyRepository;
import com.college.service.AllDocuments;
import com.college.service.HolidayService;
import com.college.service.ResponsibilityDocService;
import com.college.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
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
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping(value = "approval")
public class ApprovalController {
    @Autowired
    AllDocuments allDocuments;
    @Autowired
    HolidayService holidayService;
    @Autowired
    ResponsibilityDocService responsibilityDocService;
    @Autowired
    FacultyRepository facultyRepository;
    @Autowired
    UserService userService;
    @Autowired
    private MessageSource messages;
    @Autowired
    private JavaMailSender mailSender;

    @RequestMapping(value = "akuapproval")
    public ModelAndView getAkuApproval() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/approval/akuapproval.jsp");
        return mv;
    }
    @RequestMapping(value = "grievance")
    public ModelAndView getGrievance() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/approval/grievance.jsp");
        return mv;
    }
    @RequestMapping(value = "raisequery")
    public ResponseEntity raiseQuery(@RequestParam("name") String name ,@RequestParam("email") String email,@RequestParam("phone") String phone ,@RequestParam("query") String query
            ,@RequestParam("registration") String registration ) {


        SimpleMailMessage emailObj = new SimpleMailMessage();
        emailObj.setSubject("New Grievence Query Received");
        emailObj.setText("Grievence details are \n" +
                "\nName:- " + name +
                "\nRegistration:- " + registration +
                "\nPhone no :- " + phone +
                "Email:- " + email +
                "\nQuery:-\n " + query
        );
        emailObj.setTo("principal@keck.ac.in");
        emailObj.setFrom("test@keck.ac.in");
        try{
            mailSender.send(emailObj);
        }catch(Exception e){

        }

        return ResponseEntity.ok("<script>window.location.href='./grievance'</script>");
    }



    @RequestMapping(value = "mandatorydisclosure")
    public ModelAndView getMandatoryDisclosure() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/approval/mandatorydisclosure.jsp");
        return mv;
    }
    @RequestMapping(value = "aicteapproval")
    public ModelAndView getAicteApproval(Authentication authentication) {
        boolean accessAllow = false;
        if(authentication != null){
            String loggedInusername = authentication.getName();
            User loggedInUser = userService.findByUsername(loggedInusername);
            Set<Role> role = loggedInUser.getRoles();

            for ( Role r: role) {
                System.out.println(r.getName());
                if(Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")){
                    accessAllow = true;
                }
            }

        }

        System.out.println("in aicte approval method");
        ModelAndView mv = new ModelAndView();
        List<AicteDocumentsDTO> allDocumentList = allDocuments.getAllDocuments();
        mv.addObject("allDocuments" , allDocumentList);
        mv.addObject("showDeleteBtn" , accessAllow);
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
    @RequestMapping(value = "holiday/{id}")
    public ResponseEntity<byte[]> getHoliday(@PathVariable("id") Long documentId) throws IOException {
        System.out.println("User tried to download document with id :- " + documentId);
        HolidayDTO holidayDTO = holidayService.getDownloadDocument(documentId);
        String filename = holidayDTO.getFileName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/" + holidayDTO.getFileType()));
        headers.add("content-disposition", "inline;filename="+filename);
        if (holidayDTO.getUploadedFile() != null) {
            InputStream in = new ByteArrayInputStream(holidayDTO.getUploadedFile());
            return  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);

        }
        return null;

    }
    @RequestMapping(value = "deleteHoliday/{id}")
    public ResponseEntity<?> deleteHoliday(@PathVariable("id") Long documentId, Authentication authentication) throws IOException {
        boolean accessAllow = false;
        if(authentication != null){
            String loggedInusername = authentication.getName();
            User loggedInUser = userService.findByUsername(loggedInusername);
            Set<Role> role = loggedInUser.getRoles();

            for ( Role r: role) {
                System.out.println(r.getName());
                if(Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")){
                    accessAllow = true;
                }
            }

        }
        if(!accessAllow){ return new ResponseEntity<>("Access Denied" , new HttpHeaders() , HttpStatus.OK);}
        System.out.println("User tried to delete document with id :- " + documentId);
        holidayService.deleteItem(documentId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='/academic/holidays';</script>" , headers , HttpStatus.OK);

    }
    @RequestMapping(value = "responsibilityDoc/{id}")
    public ResponseEntity<byte[]> getResponsiilityDoc(@PathVariable("id") Long documentId) throws IOException {
        System.out.println("User tried to responsibility document with id :- " + documentId);
        ResponsibilityDocDTO responsibilityDocDTO = responsibilityDocService.getDownloadDocument(documentId);
        String filename = responsibilityDocDTO.getFileName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/" + responsibilityDocDTO.getFileType()));
        headers.add("content-disposition", "inline;filename="+filename);
        if (responsibilityDocDTO.getUploadedFile() != null) {
            InputStream in = new ByteArrayInputStream(responsibilityDocDTO.getUploadedFile());
            return  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);

        }
        return null;

    }
    @RequestMapping(value = "deleteResponsibilityDoc/{id}")
    public ResponseEntity<?> deleteResponsibilityDoc(@PathVariable("id") Long documentId, Authentication authentication) throws IOException {
        boolean accessAllow = false;
        if(authentication != null){
            String loggedInusername = authentication.getName();
            User loggedInUser = userService.findByUsername(loggedInusername);
            Set<Role> role = loggedInUser.getRoles();

            for ( Role r: role) {
                System.out.println(r.getName());
                if(Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")){
                    accessAllow = true;
                }
            }

        }
        if(!accessAllow){ return new ResponseEntity<>("Access Denied" , new HttpHeaders() , HttpStatus.OK);}
        System.out.println("User tried to delete document with id :- " + documentId);
        responsibilityDocService.deleteItem(documentId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='/about/administration';</script>" , headers , HttpStatus.OK);

    }
    @RequestMapping(value = "deletedocument/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable("id") Long documentId, Authentication authentication) throws IOException {
        boolean accessAllow = false;
        if(authentication != null){
            String loggedInusername = authentication.getName();
            User loggedInUser = userService.findByUsername(loggedInusername);
            Set<Role> role = loggedInUser.getRoles();

            for ( Role r: role) {
                System.out.println(r.getName());
                if(Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")){
                    accessAllow = true;
                }
            }

        }
        if(!accessAllow){ return new ResponseEntity<>("Access Denied" , new HttpHeaders() , HttpStatus.OK);}
        System.out.println("User tried to delete document with id :- " + documentId);
        allDocuments.deleteAicteDocument(documentId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='/approval/aicteapproval';</script>" , headers , HttpStatus.OK);

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
