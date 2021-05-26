package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.database.implementations.CoursesDAOImplementation;
import com.example.models.Student;

public class MainActivity extends AppCompatActivity {
    private static Context applicationContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationContext = getApplicationContext();

        TextView welcome_tv= findViewById(R.id.welcome_tv);
        Student user = (Student) getIntent().getExtras().get("user");
        welcome_tv.setText(user.getName()+" "+user.getSurname()+" "+user.getGpa());
        welcome_tv.setVisibility(View.VISIBLE);
    }

}