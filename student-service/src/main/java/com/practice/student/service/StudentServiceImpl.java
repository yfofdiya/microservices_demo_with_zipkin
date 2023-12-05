package com.practice.student.service;

import com.practice.student.client.AddressClient;
import com.practice.student.client.CourseClient;
import com.practice.student.entity.Student;
import com.practice.student.model.Address;
import com.practice.student.model.Course;
import com.practice.student.repository.StudentRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository repository;

    @Autowired
    private CourseClient courseClient;

    @Autowired
    private AddressClient addressClient;

    @Override
    public Student createStudent(Student student) {
        String studentId = UUID.randomUUID().toString();
        student.setId(studentId);
        Student createdStudent = repository.save(student);
        log.info("Student is created successfully");
        return createdStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> studentList = getStudentsFromDatabase();
        return studentList;
    }

    private List<Student> getStudentsFromDatabase() {
        log.info("Fetched All the Students");
        List<Student> students = repository.findAll();
        return students;
    }

    @Override
    public Student getStudentByStudentId(String studentId) {
        Student student = getStudentByStudentIdFromDatabase(studentId);
        return student;
    }

    private Student getStudentByStudentIdFromDatabase(String studentId) {
        Student student = repository.findById(studentId).get();
        log.info("Student with provided id {} is fetched successfully", studentId);
        return student;
    }

    @Override
    @Retry(name = "courseRetry", fallbackMethod = "getCoursesByStudentId")
    public List<Course> getCoursesByStudentId(Student student) {
        List<Course> courses = courseClient.getCoursesByStudentId(student.getId());
        log.info("Courses are fetched for student id {}", student.getId());
        return courses;
    }

    public List<Course> getCoursesByStudentId(Student student, Exception e) {
        List<Course> courses = createDummyCourse(student);
        return courses;
    }

    private List<Course> createDummyCourse(Student student) {
        log.info("Adding Dummy Course as Course service is down");
        return List.of(Course
                .builder()
                .id(UUID.randomUUID().toString())
                .name("Java 8")
                .studentId(student.getId())
                .build());
    }

    @Override
    @CircuitBreaker(name = "addressCircuitBreaker", fallbackMethod = "getAddressesByStudentId")
//    @Retry(name = "addressRetry", fallbackMethod = "getAddressesByStudentId")
    public List<Address> getAddressesByStudentId(Student student) {
        List<Address> addresses = addressClient.getAddressesByStudentId(student.getId());
        log.info("Addresses are fetched for student id {}", student.getId());
        return addresses;
    }

    public List<Address> getAddressesByStudentId(Student student, Exception e) {
        List<Address> addresses = createDummyAddress(student);
        return addresses;
    }

    private List<Address> createDummyAddress(Student student) {
        log.info("Adding Dummy Address as Address service is down");
        return List.of(Address
                .builder()
                .id(UUID.randomUUID().toString())
                .city("Dummy")
                .state("Dummy")
                .country("dummy")
                .studentId(student.getId())
                .build());
    }
}
