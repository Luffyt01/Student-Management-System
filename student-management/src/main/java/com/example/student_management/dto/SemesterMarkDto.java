package com.example.student_management.dto;


import lombok.Data;


@Data
public class SemesterMarkDto {
    private Long id;

    private int obtainedMarks;

    private int maxMarks;

    private SubjectDTO subject;

}
