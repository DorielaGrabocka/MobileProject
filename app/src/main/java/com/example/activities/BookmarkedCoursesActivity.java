package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.activities.listview_adapters.CourseListArrayAdapter;
import com.example.database.implementations.StudentCourseDAOImplementation;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.ArrayList;
import java.util.List;

public class BookmarkedCoursesActivity extends AppCompatActivity {

    private Student theUser;
    private StudentCourseDAOImplementation scDAO;
    private ListView bookmaredCoursesListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarked_courses);
        theUser = (Student) getIntent().getExtras().get("user");
        bookmaredCoursesListView = findViewById(R.id.bookmarked_courses_lv);
        scDAO = new StudentCourseDAOImplementation(getApplicationContext());
        List<StudentCourse> bookmarks = new ArrayList<>();
        bookmarks = scDAO.getBookmarkedCoursesOfStudent(theUser);
        ArrayAdapter<StudentCourse> adapter = new CourseListArrayAdapter(getApplicationContext(),
                R.layout.general_course_layout, bookmarks);
        bookmaredCoursesListView.setAdapter(adapter);
    }
}