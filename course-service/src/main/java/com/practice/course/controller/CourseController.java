package com.practice.course.controller;

import com.practice.course.dto.CourseDTO;
import com.practice.course.entity.Course;
import com.practice.course.service.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@Slf4j
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        return ResponseEntity.ok().body(service.getCourses());
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@Valid @RequestBody CourseDTO courseDTO) {
        Course course = Course
                .builder()
                .name(courseDTO.getCourseName())
                .studentId(courseDTO.getStudentId())
                .build();
        Course createdCourse = service.createCourse(course);
        return ResponseEntity.ok().body(createdCourse);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseByCourseId(@PathVariable("courseId") String courseId) {
        return ResponseEntity.ok().body(service.getCourseByCourseId(courseId));
    }

    @GetMapping("/students/{studentId}")
    public ResponseEntity<List<Course>> getCourseByStudentId(@PathVariable("studentId") String studentId) {
        log.info("Fetching all the courses enrolled by the student {}", studentId);
        return ResponseEntity.ok().body(service.getCoursesByStudentId(studentId));
    }
}
