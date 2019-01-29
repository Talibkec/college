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
}
