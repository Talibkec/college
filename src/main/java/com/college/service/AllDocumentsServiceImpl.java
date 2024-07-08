package com.college.service;

import com.college.core.entity.AicteDocuments;
import com.college.core.model.AicteDocumentsDTO;
import com.college.repository.AicteDocumentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class AllDocumentsServiceImpl implements AllDocuments {

    Logger logger = LoggerFactory.getLogger(ImageSlideServiceImpl.class);
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    AicteDocumentRepository aicteDocumentRepository;
    @Override
    public void saveAicteDocument(AicteDocumentsDTO aicteDocumentsDTO) {
         AicteDocuments aicteDocuments= modelMapper.map(aicteDocumentsDTO, AicteDocuments.class);
        aicteDocumentRepository.save(aicteDocuments);
    }

    @Override
    public AicteDocumentsDTO getAicteDocument(Long id) {
        AicteDocumentsDTO aicteDocumentsDTO = null;
        AicteDocuments aicteDocuments = aicteDocumentRepository.findOne(id);
        if (aicteDocuments != null) {
            aicteDocumentsDTO = modelMapper.map(aicteDocuments, AicteDocumentsDTO.class);
        }
        return aicteDocumentsDTO;
    }

    @Override
    public List<AicteDocumentsDTO> getAllDocuments() {
        logger.info("ff Query is being served from database for image slide.");

        List<AicteDocuments> aicteDocuments = aicteDocumentRepository.getAllDocuments();
        Type targetListType = new TypeToken<List<AicteDocumentsDTO>>() {
        }.getType();

        return modelMapper.map(aicteDocuments, targetListType);
    }

    @Override
    public void deleteAicteDocument(Long id) {
        aicteDocumentRepository.delete(id);
    }
}
