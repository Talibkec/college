package com.college.service;

import com.college.core.entity.Request;
import com.college.core.model.RequestDTO;
import com.college.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class RequestServiceImpl implements RequestService{
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    RequestRepository requestRepository;

    @Override
    public List<RequestDTO> getAllRequest() {
        List<Request> requests = requestRepository.findAll();
        Type targetListType = new TypeToken<List<RequestDTO>>() {}.getType();
        return modelMapper.map(requests, targetListType);
    }

    @Override
    public void saveRequest(RequestDTO requestDTO) {
        Request request = modelMapper.map(requestDTO, Request.class);
        requestRepository.save(request);
    }

    @Override
    public List<RequestDTO> getFacultyRequest(Long facultyId) {
        List<Request> request = requestRepository.getFacultyRequest(facultyId);
        Type requestType = new TypeToken<List<RequestDTO>>(){}.getType();
        return  modelMapper.map(request, requestType);

    }
}
