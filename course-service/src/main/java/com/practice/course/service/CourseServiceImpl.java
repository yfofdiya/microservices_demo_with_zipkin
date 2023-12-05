package com.practice.course.service;

import com.practice.course.entity.Course;
import com.practice.course.exception.CourseNotFoundException;
import com.practice.course.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public Course createCourse(Course course) {
        String courseId = UUID.randomUUID().toString();
        course.setId(courseId);
        Course createdCourse = repository.save(course);
        log.info("Course is created successfully");
        return createdCourse;
    }

    @Override
    public List<Course> getCourses() {
        List<Course> courses = repository.findAll();
        log.info("All courses are fetched successfully");
        return courses;
    }

    @Override
    public Course getCourseByCourseId(String courseId) {
        return repository.findById(courseId).orElseThrow(() -> {
                    throw new CourseNotFoundException("Course not found by provided id " + courseId);
                }
        );
    }

    @Override
    public List<Course> getCoursesByStudentId(String studentId) {
        return repository.findByStudentId(studentId);
    }
}
