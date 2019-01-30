package com.college.service;

import com.college.core.entity.Department;
import com.college.core.entity.NoticeBoard;
import com.college.core.model.DepartmentDTO;
import com.college.core.model.NoticeBoardDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.NoticeBoardRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    DepartmentRepository departmentRepository;
    @Override
    public List<DepartmentDTO> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();
        Type targetListType = new TypeToken<List<DepartmentRepository>>() {}.getType();
        return modelMapper.map(departments, targetListType);
    }
}
