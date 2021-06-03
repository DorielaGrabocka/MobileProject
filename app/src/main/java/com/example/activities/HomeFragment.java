package com.example.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.database.implementations.StudentCourseDAOImplementation;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    //logic elements
    private Context context;
    private Student theUser;
    private List<StudentCourse> bookmarkedCourses;
    private StudentCourseDAOImplementation scDAO;
    private View view;
    //GUI elements
    private LinearLayout myCourseLayout, bookmarksLayout, searchCoursesLayout,
            searchStudentsLayout,goToUNYTLayout, settingLayout;

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int clickedId = v.getId();
            Intent gotToIntent = new Intent();
            switch (clickedId){
                case R.id.home_my_courses_container:
                    gotToIntent = new Intent(context, MyCoursesActivity.class);
                    gotToIntent.putExtra("user", theUser);
                    gotToIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case R.id.home_bookmarks_container:
                    gotToIntent = new Intent(context, BookmarkedCoursesActivity.class);
                    gotToIntent.putExtra("user", theUser);
                    gotToIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case R.id.home_search_courses_container:
                    gotToIntent = new Intent(context, SearchCoursesActivity.class);
                    gotToIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case R.id.home_search_students_container:
                    gotToIntent = new Intent(context, SearchStudentsActivity.class);
                    gotToIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case R.id.home_settings_container:
                    gotToIntent = gotToIntent = new Intent(context, SettingsActvity.class);
                    gotToIntent.putExtra("user", theUser);
                    gotToIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
                case R.id.home_got_to_UNYT_container:
                    Uri unytUri = Uri.parse("https:\\www.unyt.edu.al");
                    gotToIntent = gotToIntent = new Intent(Intent.ACTION_VIEW, unytUri);
                    gotToIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    break;
            }
            startActivity(gotToIntent);
        }
    };


    public HomeFragment(Context context, Student theUser) {
        this.context = context;
        this.theUser = theUser;
        scDAO = new StudentCourseDAOImplementation(context);
        bookmarkedCourses = scDAO.getBookmarkedCoursesOfStudent(theUser);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        initializeGUIComponents();
        return view;
    }

    private void initializeGUIComponents() {
        myCourseLayout = view.findViewById(R.id.home_my_courses_container);
        myCourseLayout.setOnClickListener(listener);
        bookmarksLayout = view.findViewById(R.id.home_bookmarks_container);
        bookmarksLayout.setOnClickListener(listener);
        searchCoursesLayout = view.findViewById(R.id.home_search_courses_container);
        searchCoursesLayout.setOnClickListener(listener);
        searchStudentsLayout = view.findViewById(R.id.home_search_students_container);
        searchStudentsLayout.setOnClickListener(listener);
        settingLayout = view.findViewById(R.id.home_settings_container);
        settingLayout.setOnClickListener(listener);
        goToUNYTLayout = view.findViewById(R.id.home_got_to_UNYT_container);
        goToUNYTLayout.setOnClickListener(listener);
    }
}
