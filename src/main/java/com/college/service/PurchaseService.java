package com.college.service;

import com.college.core.model.PurchaseDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface PurchaseService {
    List<PurchaseDTO> getAllPurchase();
    public void savePurchaseDetails(PurchaseDTO purchaseDTO);

    List<PurchaseDTO> getPurchaseByProductId(Long productId, Date fromDate, Date toDate);

    PurchaseDTO getPurchase(Long purchaseId);

    void deletePurchase(Long purchaseId);

    List<PurchaseDTO> getPurchaseBtweenDates(Date from, Date to);
}
