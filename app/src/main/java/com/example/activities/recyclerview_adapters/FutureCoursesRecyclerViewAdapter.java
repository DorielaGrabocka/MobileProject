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

import java.util.ArrayList;
import java.util.List;


public class FutureCoursesRecyclerViewAdapter extends RecyclerView.Adapter<FutureCoursesRecyclerViewAdapter.ViewHolder>{

    private List<Course> courses = new ArrayList<>();
    private Context context;

    public FutureCoursesRecyclerViewAdapter(Context context) {
        this.context = context;

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

        //missing image
        /*Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView title_tv, instructor_tv, credits_tv, time_tv;

        private Button bookmarkCourseButton;
        private View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Bookmark course is clicked", Toast.LENGTH_SHORT).show();
                //change db
            }
        };

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.course_title_tv);
            instructor_tv = itemView.findViewById(R.id.instructor_tv);
            credits_tv = itemView.findViewById(R.id.credits_tv);
            time_tv = itemView.findViewById(R.id.time_tv);
            parent = itemView.findViewById(R.id.parent);
            bookmarkCourseButton = itemView.findViewById(R.id.bookmark_btn);
            bookmarkCourseButton.setOnClickListener(buttonClickListener);
        }
    }
}
