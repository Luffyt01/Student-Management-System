package com.example.student_management.services;


import com.example.student_management.dto.CourseDto;

import com.example.student_management.dto.CreateCourseDto;

import com.example.student_management.entity.Course;

import com.example.student_management.entity.Semester;

import com.example.student_management.repository.CourseRepository;

import com.example.student_management.repository.SemesterRepository;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;


import java.util.ArrayList;

import java.util.List;

import java.util.stream.Collectors;


@Service
public class CourseService {

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    private final SemesterRepository semesterRepository;


    public CourseService(CourseRepository courseRepository, ModelMapper modelMapper, SemesterRepository semesterRepository) {
        this.courseRepository = courseRepository;

        this.modelMapper = modelMapper;

        this.semesterRepository = semesterRepository;

    }

    public CourseDto createCourse(CreateCourseDto createCourseDto) {
        Course course = modelMapper.map(createCourseDto, Course.class);

        Course savedCourse = courseRepository.save(course);


        List< Semester> semesterList = new ArrayList<>();


        for (int i=1;
 i<=createCourseDto.getTotalSemesters();
 i++){
            Semester semester = new Semester();

            semester.setSemester(i);

            semester.setCourse(savedCourse);

            semesterList.add(semesterRepository.save(semester));

        }


        savedCourse.setSemesters(semesterList);

        return modelMapper.map(savedCourse, CourseDto.class);

    }

    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll().stream()
                .map(course -> modelMapper.map(course, CourseDto.class))
                .collect(Collectors.toList());

    }

    public CourseDto getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id " + id));

        return modelMapper.map(course, CourseDto.class);

    }
}
