package com.example.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.database.implementations.CoursesDAOImplementation;
import com.example.database.implementations.StudentCourseDAOImplementation;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.models.Student;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private static Context applicationContext;
    private BottomNavigationView navigationMenu;
    private FrameLayout framelayout;

    //action listener for the navbar
    private BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {
        Fragment selectedFragment = null;
        boolean loggedout=false;
        switch (item.getItemId()){
            case R.id.home_nav:
                selectedFragment = new HomeFragment();
                break;
            case R.id.courses_nav:
                selectedFragment = new CoursesFragment();
                break;
            case R.id.search_nav:
                selectedFragment = new SearchFragment();
                break;
            case R.id.logout_nav:
                StudentDAOImplementation studentDAOImplementation =
                        new StudentDAOImplementation(getApplicationContext());
                studentDAOImplementation.setTheUser(null);
                Intent loginPage =  new Intent(MainActivity.this, LogIn.class);
                startActivity(loginPage);
                loggedout=true;
                break;
        }

        if(!loggedout)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, selectedFragment)
                .commit();
        return true;
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationContext = getApplicationContext();

        TextView welcome_tv= findViewById(R.id.welcome_tv);
        Student user = (Student) getIntent().getExtras().get("user");
        welcome_tv.setText("Welcome "+user.getName()+" "+user.getSurname()+" ");
        framelayout = findViewById(R.id.frameLayout);
        navigationMenu = findViewById(R.id.nav_bar_menu);
        navigationMenu.setOnNavigationItemSelectedListener(listener);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new HomeFragment())
                .commit();
    }

}