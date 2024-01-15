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
import java.util.Optional;

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
        Type targetListType = new TypeToken<List<PurchaseRepository>>() {
        }.getType();
        return modelMapper.map(purchases, targetListType);
    }

    @Override
    public void savePurchaseDetails(PurchaseDTO purchaseDTO) {

        Product product = updateProduct(purchaseDTO);
        Purchase purchase = modelMapper.map(purchaseDTO, Purchase.class);
        purchase.setProduct(product);
        productRepository.save(product);
        purchaseRepository.save(purchase);
    }

    @Override
    public List<PurchaseDTO> getPurchaseByProductId(Long productId, Date fromDate, Date toDate) {
        List<Purchase> purchases = purchaseRepository.getPurchaseByProductId(productId, fromDate, toDate);
        Type targetListType = new TypeToken<List<PurchaseDTO>>() {
        }.getType();
        return modelMapper.map(purchases, targetListType);
    }

    @Override
    public PurchaseDTO getPurchase(Long purchaseId) {
        Optional<Purchase> purchase = purchaseRepository.findById(purchaseId);
        return modelMapper.map(purchase.get(), PurchaseDTO.class);
    }

    @Override
    public void deletePurchase(Long purchaseId) {
        purchaseRepository.deleteById(purchaseId);
    }

    @Override
    public List<PurchaseDTO> getPurchaseBtweenDates(Date from, Date to) {

        List<PurchaseDTO> purchaseDTOS = null;
        List<Purchase> purchases = purchaseRepository.getPurchaseBtweenDates(from, to);
        Type targetListType = new TypeToken<List<PurchaseDTO>>() {
        }.getType();
        if (purchases != null) {
            purchaseDTOS = modelMapper.map(purchases, targetListType);
        }
        return purchaseDTOS;
    }

    private Product updateProduct(PurchaseDTO purchaseDTO) {
        Optional<Product> product = productRepository.findById(purchaseDTO.getProduct().getProductId());
        if (product.get().getAvailableQuantity() != null) {
            product.get().setAvailableQuantity(product.get().getAvailableQuantity() + purchaseDTO.getQuantity());
        } else {
            product.get().setAvailableQuantity(purchaseDTO.getQuantity());
        }
        if (product.get().getProductQuantity() != null) {
            product.get().setProductQuantity(product.get().getProductQuantity() + purchaseDTO.getQuantity());
        } else {
            product.get().setProductQuantity(purchaseDTO.getQuantity());
        }
        return product.get();
    }
}
