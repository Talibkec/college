package com.college.repository;

import com.college.core.entity.NoticeBoard;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeBoardRepository extends JpaRepository<NoticeBoard, Long> {

    @Query("select nb from NoticeBoard nb where nb.noticeType = :noticeType order by nb.date DESC")
    public List<NoticeBoard> getCseNotices(@Param("noticeType") String noticeType, Pageable pageable);

    @Query("select nb from NoticeBoard nb where nb.noticeType = :noticeType order by nb.date DESC")
    public List<NoticeBoard> getCivilNotices(@Param("noticeType") String noticeType, Pageable pageable);

    @Query("select nb from NoticeBoard nb where nb.noticeType = :noticeType order by nb.date DESC")
    public List<NoticeBoard> getMechNotices(@Param("noticeType") String noticeType, Pageable pageable);

    @Query("select nb from NoticeBoard nb where nb.noticeType = :noticeType order by nb.date DESC")
    public List<NoticeBoard> getElecNotices(@Param("noticeType") String noticeType, Pageable pageable);

    @Query("select nb from NoticeBoard nb where nb.noticeType = :noticeType order by nb.date DESC")
    public List<NoticeBoard> getAshNotices(@Param("noticeType") String noticeType, Pageable pageable);

    @Query("select nb from NoticeBoard nb where nb.noticeType = :notice or nb.noticeType = :news order by nb.date DESC")
    List<NoticeBoard> getHomePageNotices(@Param("notice") String notice, @Param("news") String news, Pageable pageable);
}