package com.college.service;

import com.college.core.model.OrderDTO;
import com.college.core.model.RequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {
    List<RequestDTO> getAllRequest();

    void saveRequest(RequestDTO requestDTO);

    List<RequestDTO> getFacultyRequest(Long facultyId);

    RequestDTO getRequest(Long id);

    void deleteFacultyRequest(Long requestID);
}
