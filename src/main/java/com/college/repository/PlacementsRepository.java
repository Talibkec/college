package com.college.repository;

import com.college.core.entity.Placements;
import com.college.core.entity.Request;

import com.college.core.entity.Placements;
import com.college.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlacementsRepository extends JpaRepository<Placements, Long> {


}
