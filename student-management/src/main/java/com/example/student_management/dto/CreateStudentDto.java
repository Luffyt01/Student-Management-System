package com.example.student_management.dto;


import lombok.Data;


import java.time.LocalDate;


@Data
public class CreateStudentDto {
    private String admissionNo;

    private String name;

    private String fatherName;

    private String email;

    private Integer age;

    private String phone;

    private LocalDate dateOfBirth;

    private Long courseId;
 // link to Course by Id
}
