package com.college.core.entity;
import javax.naming.Name;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="ORDER")
public class Order {
    private Long orderId;
    private Long requestId;
    private Long facultyId;
    private Long productId;
    private Integer productQuantity;
    private Date requestDate;
    private String approval;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO, generator = "orderId")
    @SequenceGenerator(name = "orderId", sequenceName = "orderId")
    @Column(name = "orderId")
    public Long getOrderId() {
        return orderId;
    }

    //Setter and getter for Order Id
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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



    //Setter and getter for Product Id
    public void setProductId(Long productId) {
        this.productId = productId;
    }
    @Column(name = "Prod_Id")
    public Long getProductId() {
        return productId;
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
    @Column(name = "Req_Date")
    public Date getRequestDate() {
        return requestDate;
    }

    /*-------------------------------------------------------------*/


    //Setter and getter for Approval
    public void setApproval(String approval) {
        this.approval = approval;
    }
    @Column(name = "approval")
    public String getApproval() {
        return approval;
    }


    /*-------------------------------------------------------------*/
}
