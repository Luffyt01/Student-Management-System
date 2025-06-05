const studentService = require('../services/studentService');

exports.createStudent = async (req, res) => {
  try {
    const studentDto = await studentService.createStudent(req.body);
    res.status(201).json(studentDto);
  } catch (error) {
    console.error('Create Student Error:', error);
    res.status(500).json({ message: error.message, errors: error.errors });
  }
};


exports.getStudentByRollNo = async (req, res) => {
  try {
    const student = await studentService.getStudentByRollNo(req.params.rollNo);
    res.json(student);
  } catch (error) {
    res.status(404).json({ message: error.message });
  }
};

exports.addMarksToStudent = async (req, res) => {
  try {
    const studentDto = await studentService.addMarksToStudent(req.params.rollNo, req.body);
    res.json(studentDto);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.getStudentMarks = async (req, res) => {
  try {
    const marks = await studentService.getStudentMarks(req.params.rollNo);
    res.json(marks);
  } catch (error) {
    res.status(404).json({ message: error.message });
  }
};

exports.calculateCgpa = async (req, res) => {
  try {
    const cgpa = await studentService.calculateCgpa(req.params.rollNo);
    res.json(cgpa);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};
