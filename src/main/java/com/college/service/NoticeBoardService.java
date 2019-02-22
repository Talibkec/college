package com.college.service;

import com.college.core.controller.ControllerUtility;
import com.college.core.model.NoticeBoardDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Service
public interface NoticeBoardService {
    List<NoticeBoardDTO> getAllNotice();
    NoticeBoardDTO getNoticeDocument(Long id);
    List<NoticeBoardDTO> getCseNotices();
    List<NoticeBoardDTO> getCivilNotices();
    List<NoticeBoardDTO> getMechNotices();
    List<NoticeBoardDTO> getEecNotices();
    List<NoticeBoardDTO> getApplidScienceNotices();
    void saveNoticeBoard(NoticeBoardDTO noticeBoardDTO);
    void deleteItem(Long id);
}
