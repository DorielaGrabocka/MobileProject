package com.example.activities.recyclerview_adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.CoursePage;
import com.example.activities.MainActivity;
import com.example.activities.R;
import com.example.models.Course;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.ArrayList;
import java.util.List;


public class PastCoursesRecyclerViewAdapter extends RecyclerView.Adapter<PastCoursesRecyclerViewAdapter.ViewHolder>{

    private List<StudentCourse> pastCourses = new ArrayList<>();
    private Context context;
    private Student theUser;
    public PastCoursesRecyclerViewAdapter(Context context, Student user) {
        this.context = context;
        theUser = user;
    }

    public void setCourses(List<StudentCourse> courses) {
        this.pastCourses = courses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.past_courses_layout_recyclerview,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title_tv.setText("Title: "+ pastCourses.get(position).getCourse().getTitle());
        holder.instructor_tv.setText("Instructor: "+ pastCourses.get(position).getCourse().getInstructor());
        holder.credits_tv.setText("Credits: "+ pastCourses.get(position).getCourse().getCredits());
        holder.time_tv.setText("Time: "+ pastCourses.get(position).getCourse().getTime());
        holder.gpa_tv.setText("Grade: "+ pastCourses.get(position).getQualityPointsFromLetterGrade());
        holder.grade_tv.setText("GPA: "+ pastCourses.get(position).getGrade());

        holder.go_to_coursepage_btn.setOnClickListener(v -> {
//            Toast.makeText(context, "Go to course clicked!", Toast.LENGTH_SHORT).show();
//            //implement it here
            Intent coursePageIntent = new Intent(context, CoursePage.class);
            coursePageIntent.putExtra("course", pastCourses.get(position).getCourse());
            coursePageIntent.putExtra("user", theUser);
            coursePageIntent.putExtra("past", true);
            coursePageIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(coursePageIntent);
        });
    }

    @Override
    public int getItemCount() {
        return pastCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView title_tv, instructor_tv, credits_tv, time_tv, gpa_tv, grade_tv;
        private Button go_to_coursepage_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.course_title_tv);
            instructor_tv = itemView.findViewById(R.id.instructor_tv);
            credits_tv = itemView.findViewById(R.id.credits_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            gpa_tv = itemView.findViewById(R.id.gpa_tv);
            grade_tv = itemView.findViewById(R.id.grade_tv);
            parent = itemView.findViewById(R.id.parent);
            go_to_coursepage_btn = itemView.findViewById(R.id.go_to_coursepage_btn);
        }
    }
}
