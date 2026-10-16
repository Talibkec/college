package com.college.core.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.college.core.model.DownloadDTO;
import com.college.service.DownloadService;
import com.college.service.UploadService;
import com.college.service.AccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller

public class NBAController {
    private static final Logger logger = LoggerFactory.getLogger(NBAController.class);
  private final AccessService accessService;
  private final DownloadService downloadService;
  private final UploadService uploadService;

  public NBAController(AccessService accessService, DownloadService downloadService, UploadService uploadService) {
      this.accessService = accessService;
      this.downloadService = downloadService;
      this.uploadService = uploadService;
  }

  @RequestMapping(value = "nba/nba")
  public ModelAndView getNBA(Authentication authentication) {

    boolean accessAllow = accessService.isAdminOrHod(authentication);

    ModelAndView mv = new ModelAndView();
    List<DownloadDTO> allDocumentList = downloadService.getAllNBADocuments();
    mv.setViewName("nba/nba.jsp");
    for (DownloadDTO download : allDocumentList) {
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
  public ResponseEntity<String> uploadDownload(
          @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption,
          Authentication authentication) {
    String problem = UploadService.documentUploadProblem(uploadfile, caption, "Please provide a caption");
    if (problem != null) {
      return SlideImageController.badRequest(problem);
    }
    if (authentication.isAuthenticated()) {
      uploadService.saveDownload(uploadfile, caption, "nba", authentication.getName());
    }
    return SlideImageController.ok("Document uploaded successfully");
  }

}
