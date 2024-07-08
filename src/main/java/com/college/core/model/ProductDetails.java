package com.college.core.model;

public class ProductDetails {

    private Long productDetailsId;
    private Long productId;
    private Long quantity;
    private String status;
    private String commnent;
    private String fontColor;
    private String paperColor;

    public Long getProductDetailsId() {
        return productDetailsId;
    }

    public void setProductDetailsId(Long productDetailsId) {
        this.productDetailsId = productDetailsId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCommnent() {
        return commnent;
    }

    public void setCommnent(String commnent) {
        this.commnent = commnent;
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

}
