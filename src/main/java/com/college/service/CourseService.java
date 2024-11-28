package com.college.service;

import com.college.core.model.CourseDTO;

import java.util.List;
import java.util.Map;

public interface CourseService {
    List<CourseDTO> getAllCourses();
    void saveCourse(CourseDTO courseDTO);
    CourseDTO addCourse(CourseDTO courseDTO);
    void deleteCourse(Long courseId);

}

