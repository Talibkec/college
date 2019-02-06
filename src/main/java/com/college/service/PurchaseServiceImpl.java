package com.college.service;

import com.college.core.entity.Department;
import com.college.core.entity.Product;
import com.college.core.entity.Purchase;
import com.college.core.model.DepartmentDTO;
import com.college.core.model.PurchaseDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.ProductRepository;
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

    private Product updateProduct(PurchaseDTO purchaseDTO){
        Product product = productRepository.findOne(purchaseDTO.getProductDTO().getProductId());
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
