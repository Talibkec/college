package com.college.service;

import com.college.core.model.RequestDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RequestService {
    List<RequestDTO> getAllRequest();
}
