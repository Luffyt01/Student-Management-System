const { Subject, Semester } = require('../models');

exports.createSubject = async (subjectDto) => {
  const subject = await Subject.create({
    subjectCode: subjectDto.subjectCode,
    subjectName: subjectDto.subjectName,
    credit: subjectDto.credit,
    maxMarks: subjectDto.maxMarks,
    obtainedMarks: subjectDto.obtainedMarks,
    semesterId: subjectDto.semesterId,
  });
  return subject;
};

exports.getSubjectById = async (id) => {
  const subject = await Subject.findByPk(id, { include: Semester });
  if (!subject) throw new Error('Subject not found');
  return subject;
};

exports.getAll
