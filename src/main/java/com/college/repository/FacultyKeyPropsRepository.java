package com.college.repository;

import com.college.core.entity.Faculty;
import com.college.core.entity.FacultyKeyProps;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacultyKeyPropsRepository extends JpaRepository<FacultyKeyProps, Long> {
}
