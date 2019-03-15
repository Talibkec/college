package com.college.repository;

import com.college.core.entity.Faculty;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {
    @Query(value = "select * from Faculty", nativeQuery=true)
    public List<Request> getAllFaculty();

    @Query("select f from Faculty f where f.user.username = :userName")
    Faculty findFacltyByUserName(@Param("userName") String userName);

    @Query("select f.facultyName from Faculty f where f.facultyName like lower(CONCAT('%',:facultyName,'%'))")
    List<String> searchFacultyName(@Param("facultyName") String facultyName);

    @Query("select f from Faculty f where f.facultyName = :facultyName)")
    List<Faculty> getFacultyByName(@Param("facultyName") String facultyName);

    @Query("select f from Faculty f where f.facultyOfficialEmail = :userEmail")
    Faculty findUserByEmail(@Param("userEmail") String userEmail);
}
