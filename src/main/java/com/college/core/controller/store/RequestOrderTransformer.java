package com.college.core.controller.store;

import com.college.core.model.OrderDTO;
import com.college.core.model.RequestDTO;

import java.util.Date;

public class RequestOrderTransformer {
    public static OrderDTO convertToOrderDTO(RequestDTO requestDTO) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setRequestId(requestDTO.getRequestId());
        orderDTO.setFacultyId(requestDTO.getFacultyId());
        orderDTO.setProduct(requestDTO.getProduct());
        orderDTO.setProductQuantity(requestDTO.getProductQuantity());
        orderDTO.setRequestDate(requestDTO.getRequestDate());
        orderDTO.setApproval(requestDTO.getApprovalDate());
        orderDTO.setDeliveryDate(new Date());
        return  orderDTO;
    }
}
