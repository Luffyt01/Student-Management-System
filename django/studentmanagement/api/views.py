from django.shortcuts import render
from .models import Course, Semester
from .serializers import CourseSerializer
from rest_framework import viewsets, status
from rest_framework.decorators import action
from rest_framework.response import Response
from .models import Student, Subject
from .serializers import StudentSerializer
from .serializers import SubjectSerializer
from rest_framework import generics


class CourseViewSet(viewsets.ModelViewSet):

    queryset = Course.objects.all()
    serializer_class = CourseSerializer

    def perform_create(self, serializer):
        course = serializer.save()
        # Create semesters as per total_semesters
        for i in range(1, course.total_semesters + 1):
            Semester.objects.create(semester=i, course=course)


class StudentViewSet(viewsets.ModelViewSet):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer

    @action(detail=True, methods=['post'])
    def add_marks(self, request, pk=None):
        student = self.get_object()
        subject_id = request.data.get('subject_id')
        obtained_marks = request.data.get('obtained_marks')

        try:
            subject = Subject.objects.get(id=subject_id)
        except Subject.DoesNotExist:
            return Response({'error': 'Subject not found'}, status=status.HTTP_404_NOT_FOUND)

        # Update obtained marks for subject
        subject.obtained_marks = obtained_marks
        subject.save()

        # Return updated student
        serializer = self.get_serializer(student)
        return Response(serializer.data)

    @action(detail=True, methods=['get'])
    def calculate_cgpa(self, request, pk=None):
        student = self.get_object()
        semesters = student.course.semesters.all()
        total_cgpa = 0
        for semester in semesters:
            subjects = semester.subjects.all()
            total_marks = sum(subject.max_marks for subject in subjects)
            obtained = sum(subject.obtained_marks for subject in subjects)
            if total_marks > 0:
                semester.obtained_marks = (obtained / total_marks) * 100
                semester.save()
                total_cgpa += semester.obtained_marks / 10  
        cgpa = total_cgpa / semesters.count() if semesters.exists() else 0
        return Response({'roll_no': student.id, 'cgpa': cgpa})

class SubjectViewSet(viewsets.ModelViewSet):
    queryset = Subject.objects.all()
    serializer_class = SubjectSerializer



class StudentListView(generics.ListAPIView):
    queryset = Student.objects.all()
    serializer_class = StudentSerializer
