package com.college.service;

import com.college.core.entity.NoticeBoard;
import com.college.core.model.NoticeBoardDTO;
import com.college.repository.NoticeBoardRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService{

    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    NoticeBoardRepository noticeBoardRepository;

    @Override
    @Cacheable(value = "homepageNoticeCache")
    public List<NoticeBoardDTO> getAllNotice(Pageable pageable) {
        logger.info("Query is bering served from database.");
        String news = "News";
        String notice = "Notice";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getHomePageNotices(notice, news, pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();

        return modelMapper.map(noticeBoards, targetListType);
    }
    @Override
    public NoticeBoardDTO getNoticeDocument(Long id) {
        NoticeBoardDTO noticeBoardDTO = null;
        NoticeBoard noticeBoard = noticeBoardRepository.findOne(id);
        if(noticeBoard != null){
            noticeBoardDTO = modelMapper.map(noticeBoard, NoticeBoardDTO.class);
        }
        return noticeBoardDTO;
    }

    @Override
    public List<NoticeBoardDTO> getCseNotices(Pageable pageable) {
        String noticeType = "CSENotice";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getCseNotices(noticeType,pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();

            return modelMapper.map(noticeBoards, targetListType);

    }

    @Override
    public List<NoticeBoardDTO> getMechNotices(Pageable pageable) {
        String noticeType = "MECHNotice";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getMechNotices(noticeType,pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();

        return modelMapper.map(noticeBoards, targetListType);

    }

    @Override
    public List<NoticeBoardDTO> getEecNotices(Pageable pageable) {
        String noticeType = "ELECNotice";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getElecNotices(noticeType,pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();
        return modelMapper.map(noticeBoards, targetListType);
    }

    @Override
    public List<NoticeBoardDTO> getApplidScienceNotices(Pageable pageable) {
        String noticeType = "ASHNotice";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getAshNotices(noticeType, pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();

        return modelMapper.map(noticeBoards, targetListType);

    }

    @Override
    public List<NoticeBoardDTO> getCivilNotices(Pageable pageable) {
        String noticeType = "CIVILNotice";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getCivilNotices(noticeType, pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();
        return modelMapper.map(noticeBoards, targetListType);
    }



    @Override
    @CacheEvict(value = "homepageNoticeCache", allEntries = true)
    public void saveNoticeBoard(NoticeBoardDTO noticeBoardDTO) {
        NoticeBoard noticeBoard = modelMapper.map(noticeBoardDTO, NoticeBoard.class);
        noticeBoardRepository.save(noticeBoard);
    }

    @Override
    @CacheEvict(value = "homepageNoticeCache", allEntries = true)
    public void deleteItem(Long id) {
        noticeBoardRepository.delete(id);
    }
}
