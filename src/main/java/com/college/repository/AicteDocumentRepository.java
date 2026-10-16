package com.college.repository;

import com.college.core.entity.AicteDocuments;
import com.college.core.entity.ImageSlide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AicteDocumentRepository extends  JpaRepository<AicteDocuments , Long> {

        @Query("select a from AicteDocuments a")
        List<AicteDocuments> getAllDocuments();

    }
