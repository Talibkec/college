package com.college.service;

import com.college.core.entity.LabDocument;
import com.college.core.model.LabDocumentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabDocumentService {
    List<LabDocument> getAllDownload();

    LabDocumentDTO getDownloadDocument(Long id);


    void saveDownloadBoard(LabDocumentDTO responsibilityDocDTO);

    void deleteItem(Long id);

    List<LabDocument> getAllLabDocByDepId(Integer deptId);
}
