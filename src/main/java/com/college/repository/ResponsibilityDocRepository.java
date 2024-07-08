package com.college.repository;

import com.college.core.entity.Download;
import com.college.core.entity.ResponsibilityDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsibilityDocRepository extends JpaRepository<ResponsibilityDoc, Long> {

    @Query(value = "select * from responsibility_doc", nativeQuery = true)
    List<ResponsibilityDoc> getAllDocuments();

}
