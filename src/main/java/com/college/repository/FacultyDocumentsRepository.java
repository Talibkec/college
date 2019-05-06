package com.college.repository;

import com.college.core.entity.FacultyDocuments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyDocumentsRepository extends JpaRepository <FacultyDocuments, Long> {


    @Query("select fd from FacultyDocuments fd where fd.uploadedBy = :userName order by fd.date desc")
    List<FacultyDocuments> getFacultyDocuments(@Param("userName") String userName);
}
