package com.college.core.model;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class Customer {

    private Long customerId;
    private Long customerMobNumber;
    private String customerFName;
    private String customerMName;
    private String customerLName;
    private String customerEmailId;
    private String customerAddress;
    private Set<CustomerOrder> customerOrders = new HashSet<CustomerOrder>(0);

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCustomerMobNumber() {
        return customerMobNumber;
    }

    public void setCustomerMobNumber(Long customerMobNumber) {
        this.customerMobNumber = customerMobNumber;
    }

    public String getCustomerFName() {
        return customerFName;
    }

    public void setCustomerFName(String customerFName) {
        this.customerFName = customerFName;
    }

    public String getCustomerMName() {
        return customerMName;
    }

    public void setCustomerMName(String customerMName) {
        this.customerMName = customerMName;
    }

    public String getCustomerLName() {
        return customerLName;
    }

    public void setCustomerLName(String customerLName) {
        this.customerLName = customerLName;
    }

    public String getCustomerEmailId() {
        return customerEmailId;
    }

    public void setCustomerEmailId(String customerEmailId) {
        this.customerEmailId = customerEmailId;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Set<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(Set<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }

}
