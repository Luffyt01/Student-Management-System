package com.example.student_management.dto;


import lombok.Data;


import java.time.LocalDate;

import java.util.List;


@Data
public class StudentDto {
    private Long rollNo;

    private String admissionNo;

    private String name;

    private String fatherName;

    private String email;

    private Integer age;

    private String phone;

    private LocalDate dateOfBirth;

    private LocalDate admissionDate;

}
