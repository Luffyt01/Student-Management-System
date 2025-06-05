const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Semester = require('./semester');

const Subject = sequelize.define('Subject', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  subjectCode: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true,
  },
  subjectName: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  credit: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  maxMarks: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  semesterId: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
});

// Associations
Subject.belongsTo(Semester, { foreignKey: 'semesterId' });
Semester.hasMany(Subject, { foreignKey: 'semesterId' });

module.exports = Subject;
