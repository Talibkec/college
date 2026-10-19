package com.college.repository;

import com.college.core.entity.LinkItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinkItemRepository extends JpaRepository<LinkItem, Long> {
    List<LinkItem> findByListCodeOrderBySortOrderAscLinkIdAsc(String listCode);

    List<LinkItem> findByVisibleTrueOrderBySortOrderAscLinkIdAsc();

    List<LinkItem> findByParentId(Long parentId);
}
