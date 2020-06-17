package com.college.repository;

import com.college.core.entity.Achievement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement,Long> {
    @Query(value="select * from (select * from achievement_slide order by achievement_slide_id DESC LIMIT 3) sub ORDER BY achievement_slide_id DESC", nativeQuery = true)
    List<Achievement> getAllImages();
}
