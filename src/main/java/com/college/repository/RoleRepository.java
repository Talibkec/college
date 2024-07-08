package com.college.repository;

import com.college.core.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("select r from Role r where r.name = :sm")
    Role getRoleByName(@Param("sm") String sm);

    @Query("select h from Role h where h.name = :hod")
    Role getHodRoleByName(@Param("hod") String hod);
}
