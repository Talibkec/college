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

    @Query("select new com.college.core.entity.NoticeBoard(nb.id,nb.headLine,nb.noticeType,nb.link,nb.uploadedFileName, nb.date, nb.isScrollable) from NoticeBoard nb where nb.noticeType = :noticeType or noticeType = :news order by nb.date DESC")
    List<NoticeBoard> getCseNotices(@Param("noticeType") String notice, @Param("news") String news, Pageable pageable);

    @Query("select new com.college.core.entity.NoticeBoard(nb.id,nb.headLine,nb.noticeType,nb.link,nb.uploadedFileName, nb.date, nb.isScrollable) from NoticeBoard nb where nb.noticeType = :noticeType or noticeType = :news order by nb.date DESC")
    List<NoticeBoard> getCivilNotices(@Param("noticeType") String notice, @Param("news") String news, Pageable pageable);


    @Query("select new com.college.core.entity.NoticeBoard(nb.id,nb.headLine,nb.noticeType,nb.link,nb.uploadedFileName, nb.date, nb.isScrollable) from NoticeBoard nb where nb.noticeType = :noticeType or noticeType = :news order by nb.date DESC")
    List<NoticeBoard> getMechNotices(@Param("noticeType") String notice, @Param("news") String news, Pageable pageable);

    @Query("select new com.college.core.entity.NoticeBoard(nb.id,nb.headLine,nb.noticeType,nb.link,nb.uploadedFileName, nb.date, nb.isScrollable) from NoticeBoard nb where nb.noticeType = :noticeType or noticeType = :news order by nb.date DESC")
    List<NoticeBoard> getElecNotices(@Param("noticeType") String notice, @Param("news") String news, Pageable pageable);

    @Query("select new com.college.core.entity.NoticeBoard(nb.id,nb.headLine,nb.noticeType,nb.link,nb.uploadedFileName, nb.date, nb.isScrollable) from NoticeBoard nb where nb.noticeType = :noticeType or noticeType = :news order by nb.date DESC")
    List<NoticeBoard> getAshNotices(@Param("noticeType") String notice, @Param("news") String news, Pageable pageable);


    @Query("select new com.college.core.entity.NoticeBoard(nb.id,nb.headLine,nb.noticeType,nb.link,nb.uploadedFileName, nb.date, nb.isScrollable) from NoticeBoard nb where nb.noticeType = :noticeType order by nb.date DESC")
    List<NoticeBoard> getBlinkingMessage(@Param("noticeType") String noticeType, Pageable pageable);


    @Query("select new com.college.core.entity.NoticeBoard(nb.id,nb.headLine,nb.noticeType,nb.link,nb.uploadedFileName, nb.date, nb.isScrollable) from NoticeBoard nb where nb.noticeType = :notice or noticeType = :news or noticeType = :Tendor order by nb.date DESC")
    List<NoticeBoard> getHomePageNotices(@Param("notice") String notice, @Param("news") String news, @Param("Tendor") String Tendor, Pageable pageable);
}