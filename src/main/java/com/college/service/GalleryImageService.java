package com.college.service;

import com.college.core.model.GalleryImageDTO;
import com.college.core.model.ImageSlideDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GalleryImageService {

    void saveImageSlide(GalleryImageDTO galleryImageDTO);
    List<GalleryImageDTO> getAllImages();
    GalleryImageDTO getImages(Long id);


}
