package com.college.repository;

import com.college.core.entity.Faculty;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query(value = "select * from Faculty", nativeQuery=true)
    public List<Request> getAllFaculty();

}
