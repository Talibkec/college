package com.college.service;

import com.college.core.model.NoticeBoardDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface NoticeBoardService {
    List<NoticeBoardDTO> getAllNotice(Pageable pageable);
    NoticeBoardDTO getNoticeDocument(Long id);
    List<NoticeBoardDTO> getCseNotices(Pageable pageable);
    List<NoticeBoardDTO> getCivilNotices(Pageable pageable);
    List<NoticeBoardDTO> getMechNotices(Pageable pageable);
    List<NoticeBoardDTO> getEecNotices(Pageable pageable);
    List<NoticeBoardDTO> getApplidScienceNotices(Pageable pageable);
    void saveNoticeBoard(NoticeBoardDTO noticeBoardDTO);
    void deleteItem(Long id);
}
