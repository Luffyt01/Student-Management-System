package com.example.student_management.repository;


import com.example.student_management.entity.Semester;
import com.example.student_management.entity.Subject;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findBySemester(Semester semester);
    // You can add custom query methods here if needed
}
