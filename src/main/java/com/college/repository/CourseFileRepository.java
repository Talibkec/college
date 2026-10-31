package com.college.repository;

import com.college.core.entity.CourseFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseFileRepository extends JpaRepository<CourseFile, Long> {

    List<CourseFile> findBySiteCodeOrderBySemesterAscSubjectCodeAscSubjectNameAsc(String siteCode);

    long countByFileId(Long fileId);
}
