package com.college.service;

import com.college.core.model.OrderDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public interface OrderService {
    List<OrderDTO> getAllOrder();

    List<OrderDTO> getFacultyOrders(Long userName);

    void saveOrder(OrderDTO orderDTO);

    List<OrderDTO> getOrderByFacultyName(Long facultyId, Date from, Date to);

    List<OrderDTO> getOrderByProductName(String prodName, Date from, Date to);

    List<OrderDTO> getOrderBetweenDate(Date from, Date to);
}
