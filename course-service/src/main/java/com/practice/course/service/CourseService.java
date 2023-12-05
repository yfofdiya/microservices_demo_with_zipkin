package com.practice.course.service;

import com.practice.course.entity.Course;

import java.util.List;

public interface CourseService {

    // Create a new Course
    Course createCourse(Course course);

    // Get All Courses
    List<Course> getCourses();

    // Get Course by Course ID
    Course getCourseByCourseId(String courseId);

    // Get Courses by Student ID
    List<Course> getCoursesByStudentId(String studentId);

}
