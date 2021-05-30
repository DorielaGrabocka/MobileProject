package com.example.activities.recyclerview_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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


public class PresentCoursesRecyclerViewAdapter extends RecyclerView.Adapter<PresentCoursesRecyclerViewAdapter.ViewHolder>{

    private List<Course> currentCourses = new ArrayList<>();
    private Context context;

    public PresentCoursesRecyclerViewAdapter(Context context) {
        this.context = context;

    }

    public void setCourses(List<Course> courses) {
        this.currentCourses = courses;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.present_courses_layout_recyclerview,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title_tv.setText("Title: "+ currentCourses.get(position).getTitle());
        holder.instructor_tv.setText("Instructor: "+ currentCourses.get(position).getInstructor());
        holder.credits_tv.setText("Credits: "+ currentCourses.get(position).getCredits());
        holder.time_tv.setText("Time: "+ currentCourses.get(position).getTime());
        holder.venue_tv.setText("Venue: "+ currentCourses.get(position).getVenue());
        holder.grade_tv.setText("GPA: Not Available yet");
        //missing image
        /*Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return currentCourses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView title_tv, instructor_tv, credits_tv, time_tv, grade_tv,venue_tv;
        private Button go_to_course_btn, grade_analysis_btn;
        private ImageView image;
        private View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.getId()==R.id.go_to_coursepage_btn){
                    Toast.makeText(context, "Go to Course page clicked!", Toast.LENGTH_SHORT).show();
                    //do it here
                }
                else{
                    Toast.makeText(context, "Grade Analytics clicked!", Toast.LENGTH_SHORT).show();
                }
            }
        };

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.course_title_tv);
            instructor_tv = itemView.findViewById(R.id.instructor_tv);
            credits_tv = itemView.findViewById(R.id.credits_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            grade_tv = itemView.findViewById(R.id.grade_tv);
            venue_tv = itemView.findViewById(R.id.venue_tv);
            parent = itemView.findViewById(R.id.parent);
            go_to_course_btn = itemView.findViewById(R.id.go_to_coursepage_btn);
            grade_analysis_btn = itemView.findViewById(R.id.grade_analytics_btn);
            go_to_course_btn.setOnClickListener(listener);
            grade_analysis_btn.setOnClickListener(listener);
            image = itemView.findViewById(R.id.image);
        }
    }
}
