package com.college.repository;

import com.college.core.entity.Administration;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

    @Repository
    public interface AdministrationRepository extends JpaRepository<Administration, Long> {
        @Query(value = "select * from Administration", nativeQuery=true)
        public List<Request> getAllAdministration();
    }
