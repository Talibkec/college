package com.college.core.entity;

import javax.persistence.*;


@Entity
@Table(name="PRODUCT")
public class Product {
    private Long productId;
    private String productName;
    private Integer availableQuantity;
    private Integer productQuantity;
    private String productDescription;
    private String vendorName;
    private String productType;


    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "productId")
    @SequenceGenerator(name = "productId", sequenceName = "productId")


    //Setter and getter for Product Id
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    @Column(name="P_ID")
    public Long getProductId() {
        return productId;
    }

    /*-------------------------------------------------------------*/



    //Setter and getter for Product Name
    public void setProductName(String productName) {
        this.productName = productName;
    }
    @Column(name="Product_Name")
    public String getProductName() {
        return productName;
    }


    /*-------------------------------------------------------------*/



    //Setter and getter for Available Quantity
    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
    @Column(name="Available_Quantity")
    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    /*-------------------------------------------------------------*/


    //Setter and getter for Product Quantity
    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
    @Column(name="Product_Quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }


    /*-------------------------------------------------------------*/



    //Setter and getter for Product Description
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
    @Column(name="Product_Description")
    public String getProductDescription() {
        return productDescription;
    }


    /*-------------------------------------------------------------*/



    //Setter and getter for Vendor Name
    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }
    @Column(name="Vendor_Name")
    public String getVendorName() {
        return vendorName;
    }


    /*-------------------------------------------------------------*/



    //Setter and getter for Product Type
    public void setProductType(String productType) {
        this.productType = productType;
    }
    @Column(name = "Prod_Type")
    public String getProductType() {
        return productType;
    }

    /*-------------------------------------------------------------*/

}
