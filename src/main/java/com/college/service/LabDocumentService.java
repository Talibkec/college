package com.college.service;

import com.college.core.model.LabDocumentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LabDocumentService {
    List<LabDocumentDTO> getAllDownload();

    LabDocumentDTO getDownloadDocument(Long id);


    void saveDownloadBoard(LabDocumentDTO responsibilityDocDTO);

    void deleteItem(Long id);

    List<LabDocumentDTO> getAllLabDocByDepId(Integer deptId);
}
