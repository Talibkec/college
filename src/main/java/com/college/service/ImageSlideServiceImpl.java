package com.college.service;

import com.college.core.entity.ImageSlide;
import com.college.core.entity.NoticeBoard;
import com.college.core.model.ImageSlideDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.repository.ImageSlideRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ImageSlideServiceImpl implements ImageSlideService {
    Logger logger = LoggerFactory.getLogger(ImageSlideServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ImageSlideRepository imageSlideRepository;

    @Override
    @CacheEvict(value = "homepageSlideImageCache", allEntries = true)
    public void saveImageSlide(ImageSlideDTO imageSlideDTO) {
        ImageSlide imageSlide = modelMapper.map(imageSlideDTO, ImageSlide.class);
        imageSlideRepository.save(imageSlide);
    }

    @Override
    public ImageSlideDTO getImages(Long id) {
        ImageSlideDTO imageSlideDTO = null;
        ImageSlide imageSlide = imageSlideRepository.findOne(id);
        if (imageSlide != null) {
            imageSlideDTO = modelMapper.map(imageSlide, ImageSlideDTO.class);
        }
        return imageSlideDTO;
    }

    @Override
    @Cacheable(value = "homepageSlideImageCache")
    public List<ImageSlideDTO> getAllImages() {
        logger.info("Query is being served from database for image slide.");

        List<ImageSlide> imageSlides = imageSlideRepository.getAllImages();
        Type targetListType = new TypeToken<List<ImageSlideDTO>>() {
        }.getType();

        return modelMapper.map(imageSlides, targetListType);
    }

    @Override
    @CacheEvict(value = "homepageSlideImageCache", allEntries = true)
    public void deleteSlideImage(Long id) {
        imageSlideRepository.delete(id);
    }

}
