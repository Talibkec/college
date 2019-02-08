package com.college.repository;


import com.college.core.entity.Purchase;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

    @Query("select p from Purchase p where p.product.productId = :productId and p.purchaseDate between :fromDate and :toDate")
    List<Purchase> getPurchaseByProductId(@Param("productId") Long productId, @Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
