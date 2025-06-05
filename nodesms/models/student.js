const { DataTypes } = require('sequelize');
const { sequelize } = require('../config/database');

const Student = sequelize.define('Student', {
  rollNo: {
    type: DataTypes.STRING,
    allowNull: true, // optional, or false if you want required
    defaultValue: DataTypes.UUIDV4,
  },
  admissionNo: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true,
  },
  studentName: {  // This matches your DB column
    type: DataTypes.STRING,
    allowNull: false,
  },
  email: {
    type: DataTypes.STRING,
    allowNull: false,
    unique: true,
  },
  age: {
    type: DataTypes.INTEGER,
    allowNull: false,
  },
  phone: {
    type: DataTypes.STRING,
    allowNull: false,
  },
  courseId: {
    type: DataTypes.INTEGER,
    allowNull: false,
  }
});

module.exports = Student;
