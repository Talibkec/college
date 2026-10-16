package com.college.service;
import com.college.core.entity.LabDocument;
import com.college.core.model.LabDocumentDTO;
import com.college.repository.LabDocumentRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class LabDocumentServiceImpl implements LabDocumentService{

    Logger logger = LoggerFactory.getLogger(NoticeBoardServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    private final LabDocumentRepository labDocumentRepository;

    public LabDocumentServiceImpl(LabDocumentRepository labDocumentRepository) {
        this.labDocumentRepository = labDocumentRepository;
    }

    @Override
    public List<LabDocument> getAllDownload() {
        return labDocumentRepository.getAllDocuments();
    }

    @Override
    public LabDocumentDTO getDownloadDocument(Long id) {
        LabDocumentDTO downloadDTO = null;
        LabDocument holiday  = labDocumentRepository.findById(id).orElse(null);
        if(holiday != null){
            downloadDTO = modelMapper.map(holiday , LabDocumentDTO.class);
        }
        return downloadDTO;
    }

    @Override
    public void saveDownloadBoard(LabDocumentDTO holidayDTO) {
        LabDocument responsibilityDoc = modelMapper.map(holidayDTO , LabDocument.class);
        labDocumentRepository.save(responsibilityDoc);
    }

    @Override
    public void deleteItem(Long id) {
        labDocumentRepository.deleteById(id);
    }

    @Override
    public List<LabDocument> getAllLabDocByDepId(Integer deptId) {
        return labDocumentRepository.getLabByDeptId(deptId);

    }
}
