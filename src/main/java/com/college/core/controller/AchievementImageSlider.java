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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.List;

@Controller
public class AchievementImageSlider {
    @Autowired
    AchievementSlideService achievementSlideService;
    private final Logger logger = LoggerFactory.getLogger(AchievementImageSlider.class);

    private static final int DESKTOP_WIDTH = 6000;
    private static final int DESKTOP_HEIGHT = 4000;
    private static final int MOBILE_WIDTH = 1080;
    private static final int MOBILE_HEIGHT = 1920;

    @ResponseBody
    @RequestMapping(value = "/auth/api/achievementSlideImage", method = RequestMethod.POST)
    public ResponseEntity<?> uploadFile(
            @RequestParam("slideImage") MultipartFile uploadfile,
            @RequestParam("caption") String caption,
            @RequestParam("displayFlag") int displayFlag) {  // 0=desktop, 1=mobile

        logger.debug("Achievement slide upload!");
        String fileName = uploadfile.getOriginalFilename();
        String fileType = uploadfile.getContentType();

        if (uploadfile.isEmpty() || StringUtils.isEmpty(caption)) {
            String msg = uploadfile.isEmpty() ? "Please select a file." : "Please give a proper caption";
            return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }


        try {
            BufferedImage image = ImageIO.read(uploadfile.getInputStream());
            int width = image.getWidth();
            int height = image.getHeight();
            logger.info("Image resolution is {} x {}", width, height);

            if ((displayFlag == 0 && (width != DESKTOP_WIDTH || height != DESKTOP_HEIGHT)) ||
                    (displayFlag == 1 && (width != MOBILE_WIDTH || height != MOBILE_HEIGHT))) {

                String requiredSize = displayFlag == 0 ?
                        DESKTOP_WIDTH + "x" + DESKTOP_HEIGHT :
                        MOBILE_WIDTH + "x" + MOBILE_HEIGHT;

                String msg = "Please resize your image to " + requiredSize + " pixels";
                return new ResponseEntity(msg, new HttpHeaders(), HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            logger.error("Error reading image dimensions", e);
            return new ResponseEntity("Error processing image", new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

        // Save the image with flag
        saveAchievementImage(uploadfile, caption, fileName, fileType, displayFlag);

        return new ResponseEntity("Achievement slide uploaded successfully", new HttpHeaders(), HttpStatus.OK);
    }

    private void saveAchievementImage(MultipartFile uploadfile, String caption,
                                      String fileName, String fileType, int displayFlag) {
        AchievementDTO achievementDTO = new AchievementDTO();
        achievementDTO.setAchievementCaption(caption);
        achievementDTO.setFileName(fileName);
        achievementDTO.setFileType(fileType);
        achievementDTO.setDisplayFlag(displayFlag);

        try {
            achievementDTO.setAchievementImage(uploadfile.getBytes());
        } catch (IOException e) {
            logger.error("Error saving achievement image", e);
            throw new RuntimeException(e);
        }

        achievementSlideService.saveAchievementSlide(achievementDTO);
    }

    @ResponseBody
    @RequestMapping(value = "/auth/uploadfile/uploadgalleryimage", method = RequestMethod.GET)
    public ModelAndView getUploadGalleryPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("uploadGalleryImage.jsp");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/api/achievements/flag/{flag}", method = RequestMethod.GET)
    public ResponseEntity<List<AchievementDTO>> getAchievementImagesByFlag(@PathVariable int flag) {
        logger.debug("Fetching achievement images for flag: {}", flag);
        List<AchievementDTO> achievements = achievementSlideService.getByDisplayFlag(flag);

        if (achievements.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(achievements, HttpStatus.OK);
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
