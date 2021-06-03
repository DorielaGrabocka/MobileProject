package com.example.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activities.listview_adapters.CommentsListArrayAdapter;
import com.example.database.implementations.CoursesDAOImplementation;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.models.Comment;
import com.example.models.Course;
import com.example.models.Student;

public class CoursePage extends AppCompatActivity {

    private Course theCourse;
    private TextView title_tv, instructor_tv, credits_tv, class_tv, faculty_tv, time_tv, day_tv,
    department_tv, eligibility_tv, midterm_tv, final_tv, other_tv, project_tv;
    private Button addComment_btn, grade_analytics_btn;
    private ListView comments_lv;
    private boolean past;
    private EditText comment_et;
    private CoursesDAOImplementation courseDAO;
    private Student theUser;
    private View.OnClickListener addCommentListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //Toast.makeText(CoursePage.this, "Add Button clicked!", Toast.LENGTH_SHORT).show();
            if(comment_et.getText()==null || comment_et.getText().toString().isEmpty()){
                Toast.makeText(CoursePage.this, "Please write a comment!", Toast.LENGTH_SHORT).show();
                //Log.println(Log.INFO,"Inside no text!", "Inside no text!");
            }else{
                //add the comment to the database
                Comment newComment = new Comment();
                newComment.setComment(comment_et.getText().toString());
                newComment.setCourseId(theCourse.getId());
                newComment.setStudentId(theUser.getId());
                try{
                    courseDAO.addCommentAboutCourse(newComment);
                    showComments();
                    Toast.makeText(getApplicationContext(), "Comment added successfully!", Toast.LENGTH_LONG).show();
                }catch(Exception e){
                    Toast.makeText(getApplicationContext(), "A problem has occurred!", Toast.LENGTH_LONG).show();
                }
            }
        }
    };
    private AdapterView.OnItemClickListener deleteItemListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Comment commentToBeDeleted = (Comment) parent.getItemAtPosition(position);
            if(commentToBeDeleted.getStudentId()==theUser.getId()){
                try{
                    courseDAO.deleteComment(commentToBeDeleted);
                    showComments();
                    Toast.makeText(CoursePage.this, "Comment added succesfully!", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(CoursePage.this, "An error occurred!", Toast.LENGTH_SHORT).show();
                }
                //Log.println(Log.INFO, "Database: ", String.valueOf(courseDAO.getAllComments(theCourse.getId()).size()));
            }else{
                Toast.makeText(CoursePage.this, "You can delete only your comments!", Toast.LENGTH_LONG).show();
            }
        }
    };
    private View.OnClickListener gradeAnalyticsBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent toGradeAnalytics = new Intent(getApplicationContext(), GradeAnalyticsActivity.class);
            toGradeAnalytics.putExtra("course", theCourse);
            startActivity(toGradeAnalytics);
        }
    };


    /**Method to refresh the listview contents*/
    private void showComments() {
        ArrayAdapter<Comment> commentsAdapter =
                new CommentsListArrayAdapter(getApplicationContext(),
                        R.layout.comments_list_design,
                        courseDAO.getAllComments(theCourse.getId()));
        comments_lv.setAdapter(commentsAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_page);
        theCourse = (Course) getIntent().getExtras().get("course");
        past = getIntent().getExtras().getBoolean("past");
        theUser = (Student) getIntent().getExtras().get("user");
        initializeGUIComponents();
        setGUIValues();
        courseDAO = new CoursesDAOImplementation(getApplicationContext());
        showComments();
    }

    private void setGUIValues() {
        title_tv.setText("Title: "+theCourse.getTitle());
        instructor_tv.setText("Instructor: "+theCourse.getInstructor());
        credits_tv.setText("Credits: "+ theCourse.getCredits());
        class_tv.setText("Class: "+theCourse.getVenue());
        faculty_tv.setText("Faculty: "+theCourse.getFaculty());
        time_tv.setText("Time: "+theCourse.getTime());
        day_tv.setText("Day: "+theCourse.getDay());
        department_tv.setText("Department: "+theCourse.getDepartment());
        eligibility_tv.setText("Eligibility: "+theCourse.getEligibility());
        midterm_tv.setText("Midterm: " + theCourse.getMidterm_weight()+"%");
        final_tv.setText("Final: " + theCourse.getFinal_weight()+"%");
        other_tv.setText("Other: " + theCourse.getOther_components_weight()+"%");
        project_tv.setText("Project: "+ theCourse.getProject_weight()+"%");
    }

    private void initializeGUIComponents() {
        title_tv = findViewById(R.id.course_page_title_tv);
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
        grade_analytics_btn = findViewById(R.id.course_page_grade_analytics_btn);
        if(past) grade_analytics_btn.setVisibility(View.INVISIBLE);
        comments_lv = findViewById(R.id.comments_lv);
        comments_lv.setOnItemClickListener(deleteItemListener);
        comment_et = findViewById(R.id.comment_et);
        addComment_btn.setOnClickListener(addCommentListener);
        grade_analytics_btn.setOnClickListener(gradeAnalyticsBtnListener);
    }
}