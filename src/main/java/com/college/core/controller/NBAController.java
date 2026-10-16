package com.college.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.college.core.entity.Download;
import com.college.core.entity.Role;
import com.college.core.entity.User;
import com.college.core.model.DownloadDTO;
import com.college.service.DownloadService;
import com.college.service.UserService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Controller

public class NBAController {
    private static final Logger logger = LoggerFactory.getLogger(NBAController.class);


  @Autowired
  UserService userService;

  @Autowired
  DownloadService downloadService;

  @RequestMapping(value = "nba/nba")
  public ModelAndView getNBA(Authentication authentication) {

    boolean accessAllow = false;
    if(authentication != null){
      String loggedInusername = authentication.getName();
      User loggedInUser = userService.findByUsername(loggedInusername);
      Set<Role> role = loggedInUser.getRoles();

      for ( Role r: role) {
        logger.debug("Role of the logged-in user: {}", r.getName());
        if(Objects.equals(r.getName(), "Admin") || Objects.equals(r.getName(), "HOD")){
          accessAllow = true;
        }
      }

    }

    ModelAndView mv = new ModelAndView();
    List<Download> allDocumentList = downloadService.getAllNBADocuments();
    mv.setViewName("nba/nba.jsp");
    for (Download download : allDocumentList) {
      logger.debug("Document: {}", download.getFileName());
    }
    mv.addObject("allDocuments" , allDocumentList);
    mv.addObject("showDeleteBtn" , accessAllow);

    return mv;
  }

  @RequestMapping(value = "/auth/nba/uploadNBA")
  public ModelAndView uploadDownload() {
    ModelAndView model = new ModelAndView();
    //model.addObject("users", getUsers());
    model.setViewName("/nba/uploadNBA.jsp");
    return model;

  }

  @ResponseBody
  @RequestMapping(value = "/auth/api/nba/uploadNBA", method = RequestMethod.POST)
  public ResponseEntity<?> uploadDownload(
          @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption , Authentication authentication) {
    String fileName = uploadfile.getOriginalFilename();
    String fileType = uploadfile.getContentType();
    if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
      String msg = "";
      if (uploadfile.isEmpty()) {
        msg = "Please select a file.";
      } else {
        msg = "Please provide a caption";
      }
      return new ResponseEntity<>(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    saveDownloadFile(uploadfile, caption, fileName, fileType , authentication);


    return new ResponseEntity("Document uploaded successfully", new HttpHeaders(), HttpStatus.OK);

  }

  private void saveDownloadFile(MultipartFile uploadfile, String caption, String fileName, String fileType , Authentication authentication) {
    if(!authentication.isAuthenticated()){
      return ;
    }
    DownloadDTO downloadDTO = new DownloadDTO();
    downloadDTO.setFileName(caption);
    downloadDTO.setDocType("nba");

    String username = authentication.getName();
    downloadDTO.setUploadedBy(authentication.getName());
    try {
      downloadDTO.setUploadedFile(uploadfile.getBytes());
    } catch (IOException e) {
      logger.error("Could not read the uploaded NBA document {}", fileName, e);
    }
    downloadDTO.setFileType(FilenameUtils.getExtension(fileName));
    downloadService.saveDownloadBoard(downloadDTO);
  }

}
