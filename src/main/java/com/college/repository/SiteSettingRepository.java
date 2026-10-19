package com.college.repository;

import com.college.core.entity.SiteSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SiteSettingRepository extends JpaRepository<SiteSetting, String> {
    List<SiteSetting> findByAreaCodeInOrderBySettingKeyAsc(Collection<String> areaCodes);
}
