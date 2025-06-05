package com.example.student_management.dto;


import lombok.Data;


@Data
public class CreateCourseDto {
    private String courseName;

    private Integer courseDuration;

    private Integer totalSemesters;

}

