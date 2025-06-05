package com.example.student_management.repository;


import com.example.student_management.entity.SemesterMark;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;


import java.util.List;

import java.util.Optional;


public interface SemesterMarksRepository extends JpaRepository<SemesterMark, Long> {

    @Query("SELECT sm FROM SemesterMark sm WHERE sm.student.rollNo = :rollNo")
    Optional<List<SemesterMark>> findByStudentRollNo(Long rollNo);

}
