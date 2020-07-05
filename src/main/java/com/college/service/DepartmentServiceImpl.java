package com.college.service;

import com.college.core.entity.Department;
import com.college.core.model.DepartmentDTO;
import com.college.repository.DepartmentRepository;
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
        Type targetListType = new TypeToken<List<DepartmentRepository>>() {
        }.getType();
        return modelMapper.map(departments, targetListType);
    }

    @Override
    public List<Department> getAllDepartments(){
        return departmentRepository.findAll();
    }

    @Override
    public String getHodName(Long deptId){
        Department department = departmentRepository.getHodName(deptId);
        return department.getDepartmentHod();
    }

}
