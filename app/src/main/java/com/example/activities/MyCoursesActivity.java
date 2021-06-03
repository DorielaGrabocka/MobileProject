package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.activities.listview_adapters.CourseListArrayAdapter;
import com.example.activities.listview_adapters.MyCourseListArrayAdapter;
import com.example.database.implementations.StudentCourseDAOImplementation;
import com.example.models.Course;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class MyCoursesActivity extends AppCompatActivity {

    private Student theUser;
    private StudentCourseDAOImplementation scDAO;
    private ListView myCoursesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_courses);
        theUser = (Student) getIntent().getExtras().get("user");
        myCoursesListView = findViewById(R.id.my_current_courses_lv);
        scDAO = new StudentCourseDAOImplementation(getApplicationContext());
        List<Course> currentCourses = theUser.getListOfCurrentCourses();
        ArrayAdapter<Course> adapter = new MyCourseListArrayAdapter(getApplicationContext(),
                R.layout.general_course_layout, currentCourses);
        myCoursesListView.setAdapter(adapter);
    }
}