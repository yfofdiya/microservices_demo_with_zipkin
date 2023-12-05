package com.practice.course.repository;

import com.practice.course.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {

    @Query(value = "SELECT * FROM courses WHERE student_id = :studentId", nativeQuery = true)
    List<Course> findByStudentId(@Param("studentId") String studentId);
}
