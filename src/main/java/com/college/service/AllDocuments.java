package com.college.service;

import com.college.core.model.AicteDocumentsDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface AllDocuments {
    void saveAicteDocument(AicteDocumentsDTO aicteDocumentsDTO);

    AicteDocumentsDTO getAicteDocument(Long id);

    List<AicteDocumentsDTO> getAllDocuments();

    void deleteAicteDocument(Long id);
}
