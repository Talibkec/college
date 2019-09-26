package com.college.service;

import com.college.core.model.ImageSlideDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageSlideService {
    void  saveImageSlide(ImageSlideDTO imageSlideDTO);
    ImageSlideDTO getImages(Long id);
    List<ImageSlideDTO> getAllImages();
    void deleteSlideImage(Long id);
}
