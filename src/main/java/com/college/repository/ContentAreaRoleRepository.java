package com.college.repository;

import com.college.core.entity.ContentAreaRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContentAreaRoleRepository extends JpaRepository<ContentAreaRole, ContentAreaRole.Key> {
    List<ContentAreaRole> findByRoleNameIn(Collection<String> roleNames);
}
