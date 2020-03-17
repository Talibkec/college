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
        String Tendor = "Tendor";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getHomePageNotices(notice, news,Tendor, pageable);
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
        String news="CSENews";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getCseNotices(noticeType,news,pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();

            return modelMapper.map(noticeBoards, targetListType);

    }

    @Override
    public List<NoticeBoardDTO> getMechNotices(Pageable pageable) {
        String noticeType = "MECHNotice";
        String news="MECHNews";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getMechNotices(noticeType,news,pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();

        return modelMapper.map(noticeBoards, targetListType);

    }

    @Override
    public List<NoticeBoardDTO> getEecNotices(Pageable pageable) {
        String noticeType = "ELECNotice";
        String news="ELECNews";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getElecNotices(noticeType,news,pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();
        return modelMapper.map(noticeBoards, targetListType);
    }

    @Override
    public List<NoticeBoardDTO> getApplidScienceNotices(Pageable pageable) {
        String noticeType = "ASHNotice";
        String news="ASHNews";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getAshNotices(noticeType,news, pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();

        return modelMapper.map(noticeBoards, targetListType);

    }

    @Override
    public List<NoticeBoardDTO> getCivilNotices(Pageable pageable) {
        String noticeType = "CIVILNotice";
        String news="CIVILNews";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getCivilNotices(noticeType,news, pageable);
        Type targetListType = new TypeToken<List<NoticeBoardDTO>>() {}.getType();
        return modelMapper.map(noticeBoards, targetListType);
    }






    @Override
    public List<NoticeBoardDTO> getBlinkingMessage(Pageable pageable) {
        String noticeType = "BLINKINGMessage";
        List<NoticeBoard> noticeBoards = noticeBoardRepository.getBlinkingMessage(noticeType, pageable);
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
