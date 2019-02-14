package com.college.service;

import com.college.core.entity.Product;
import com.college.core.entity.Purchase;
import com.college.core.model.PurchaseDTO;
import com.college.repository.ProductRepository;
import com.college.repository.PurchaseRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    ProductRepository productRepository;
    @Override
    public List<PurchaseDTO> getAllPurchase() {
        List<Purchase> purchases = purchaseRepository.findAll();
        Type targetListType = new TypeToken<List<PurchaseRepository>>() {}.getType();
        return modelMapper.map(purchases, targetListType);
    }
    @Override
    public void savePurchaseDetails(PurchaseDTO purchaseDTO){

        Product product = updateProduct(purchaseDTO);
        Purchase purchase = modelMapper.map(purchaseDTO, Purchase.class);
        purchase.setProduct(product);
        productRepository.save(product);
        purchaseRepository.save(purchase);
    }

    @Override
    public List<PurchaseDTO> getPurchaseByProductId(Long productId, Date fromDate, Date toDate) {
        List<Purchase> purchases = purchaseRepository.getPurchaseByProductId(productId, fromDate, toDate);
        Type targetListType = new TypeToken<List<PurchaseDTO>>() {}.getType();
        return modelMapper.map(purchases, targetListType);
    }

    @Override
    public PurchaseDTO getPurchase(Long purchaseId) {
        Purchase purchase = purchaseRepository.findOne(purchaseId);
        return  modelMapper.map(purchase, PurchaseDTO.class);
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        purchaseRepository.delete(purchaseId);
    }

    @Override
    public List<PurchaseDTO> getPurchaseBtweenDates(Date from, Date to) {

        List<PurchaseDTO> purchaseDTOS = null;
        List<Purchase> purchases = purchaseRepository.getPurchaseBtweenDates(from, to);
        Type targetListType = new TypeToken<List<PurchaseDTO>>() {}.getType();
        if(purchases != null) {
            purchaseDTOS = modelMapper.map(purchases, targetListType);
        }
        return  purchaseDTOS;
    }

    private Product updateProduct(PurchaseDTO purchaseDTO){
        Product product = productRepository.findOne(purchaseDTO.getProduct().getProductId());
        if(product.getAvailableQuantity()!= null) {
            product.setAvailableQuantity(product.getAvailableQuantity() + purchaseDTO.getQuantity());
        }
        else {
            product.setAvailableQuantity(purchaseDTO.getQuantity());
        }
        if(product.getProductQuantity()!= null){
            product.setProductQuantity(product.getProductQuantity() + purchaseDTO.getQuantity());
        }
        else{
            product.setProductQuantity(purchaseDTO.getQuantity());
        }
        return product;
    }
}
