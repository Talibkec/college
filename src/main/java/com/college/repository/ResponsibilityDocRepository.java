package com.college.repository;

import com.college.core.entity.Download;
import com.college.core.entity.ResponsibilityDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponsibilityDocRepository extends JpaRepository<ResponsibilityDoc, Long> {

    // list page: without the file content
    @Query("select new com.college.core.entity.ResponsibilityDoc(r.id, r.fileName, r.uploadedBy, r.date, r.fileType) from ResponsibilityDoc r")
    List<ResponsibilityDoc> getAllDocuments();

}
