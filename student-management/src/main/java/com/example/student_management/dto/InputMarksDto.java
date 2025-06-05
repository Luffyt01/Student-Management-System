package com.example.student_management.dto;


import lombok.Data;


@Data
public class InputMarksDto {
    private Long subjectId;

    private int obtainedMarks;

    private int maxMarks;

}
