package com.example.student_management.dto;


import com.example.student_management.entity.Course;
import com.example.student_management.entity.Student;

import com.example.student_management.entity.Subject;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;


@Data
public class SemesterDto {
    private Long id;

    private int totalMarks;
    private int obtainedMarks;

    private int semester;
}
