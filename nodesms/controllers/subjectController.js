const subjectService = require('../services/subjectService');

exports.createSubject = async (req, res) => {
  try {
    const subjectDto = await subjectService.createSubject(req.body);
    res.status(201).json(subjectDto);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.getSubjectById = async (req, res) => {
  try {
    const subject = await subjectService.getSubjectById(req.params.id);
    res.json(subject);
  } catch (error) {
    res.status(404).json({ message: error.message });
  }
};

exports.getAllSubjects = async (req, res) => {
  try {
    const subjects = await subjectService.getAllSubjects();
    res.json(subjects);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.updateSubject = async (req, res) => {
  try {
    const updatedSubject = await subjectService.updateSubject(req.params.id, req.body);
    res.json(updatedSubject);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};
