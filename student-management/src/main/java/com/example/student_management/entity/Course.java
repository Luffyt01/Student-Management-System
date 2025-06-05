package com.example.student_management.entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;


import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private Integer courseDuration;
    private Integer totalSemesters;
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Semester> semesters;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Student> students;

}
