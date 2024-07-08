package com.college.service;

import com.college.core.model.FacultyDocumentsDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyDocumentsService {
    List<FacultyDocumentsDTO> getFacultyDocuments(String userName);

    void saveFacultydocumentsDetails(FacultyDocumentsDTO facultyDocumentsDTO);

    FacultyDocumentsDTO getFacultyDocument(Long id);

    void deleteFacultyDoc(Long id);
}
