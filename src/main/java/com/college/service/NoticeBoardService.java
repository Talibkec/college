package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.model.NoticeBoardDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
