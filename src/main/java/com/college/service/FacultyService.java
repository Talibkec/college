package com.college.service;

import com.college.core.model.FacultyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    List<FacultyDTO> getAllFaculty();
}
