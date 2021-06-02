package com.example.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.models.Course;

public class GradeAnalyticsActivity extends AppCompatActivity {

    private Button calculate_grade_btn;
    private TextView grade_analytics_title_tv, midterm_weight_tv, final_weight_tv, project_weight_tv, other_weight_tv;
    private EditText midterm_et, final_et, other_et, project_et;
    private ProgressBar final_grade_pgb;
    private TextView final_numeric_grade_tv, final_grade_tv;
    private Course theCourse;
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            try{
                int midterm = Integer.parseInt(midterm_et.getText().toString());
                int final_grade = Integer.parseInt(final_et.getText().toString());
                int project = Integer.parseInt(project_et.getText().toString());
                int other = Integer.parseInt(other_et.getText().toString());

                double finalNumericGrade = midterm*theCourse.getMidterm_weight()/100
                        +final_grade*theCourse.getFinal_weight()/100
                        +other*theCourse.getOther_components_weight()/100
                        +project*theCourse.getProject_weight()/100;
                String letterGrade = letterFromPoints(finalNumericGrade);

                final_grade_pgb.setProgress((int) Math.round(finalNumericGrade));
                final_numeric_grade_tv.setText(String.valueOf(finalNumericGrade));
                final_grade_tv.setText(letterGrade);


            }catch(Exception e){
                Toast.makeText(GradeAnalyticsActivity.this, "Fill the data", Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade_analytics);
        theCourse = (Course) getIntent().getExtras().get("course");
        initializeComponents();
        fillGUIComponents();
    }

    private void initializeComponents(){
        calculate_grade_btn = findViewById(R.id.calculate_grade_btn);
        grade_analytics_title_tv = findViewById(R.id.grade_analytics_title_tv);
        midterm_weight_tv = findViewById(R.id.grade_analytics_midterm_weight_tv);
        final_weight_tv = findViewById(R.id.grade_analytics_final_weight_tv);
        project_weight_tv = findViewById(R.id.grade_analytics_project_weight_tv);
        other_weight_tv = findViewById(R.id.grade_analytics_other_weight_tv);
        midterm_et = findViewById(R.id.grade_analytics_midterm_et);
        final_et = findViewById(R.id.grade_analytics_final_et);
        other_et = findViewById(R.id.grade_analytics_other_et);
        project_et = findViewById(R.id.grade_analytics_project_et);
        final_grade_pgb = findViewById(R.id.final_grade_pgb);
        final_numeric_grade_tv = findViewById(R.id.final_numeric_grade_tv);
        final_grade_tv = findViewById(R.id.final_grade_tv);
        calculate_grade_btn.setOnClickListener(listener);
    }

    private void fillGUIComponents(){
        grade_analytics_title_tv.setText("Course: "+theCourse.getTitle());
        midterm_weight_tv.setText("Midterm: "+theCourse.getMidterm_weight()+"%");
        final_weight_tv .setText("Final: "+theCourse.getFinal_weight()+"%");
        project_weight_tv.setText("Project: "+theCourse.getProject_weight()+"%");
        other_weight_tv.setText("Other: "+theCourse.getOther_components_weight()+"%");
    }

    private String letterFromPoints(double points){
        String letter="A";
        if(points>95){
            letter="A";
        }
        else if(points>89 && points<96){
            letter="A-";
        }
        else if(points>86 && points<90){
            letter="B+";
        }
        else if(points>82 && points<87){
            letter="B";
        }
        else if(points>79 && points<83){
            letter="B-";
        }
        else if(points>76 && points<80){
            letter="C+";
        }
        else if(points>72 && points<77){
            letter="C";
        }
        else if(points>69 && points<73){
            letter="C-";
        }
        else if(points>66 && points<70){
            letter="D+";
        }
        else if(points>62 && points<67){
            letter="D";
        }
        else if(points>59 && points<63){
            letter="D-";
        }
        else if(points<60){
            letter="F";
        }

        return letter;
    }
}