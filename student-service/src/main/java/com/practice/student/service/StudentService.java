package com.practice.student.service;

import com.practice.student.entity.Student;
import com.practice.student.model.Address;
import com.practice.student.model.Course;

import java.util.List;

public interface StudentService {

    // Create Student
    Student createStudent(Student student);

    // Get All Students
    List<Student> getAllStudents();

    // Get Student by Student ID
    Student getStudentByStudentId(String studentId);

    List<Course> getCoursesByStudentId(Student student);

    List<Address> getAddressesByStudentId(Student student);
}
