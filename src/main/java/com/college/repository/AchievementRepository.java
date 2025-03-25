package com.college.repository;

import com.college.core.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    @Query("SELECT a FROM Achievement a ORDER BY a.achievementSlideId DESC")
    List<Achievement> getAllImages();

    @Query("SELECT a FROM Achievement a WHERE a.displayFlag = :flag ORDER BY a.achievementSlideId DESC")
    List<Achievement> findByDisplayFlag(@Param("flag") int flag);
}
