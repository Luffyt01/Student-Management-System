from django.urls import path
from .views import (
    CourseViewSet,
    StudentViewSet,
    SubjectViewSet,
)
from .views import StudentListView


course_create = CourseViewSet.as_view({'post': 'create'})
course_list = CourseViewSet.as_view({'get': 'list'})
course_detail = CourseViewSet.as_view({
    'get': 'retrieve',
    'put': 'update',
    'patch': 'partial_update',
    'delete': 'destroy'
})

student_create = StudentViewSet.as_view({'post': 'create'})
student_detail = StudentViewSet.as_view({
    'get': 'retrieve',
    'put': 'update',
    'patch': 'partial_update',
    'delete': 'destroy'
})

student_marks = StudentViewSet.as_view({
    'post': 'add_marks',
    'get': 'get_student_marks'  
})
student_calculate_cgpa = StudentViewSet.as_view({'get': 'calculate_cgpa'})

subject_create = SubjectViewSet.as_view({'post': 'create'})
subject_list = SubjectViewSet.as_view({'get': 'list'})
subject_detail = SubjectViewSet.as_view({
    'get': 'retrieve',
    'put': 'update',
    'patch': 'partial_update',
    'delete': 'destroy'
})

urlpatterns = [
    # Courses
    path('students/', StudentListView.as_view(), name='student-list'),
    path('courses/create/', course_create, name='course-create'),
    path('courses/', course_list, name='course-list'),
    path('courses/<int:pk>/', course_detail, name='course-detail'),

    # Students
    path('students/create/', student_create, name='student-create'),
    path('students/<int:pk>/', student_detail, name='student-detail'),
    path('students/<int:pk>/marks/', student_marks, name='student-marks'),
    path('students/<int:pk>/cgpa/', student_calculate_cgpa, name='student-calculate-cgpa'),

    # Subjects
    path('subjects/create/', subject_create, name='subject-create'),
    path('subjects/getAll/', subject_list, name='subject-list'),
    path('subjects/<int:pk>/', subject_detail, name='subject-detail'),
]
