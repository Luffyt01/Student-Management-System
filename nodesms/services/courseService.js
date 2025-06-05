const { Course, Semester } = require('../models');

exports.createCourse = async (createCourseDto) => {
  const course = await Course.create(createCourseDto);

  const semesters = [];
  for (let i = 1; i <= createCourseDto.totalSemesters; i++) {
    const semester = await Semester.create({
      semester: i,
      courseId: course.id,
    });
    semesters.push(semester);
  }

  course.semesters = semesters;

  return course;
};

exports.getAllCourses = async () => {
  return await Course.findAll({ include: Semester });
};

exports.getCourseById = async (id) => {
  const course = await Course.findByPk(id, { include: Semester });
  if (!course) throw new Error('Course not found');
  return course;
};
