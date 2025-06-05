from rest_framework import serializers
from .models import Course, Student, Semester, Subject

# Serialize or deserialize the subject for all the fields
class SubjectSerializer(serializers.ModelSerializer):
    class Meta:
        model = Subject
        fields = '__all__'

# Has one to many mapping to subjects
# one sem can have many subjects
class SemesterSerializer(serializers.ModelSerializer):
    subjects = SubjectSerializer(many=True, read_only=True)
    class Meta:
        model = Semester
        fields = '__all__'

# Has one to many mapping to sem
# one student can have many sem
class StudentSerializer(serializers.ModelSerializer):
    admission_date = serializers.DateTimeField(read_only=True)  
    date_of_birth = serializers.DateField()                     
    semesters = SemesterSerializer(many=True, read_only=True)

    class Meta:
        model = Student
        fields = '__all__'

#Has one to many mapping with the semester
#has one to manny mapping with the students
class CourseSerializer(serializers.ModelSerializer):
    semesters = SemesterSerializer(many=True, read_only=True)
    students = StudentSerializer(many=True, read_only=True)

    class Meta:
        model = Course
        fields = '__all__'
