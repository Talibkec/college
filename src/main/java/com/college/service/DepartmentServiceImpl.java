package com.college.service;

import com.college.core.entity.Department;
import com.college.core.model.DepartmentDTO;
import com.college.repository.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    ModelMapper modelMapper = new ModelMapper();
    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public List<DepartmentDTO> getAllDepartments(){
        return departmentRepository.findAll().stream().map(d -> {
            DepartmentDTO dto = new DepartmentDTO();
            dto.setDepartmentId(d.getDepartmentId());
            dto.setDepartmentName(d.getDepartmentName());
            dto.setDepartmentHod(d.getDepartmentHod());
            return dto;
        }).toList();
    }

    @Override
    public String getHodName(Long deptId){
        Department department = departmentRepository.getHodName(deptId);
        if(department != null) {
            return department.getDepartmentHod();
        }else {
            return "";
        }

    }

}
