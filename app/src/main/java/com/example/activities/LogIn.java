package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.database.implementations.StudentDAOImplementation;
import com.example.database.interfaces.StudentDAO;
import com.example.models.Student;

public class LogIn extends AppCompatActivity {
    StudentDAOImplementation studentDAO;
    EditText id_tv, password_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        id_tv = findViewById(R.id.id_tv);
        password_tv = findViewById(R.id.password_tv);
        studentDAO = new StudentDAOImplementation(getApplicationContext());
    }

    /**Action handler for login button*/
    public void loginClicked(View v){
        //try{
            String id = id_tv.getText().toString();
            String password = password_tv.getText().toString();
            //Toast.makeText(this, id+" "+password, Toast.LENGTH_SHORT).show();
            Student theUser = studentDAO.authenticateUser(id, password);
            if(theUser!=null){
                //go to the Main page
                Intent mainActivity = new Intent(LogIn.this, MainActivity.class);
                mainActivity.putExtra("user", theUser);
                startActivity(mainActivity);
            }
            else{
                Toast.makeText(this, "Wrong credentials! Try again!", Toast.LENGTH_SHORT).show();
            }
        //}catch (Exception e){
//            Toast.makeText(this, "Please fill the ID and the password!"+e.printStackTrace(), Toast.LENGTH_SHORT).show();

        //}

    }
}