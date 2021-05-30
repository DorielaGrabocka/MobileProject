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
import com.example.models.Course;
import com.example.models.StudentCourse;

import java.util.ArrayList;
import java.util.List;


public class PastCoursesRecyclerViewAdapter extends RecyclerView.Adapter<PastCoursesRecyclerViewAdapter.ViewHolder>{

    private List<StudentCourse> pastCourses = new ArrayList<>();
    private Context context;

    public PastCoursesRecyclerViewAdapter(Context context) {
        this.context = context;

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

        //missing image
        /*Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.image);*/
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

            go_to_coursepage_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Go to Course clicked!", Toast.LENGTH_SHORT).show();
                    //implement it here
                }
            });
        }
    }
}
