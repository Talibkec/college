package com.college.repository;

import com.college.core.entity.DepartmentSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentSiteRepository extends JpaRepository<DepartmentSite, String> {
    List<DepartmentSite> findByHodUsername(String hodUsername);

    List<DepartmentSite> findByDeptId(Long deptId);

    List<DepartmentSite> findAllByOrderBySortOrderAsc();
}
