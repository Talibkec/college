package com.college.service;

import com.college.core.entity.Department;
import com.college.core.entity.Product;
import com.college.core.model.DepartmentDTO;
import com.college.core.model.ProductDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService{
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    ProductRepository productRepository;
    @Override
    public List<ProductDTO> getAllProduct() {
        List<Product> products = productRepository.findAll();
        Type targetListType = new TypeToken<List<ProductRepository>>() {}.getType();
        return modelMapper.map(products, targetListType);
    }
}
