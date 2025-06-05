const express = require('express');
const router = express.Router();
const studentController = require('../controllers/studentController');

router.post('/create', studentController.createStudent);
router.get('/:rollNo', studentController.getStudentByRollNo);
router.post('/:rollNo/marks', studentController.addMarksToStudent);
router.get('/:rollNo/marks', studentController.getStudentMarks);
router.get('/:rollNo/cgpa', studentController.calculateCgpa);

module.exports = router;
