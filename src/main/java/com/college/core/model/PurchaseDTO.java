package com.college.core.model;

import org.springframework.beans.factory.annotation.Autowired;


import java.util.Date;

public class PurchaseDTO {
    private Long purchaseId;
    private Integer quantity;
    private Date date;
    private String purchaseBy;
    private Long price;
    private Long invoice;
    private ProductDTO productDTO;


    public Long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPurchaseBy() {
        return purchaseBy;
    }

    public void setPurchaseBy(String purchaseBy) {
        this.purchaseBy = purchaseBy;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getInvoice() {
        return invoice;
    }

    public void setInvoice(Long invoice) {
        this.invoice = invoice;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }


}
