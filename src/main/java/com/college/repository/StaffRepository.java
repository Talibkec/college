package com.college.repository;

import com.college.core.entity.Staff;
import com.college.core.entity.Request;

import com.college.core.entity.Staff;
import com.college.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StaffRepository extends JpaRepository<Staff, Long> {
    @Query(value = "select * from staff", nativeQuery = true)
    public List<Request> getAllStaff();

    @Query("select f from Staff f where f.user.username = :userName")
    Staff findFacltyByUserName(@Param("userName") String userName);

    @Query("select f from Staff f where f.staffName like lower(CONCAT('%',:StaffName,'%'))")
    List<Staff> searchStaffName(@Param("StaffName") String StaffName);

    @Query("select f from Staff f where f.staffOfficialEmail like lower(CONCAT('%',:StaffEmail,'%'))")
    List<Staff> searchStaffEmail(@Param("StaffEmail") String StaffEmail);

    @Query("select f from Staff f where f.staffName like lower(CONCAT('%',:StaffName,'%'))")
    Staff searchStaffByName(@Param("StaffName") String StaffName);

    @Query("select f from Staff f where f.staffName = :StaffName)")
    List<Staff> getStaffByName(@Param("StaffName") String StaffName);

    @Query("select f from Staff f where f.staffOfficialEmail = :userEmail")
    Staff findUserByEmail(@Param("userEmail") String userEmail);

//    @Query("select f from Staff f where f.departmentId = :deptno)")
//    List<Staff> getStaffByDeptNo(@Param("deptno") Long deptno);

    @Query("select f from Staff f")
    List<Staff> getFacultiesName();
    @Query("select f from Staff f where f.staffOfficialEmail = :StaffEmail")
    Staff searchByEmail(@Param("StaffEmail") String StaffEmail);

}
