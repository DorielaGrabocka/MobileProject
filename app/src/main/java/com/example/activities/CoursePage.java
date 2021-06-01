package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.database.implementations.CoursesDAOImplementation;
import com.example.models.Comment;
import com.example.models.Course;

public class CoursePage extends AppCompatActivity {

    private Course theCourse;
    private TextView title_tv, instructor_tv, credits_tv, class_tv, faculty_tv, time_tv, day_tv,
    department_tv, eligibility_tv, midterm_tv, final_tv, other_tv, project_tv;
    private Button addComment_btn, grade_analytics_btn;
    private ListView comments_lv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        theCourse = (Course) getIntent().getExtras().get("course");
        initializeGUIComponents();
        setGUIValues();
        CoursesDAOImplementation coursesDAO = new CoursesDAOImplementation(getApplicationContext());
        ArrayAdapter<Comment> commentsAdapter =
                new ArrayAdapter<>(getApplicationContext(),
                        R.layout.comments_list_design,
                        coursesDAO.getAllComments(theCourse.getId()));
        comments_lv.setAdapter(commentsAdapter);


    }

    private void setGUIValues() {
        title_tv.setText(theCourse.getTitle());
        instructor_tv.setText(theCourse.getInstructor());
        credits_tv.setText(theCourse.getCredits());
        class_tv.setText(theCourse.getVenue());
        faculty_tv.setText(theCourse.getFaculty());
        time_tv.setText(theCourse.getTime());
        day_tv.setText(theCourse.getDay());
        department_tv.setText(theCourse.getDepartment());
        eligibility_tv.setText(theCourse.getEligibility());
        midterm_tv.setText(String.valueOf(theCourse.getMidterm_weight()));
        final_tv.setText(String.valueOf(theCourse.getFinal_weight()));
        other_tv.setText(String.valueOf(theCourse.getOther_components_weight()));
        project_tv.setText(String.valueOf(theCourse.getProject_weight()));
    }

    private void initializeGUIComponents() {
        title_tv = findViewById(R.id.course_title_tv);
        instructor_tv = findViewById(R.id.course_page_instructor_tv);
        credits_tv = findViewById(R.id.course_page_credits_tv);
        class_tv = findViewById(R.id.course_page_venue_tv);
        faculty_tv = findViewById(R.id.course_page_faculty_tv);
        time_tv = findViewById(R.id.course_page_time_tv);
        day_tv = findViewById(R.id.course_page_day_tv);
        department_tv = findViewById(R.id.course_page_department_tv);
        eligibility_tv = findViewById(R.id.course_page_eligibility_tv);
        midterm_tv = findViewById(R.id.course_page_midterm_weight_tv);
        final_tv = findViewById(R.id.course_page_final_weight_tv);
        other_tv = findViewById(R.id.course_page_other_weight_tv);
        project_tv = findViewById(R.id.course_page_project_weight_tv);
        addComment_btn = findViewById(R.id.add_comment_btn);
        grade_analytics_btn = findViewById(R.id.grade_analytics_btn);
        comments_lv = findViewById(R.id.comments_lv);
    }
}