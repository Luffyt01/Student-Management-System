package com.example.student_management.dto;


import lombok.Data;


import java.util.List;


@Data
public class CourseDto {
    private Long courseId;

    private String courseName;

    private Integer courseDuration;

    private Integer totalSemesters;

}
