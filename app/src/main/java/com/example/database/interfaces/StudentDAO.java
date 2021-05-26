package com.example.database.interfaces;

import com.example.models.Course;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.List;

public interface StudentDAO extends BaseDAO<Student, Integer>{
    List<StudentCourse> getFinishedCourses();
    List<Course> getNextCourses();
    List<Course> getCurrentCourses();
}
