package com.college.repository;

import com.college.core.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    @Query(value="select * from achievement_slide", nativeQuery = true)
    List<Achievement> getAllImages();
}
