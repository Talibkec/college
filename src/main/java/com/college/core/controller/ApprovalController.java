package com.college.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.college.core.model.AicteDocumentsDTO;
import com.college.core.model.HolidayDTO;
import com.college.core.model.LabDocumentDTO;
import com.college.core.model.ResponsibilityDocDTO;
import com.college.service.*;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@Controller
@RequestMapping(value = "approval")
public class ApprovalController {
    private static final Logger logger = LoggerFactory.getLogger(ApprovalController.class);

    private final AllDocuments allDocuments;
    private final HolidayService holidayService;
    private final ResponsibilityDocService responsibilityDocService;
    private final AccessService accessService;
    private final LabDocumentService labDocumentService;
    private final LabDocumentAccess labDocumentAccess;
    private final GrievanceService grievanceService;
    private final FacultyService facultyService;

    public ApprovalController(FacultyService facultyService,
            GrievanceService grievanceService,
            AllDocuments allDocuments,
            HolidayService holidayService,
            ResponsibilityDocService responsibilityDocService,
            AccessService accessService,
            LabDocumentService labDocumentService,
            LabDocumentAccess labDocumentAccess) {
        this.labDocumentAccess = labDocumentAccess;
        this.facultyService = facultyService;
        this.grievanceService = grievanceService;
        this.allDocuments = allDocuments;
        this.holidayService = holidayService;
        this.responsibilityDocService = responsibilityDocService;
        this.accessService = accessService;
        this.labDocumentService = labDocumentService;
    }

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
        grievanceService.sendQuery(name, email, phone, query, registration);
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
        boolean accessAllow = accessService.isAdminOrHod(authentication);

        logger.debug("Listing AICTE documents");
        ModelAndView mv = new ModelAndView();
        List<AicteDocumentsDTO> allDocumentList = allDocuments.getAllDocuments();
        mv.addObject("allDocuments" , allDocumentList);
        mv.addObject("showDeleteBtn" , accessAllow);
        mv.setViewName("/approval/aicteapproval.jsp");
        return mv;
    }
    @RequestMapping(value = "document/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable("id") Long documentId) throws IOException {
        logger.debug("Download of document {}", documentId);
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
        logger.debug("Download of document {}", documentId);
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


    @RequestMapping(value = "lab/{id}")
    public ResponseEntity<byte[]> getLab(@PathVariable("id") Long documentId) throws IOException {
        logger.debug("Download of document {}", documentId);
        LabDocumentDTO labDocumentDTO = labDocumentService.getDownloadDocument(documentId);
        String filename = labDocumentDTO.getFileName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/" + labDocumentDTO.getFileType()));
        headers.add("content-disposition", "inline;filename="+filename);
        if (labDocumentDTO.getUploadedFile() != null) {
            InputStream in = new ByteArrayInputStream(labDocumentDTO.getUploadedFile());
            return  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);

        }
        return null;

    }

    @RequestMapping(value = "deleteHoliday/{id}")
    public ResponseEntity<?> deleteHoliday(@PathVariable("id") Long documentId, Authentication authentication) throws IOException {
        boolean accessAllow = accessService.isAdminOrHod(authentication);
        if(!accessAllow){ return new ResponseEntity<>("Access Denied" , new HttpHeaders() , HttpStatus.OK);}
        logger.info("Deleting document {}", documentId);
        holidayService.deleteItem(documentId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='/academic/holidays';</script>" , headers , HttpStatus.OK);

    }



    @RequestMapping(value = "deleteLab/{id}")
    public ResponseEntity<?> deleteLab(@PathVariable("id") Long documentId, Authentication authentication) throws IOException {
        Integer deptId = labDocumentService.departmentOf(documentId);
        // Admin, or the HOD of the document's department (before: every HOD for every department)
        boolean accessAllow = accessService.isAdminOrHod(authentication)
                && (deptId == null || labDocumentAccess.canManage(authentication, deptId));
        if(!accessAllow){ return new ResponseEntity<>("Access Denied" , new HttpHeaders() , HttpStatus.OK);}
        logger.info("Deleting document {}", documentId);
        labDocumentService.deleteItem(documentId);
        HttpHeaders headers = new HttpHeaders();
        // back to the lab page of the department (before: the holidays page)
        String back = deptId == null ? "/academic/holidays" : "/academic/lab?dept_id=" + deptId;
        return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='" + back + "';</script>" , headers , HttpStatus.OK);

    }
    @RequestMapping(value = "responsibilityDoc/{id}")
    public ResponseEntity<byte[]> getResponsiilityDoc(@PathVariable("id") Long documentId) throws IOException {
        logger.debug("Download of responsibility document {}", documentId);
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
        boolean accessAllow = accessService.isAdminOrHod(authentication);
        if(!accessAllow){ return new ResponseEntity<>("Access Denied" , new HttpHeaders() , HttpStatus.OK);}
        logger.info("Deleting document {}", documentId);
        responsibilityDocService.deleteItem(documentId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='/about/administration';</script>" , headers , HttpStatus.OK);

    }
    @RequestMapping(value = "deletedocument/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable("id") Long documentId, Authentication authentication) throws IOException {
        boolean accessAllow = accessService.isAdminOrHod(authentication);
        if(!accessAllow){ return new ResponseEntity<>("Access Denied" , new HttpHeaders() , HttpStatus.OK);}
        logger.info("Deleting document {}", documentId);
        allDocuments.deleteAicteDocument(documentId);
        HttpHeaders headers = new HttpHeaders();
        return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='/approval/aicteapproval';</script>" , headers , HttpStatus.OK);

    }
    @RequestMapping(value = "addFaculty", method = RequestMethod.GET)
    public void addFaculty() {
        facultyService.addPlaceholderFaculty();
    }

}
