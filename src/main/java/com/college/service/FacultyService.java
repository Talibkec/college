package com.college.service;

import com.college.core.entity.User;
import com.college.core.model.FacultyDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FacultyService {
    List<FacultyDTO> getAllFaculty();

    FacultyDTO getFaculty(String userName);

    void saveFaculty(FacultyDTO facultyDTO);

    FacultyDTO getFacultyById(Long id);

    List<FacultyDTO> searchFacultyName(String facultyName);

    FacultyDTO searchFaculty(String facultyName);

    List<FacultyDTO> getFacultyByName(String facultyName);

    FacultyDTO findUserByEmail(String userEmail);

    void createPasswordResetTokenForUser(User user, String token);
}
