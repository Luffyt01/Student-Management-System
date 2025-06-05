package com.example.student_management.services;


import com.example.student_management.dto.*;

import com.example.student_management.entity.*;

import com.example.student_management.repository.*;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;


import java.util.List;


@Service
@RequiredArgsConstructor
public class StudentService {

    private final SemesterMarksRepository semesterMarksRepository;

    private final StudentRepository studentRepository;

    private final SubjectRepository subjectRepository;

    private final CourseRepository courseRepository;

    private final ModelMapper modelMapper;

    private final SemesterRepository semesterRepository;




    @Transactional
    public StudentDto createStudent(CreateStudentDto createStudentDto) {
        Student student = modelMapper.map(createStudentDto, Student.class);


        if(createStudentDto.getCourseId() != null){
            Course course = courseRepository.findById(createStudentDto.getCourseId())
                    .orElseThrow(() -> new RuntimeException("Course not found"));

            student.setCourse(course);

        }

        Student savedStudent = studentRepository.save(student);

        return modelMapper.map(savedStudent, StudentDto.class);

    }

    public StudentDto getStudentByRollNo(Long rollNo) {
        Student student = studentRepository.findById(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found with rollNo: " + rollNo));

        return modelMapper.map(student, StudentDto.class);

    }

    @Transactional
    public StudentDto addMarksToStudent(Long rollNo, InputMarksDto inputMarksDto) {
        Student student = studentRepository.findById(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found with rollNo: " + rollNo));

        Subject subject = subjectRepository.findById(inputMarksDto.getSubjectId())
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + inputMarksDto.getSubjectId()));

        SemesterMark semesterMark = new SemesterMark();

        semesterMark.setObtainedMarks(inputMarksDto.getObtainedMarks());

        semesterMark.setSubject(subject);

        semesterMark.setStudent(student);

        SemesterMark savedMark = semesterMarksRepository.save(semesterMark);

        studentRepository.save(student);

        return modelMapper.map(student, StudentDto.class);

    }


    public List<SemesterMarkDto> getStudentMarks(Long rollNo) {
        List<SemesterMark> semesterMarks = semesterMarksRepository.findByStudentRollNo(rollNo)
                .orElseThrow(() -> new RuntimeException("Marks not found for student with rollNo " + rollNo));


        return semesterMarks.stream()
                .map(mark -> modelMapper.map(mark, SemesterMarkDto.class))
                .toList();

    }

    public CgpaDto calculateCgpa(Long rollNo) {
        Student student = studentRepository.findById(rollNo)
                .orElseThrow(() -> new RuntimeException("Student not found with id: "+rollNo));


        List<Semester> semesterList = student.getCourse().getSemesters();

        for (Semester semester : semesterList) {
            if (semester.getObtainedMarks() == 0.0){
                List<Subject> subjects = subjectRepository.findBySemester(semester);
                double totalMarks = 0;
                double obtMarks = 0;
                for (Subject subject: subjects){
                    totalMarks+=subject.getMaxMarks();
                    obtMarks+=subject.getObtainedMarks();
                }
                semester.setObtainedMarks((obtMarks/totalMarks)*100.0);
                semesterRepository.save(semester);
            }
        }

        List<Semester> semlist = student.getCourse().getSemesters();
        double totalCgpa = 0.0;
        for (Semester semester: semlist){
            totalCgpa += ((int) semester.getObtainedMarks() *100.0)/1000.0;
        }

        CgpaDto cgpaDto = new CgpaDto();
        cgpaDto.setRollNo(rollNo);
        cgpaDto.setCgpa(totalCgpa/semlist.size());

        return cgpaDto;

    }
}
