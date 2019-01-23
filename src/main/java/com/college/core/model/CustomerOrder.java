package com.college.core.model;

import java.util.Date;
import java.util.List;

public class CustomerOrder {

    private Long orderId;
    private Long customerId;
    private Date orderPlaceDate;
    private Date orderDueDate;
    private Date orderDeliveredDate;
    private Double orderTotalPrice;
    private Double orderAdvanceMoney;
    private Double orderDiscount;
    private Double orderBalance;
    private String orderStatus;
    private Customer customer;

    private List<ProductDetails> orderDetails;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Date getOrderPlaceDate() {
        return orderPlaceDate;
    }

    public void setOrderPlaceDate(Date orderPlaceDate) {
        this.orderPlaceDate = orderPlaceDate;
    }

    public Date getOrderDueDate() {
        return orderDueDate;
    }

    public void setOrderDueDate(Date orderDueDate) {
        this.orderDueDate = orderDueDate;
    }

    public Date getOrderDeliveredDate() {
        return orderDeliveredDate;
    }

    public void setOrderDeliveredDate(Date orderDeliveredDate) {
        this.orderDeliveredDate = orderDeliveredDate;
    }

    public Double getOrderTotalPrice() {
        return orderTotalPrice;
    }

    public void setOrderTotalPrice(Double orderTotalPrice) {
        this.orderTotalPrice = orderTotalPrice;
    }

    public Double getOrderAdvanceMoney() {
        return orderAdvanceMoney;
    }

    public void setOrderAdvanceMoney(Double orderAdvanceMoney) {
        this.orderAdvanceMoney = orderAdvanceMoney;
    }

    public Double getOrderDiscount() {
        return orderDiscount;
    }

    public void setOrderDiscount(Double orderDiscount) {
        this.orderDiscount = orderDiscount;
    }

    public Double getOrderBalance() {
        return orderBalance;
    }

    public void setOrderBalance(Double orderBalance) {
        this.orderBalance = orderBalance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public List<ProductDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<ProductDetails> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
