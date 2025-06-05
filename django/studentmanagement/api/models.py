from django.db import models
from django.utils.timezone import now

# Represent's course entity 
# Returns the name when displaying the object
class Course(models.Model):
    course_name = models.CharField(max_length=255)
    course_duration = models.IntegerField()
    total_semesters = models.IntegerField()

    def __str__(self):
        return self.course_name

# Represent's student entity
# Many to One relation with course
class Student(models.Model):
    name = models.CharField(max_length=255)
    roll_no = models.IntegerField()
    course = models.ForeignKey(Course, on_delete=models.CASCADE)
    admission_no = models.CharField(max_length=100)
    father_name = models.CharField(max_length=255)
    email = models.EmailField()
    age = models.IntegerField()
    phone = models.CharField(max_length=20)
    date_of_birth = models.DateField()            
    admission_date = models.DateTimeField(auto_now_add=True)  


# Represents Semester Entity
# Has Many to One relation with student and course
class Semester(models.Model):
    semester = models.IntegerField()
    obtained_marks = models.FloatField(default=0.0)
    course = models.ForeignKey(Course, on_delete=models.CASCADE, related_name='semesters')
    student = models.ForeignKey(Student, on_delete=models.CASCADE, related_name='semesters', null=True, blank=True)

    def __str__(self):
        return f"Semester {self.semester} - {self.course.course_name}"


# Represent the Subject entity
# Has Many to one mapping to the semester
class Subject(models.Model):
    subject_name = models.CharField(max_length=255)
    subject_code = models.CharField(max_length=50)
    credit = models.IntegerField()
    max_marks = models.IntegerField()
    obtained_marks = models.IntegerField(default=0)
    semester = models.ForeignKey(Semester, on_delete=models.CASCADE, related_name='subjects')

    def __str__(self):
        return self.subject_name
