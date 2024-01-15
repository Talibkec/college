package com.college.service;
import com.college.core.entity.Download;
import com.college.core.entity.ResponsibilityDoc;
import com.college.core.model.DownloadDTO;
import com.college.core.model.ResponsibilityDocDTO;
import com.college.repository.DownloadRepository;
import com.college.repository.ResponsibilityDocRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResponsibilityDocServiceImpl implements ResponsibilityDocService{
    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ResponsibilityDocRepository responsibilityDocRepository;
    @Override
    public List<ResponsibilityDoc> getAllDownload() {
        return responsibilityDocRepository.getAllDocuments();
    }

    @Override
    public ResponsibilityDocDTO getDownloadDocument(Long id) {
        ResponsibilityDocDTO downloadDTO = null;
        Optional<ResponsibilityDoc> responsibilityDoc = responsibilityDocRepository.findById(id);
        if(responsibilityDoc.isPresent() ){
            downloadDTO = modelMapper.map(responsibilityDoc.get() , ResponsibilityDocDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(ResponsibilityDocDTO responsibilityDocDTO) {
        ResponsibilityDoc responsibilityDoc = modelMapper.map(responsibilityDocDTO , ResponsibilityDoc.class);
        responsibilityDocRepository.save(responsibilityDoc);
    }

    @Override
    public void deleteItem(Long id) {
        responsibilityDocRepository.deleteById(id);
    }
}
