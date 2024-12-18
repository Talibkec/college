package com.college.service;
import com.college.core.entity.Download;
import com.college.core.model.DownloadDTO;
import com.college.repository.DownloadRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DownloadServiceImpl implements DownloadService{
    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    DownloadRepository downloadRepository;
    @Override
    public List<Download> getAllDownload() {
        return downloadRepository.getAllDocuments();
    }

    @Override
    public DownloadDTO getDownloadDocument(Long id) {
        DownloadDTO downloadDTO = null;
        Download download = downloadRepository.findOne(id);
        if(download != null){
            downloadDTO = modelMapper.map(download , DownloadDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(DownloadDTO DownloadDTO) {
        Download download = modelMapper.map(DownloadDTO , Download.class);
        downloadRepository.save(download);
    }

    @Override
    public void deleteItem(Long id) {
       downloadRepository.delete(id);
    }
}
