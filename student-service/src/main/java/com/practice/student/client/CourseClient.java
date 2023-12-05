package com.practice.student.client;


import com.practice.student.model.Course;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "course-service", url = "http://localhost:9092/courses")
@FeignClient(name = "COURSE-SERVICE")
public interface CourseClient {

    @GetMapping("/courses/students/{studentId}")
    List<Course> getCoursesByStudentId(@PathVariable("studentId") String studentId);
}
