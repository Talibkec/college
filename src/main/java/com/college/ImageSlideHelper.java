package com.college;

import com.college.core.controller.ControllerUtility;
import com.college.core.model.FacultyDTO;
import com.college.core.model.FacultyDocumentsDTO;
import com.college.core.model.ImageSlideDTO;
import com.college.core.model.RequestDTO;
import com.college.service.ImageSlideService;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

public class ImageSlideHelper {
    private final ImageSlideService imageSlideService;

    public ImageSlideHelper(ImageSlideService imageSlideService) {
        this.imageSlideService = imageSlideService;
    }

    public ModelAndView getSlideImages(Long id) {
        ModelAndView mv = new ModelAndView();
        List<ImageSlideDTO> allSlideImage = imageSlideService.getAllImages();
        ImageSlideDTO imageSlideDTO = imageSlideService.getImages(id);

        if (imageSlideDTO.getFileType() != null)
            mv.addObject("fileExtension", "." + imageSlideDTO.getFileType());
        mv.addObject("imageList", allSlideImage);

        return mv;
    }

}
