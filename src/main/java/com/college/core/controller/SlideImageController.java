package com.college.core.controller;

import com.college.service.LabDocumentAccess;
import com.college.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/** Admin uploads: home page slides, gallery images and the downloadable documents. */
@Controller
public class SlideImageController {

    private static final Logger logger = LoggerFactory.getLogger(SlideImageController.class);

    private final UploadService uploadService;
    private final LabDocumentAccess labDocumentAccess;

    public SlideImageController(UploadService uploadService, LabDocumentAccess labDocumentAccess) {
        this.uploadService = uploadService;
        this.labDocumentAccess = labDocumentAccess;
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadslideimage", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(
            @RequestParam("slideImage") MultipartFile uploadfile,
            @RequestParam("caption") String caption,
            @RequestParam("flag") int flag) {
        logger.debug("Single file upload!");
        String problem = uploadService.slideImageProblem(uploadfile, caption, flag);
        if (problem != null) {
            return badRequest(problem);
        }
        uploadService.saveSlideImage(uploadfile, caption, flag);
        return ok("Document uploaded successfully");
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadAICTEapproval", method = RequestMethod.POST)
    public ResponseEntity<String> uploadAICTEapproval(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption) {
        logger.debug("Single aicte file upload!");
        String problem = UploadService.documentUploadProblem(uploadfile, caption, "Please provide a year");
        if (problem != null) {
            return badRequest(problem);
        }
        uploadService.saveAicteDocument(uploadfile, caption);
        return ok("Document uploaded successfully");
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadDownload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadDownload(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption,
            Authentication authentication) {
        logger.debug("Single download file upload!");
        String problem = UploadService.documentUploadProblem(uploadfile, caption, "Please provide a caption");
        if (problem != null) {
            return badRequest(problem);
        }
        if (authentication.isAuthenticated()) {
            uploadService.saveDownload(uploadfile, caption, "download", authentication.getName());
        }
        return ok("Document uploaded successfully");
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadResponsibilityDoc", method = RequestMethod.POST)
    public ResponseEntity<String> uploadResponsibilityDoc(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption,
            Authentication authentication) {
        logger.debug("Single responsibility file upload!");
        String problem = UploadService.documentUploadProblem(uploadfile, caption, "Please provide a session");
        if (problem != null) {
            return badRequest(problem);
        }
        if (authentication.isAuthenticated()) {
            uploadService.saveResponsibilityDoc(uploadfile, caption, authentication.getName());
        }
        return ok("Responsibility Doc uploaded successfully");
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadHoliday", method = RequestMethod.POST)
    public ResponseEntity<String> uploadHoliday(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption,
            Authentication authentication) {
        logger.debug("Single holiday file upload!");
        String problem = UploadService.documentUploadProblem(uploadfile, caption, "Please provide a year");
        if (problem != null) {
            return badRequest(problem);
        }
        if (authentication.isAuthenticated()) {
            uploadService.saveHoliday(uploadfile, caption, authentication.getName());
        }
        return ok("Holiday Doc uploaded successfully");
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadLabDoc", method = RequestMethod.POST)
    public ResponseEntity<String> uploadLabDoc(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption,
            @RequestParam("deptId") Integer depId, Authentication authentication) {
        logger.debug("Single lab file upload!");
        if (!labDocumentAccess.canManage(authentication, depId)) { // Admin, or the HOD of that department
            return new ResponseEntity<>("You may only upload lab documents of your own department.", new HttpHeaders(), HttpStatus.FORBIDDEN);
        }
        String problem = UploadService.documentUploadProblem(uploadfile, caption, "Please provide a year");
        if (problem != null) {
            return badRequest(problem);
        }
        if (authentication.isAuthenticated()) {
            uploadService.saveLabDoc(uploadfile, caption, depId, authentication.getName());
        }
        return ok("Lab Doc uploaded successfully");
    }

    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadgalleryimage", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFiles(
            @RequestParam("galleryImage") MultipartFile uploadfile, @RequestParam("caption") String caption) {
        logger.debug("Single file upload!");
        String problem = UploadService.documentUploadProblem(uploadfile, caption, "Please give a proper caption");
        if (problem != null) {
            return badRequest(problem);
        }
        uploadService.saveGalleryImage(uploadfile, caption);
        // response kept from the original code (including the MultipartFile's toString)
        return ok("/wp-content/uploads/notice/" + uploadfile.getOriginalFilename() + "," + caption + "," + uploadfile);
    }

    @RequestMapping(value = "/auth/uploadfile/uploadslideimage", method = RequestMethod.GET)
    public ModelAndView displaySlideImage() {
        ModelAndView model = new ModelAndView();
        model.setViewName("uploadslideimage.jsp");
        return model;
    }

    static ResponseEntity<String> ok(String body) {
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
    }

    static ResponseEntity<String> badRequest(String body) {
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
