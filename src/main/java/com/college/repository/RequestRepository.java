package com.college.repository;

import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    @Query(value = "select * from Request", nativeQuery=true)
    public List<Request> getAllRequests();

    @Query("select r from Request r where r.facultyId = :facultyId")
    List<Request> getFacultyRequest(@Param("facultyId") Long facultyId);

    @Query("select r from Request r where r.status = :aNew")
    List<Request> getNewRequest(@Param("aNew") String aNew);
}
