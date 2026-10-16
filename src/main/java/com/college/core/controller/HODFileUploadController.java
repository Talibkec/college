package com.college.core.controller;

import com.college.service.UploadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/** Department notice upload by an HOD (the form has no link field, so none is stored). */
@Controller
public class HODFileUploadController {

    private static final Logger logger = LoggerFactory.getLogger(HODFileUploadController.class);

    private final UploadService uploadService;

    public HODFileUploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @ResponseBody
    @RequestMapping(value = "/hod/api/upload", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(
            @RequestParam("noticeFile") MultipartFile uploadfile, @RequestParam("noticeHeader") String noticeHeader,
            @RequestParam("noticeType") String noticeType, @RequestParam("isScrollable") Boolean isScrollable,
            @RequestParam("date") String date) {
        logger.debug("Single file upload!");
        uploadService.saveNotice(ControllerUtility.getPrincipalName(), noticeHeader, noticeType, null, isScrollable,
                uploadfile, date);
        String body = ControllerUtility.noticeUploadResponse(uploadfile.getOriginalFilename(), noticeHeader, noticeType);
        return new ResponseEntity<>(body, new HttpHeaders(), HttpStatus.OK);
    }
}
