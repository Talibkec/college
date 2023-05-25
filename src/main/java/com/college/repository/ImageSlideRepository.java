package com.college.repository;

import com.college.core.entity.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSlideRepository extends JpaRepository<ImageSlide, Long> {
    @Query(value = "select * from image_slide order by image_slide_id desc limit 6 ", nativeQuery = true)
        //@Query("select ims from ImageSlide ims")
    List<ImageSlide> getAllImages();

}
