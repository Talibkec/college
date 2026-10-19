package com.college.repository;

import com.college.core.entity.ContentChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContentChangeLogRepository extends JpaRepository<ContentChangeLog, Long> {
}
