package com.college.core.controller;

import com.college.core.model.ImageSlideDTO;

import com.college.service.ImageSlideService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


@Controller
public class SlideImageController {
    @Autowired
    ImageSlideService imageSlideService;
    private final Logger logger = LoggerFactory.getLogger(SlideImageController.class);


    //private final String UPLOADED_FOLDER = getPath();
    @ResponseBody
    @RequestMapping(value = "/auth/api/uploadslideimage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("slideImage") MultipartFile uploadfile, @RequestParam("caption") String caption) {
        logger.debug("Single file upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();
        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = "";
            if (uploadfile.isEmpty()) {
                msg = "Please select a file.";
            } else {
                msg = "Please give a proper caption";
            }
            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }



        //fileName = UploadFileUtility.saveUploadedFiles(Arrays.asList(uploadfile), UPLOADED_FOLDER);
        saveImageSlide(uploadfile,caption,fileName,fileType);


        String notice = "http://keck.ac.in/wp-content/uploads/notice/" + fileName;
        notice = notice + "," + caption;
        notice = notice + "," + uploadfile;
        return new ResponseEntity(notice, new HttpHeaders(), HttpStatus.OK);

    }
    @RequestMapping(value = "/auth/uploadfile/uploadslideimage", method = RequestMethod.GET)
    public ModelAndView displaySlideImage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadslideimage.jsp");
        return model;
    }

    private void saveImageSlide( MultipartFile uploadfile, String caption,String fileName,String fileType) {
        ImageSlideDTO imageSlideDTO = new ImageSlideDTO();
        imageSlideDTO.setCaption(caption);
        imageSlideDTO.setFileName(fileName);
        imageSlideDTO.setFileType(fileType);

        try {
            imageSlideDTO.setImage(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        imageSlideDTO.setFileType(FilenameUtils.getExtension(fileName));
        imageSlideService.saveImageSlide(imageSlideDTO);



    }





}


