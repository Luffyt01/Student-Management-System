package com.example.student_management.entity;


import jakarta.persistence.*;

import lombok.AllArgsConstructor;

import lombok.Getter;

import lombok.NoArgsConstructor;

import lombok.Setter;

import org.hibernate.annotations.CreationTimestamp;

import org.springframework.data.jpa.repository.Modifying;


import java.time.LocalDate;

import java.util.List;



@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rollNo;


    private String admissionNo;

    private String name;

    private String fatherName;

    private String email;

    private Integer age;

    private String phone;

    private LocalDate dateOfBirth;


    @CreationTimestamp
    @Column(updatable = false)
    private LocalDate admissionDate;


    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;



}
