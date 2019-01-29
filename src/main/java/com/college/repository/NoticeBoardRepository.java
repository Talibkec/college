package com.college.repository;

import com.college.core.entity.NoticeBoard;
import com.college.core.model.NoticeBoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

    @Query(value = "select * from noticeboard where nb_noticedept = null", nativeQuery = true)
    public List<NoticeBoard> getCSENotices();
}