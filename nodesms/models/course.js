const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Course = sequelize.define('Course', {
  courseCode: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true,
  },
  courseName: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  totalSemesters: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
});

module.exports = Course;
