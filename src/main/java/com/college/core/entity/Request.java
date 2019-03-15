package com.college.core.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="REQUEST")
public class Request {
    private Long requestId;
    private Long facultyId;
    private Product product;
    private Integer productQuantity;
    private Date requestDate;
    private String status;
    private Date approvalDate;
    private Date deliverDate;
    private String desc;
    @Column(name = "DESCRIPTION")
    public String getDesc() { return desc;}

    public void setDesc(String desc) {this.desc = desc;}
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "requestId")
    @SequenceGenerator(name = "requestId", sequenceName = "requestId")
    @Column(name = "request_Id")
    public Long getRequestId() {
        return requestId;
    }


    //Setter and getter for Request ID

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    /*-------------------------------------------------------------*/




    //Setter and getter for Faculty ID

    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
    @Column(name = "F_Id")
    public Long getFacultyId() {
        return facultyId;
    }


    /*-------------------------------------------------------------*/



    //Setter and getter for Product ID
    @ManyToOne
    @JoinColumn(name = "P_ID")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    /*-------------------------------------------------------------*/



    //Setter and getter for Product Quantity

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
    @Column(name = "Prod_Quantity")
    public Integer getProductQuantity() {
        return productQuantity;
    }


    /*-------------------------------------------------------------*/




    //Setter and getter for Request Date

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }
    @Column(name = "Request_Date")
    @Temporal(TemporalType.DATE)
    public Date getRequestDate() {
        return requestDate;
    }

    /*-------------------------------------------------------------*/


    //Setter and getter for Status

    public void setStatus(String status) {
        this.status = status;
    }
    @Column(name = "Status")
    public String getStatus() {
        return status;
    }

    /*-------------------------------------------------------------*/




    //Setter and getter for Approval Date

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }
    @Column(name = "Approval_Date")
    @Temporal(TemporalType.DATE)
    public Date getApprovalDate() {
        return approvalDate;
    }

    /*-------------------------------------------------------------*/




    //Setter and getter for Deliver Date

    public void setDeliverDate(Date deliverDate) {
        this.deliverDate = deliverDate;
    }
    @Column(name = "Deliver_Date")
    @Temporal(TemporalType.DATE)
    public Date getDeliverDate() {
        return deliverDate;
    }



    /*-------------------------------------------------------------*/

}
