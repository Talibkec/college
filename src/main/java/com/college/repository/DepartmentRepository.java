package com.college.repository;

import com.college.core.entity.Department;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query(value = "select * from Department", nativeQuery = true)
    public List<Request> getAllDepartmnet();
}
