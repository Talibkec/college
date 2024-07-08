package com.college.repository;

import com.college.core.entity.Holiday;
import com.college.core.entity.ResponsibilityDoc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {

    @Query(value = "select * from holiday", nativeQuery = true)
    List<Holiday> getAllDocuments();

}
