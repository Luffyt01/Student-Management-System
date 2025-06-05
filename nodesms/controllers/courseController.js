const courseService = require('../services/courseService');

exports.createCourse = async (req, res) => {
  try {
    const courseDto = await courseService.createCourse(req.body);
    res.status(201).json(courseDto);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.getAllCourses = async (req, res) => {
  try {
    const courses = await courseService.getAllCourses();
    res.json(courses);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
};

exports.getCourseById = async (req, res) => {
  try {
    const course = await courseService.getCourseById(req.params.id);
    res.json(course);
  } catch (error) {
    res.status(404).json({ message: error.message });
  }
};
