package com.example.activities.listview_adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.activities.R;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.models.Comment;
import com.example.models.Course;
import com.example.models.Student;
import com.example.models.StudentCourse;

import java.util.List;

public class CourseListArrayAdapter extends ArrayAdapter<StudentCourse> {
    private Context context;
    public CourseListArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View custom = inflater.inflate(R.layout.general_course_layout, parent, false);
        TextView course_title_tv = custom.findViewById(R.id.list_course_title_tv);
        TextView instructor_tv = custom.findViewById(R.id.list_instructor_tv);
        TextView credits_tv = custom.findViewById(R.id.list_credits1_tv);
        TextView time_tv = custom.findViewById(R.id.list_time_tv);
        TextView department_tv = custom.findViewById(R.id.list_department_tv);
        Course c = getItem(position).getCourse();
        course_title_tv.setText("Title: "+c.getTitle());
        instructor_tv.setText("Instructor: "+c.getInstructor());
        credits_tv.setText("Credits: "+c.getCredits());
        time_tv.setText("Time: "+c.getDay()+", "+c.getTime());
        department_tv.setText("Department: "+c.getDepartment());
        return custom;
    }
}
