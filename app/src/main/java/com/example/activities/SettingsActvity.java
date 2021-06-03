package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.database.implementations.StudentCourseDAOImplementation;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.database.interfaces.StudentDAO;
import com.example.models.Student;

public class SettingsActvity extends AppCompatActivity {

    private Student theUser;
    private Button updateBtn;
    private EditText name_et, surname_et, age_et, email_et, major_et, minor_et;
    private View.OnClickListener listener = v -> {
        //Toast.makeText(SettingsActvity.this, "Update data button clicked!", Toast.LENGTH_SHORT).show();
        StudentDAOImplementation sDAO = new StudentDAOImplementation(getApplicationContext());
        try{
            String age = age_et.getText().toString();
            String email = email_et.getText().toString();
            if(age.isEmpty() || email.isEmpty()) throw new Exception();
            else{
                theUser.setAge(Integer.parseInt(age));
                theUser.setEmail(email);
                sDAO.update(theUser);
                sDAO.setTheUser(theUser);
                Toast.makeText(getApplicationContext(), "Data updated succesfully! Log out to see them!", Toast.LENGTH_SHORT).show();
                fillGUIComponents();
            }
        }catch(Exception e){
            Toast.makeText(getApplicationContext(), "Age and Email cannot be empty!", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_actvity);
        theUser = (Student) getIntent().getExtras().get("user");
        initializeGUIComponents();
        fillGUIComponents();
    }

    private void fillGUIComponents() {
        name_et.setText(theUser.getName());
        surname_et.setText(theUser.getSurname());
        email_et.setText(theUser.getEmail());
        age_et.setText(String.valueOf(theUser.getAge()));
        major_et.setText(theUser.getMajor());
        minor_et.setText(theUser.getMinor());
    }

    private void initializeGUIComponents() {
        name_et = findViewById(R.id.settings_name_et);
        surname_et = findViewById(R.id.settings_surname_et);
        age_et = findViewById(R.id.settings_age1_et);
        email_et = findViewById(R.id.settings_email_et);
        major_et = findViewById(R.id.settings_major_et);
        minor_et = findViewById(R.id.settings_minor_et);
        updateBtn = findViewById(R.id.update_btn);
        updateBtn.setOnClickListener(listener);
    }
}