package com.college.repository;

import com.college.core.entity.ContentArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentAreaRepository extends JpaRepository<ContentArea, String> {
    List<ContentArea> findAllByOrderBySortOrderAsc();
}
