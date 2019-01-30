package com.college.repository;

import com.college.core.entity.Product;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from Product", nativeQuery=true)
    public List<Request> getAllProducts();

}
