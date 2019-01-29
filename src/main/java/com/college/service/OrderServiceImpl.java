package com.college.service;

import com.college.core.entity.Department;
import com.college.core.entity.Order;
import com.college.core.model.DepartmentDTO;
import com.college.core.model.OrderDTO;
import com.college.repository.DepartmentRepository;
import com.college.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        Type targetListType = new TypeToken<List<OrderRepository>>() {}.getType();
        return modelMapper.map(orders, targetListType);
    }
}
