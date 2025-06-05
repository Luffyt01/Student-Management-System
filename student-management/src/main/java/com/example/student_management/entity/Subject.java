package com.example.student_management.entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long subjectId;

    private String subjectName;

    private String subjectCode;

    private int credit;

    private int maxMarks;

    private int obtainedMarks;


    @ManyToOne
    @JoinColumn(name = "semester_id")
    private Semester semester;

}
