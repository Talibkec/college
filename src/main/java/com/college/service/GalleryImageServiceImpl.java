package com.college.service;

import com.college.core.entity.GalleryImage;
import com.college.core.entity.ImageSlide;
import com.college.core.model.GalleryImageDTO;
import com.college.core.model.ImageSlideDTO;
import com.college.repository.GalleryImageRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class GalleryImageServiceImpl implements GalleryImageService{
    Logger logger = LoggerFactory.getLogger(ImageSlideServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    GalleryImageRepository galleryImageRepository;

    @Override
    @CacheEvict(value = "homepageSlideImageCache", allEntries = true)
    public void saveImageSlide(GalleryImageDTO galleryImageDTO) {
        GalleryImage galleryImage = modelMapper.map(galleryImageDTO, GalleryImage.class);
        galleryImageRepository.save(galleryImage);
    }

    public List<GalleryImageDTO> getAllImages() {
        logger.info("Query is being served from database for image slide.");

        List<GalleryImage> galleryImages = galleryImageRepository.getAllImages();
        Type targetListType = new TypeToken<List<GalleryImageDTO>>() {}.getType();

        return modelMapper.map(galleryImages, targetListType);
    }

    @Override
    public GalleryImageDTO getImages(Long id)
    {
        GalleryImageDTO galleryImageDTO = null;
        GalleryImage galleryImage = galleryImageRepository.findOne(id);
        if(galleryImage != null){
            galleryImageDTO = modelMapper.map(galleryImage, GalleryImageDTO.class);
        }
        return galleryImageDTO;
    }
}
