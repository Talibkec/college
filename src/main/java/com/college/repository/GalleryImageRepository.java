package com.college.repository;

import com.college.core.entity.GalleryImage;
import com.college.core.entity.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GalleryImageRepository extends JpaRepository<GalleryImage,Long> {
    @Query(value ="select * from gallery_image", nativeQuery =true )
        //@Query("select ims from ImageSlide ims")
    List<GalleryImage> getAllImages();
}
