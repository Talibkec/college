package com.college.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="PURCHASE")

public class Purchase {

    private Long purchaseId;
    private Integer quantity;
    private Date purchaseDate;
    private String purchaseBy;
    private Long price;
    private Long invoice;
    private Product product;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "purchaseId")
    @SequenceGenerator(name = "purchaseId", sequenceName = "purchaseId")
    @Column(name = "purchase_Id")
    public Long getPurchaseId() {
        return purchaseId;
    }


    //Setter and getter for Purchase ID
    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
    /*-------------------------------------------------------------*/


    //Setter and getter for Quantity
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column(name = "Quantity")
    public Integer getQuantity() {
        return quantity;
    }


    /*-------------------------------------------------------------*/


    //Setter and getter for Date
    @Temporal(TemporalType.DATE)
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Column(name = "Date")
    public Date getPurchaseDate() {
        return purchaseDate;
    }


    /*-------------------------------------------------------------*/


    //Setter and getter for Purchase By
    public void setPurchaseBy(String purchaseBy) {
        this.purchaseBy = purchaseBy;
    }

    @Column(name = "PurchaseBy")
    public String getPurchaseBy() {
        return purchaseBy;
    }


    /*-------------------------------------------------------------*/


    //Setter and getter for Price
    public void setPrice(Long price) {
        this.price = price;
    }

    @Column(name = "Price")
    public Long getPrice() {
        return price;
    }


    /*-------------------------------------------------------------*/


    //Setter and getter for Invoice/Bill
    public void setInvoice(Long invoice) {
        this.invoice = invoice;
    }

    @Column(name = "invoice")
    public Long getInvoice() {
        return invoice;
    }

    @ManyToOne
    @JoinColumn(name = "P_ID", referencedColumnName = "P_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


}

    /*-------------------------------------------------------------*/



