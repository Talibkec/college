package com.college.service;

import com.college.core.model.ImageSlideDTO;
import org.springframework.stereotype.Service;

@Service
public interface ImageSlideService {
    void  saveImageSlide(ImageSlideDTO imageSlideDTO);
}
