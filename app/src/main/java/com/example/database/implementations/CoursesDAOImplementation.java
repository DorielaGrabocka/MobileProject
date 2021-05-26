package com.example.database.implementations;

import android.content.Context;
import android.database.Cursor;

import com.example.database.daofactory.DAOFactory;
import com.example.database.daofactory.DatabaseHelper;
import com.example.database.interfaces.CourseDAO;
import com.example.models.Course;
import com.example.models.Student;

import java.util.List;

public class CoursesDAOImplementation implements CourseDAO {
    private DatabaseHelper database;
    private final static String BASIC_QUERY = "SELECT id, title, instructor, credits, day, time, " +
            "class, department, faculty, eligibility, midterm_weight, " +
            "final_weight, project_weight, other_components_weight" +
            " FROM course ";

    public CoursesDAOImplementation(Context context) {
        database = DAOFactory.getDatabaseAccessObject(context);
    }

    @Override
    public List<Student> getAllStudents(Course c) {
        return null;
    }

    @Override
    public void insert(Course course) {

    }

    @Override
    public void update(Course course) {

    }

    @Override
    public void delete(Course course) {

    }

    @Override
    public Course find(Integer key) {
        String query = BASIC_QUERY+" WHERE id=?";
        Cursor cursor = database.getReadableDatabase().rawQuery(query, new String[]{key.toString()});
        if(cursor.moveToFirst() && cursor.getCount()==1){
            return getCourseFromCursor(cursor);
        }
        return null;
    }

    @Override
    public List<Course> getAll() {
        return null;
    }

    public void closeConnection(){
        database.close();
    }

    /**Method to construct a  Course form a cursor row
     * @param c is the cursor
     * @return a course
     * */
    public static Course getCourseFromCursor(Cursor c){
        Course course = new Course();
        course.setId(c.getInt(0));
        course.setTitle(c.getString(1));
        course.setInstructor(c.getString(2));
        course.setCredits(c.getInt(3));
        course.setDay(c.getString(4));
        course.setTime(c.getString(5));
        course.setVenue(c.getString(6));
        course.setDepartment(c.getString(7));
        course.setFaculty(c.getString(8));
        course.setEligibility(c.getString(9));
        course.setMidterm_weight(c.getDouble(10));
        course.setFinal_weight(c.getDouble(11));
        course.setProject_weight(c.getDouble(12));
        course.setOther_components_weight(c.getDouble(13));

        return course;
    }

    public static String getBASIC_QUERYCourses() {
        return BASIC_QUERY;
    }
}
