package com.college.service;

import com.college.core.entity.Department;
import com.college.core.entity.Purchase;
import com.college.core.model.DepartmentDTO;
import com.college.core.model.PurchaseDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    PurchaseRepository purchaseRepository;
    @Override
    public List<PurchaseDTO> getAllPurchase() {
        List<Purchase> purchases = purchaseRepository.findAll();
        Type targetListType = new TypeToken<List<PurchaseRepository>>() {}.getType();
        return modelMapper.map(purchases, targetListType);
    }
}
