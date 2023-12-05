package com.practice.student.entity;

import com.practice.student.model.Address;
import com.practice.student.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "students")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    private String id;
    private String name;
    private String rollNumber;
    private String phoneNumber;

    @Transient
    private List<Address> addresses = new ArrayList<>();

    @Transient
    private List<Course> courses = new ArrayList<>();
}
