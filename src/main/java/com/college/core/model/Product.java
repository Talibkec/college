package com.college.core.model;

import java.util.ArrayList;
import java.util.List;

public class Product {

    private Long productId;
    private String productName;
    private String productDescription;

    List<ProductAttribute> prodAttributes = new ArrayList<>(0);

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public List<ProductAttribute> getProdAttributes() {
        return prodAttributes;
    }

    public void setProdAttributes(List<ProductAttribute> prodAttributes) {
        this.prodAttributes = prodAttributes;
    }

}
