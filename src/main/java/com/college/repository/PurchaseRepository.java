package com.college.repository;


import com.college.core.entity.Purchase;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    @Query(value = "select * from Purchase", nativeQuery=true)
    public List<Request> getAllPurchase();

}
