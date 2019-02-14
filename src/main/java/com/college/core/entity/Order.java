package com.college.core.entity;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ORDER_COLLEGE")
public class Order {
    private Long orderId;
    private Long requestId;
    private Long facultyId;
    private Product product;
    private Integer productQuantity;
    private Date requestDate;
    private Date deliveryDate;
    private  Date approvalDate;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "orderId")
    @SequenceGenerator(name = "orderId", sequenceName = "orderId")
    public Long getOrderId() {
        return orderId;
    }

    //Setter and getter for Order Id
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @ManyToOne
    @JoinColumn
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Temporal(TemporalType.DATE)
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /*-------------------------------------------------------------*/

    //Setter and getter for Request Id
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
    @Column(name = "req_Id")
    public Long getRequestId() {
        return requestId;
    }

    /*-------------------------------------------------------------*/



    //Setter and getter for faculty Id
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }
    @Column(name = "F_Id")
    public Long getFacultyId() {
        return facultyId;
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
    @Temporal(TemporalType.DATE)
    @Column(name = "Req_Date")
    public Date getRequestDate() {
        return requestDate;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }

    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
    }

}
