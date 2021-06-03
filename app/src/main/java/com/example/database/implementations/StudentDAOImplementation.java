package com.example.database.implementations;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.activities.MainActivity;
import com.example.database.daofactory.DAOFactory;
import com.example.database.daofactory.DatabaseHelper;
import com.example.database.interfaces.StudentDAO;
import com.example.models.Course;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImplementation implements StudentDAO {

    private DatabaseHelper database;
    private Student theUser;
    private Context context;


    public StudentDAOImplementation(Context context) {
        this.database = DAOFactory.getDatabaseAccessObject(context);
        this.context = context;
    }

    @Override
    public List<StudentCourse> getFinishedCourses() {
        StudentCourseDAOImplementation scDAO = new StudentCourseDAOImplementation(context);
        List<StudentCourse> listOfCourses = scDAO.getFinishedCoursesOfStudent(theUser);
        return listOfCourses;
    }

    @Override
    public List<Course> getNextCourses() {
        String query = CoursesDAOImplementation.getBASIC_QUERYCourses()
                +" where id not in " +
                "(select sc.CourseID from student s join studentcourse sc on s.id=sc.StudentID " +
                "where s.id=? and (status='0' or status='1')) " +
                "and (Eligibility='All' OR Department=?);";
        List<Course> listOfEligibleCourses = new ArrayList<>();
        Cursor cursor = database.getReadableDatabase()
                .rawQuery(query, new String[]{String.valueOf(theUser.getId()),theUser.getMajor()});
        while(cursor.moveToNext()){
            listOfEligibleCourses.add(CoursesDAOImplementation.getCourseFromCursor(cursor));
        }
        cursor.close();
        return listOfEligibleCourses;
    }

    @Override
    public List<Course> getCurrentCourses() {
        String query = CoursesDAOImplementation.getBASIC_QUERYCourses()+" c JOIN studentcourse sc " +
                "on c.id=sc.courseid WHERE sc.studentid=? AND sc.status='0';";
        //having a status of 0 means that the student is undertaking the course
        //status 1-> course is done
        //status 2-> course is bookmarked, can be done or can be undergoing, or neither
        Cursor cursor = database.getReadableDatabase().rawQuery(query,
                new String[]{String.valueOf(theUser.getId())});
        List<Course> listOfCourses = new ArrayList<>();
        //populate the list
        while (cursor.moveToNext()){
            listOfCourses.add(CoursesDAOImplementation.getCourseFromCursor(cursor));
        }
        cursor.close();
        return listOfCourses;
    }

    @Override
    public void insert(Student student) {

    }

    @Override
    public void update(Student student) {
        SQLiteDatabase db = database.getWritableDatabase();
        String query = "UPDATE student SET age=?, email=? WHERE id=?;";
        String[] arguments = {String.valueOf(student.getAge()),
                String.valueOf(student.getEmail()),
                String.valueOf(student.getId())};
        db.execSQL(query, arguments);
        db.close();
    }

    @Override
    public void delete(Student student) {

    }

    @Override
    public Student find(Integer key) {

        String query = "SELECT id, name, surname, age, major, minor, password, email" +
                " FROM student WHERE id=?";
        Cursor result = database.getReadableDatabase().rawQuery(query, new String[]{key.toString()});
        if(result.moveToFirst() && result.getCount()==1){//student with the above ID is found
            Student student=getStudentFormCursor(result);
            return  student;
        }
        result.close();
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }

    @Override
    public void closeConnection() {
        database.close();
    }

    /**Method to authenticate the user trying to access the app
     * @param code - the unique code that the user has
     * @param password- the password of the user
     * @return the student if exists or null*/
    @RequiresApi(api = Build.VERSION_CODES.N)
    public Student authenticateUser(String code, String password){
        Student potentialUser = find(Integer.parseInt(code));
        if(potentialUser!=null && potentialUser.getPassword().equals(password)){
            this.theUser = potentialUser;
            theUser.setListOfCurrentCourses(getCurrentCourses());
            theUser.setListOfFinishedCourses(getFinishedCourses());
            theUser.setListOfNextPossibleCourses(getNextCourses());
            theUser.setCredits();
            theUser.setGpa();
            return theUser;
        }
        return null;
    }


    /**Method to construct a student from acursor row.
     * @param c- is the cursor
     * @return a student
     * */
    private Student getStudentFormCursor(Cursor c){
        Student student = new Student();
        student.setId(c.getInt(0));
        student.setName(c.getString(1));
        student.setSurname(c.getString(2));
        student.setAge(c.getInt(3));
        student.setMajor(c.getString(4));
        student.setMinor(c.getString(5));
        student.setPassword(c.getString(6));
        student.setEmail(c.getString(7));
        return student;
    }

    public Student getTheUser() {
        return theUser;
    }

    public void setTheUser(Student theUser) {
        this.theUser = theUser;
    }
}
