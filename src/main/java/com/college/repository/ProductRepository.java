package com.college.repository;

import com.college.core.entity.Product;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "select * from Product", nativeQuery=true)
    public List<Request> getAllProducts();

    @Query("select prod from Product prod where prod.productName like lower(CONCAT('%',:prodName,'%'))")
    List<Product> getProductNamesLike(@Param("prodName") String prodName);

    @Query("select prod from Product prod where prod.vendorName like lower(CONCAT('%',:vendorName,'%'))")
    List<Product> getvendorNameLike(@Param("vendorName") String vendorName);

    @Query("select prod from Product prod where prod.vendorName = :vendorName and prod.productName = :prodName")
    List<Product> products(@Param("prodName") String prodName, @Param("vendorName") String vendorName);

    @Query("select prod from Product prod where prod.productName = :proName")
    List<Product> getProductNames(@Param("proName") String proName);

    @Query("select prod from Product prod where prod.vendorName = :vendorName")
    List<Product> getvendorNames(@Param("vendorName") String vendorName);
}
