package com.college.repository;

import com.college.core.entity.TpoCoordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface TpoRepository extends JpaRepository<TpoCoordinator, Long> {

    // Find by student name
    TpoCoordinator findByStudentName(String studentName);

    // Find by email
    TpoCoordinator findByEmail(String email);

    // Find by student name containing (for searching)
    List<TpoCoordinator> findByStudentNameContaining(String studentName);

    // Find by email containing (for searching)
    List<TpoCoordinator> findByEmailContaining(String email);

    // Find by department id
    List<TpoCoordinator> findByDepartmentId(Long deptId);

}
