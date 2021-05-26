package com.example.database.interfaces;

import com.example.models.Course;
import com.example.models.Student;

import java.util.List;

public interface CourseDAO extends BaseDAO<Course, Integer>{
    List<Student> getAllStudents(Course c);
}
