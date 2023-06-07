package com.college.repository;

import com.college.core.entity.AicteDocuments;
import com.college.core.entity.Download;
import com.college.core.model.DownloadDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DownloadRepository extends JpaRepository<Download, Long> {

    @Query(value = "select * from download", nativeQuery = true)
    List<Download> getAllDocuments();

}
