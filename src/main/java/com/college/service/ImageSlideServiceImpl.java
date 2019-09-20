package com.college.service;

import com.college.core.entity.ImageSlide;
import com.college.core.entity.NoticeBoard;
import com.college.core.model.ImageSlideDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.repository.ImageSlideRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

@Service
public class ImageSlideServiceImpl implements  ImageSlideService{
    Logger logger = LoggerFactory.getLogger(ImageSlideServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ImageSlideRepository imageSlideRepository;

    @Override

    public void saveImageSlide(ImageSlideDTO imageSlideDTO) {
        ImageSlide imageSlide = modelMapper.map(imageSlideDTO, ImageSlide.class);
        imageSlideRepository.save(imageSlide);
    }

}
