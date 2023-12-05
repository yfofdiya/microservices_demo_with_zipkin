package com.practice.student.dto;

import com.practice.student.annotation.PhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String studentName;

    @NotBlank(message = "Roll number is required")
    private String rollNumber;

    @NotBlank(message = "Phone number is required")
    @PhoneNumber
    private String phoneNumber;
}
