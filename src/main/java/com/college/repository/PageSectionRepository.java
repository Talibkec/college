package com.college.repository;

import com.college.core.entity.PageSection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PageSectionRepository extends JpaRepository<PageSection, Long> {
    List<PageSection> findByAreaCodeInOrderByPageKeyAscSortOrderAsc(Collection<String> areaCodes);
}
