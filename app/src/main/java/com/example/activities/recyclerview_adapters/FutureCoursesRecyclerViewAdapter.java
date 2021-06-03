package com.example.activities.recyclerview_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.R;
import com.example.database.implementations.CoursesDAOImplementation;
import com.example.database.implementations.StudentCourseDAOImplementation;
import com.example.models.Course;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.ArrayList;
import java.util.List;


public class FutureCoursesRecyclerViewAdapter extends RecyclerView.Adapter<FutureCoursesRecyclerViewAdapter.ViewHolder>{

    private List<Course> courses = new ArrayList<>();
    private Context context;
    private Student theUser;

    public FutureCoursesRecyclerViewAdapter(Context context, Student user) {
        this.context = context;
        theUser = user;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.future_courses_layout_recyclerview,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title_tv.setText("Title: "+ courses.get(position).getTitle());
        holder.instructor_tv.setText("Instructor: "+ courses.get(position).getInstructor());
        holder.credits_tv.setText("Credits: "+ courses.get(position).getCredits());
        holder.time_tv.setText("Time: "+ courses.get(position).getDay()+", "+courses.get(position).getTime());
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "Bookmark course is clicked", Toast.LENGTH_SHORT).show();
                //change db
                StudentCourse sc= new StudentCourse();
                sc.setStudentId(theUser.getId());
                sc.setCourseId(courses.get(position).getId());
                sc.setStatus(2);
                sc.setGrade("-");
                StudentCourseDAOImplementation scDAO = new StudentCourseDAOImplementation(context);
                try{
                    scDAO.bookmarkCourse(sc);
                    Toast.makeText(context, "Course Bookmarked! Check Bookmarks in Home Page.", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(context, "An error has occurred! Try again!", Toast.LENGTH_SHORT).show();
                }
            }
        };
        holder.bookmarkCourseButton.setOnClickListener(buttonClickListener);
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView title_tv, instructor_tv, credits_tv, time_tv;

        private Button bookmarkCourseButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.course_title_tv);
            instructor_tv = itemView.findViewById(R.id.instructor_tv);
            credits_tv = itemView.findViewById(R.id.credits_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            parent = itemView.findViewById(R.id.parent);
            bookmarkCourseButton = itemView.findViewById(R.id.bookmark_btn);
        }
    }
}
