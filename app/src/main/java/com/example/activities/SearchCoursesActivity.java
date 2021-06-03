package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.activities.listview_adapters.MyCourseListArrayAdapter;
import com.example.database.implementations.CoursesDAOImplementation;
import com.example.models.Course;

public class SearchCoursesActivity extends AppCompatActivity {

    private RadioGroup filter_criteria_rg;
    private EditText keyword_et;
    private Button search_btn;
    private ListView filtered_courses_lv;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                CoursesDAOImplementation cDAO = new CoursesDAOImplementation(getApplicationContext());
                String keyword = keyword_et.getText().toString();
                if(keyword.isEmpty()) throw new Exception();
                if(filter_criteria_rg.getCheckedRadioButtonId()==R.id.filter_by_title){
                    //Toast.makeText(SearchCoursesActivity.this, "By Title keyword: "+keyword, Toast.LENGTH_SHORT).show();
                    ArrayAdapter<Course> adapter =
                            new MyCourseListArrayAdapter(getApplicationContext(),
                                    R.layout.general_course_layout,
                                    cDAO.findCourseByTitle(keyword));
                    filtered_courses_lv.setAdapter(adapter);
                }
                else if(filter_criteria_rg.getCheckedRadioButtonId()==R.id.filter_by_instructor){
                    //Toast.makeText(SearchCoursesActivity.this, "By Instructor keyword: "+keyword, Toast.LENGTH_SHORT).show();
                    ArrayAdapter<Course> adapter =
                            new MyCourseListArrayAdapter(getApplicationContext(),
                                    R.layout.general_course_layout,
                                    cDAO.findCourseByInstructor(keyword));
                    filtered_courses_lv.setAdapter(adapter);
                }
            }catch (Exception e){
                Toast.makeText(SearchCoursesActivity.this, "The keyword must be filled!", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_courses);
        initializeGUIComponents();
    }

    private void initializeGUIComponents(){
        filter_criteria_rg = findViewById(R.id.filter_criteria_rg);
        keyword_et = findViewById(R.id.search_keyword);
        search_btn = findViewById(R.id.search_course_search_btn);
        filtered_courses_lv = findViewById(R.id.filtered_courses_lv);
        search_btn.setOnClickListener(listener);
    }
}