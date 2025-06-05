const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Student = require('./student');
const Subject = require('./subject');

const SemesterMark = sequelize.define('SemesterMark', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  obtainedMarks: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  studentId: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  subjectId: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
});

// Associations
SemesterMark.belongsTo(Student, { foreignKey: 'studentId' });
Student.hasMany(SemesterMark, { foreignKey: 'studentId' });

SemesterMark.belongsTo(Subject, { foreignKey: 'subjectId' });
Subject.hasMany(SemesterMark, { foreignKey: 'subjectId' });

module.exports = SemesterMark;
