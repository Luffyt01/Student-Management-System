const { Student, Subject, SemesterMark, Course, Semester } = require('../models');

exports.createStudent = async (createStudentDto) => {
  // create student with associated courseId
  const student = await Student.create(createStudentDto);
  return student;
};

exports.getStudentByRollNo = async (rollNo) => {
  const student = await Student.findByPk(rollNo, { include: [Course] });
  if (!student) throw new Error('Student not found');
  return student;
};

exports.addMarksToStudent = async (rollNo, inputMarksDto) => {
  const student = await Student.findByPk(rollNo);
  if (!student) throw new Error('Student not found');

  const subject = await Subject.findByPk(inputMarksDto.subjectId);
  if (!subject) throw new Error('Subject not found');

  // Create semester mark
  const mark = await SemesterMark.create({
    obtainedMarks: inputMarksDto.obtainedMarks,
    studentId: rollNo,
    subjectId: inputMarksDto.subjectId,
  });

  return student;
};

exports.getStudentMarks = async (rollNo) => {
  const marks = await SemesterMark.findAll({ where: { studentId: rollNo }, include: [Subject] });
  if (!marks) throw new Error('Marks not found');
  return marks;
};

exports.calculateCgpa = async (rollNo) => {
  const student = await Student.findByPk(rollNo, {
    include: [
      {
        model: Course,
        include: [Semester],
      },
    ],
  });
  if (!student) throw new Error('Student not found');


  let totalCgpa = 0;
  const semesters = student.Course.Semesters;

  semesters.forEach((semester) => {
    totalCgpa += semester.obtainedMarks || 0;
  });

  const cgpa = totalCgpa / semesters.length;

  return { rollNo, cgpa };
};
