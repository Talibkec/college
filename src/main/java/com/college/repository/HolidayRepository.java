package com.college.repository;

import com.college.core.entity.Holiday;
import com.college.core.entity.ResponsibilityDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    // list page: without the file content
    @Query("select new com.college.core.entity.Holiday(h.id, h.fileName, h.uploadedBy, h.date, h.fileType) from Holiday h order by h.id desc")
    List<Holiday> getAllDocuments();

}
