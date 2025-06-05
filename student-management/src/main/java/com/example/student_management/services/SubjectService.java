package com.example.student_management.services;


import com.example.student_management.dto.SubjectDTO;

import com.example.student_management.entity.Semester;

import com.example.student_management.entity.Subject;

import com.example.student_management.repository.SemesterRepository;

import com.example.student_management.repository.SubjectRepository;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

import java.util.Optional;

import java.util.stream.Collectors;



@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;


    @Autowired
    private SemesterRepository semesterRepository;


    @Autowired
    private ModelMapper modelMapper;


    public SubjectDTO createSubject(SubjectDTO subjectDTO) {
        Subject subject = new Subject();
        subject.setSubjectCode(subjectDTO.getSubjectCode());
        subject.setSubjectName(subjectDTO.getSubjectName());
        subject.setCredit(subjectDTO.getCredit());
        subject.setMaxMarks(subjectDTO.getMaxMarks());
        subject.setObtainedMarks(subjectDTO.getObtainedMarks());


        Semester semester = semesterRepository.findById(subjectDTO.getSemesterId())
                .orElse(null);
        subject.setSemester(semester);

        Subject saved = subjectRepository.save(subject);

        return modelMapper.map(saved, SubjectDTO.class);

    }

    public SubjectDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));


        return modelMapper.map(subject, SubjectDTO.class);

    }

    public List<SubjectDTO> getAllSubjects() {
        return subjectRepository.findAll().stream()
                .map(subject -> {
                    return modelMapper.map(subject, SubjectDTO.class);

                })
                .collect(Collectors.toList());

    }

    public SubjectDTO updateSubject(Long id, SubjectDTO subjectDTO) {
        Subject existing = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found"));


        modelMapper.map(subjectDTO, existing);


        if (subjectDTO.getSemesterId() != null) {
            Semester semester = semesterRepository.findById(subjectDTO.getSemesterId())
                    .orElseThrow(() -> new RuntimeException("Semester not found"));

            existing.setSemester(semester);

        }

        Subject updated = subjectRepository.save(existing);

        return modelMapper.map(updated, SubjectDTO.class);

    }
}
