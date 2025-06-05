package com.example.student_management.controller;


import com.example.student_management.dto.CourseDto;

import com.example.student_management.dto.CreateCourseDto;

import com.example.student_management.services.CourseService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;

    }

    @PostMapping("/create")
    public ResponseEntity<CourseDto> createCourse(@RequestBody CreateCourseDto createCourseDto) {
        return ResponseEntity.ok(courseService.createCourse(createCourseDto));

    }

    @GetMapping
    public ResponseEntity<List<CourseDto>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());

    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));

    }
}
