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

    // list pages: without the file content
    @Query("select new com.college.core.entity.Download(d.id, d.fileName, d.uploadedBy, d.date, d.fileType) from Download d where d.docType = 'download'")
    List<Download> getAllDocuments();
    @Query("select new com.college.core.entity.Download(d.id, d.fileName, d.uploadedBy, d.date, d.fileType) from Download d where d.docType = 'nba'")
    List<Download> getAllNBADocuments();

}
