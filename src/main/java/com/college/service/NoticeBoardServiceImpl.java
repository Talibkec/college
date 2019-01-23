package com.college.service;

import com.college.core.entity.NoticeBoard;
import com.college.core.model.NoticeBoardDTO;
import com.college.repository.NoticeBoardRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService{

    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    NoticeBoardRepository noticeBoardRepository;

    @Override
    public List<NoticeBoardDTO> getAllNotice() {
        List<NoticeBoard> noticeBoards = noticeBoardRepository.findAll();
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();
        return modelMapper.map(noticeBoards, targetListType);
    }

    @Override
    public void saveNoticeBoard(NoticeBoardDTO noticeBoardDTO) {
        NoticeBoard noticeBoard = modelMapper.map(noticeBoardDTO, NoticeBoard.class);
        noticeBoardRepository.save(noticeBoard);
    }

    @Override
    public void deleteItem(Long id) {
        noticeBoardRepository.delete(id);
    }
}
