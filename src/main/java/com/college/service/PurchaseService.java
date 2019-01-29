package com.college.service;

import com.college.core.model.PurchaseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PurchaseService {
    List<PurchaseDTO> getAllPurchase();
}
