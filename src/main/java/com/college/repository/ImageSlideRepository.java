package com.college.repository;

import com.college.core.entity.ImageSlide;
import com.college.core.model.ImageSlideDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSlideRepository extends JpaRepository<ImageSlide,Long> {
    @Query(value ="select * from ImageSlide ", nativeQuery =true )
    List<ImageSlide> getAllImages();

}
