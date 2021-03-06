package com.example.database.implementations;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.database.daofactory.DAOFactory;
import com.example.database.daofactory.DatabaseHelper;
import com.example.database.interfaces.CourseDAO;
import com.example.models.Comment;
import com.example.models.Course;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.ArrayList;
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
        cursor.close();
        return null;
    }

    /**Method to get the list of books based on title
     * @param title - is the keyword of title
     * @return the list of books
     * */
    public List<Course> findCourseByTitle(String title){
        String query = BASIC_QUERY+" WHERE lower(title) LIKE '%"+title+"%'";
        return getCourses(query);
    }

    /**Method to get the list of books based on title
     * @param instructor - is the keyword of title
     * @return the list of books
     * */
    public List<Course> findCourseByInstructor(String instructor){
        String query = BASIC_QUERY+" WHERE lower(instructor) LIKE '%"+instructor+"%'";
        return getCourses(query);
    }

    /**Utility method to get courses from a query.
     * @param query - is the query that will retrive the infromation
     * @return  is the list of courses
     * */
    private List<Course> getCourses(String query){
        List<Course> courses = new ArrayList<>();
        Cursor c = database.getReadableDatabase().rawQuery(query, null);
        while(c.moveToNext()){
            courses.add(getCourseFromCursor(c));
        }
        return courses;
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

    /**Method to get the basic query for the course table
     * @return a string*/
    public static String getBASIC_QUERYCourses() {
        return BASIC_QUERY;
    }

    /**Method used to add a comment for a course.
     * @param comment is the comment to  be added to the database
     * */
    public void addCommentAboutCourse(Comment comment){
        SQLiteDatabase db = database.getWritableDatabase();
        String query = "INSERT INTO 'comment' ('studentid', 'courseid', 'text') VALUES (?,?,?)";
        db.execSQL(query, new String[]{String.valueOf(comment.getStudentId()),
                String.valueOf(comment.getCourseId()),
                comment.getComment()});
        db.close();
    }

    /**Method used to delete a comment from a course
     * @param comment  is the comment to be deleted
     * */
    public void deleteComment(Comment comment){
        SQLiteDatabase db = database.getWritableDatabase();
        String query = "DELETE FROM 'comment' WHERE studentID=? AND courseID=? AND text=?;";
        Cursor c = db.rawQuery(query, new String[]{String.valueOf(comment.getStudentId()),
                String.valueOf(comment.getCourseId()),
                comment.getComment()});
        Log.println(Log.INFO, "Database",String.valueOf(c.moveToFirst()));
        db.close();
    }

    /**Method to  get all all comments of a course
     * @param courseId - is the is of the course whose comments we ned to  display
     * @return a list of comments*/
    public List<Comment> getAllComments(int courseId){
        List<Comment> listOfComments = new ArrayList<>();
        String query = "SELECT studentID, courseID, text FROM comment WHERE courseID=?";
        Cursor c = database.getReadableDatabase().rawQuery(query, new String[]{String.valueOf(courseId)});
        while(c.moveToNext()){
            listOfComments.add(getCommentFromCursor(c));
        }
        c.close();
        return listOfComments;
    }

    /**Method to create a Comment object from a cursor that is supplied.
     * @param c is the cursor
     * @return a comment object.*/
    private Comment getCommentFromCursor(Cursor c) {
        Comment comment = new Comment();
        comment.setStudentId(c.getInt(0));
        comment.setCourseId(c.getInt(1));
        comment.setComment(c.getString(2));
        return comment;
    }
}
