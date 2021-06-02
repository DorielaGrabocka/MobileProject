package com.example.database.implementations;

import android.content.Context;
import android.database.Cursor;

import com.example.database.daofactory.DAOFactory;
import com.example.database.daofactory.DatabaseHelper;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class StudentCourseDAOImplementation {
    private final String BASIC_QUERY = "SELECT studentid, courseid, grade, status " +
            "FROM studentcourse ";
    private DatabaseHelper database;
    private CoursesDAOImplementation coursesDAOImplementation;

    public StudentCourseDAOImplementation(Context context) {
        this.database = DAOFactory.getDatabaseAccessObject(context);
        coursesDAOImplementation = new CoursesDAOImplementation(context);
    }

    /**Method to get student courses of a student
     * @param student the student whose courses we need
     * @return the list of StudentCourse objects
     * */
    public List<StudentCourse> getFinishedCoursesOfStudent(Student student){
        String query = BASIC_QUERY + " WHERE studentid=? AND status=?";
        Cursor cursor = database.getReadableDatabase().rawQuery(query,
                new String[]{String.valueOf(student.getId()), String.valueOf(1)});
        List<StudentCourse> listOfStudentCourses = new ArrayList<>();
        while(cursor.moveToNext()){
            listOfStudentCourses.add(createStudentCourseFormCursor(cursor));
        }
        cursor.close();
        return listOfStudentCourses;
    }

    /**Method to create the StudentCourse from a line in a cursor
     * @param c is the cursor
     * @return is the StudentCourse
     * */
    private StudentCourse createStudentCourseFormCursor(Cursor c){
        StudentCourse sc = new StudentCourse();
        sc.setStudentId(c.getInt(0));
        sc.setCourseId(c.getInt(1));
        sc.setGrade(c.getString(2));
        sc.setStatus(c.getInt(3));
        sc.setCourse(coursesDAOImplementation.find(c.getInt(1)));
        return  sc;
    }

    public void close(){
        database.close();
    }
}
