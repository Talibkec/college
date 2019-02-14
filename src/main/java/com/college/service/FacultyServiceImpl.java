package com.college.service;

import com.college.core.entity.Department;
import com.college.core.entity.Faculty;
import com.college.core.model.DepartmentDTO;
import com.college.core.model.FacultyDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.FacultyRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class FacultyServiceImpl implements FacultyService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    FacultyRepository facultyRepository;
    @Override
    public List<FacultyDTO> getAllFaculty() {
        List<Faculty> faculties = facultyRepository.findAll();
        Type targetListType = new TypeToken<List<FacultyRepository>>() {}.getType();
        return modelMapper.map(faculties, targetListType);
    }

    @Override
    public FacultyDTO getFaculty(String userName) {
        Faculty faculty = facultyRepository.findFacltyByUserName(userName);
        return modelMapper.map(faculty, FacultyDTO.class);
    }

    @Override
    public void saveFaculty(FacultyDTO facultyDTO) {
        Faculty faculty = modelMapper.map(facultyDTO, Faculty.class);
        facultyRepository.save(faculty);
    }

    @Override
    public FacultyDTO getFacultyById(Long id) {
        FacultyDTO facultyDTO = null;
        Faculty faculty = facultyRepository.findOne(id);
        if(faculty != null){
            facultyDTO = modelMapper.map(faculty, FacultyDTO.class);
        }
        return facultyDTO;
    }

    @Override
    public List<String> searchFacultyName(String facultyName) {
        return facultyRepository.searchFacultyName(facultyName);
    }

    @Override
    public List<FacultyDTO> getFacultyByName(String facultyName) {
        List<FacultyDTO> facultyDTO = null;
        List<Faculty> faculty = facultyRepository.getFacultyByName(facultyName);
        Type targetListType = new TypeToken<List<FacultyDTO>>() {}.getType();
        if(faculty != null){
            facultyDTO = modelMapper.map(faculty, targetListType);
        }
        return facultyDTO;

    }
}
