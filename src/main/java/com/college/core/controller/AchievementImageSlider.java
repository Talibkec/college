package com.college.core.controller;

import com.college.core.model.AchievementDTO;
import com.college.service.AchievementSlideService;
import com.college.service.ImageSlideService;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
public class AchievementImageSlider {
    @Autowired
    AchievementSlideService achievementSlideService;
    private final Logger logger = LoggerFactory.getLogger(AchievementImageSlider.class);

    @ResponseBody
    @RequestMapping(value = "/auth/api/achievementSlideImage", method = RequestMethod.POST)
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
        saveAchievementImage(uploadfile, caption, fileName, fileType);

        return new ResponseEntity(new HttpHeaders(), HttpStatus.OK);

    }

    private void saveAchievementImage(MultipartFile uploadfile, String caption, String fileName, String fileType) {
        AchievementDTO achievementDTO= new AchievementDTO();
        achievementDTO.setAchievementCaption(caption);
        achievementDTO.setFileName(fileName);
        achievementDTO.setFileType(fileType);
        try {
            achievementDTO.setAchievementImage(uploadfile.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        achievementDTO.setFileType(FilenameUtils.getExtension(fileName));
        achievementSlideService.saveAchievementSlide(achievementDTO);


    }

    @RequestMapping(value = "/auth/uploadfile/achievementslideimage", method = RequestMethod.GET)
    public ModelAndView displaySlideImage() {
        ModelAndView model = new ModelAndView();
        //model.addObject("users", getUsers());
        model.setViewName("uploadAchievementImage.jsp");
        return model;
    }

}
