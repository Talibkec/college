package com.college.service;

import com.college.core.entity.Department;
import com.college.core.model.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    abstract List<DepartmentDTO> getAllDepartment();

    List<Department> getAllDepartments();

    public String getHodName(Long deptId);
}
