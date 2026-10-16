package com.college.repository;

import com.college.core.entity.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageSlideRepository extends JpaRepository<ImageSlide, Long> {
    /** The six newest slides (home page slider). */
    List<ImageSlide> findTop6ByOrderByImageSlideIdDesc();

}
