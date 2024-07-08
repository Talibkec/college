package com.college.core.model;

import java.util.Date;

public class OrderDTO {
    private Long orderId;
    private Long requestId;
    private Long facultyId;
    private ProductDTO product;
    private Date approval;
    private Date deliveryDate;
    private Integer productQuantity;
    private Date requestDate;

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    private String facultyName;

    public void setApproval(Date approval) {
        this.approval = approval;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getApproval() {
        return approval;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }

    public String getFacultyName() {
        return facultyName;
    }


}
