package com.college.core.model;


import java.util.Date;

public class PurchaseDTO {
    private Long purchaseId;
    private Integer quantity;
    private Date purchaseDate;
    private String purchaseBy;
    private Long price;
    private Long invoice;
    private ProductDTO product;


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

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public ProductDTO getProduct() {
        return product;
    }

    public void setProduct(ProductDTO product) {
        this.product = product;
    }


}
