package com.college.service;
import com.college.core.entity.LabDocument;
import com.college.core.model.LabDocumentDTO;
import com.college.repository.LabDocumentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabDocumentServiceImpl implements LabDocumentService{
    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    LabDocumentRepository LabDocumentRepository;
    @Override
    public List<LabDocument> getAllDownload() {
        return LabDocumentRepository.getAllDocuments();
    }

    @Override
    public LabDocumentDTO getDownloadDocument(Long id) {
        LabDocumentDTO downloadDTO = null;
        LabDocument holiday  = LabDocumentRepository.findOne(id);
        if(holiday != null){
            downloadDTO = modelMapper.map(holiday , LabDocumentDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(LabDocumentDTO holidayDTO) {
        LabDocument responsibilityDoc = modelMapper.map(holidayDTO , LabDocument.class);
        LabDocumentRepository.save(responsibilityDoc);
    }

    @Override
    public void deleteItem(Long id) {
        LabDocumentRepository.delete(id);
    }
}
