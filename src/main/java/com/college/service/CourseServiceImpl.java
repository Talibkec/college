package com.college.service;

import com.college.core.entity.Course;
import com.college.core.model.CourseDTO;
import com.college.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final ModelMapper modelMapper;

    public CourseServiceImpl(CourseRepository courseRepository, ModelMapper modelMapper) {
        this.courseRepository = courseRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveCourse(CourseDTO courseDTO) {

        Course course = new Course();
        course.setBranchName(courseDTO.getBranchName());
        course.setRegularSeat(courseDTO.getRegularSeat());
        course.setLateralSeat(courseDTO.getLateralSeat());

        courseRepository.save(course);
    }


    @Override
    public CourseDTO addCourse(CourseDTO courseDTO) {
        Course course = modelMapper.map(courseDTO, Course.class);
        Course savedCourse = courseRepository.save(course);
        return modelMapper.map(savedCourse, CourseDTO.class);
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseRepository.deleteById(courseId);
    }


    @Override
    public List<CourseDTO> getAllCourses() {
       List<Course> courses = courseRepository.findAll();
        Type targetListType = new TypeToken<List<CourseDTO>>() {}.getType();
        return modelMapper.map(courses, targetListType);
    }


}
