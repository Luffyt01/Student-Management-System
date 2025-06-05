const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');
const Course = require('./course');

const Semester = sequelize.define('Semester', {
  id: {
    type: DataTypes.INTEGER,
    autoIncrement: true,
    primaryKey: true,
  },
  semester: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  courseId: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
});

// Associations
Semester.belongsTo(Course, { foreignKey: 'courseId' });
Course.hasMany(Semester, { foreignKey: 'courseId' });

module.exports = Semester;
