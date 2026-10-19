package com.college.repository;

import com.college.core.entity.PageBlock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PageBlockRepository extends JpaRepository<PageBlock, Long> {
    List<PageBlock> findByPageKeyOrderBySortOrderAscBlockIdAsc(String pageKey);

    List<PageBlock> findByVisibleTrueOrderBySortOrderAscBlockIdAsc();

    long countByFileId(Long fileId);

    long countByFileIdAndVisibleTrue(Long fileId);

    @Query("select distinct b.pageKey from PageBlock b order by b.pageKey")
    List<String> findPageKeys();
}
