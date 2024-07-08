package com.college.service;

import com.college.core.model.ProductDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<ProductDTO> getAllProduct();

    void save(ProductDTO product);

    List<String> prodName(String prodName);

    List<String> vendorName(String vendorName);

    List<ProductDTO> getProductDetails(String prodName, String vendorName, String productId);

    ProductDTO getProduct(Long productId);

    void deleteProduct(Long productId);

}
