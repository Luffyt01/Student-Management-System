package com.example.student_management.dto;


import lombok.Data;


@Data
public class SubjectDTO {

    private String subjectName;

    private String subjectCode;

    private int credit;

    private int maxMarks;

    private int obtainedMarks;

    private Long semesterId;
  // Reference to the Semester
}
