package com.college.repository;

import com.college.core.entity.ContentFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ContentFileRepository extends JpaRepository<ContentFile, Long> {

    /** id, name, type, size (without loading the file data). */
    @Query("select f.fileId, f.fileName, f.contentType, f.fileSize from ContentFile f where f.fileId in :ids")
    List<Object[]> findInfo(@Param("ids") Collection<Long> ids);
}
