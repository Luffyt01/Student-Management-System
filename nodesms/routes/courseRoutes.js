const express = require('express');
const router = express.Router();

const courseController = require('../controllers/courseController');

router.post('/create', courseController.createCourse);
router.get('/', courseController.getAllCourses);
router.get('/:id', courseController.getCourseById);

module.exports = router;
