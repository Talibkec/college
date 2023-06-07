package com.college.core.controller;

import com.college.core.entity.Download;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.core.model.AicteDocumentsDTO;
import com.college.core.model.DownloadDTO;
import com.college.repository.DownloadRepository;
import com.college.service.DownloadService;
import com.college.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller
@RequestMapping(value = "facilities")

public class FacilitiesController {
    @Autowired
    UserService userService;
    @Autowired
    DownloadService downloadService;
    @RequestMapping(value = "bank")
    public ModelAndView getBank() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("facilities/bank.jsp");
        return mv;
    }
    @RequestMapping(value = "downloads")
    public ModelAndView getdownloads(Authentication authentication) {

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

        System.out.println("in download method");
        ModelAndView mv = new ModelAndView();
        List<Download> allDocumentList = downloadService.getAllDownload();
        mv.setViewName("facilities/downloads.jsp");
        //System.out.println(" document list id " + allDocumentList.get(0).getId());
        for (Download download : allDocumentList) {
            System.out.println(download.getFileName());
        }
        mv.addObject("allDocuments" , allDocumentList);
        mv.addObject("showDeleteBtn" , accessAllow);

        return mv;
    }

    @RequestMapping(value = "document/{id}")
    public ResponseEntity<byte[]> getDocument(@PathVariable("id") Long documentId) throws IOException {
        System.out.println("User tried to download document with id :- " + documentId);
        DownloadDTO downloadDTO = downloadService.getDownloadDocument(documentId);
        String filename = downloadDTO.getFileName();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/" + downloadDTO.getFileType()));
        headers.add("content-disposition", "inline;filename="+filename);
        if (downloadDTO.getUploadedFile() != null) {
            InputStream in = new ByteArrayInputStream(downloadDTO.getUploadedFile());
            return  new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.OK);

        }
        return null;

    }
    @RequestMapping(value = "deletedocument/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable("id") Long documentId, Authentication authentication) throws IOException {
        boolean accessAllow = false;
        if (authentication != null) {
            String loggedInusername = authentication.getName();
            User loggedInUser = userService.findByUsername(loggedInusername);
            Set<Role> role = loggedInUser.getRoles();

            for (Role r : role) {
                System.out.println(r.getName());
                if (Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")) {
                    accessAllow = true;
                }
            }
        }
            if (!accessAllow) {
                return new ResponseEntity<>("Authorities required", new HttpHeaders(), HttpStatus.OK);
            }
            System.out.println("User tried to delete download document with id :- " + documentId);
            downloadService.deleteItem(documentId);
            HttpHeaders headers = new HttpHeaders();
            return new ResponseEntity<>("<script>alert('Succesfully deleted the file');window.location.href='/facilities/downloads';</script>", headers, HttpStatus.OK);

        }


    @RequestMapping(value = "centrallibrary")
    public ModelAndView getCentralLibrary() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/centrallibrary.jsp");
        return mv;
    }

    @RequestMapping(value = "club")
    public ModelAndView getClub() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/club.jsp");
        return mv;
    }

    @RequestMapping(value = "contactus")
    public ModelAndView getContactUs() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/contactus.jsp");
        return mv;
    }

    @RequestMapping(value = "csecenter")
    public ModelAndView getCseCenter() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/csecenter.jsp");
        return mv;
    }

    @RequestMapping(value = "guesthouse")
    public ModelAndView getGuestHouse() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/guesthouse.jsp");
        return mv;
    }

    @RequestMapping(value = "gymnasium")
    public ModelAndView getGymnasium() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/gymnasium.jsp");
        return mv;
    }

    @RequestMapping(value = "healthcenter")
    public ModelAndView getHealthCenter() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/healthcenter.jsp");
        return mv;
    }

    @RequestMapping(value = "hostels")
    public ModelAndView getHostels() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/hostels.jsp");
        return mv;
    }

    @RequestMapping(value = "placementbrochure")
    public ModelAndView getplacementbrochure() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/placementbrochure.jsp");
        return mv;
    }

    @RequestMapping(value = "sportsfacilities")
    public ModelAndView getSportsFacilities() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/sportsfacilities.jsp");
        return mv;
    }

    @RequestMapping(value = "startupcell")
    public ModelAndView getStartUpCell() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/startupcell.jsp");
        return mv;
    }

    @RequestMapping(value = "usefulllink")
    public ModelAndView getUseFullLink() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/usefullink.jsp");
        return mv;
    }

    @RequestMapping(value = "wifi")
    public ModelAndView getWifi() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/wifi.jsp");
        return mv;
    }

    @RequestMapping(value = "medicalfacilities")
    public ModelAndView getMedicalFacilities() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/facilities/medicalfacilities.jsp");
        return mv;
    }
}
