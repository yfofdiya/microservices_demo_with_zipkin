package com.practice.student.controller;

import com.practice.student.client.CourseClient;
import com.practice.student.dto.StudentDTO;
import com.practice.student.entity.Student;
import com.practice.student.model.Address;
import com.practice.student.model.Course;
import com.practice.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService service;

    @Autowired
    private CourseClient client;

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> studentList = service.getAllStudents();
        studentList.stream().map(student -> {
            List<Course> coursesByStudentId = service.getCoursesByStudentId(student);
            student.setCourses(coursesByStudentId);
            return student;
        }).collect(Collectors.toList());
        studentList.stream().map(student -> {
            List<Address> addressesByStudentId = service.getAddressesByStudentId(student);
            student.setAddresses(addressesByStudentId);
            return student;
        }).collect(Collectors.toList());
        return ResponseEntity.ok().body(studentList);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody StudentDTO studentDTO) {
        Student student = Student
                .builder()
                .name(studentDTO.getStudentName())
                .rollNumber(studentDTO.getRollNumber())
                .phoneNumber(studentDTO.getPhoneNumber())
                .build();
        Student createdStudent = service.createStudent(student);
        return ResponseEntity.ok().body(createdStudent);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<Student> getStudentByStudentId(@PathVariable("studentId") String studentId) {
        return ResponseEntity.ok().body(service.getStudentByStudentId(studentId));
    }
}
