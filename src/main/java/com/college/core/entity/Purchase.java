package com.college.core.entity;

import org.hibernate.validator.constraints.Mod10Check;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="PURCHASE")

public class Purchase {
    private Long purchaseId;
    private Integer quantity;
    private Date date;
    private String purchaseBy;
    private Long price;
    private Long invoice;




    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "purchaseId")
    @SequenceGenerator(name = "purchaseId", sequenceName = "purchaseId")



    //Setter and getter for Purchase ID
    public void setPurchaseId(Long purchaseId) {
        this.purchaseId = purchaseId;
    }
    @Column(name = "purchase_Id")
    public Long getPurchaseId() {
        return purchaseId;
    }

    /*-------------------------------------------------------------*/



    //Setter and getter for Quantity
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    @Column(name="Quantity")
    public Integer getQuantity() {
        return quantity;
    }


    /*-------------------------------------------------------------*/




    //Setter and getter for Date
    public void setDate(Date date) {
        this.date = date;
    }
    @Column(name="Date")
    public Date getDate() {
        return date;
    }


    /*-------------------------------------------------------------*/




    //Setter and getter for Purchase By
    public void setPurchaseBy(String purchaseBy) {
        this.purchaseBy = purchaseBy;
    }
    @Column(name="PurchaseBy")
    public String getPurchaseBy() {
        return purchaseBy;
    }


    /*-------------------------------------------------------------*/



    //Setter and getter for Price
    public void setPrice(Long price) {
        this.price = price;
    }
    @Column(name="Price")
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

    /*-------------------------------------------------------------*/
}

