package com.college.repository;

import com.college.core.entity.AicteDocuments;
import com.college.core.entity.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AicteDocumentRepository extends  JpaRepository<AicteDocuments , Long> {

        @Query(value = "select * from aicte_documents", nativeQuery = true)
            //@Query("select ims from ImageSlide ims")
        List<AicteDocuments> getAllDocuments();

    }
