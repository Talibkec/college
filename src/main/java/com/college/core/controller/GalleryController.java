package com.college.core.controller;

import com.college.core.model.GalleryImageDTO;
import com.college.service.GalleryImageService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "gallery")

public class GalleryController {

    @Autowired
    GalleryImageService galleryImageService;

    @RequestMapping(value = "photogallery")
    public ModelAndView getPhotoGallery(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("/gallery/photogallery.jsp");
        return mv;
    }
    @RequestMapping(value = "mediagallery")
    public ModelAndView getMediaGallery(){
        ModelAndView mv=new ModelAndView();
        List<GalleryImageDTO> imageList = galleryImageService.getAllImages();
        mv.addObject("imageList", getImageList(imageList));
        GalleryImageDTO galleryImageDTO = new GalleryImageDTO();

        if (galleryImageDTO.getFileType() != null)
            mv.addObject("fileExtension", "." + galleryImageDTO.getFileType());
        mv.addObject("galleryImageId", galleryImageDTO.getGalleryImageId());
        mv.addObject("caption", galleryImageDTO.getCaption());


        mv.setViewName("/gallery/mediagallery.jsp");
        return mv;
    }


    private List<GalleryImageDTO> getImageList(List<GalleryImageDTO> imageList) {
        List<GalleryImageDTO> galleryImage = new ArrayList<>();

        for (GalleryImageDTO dto : imageList) {
            dto.setFileType(("." + FilenameUtils.getExtension(dto.getFileName())));
            galleryImage.add(dto);

        }
        return galleryImage;
    }


}
