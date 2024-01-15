package com.college.service;

import com.college.ProductTransformer;
import com.college.core.entity.Product;
import com.college.core.model.ProductDTO;
import com.college.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        Type targetListType = new TypeToken<List<ProductRepository>>() {
        }.getType();
        return modelMapper.map(products, targetListType);
    }

    @Override
    public void save(ProductDTO product) {
        Product prod = modelMapper.map(product, Product.class);
        productRepository.save(prod);
    }

    @Override
    public List<String> prodName(String prodName) {
        prodName = prodName.toLowerCase();
        List<Product> products = productRepository.getProductNamesLike(prodName);
        List<String> productNames = ProductTransformer.getProductName(products);
        return productNames;
    }

    @Override
    public List<String> vendorName(String vendorName) {
        vendorName = vendorName.toLowerCase();
        List<Product> products = productRepository.getvendorNameLike(vendorName);
        List<String> productNames = ProductTransformer.getvendorName(products);
        return productNames;
    }

    @Override
    public List<ProductDTO> getProductDetails(String prodName, String vendorName, String productId) {
        List<Product> prods = new ArrayList<>();
        if (productId != null && productId != "") {
            Optional<Product> prod = productRepository.findById(Long.parseLong(productId));
            prods.add(prod.get());
        } else {
            if (!StringUtils.isEmpty(prodName) && !StringUtils.isEmpty(vendorName)) {
                prodName = prodName.toLowerCase();
                vendorName = vendorName.toLowerCase();
                prods = productRepository.products(prodName, vendorName);
            } else if (StringUtils.isEmpty(vendorName)) {
                prodName = prodName.toLowerCase();
                prods = productRepository.getProductNames(prodName);
            } else if (StringUtils.isEmpty(prodName)) {
                vendorName = vendorName.toLowerCase();
                prods = productRepository.getvendorNames(vendorName);
            }
        }
        Type targetListType = new TypeToken<List<ProductDTO>>() {
        }.getType();
        return modelMapper.map(prods, targetListType);
    }

    @Override
    public ProductDTO getProduct(Long productId) {
        Optional<Product> product = productRepository.findById(productId);
        return modelMapper.map(product.get(), ProductDTO.class);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
