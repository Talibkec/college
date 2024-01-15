package com.college.service;

import com.college.core.entity.Product;
import com.college.core.entity.Request;
import com.college.core.model.RequestDTO;
import com.college.repository.ProductRepository;
import com.college.repository.RequestRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    RequestRepository requestRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<RequestDTO> getAllRequest() {
        List<Request> requests = requestRepository.findAll();
        Type targetListType = new TypeToken<List<RequestDTO>>() {
        }.getType();
        return modelMapper.map(requests, targetListType);
    }

    @Override
    public void saveRequest(RequestDTO requestDTO) {

        Optional<Product> product = productRepository.findById(requestDTO.getProduct().getProductId());
        Request request = modelMapper.map(requestDTO, Request.class);
        request.setProduct(product.get());
        requestRepository.save(request);
    }

    @Override
    public List<RequestDTO> getFacultyRequest(Long facultyId) {
        List<Request> request = requestRepository.getFacultyRequest(facultyId);
        Type requestType = new TypeToken<List<RequestDTO>>() {
        }.getType();
        return modelMapper.map(request, requestType);

    }

    @Override
    public RequestDTO getRequest(Long id) {
        Optional<Request> request = requestRepository.findById(id);
        return modelMapper.map(request.get(), RequestDTO.class);
    }

    @Override
    public List<RequestDTO> getNewRequest(String aNew) {
        List<RequestDTO> requestDTO = null;
        List<Request> requests = requestRepository.getNewRequest(aNew);
        if (requests != null) {
            Type requestType = new TypeToken<List<RequestDTO>>() {
            }.getType();
            requestDTO = modelMapper.map(requests, requestType);
        }
        return requestDTO;
    }

    @Override
    public void deleteFacultyRequest(Long requestId) {
        requestRepository.deleteById(requestId);
    }
}
