package com.college.service;

import com.college.core.model.NoticeBoardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoticeBoardService {
    List<NoticeBoardDTO> getAllNotice();
    void saveNoticeBoard(NoticeBoardDTO noticeBoardDTO);
    void deleteItem(Long id);
}
