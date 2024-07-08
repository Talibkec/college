package com.college.repository;

import com.college.core.entity.FacultyKeyPropValues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyKeyPrpsValueRepository extends JpaRepository<FacultyKeyPropValues, Long> {
}
