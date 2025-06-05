package com.example.student_management.controller;


import com.example.student_management.dto.*;

import com.example.student_management.services.StudentService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import java.util.List;


@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;

    }

    @PostMapping("/create")
    public ResponseEntity<StudentDto> createStudent(@RequestBody CreateStudentDto createStudentDto) {
        return ResponseEntity.ok(studentService.createStudent(createStudentDto));

    }

    @GetMapping("/{rollNo}")
    public ResponseEntity<StudentDto> getStudentByRollNo(@PathVariable Long rollNo) {
        return ResponseEntity.ok(studentService.getStudentByRollNo(rollNo));

    }

    @PostMapping("/{rollNo}/marks")
    public ResponseEntity<StudentDto> addMarksToStudent(@PathVariable Long rollNo,
                                                        @RequestBody InputMarksDto inputMarksDto) {
        return ResponseEntity.ok(studentService.addMarksToStudent(rollNo, inputMarksDto));

    }

    @GetMapping("/{rollNo}/marks")
    public ResponseEntity<List<SemesterMarkDto>> getStudentMarks(@PathVariable Long rollNo) {
        return ResponseEntity.ok(studentService.getStudentMarks(rollNo));

    }

    @GetMapping("/{rollNo}/cgpa")
    public ResponseEntity<CgpaDto> calculateCgpa(@PathVariable Long rollNo) {
        return ResponseEntity.ok(studentService.calculateCgpa(rollNo));

    }
}
