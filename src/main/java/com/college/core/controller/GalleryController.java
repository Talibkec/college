package com.college.core.controller;

import com.college.core.model.GalleryImageDTO;
import com.college.core.model.GalleryPhotoDTO;
import com.college.service.GalleryImageService;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "gallery")

public class GalleryController {

    private final GalleryImageService galleryImageService;

    public GalleryController(GalleryImageService galleryImageService) {
        this.galleryImageService = galleryImageService;
    }

    /** Photos of the static photo gallery (formerly a scriptlet array in photogallery.jsp). */
    private static final List<GalleryPhotoDTO> PHOTOS = List.of(
            new GalleryPhotoDTO("img1.JPG", "Campus View"), new GalleryPhotoDTO("img2.JPG", "Library"),
            new GalleryPhotoDTO("img3.JPG", "Event Hall"), new GalleryPhotoDTO("img4.JPG", "Hostel Building"),
            new GalleryPhotoDTO("img5.JPG", "Lab"), new GalleryPhotoDTO("img6.JPG", "Workshop"),
            new GalleryPhotoDTO("img7.JPG", "Sports Ground"), new GalleryPhotoDTO("img8.JPG", "Conference Hall"),
            new GalleryPhotoDTO("img9.JPG", "Canteen"), new GalleryPhotoDTO("img10.JPG", "Placement Cell"),
            new GalleryPhotoDTO("img11.JPG", "Auditorium"), new GalleryPhotoDTO("img12.JPG", "Garden Area"),
            new GalleryPhotoDTO("img13.JPG", "Main Gate"));

    @RequestMapping(value = "photogallery")
    public ModelAndView getPhotoGallery(){
        ModelAndView mv=new ModelAndView();
        mv.addObject("photos", PHOTOS);
        mv.setViewName("/gallery/photogallery.jsp");
        return mv;
    }
    @RequestMapping(value = "mediagallery")
    public ModelAndView getMediaGallery(){
        ModelAndView mv=new ModelAndView();
        List<GalleryImageDTO> imageList = galleryImageService.getAllImages();
        mv.addObject("imageList", getImageList(imageList));
        GalleryImageDTO galleryImageDTO = new GalleryImageDTO();
        List<String> role = ControllerUtility.getRole();
        mv.addObject("Role", role);

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
