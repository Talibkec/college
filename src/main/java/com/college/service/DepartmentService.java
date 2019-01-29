package com.college.service;

import com.college.core.model.DepartmentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<DepartmentDTO> getAllDepartment();
}
