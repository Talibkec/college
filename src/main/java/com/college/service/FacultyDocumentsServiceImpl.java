package com.college.service;

import com.college.UploadFileUtility;
import com.college.core.entity.FacultyDocuments;
import com.college.core.model.FacultyDocumentsDTO;
import com.college.repository.FacultyDocumentsRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
@Service
public class FacultyDocumentsServiceImpl implements FacultyDocumentsService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    FacultyDocumentsRepository facultyDocumentsRepository;

    @Override
    public List<FacultyDocumentsDTO> getFacultyDocuments(String userName) {
        List<FacultyDocuments> facultyDocuments = facultyDocumentsRepository.getFacultyDocuments(userName);
        Type targetListType = new TypeToken<List<FacultyDocumentsDTO>>() {}.getType();
        List<FacultyDocumentsDTO>  facultyDocumentsDTO = modelMapper.map(facultyDocuments, targetListType);
        UploadFileUtility.setFileType(facultyDocumentsDTO);
        return facultyDocumentsDTO;
    }
    @Override
    public void saveFacultydocumentsDetails(FacultyDocumentsDTO facultyDocumentsDTO){
        FacultyDocuments facultyDocuments =modelMapper.map(facultyDocumentsDTO, FacultyDocuments.class);
        facultyDocumentsRepository.save(facultyDocuments);
    }

    @Override
    public FacultyDocumentsDTO getFacultyDocument(Long id) {
        FacultyDocuments facultyDocuments = facultyDocumentsRepository.findOne(id);
        return modelMapper.map(facultyDocuments, FacultyDocumentsDTO.class);
    }

    @Override
    public void deleteFacultyDoc(Long id) {
        facultyDocumentsRepository.delete(id);
    }

}
