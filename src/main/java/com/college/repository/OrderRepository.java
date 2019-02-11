package com.college.repository;

import com.college.core.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "select * from order", nativeQuery = true)
    public List<Order> getAllOrders();

    @Query("select o from Order o where o.facultyId = :facultyId")
    List<Order> getFacultyOrder(@Param("facultyId") Long facultyId);
}
