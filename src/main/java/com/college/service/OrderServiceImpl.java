package com.college.service;

import com.college.core.entity.Order;
import com.college.core.model.OrderDTO;
import com.college.repository.OrderRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    ModelMapper modelMapper = new ModelMapper();
    @Autowired
    OrderRepository orderRepository;

    @Override
    public List<OrderDTO> getAllOrder() {
        List<Order> orders = orderRepository.findAll();
        Type targetListType = new TypeToken<List<OrderDTO>>() {
        }.getType();
        return modelMapper.map(orders, targetListType);
    }

    @Override
    public List<OrderDTO> getFacultyOrders(Long facultyId) {
        List<Order> orders = orderRepository.getFacultyOrder(facultyId);
        Type targetListType = new TypeToken<List<OrderDTO>>() {
        }.getType();
        return modelMapper.map(orders, targetListType);
    }

    @Override
    public void saveOrder(OrderDTO orderDTO) {
        Order order = modelMapper.map(orderDTO, Order.class);
        orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getOrderByFacultyName(Long facultyId, Date from, Date to) {
        List<OrderDTO> orderDTOS = null;
        List<Order> orders = orderRepository.getOrderByFaculId(facultyId, from, to);
        if (orders != null) {
            Type targetListType = new TypeToken<List<OrderDTO>>() {
            }.getType();
            orderDTOS = modelMapper.map(orders, targetListType);
        }
        return orderDTOS;
    }

    @Override
    public List<OrderDTO> getOrderByProductName(String prodName, Date from, Date to) {
        List<OrderDTO> orderDTOS = null;
        List<Order> orders = orderRepository.getOrderByProductName(prodName, from, to);
        if (orders != null) {
            Type targetListType = new TypeToken<List<OrderDTO>>() {
            }.getType();
            orderDTOS = modelMapper.map(orders, targetListType);
        }
        return orderDTOS;
    }

    @Override
    public List<OrderDTO> getOrderBetweenDate(Date from, Date to) {
        List<OrderDTO> orderDTOS = null;
        List<Order> orders = orderRepository.getOrderBetweenDate(from, to);
        if (orders != null) {
            Type targetListType = new TypeToken<List<OrderDTO>>() {
            }.getType();
            orderDTOS = modelMapper.map(orders, targetListType);
        }
        return orderDTOS;
    }
}
