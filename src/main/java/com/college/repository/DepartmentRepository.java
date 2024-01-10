package com.college.repository;

import com.college.core.entity.Department;
import com.college.core.entity.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    @Query(value = "select * from Department", nativeQuery = true)
    public List<Request> getAllDepartmnet();
    
    

    @Query("select new com.college.core.entity.Department(dept.departmentHod) from Department dept where dept.departmentId = :deptId")
    public Department getHodName(@Param("deptId") Long deptId);
    
    @Transactional
    @Modifying
    @Query(value = "update Department set departmentHod = :facultyName where departmentId = :departmentId")
    public void  updateHodName(@Param("facultyName") String facultyName, @Param("departmentId") Long departmentId);
    
    @Transactional
    @Modifying
    @Query(value="update Department set departmentHod = NULL where departmentId = :departmentId")
    public void deleteHodName(@Param("departmentId") Long departmentId);
}
