package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.activities.listview_adapters.MyCourseListArrayAdapter;
import com.example.activities.listview_adapters.StudentListArrayAdapter;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.models.Course;
import com.example.models.Student;

public class SearchStudentsActivity extends AppCompatActivity {
    private RadioGroup filter_criteria_rg;
    private EditText keyword_et;
    private Button search_btn;
    private ListView filtered_students_lv;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                StudentDAOImplementation sDAO = new StudentDAOImplementation(getApplicationContext());
                String keyword = keyword_et.getText().toString();
                if(keyword.isEmpty()) throw new Exception();
                if(filter_criteria_rg.getCheckedRadioButtonId()==R.id.filter_by_name){
                    //Toast.makeText(SearchCoursesActivity.this, "By Title keyword: "+keyword, Toast.LENGTH_SHORT).show();
                    ArrayAdapter<Student> adapter =
                            new StudentListArrayAdapter(getApplicationContext(),
                                    R.layout.general_student_layout,
                                    sDAO.findCourseByName(keyword));
                    filtered_students_lv.setAdapter(adapter);
                }
                else if(filter_criteria_rg.getCheckedRadioButtonId()==R.id.filter_by_surname){
                    //Toast.makeText(SearchCoursesActivity.this, "By Instructor keyword: "+keyword, Toast.LENGTH_SHORT).show();
                    ArrayAdapter<Student> adapter =
                            new StudentListArrayAdapter(getApplicationContext(),
                                    R.layout.general_student_layout,
                                    sDAO.findCourseBySurname(keyword));
                    filtered_students_lv.setAdapter(adapter);
                }
            }catch (Exception e){
                Toast.makeText(SearchStudentsActivity.this, "The keyword must be filled!", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_students);
        initializeGUIComponents();
    }

    private void initializeGUIComponents(){
        filter_criteria_rg = findViewById(R.id.filter_criteria_rg);
        keyword_et = findViewById(R.id.search_keyword);
        search_btn = findViewById(R.id.search_student_search_btn);
        filtered_students_lv = findViewById(R.id.filtered_students_lv);
        search_btn.setOnClickListener(listener);
    }
}