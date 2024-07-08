package com.college.core.model;

public class ProductAttribute {
    private Long attributeId;
    private Long productId;
    private String fontColor;
    private String paperColor;
    private Double price;

    private String productDesccription;

    public Long getAttributeId() {
        return attributeId;
    }

    public void setAttributeId(Long attributeId) {
        this.attributeId = attributeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getFontColor() {
        return fontColor;
    }

    public void setFontColor(String fontColor) {
        this.fontColor = fontColor;
    }

    public String getPaperColor() {
        return paperColor;
    }

    public void setPaperColor(String paperColor) {
        this.paperColor = paperColor;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getProductDesccription() {
        return productDesccription;
    }

    public void setProductDesccription(String productDesccription) {
        this.productDesccription = productDesccription;
    }


}
