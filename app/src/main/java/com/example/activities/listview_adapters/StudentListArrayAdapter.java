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
import com.example.models.Course;
import com.example.models.Student;

import java.util.List;

public class StudentListArrayAdapter extends ArrayAdapter<Student> {
    private Context context;
    public StudentListArrayAdapter(@NonNull Context context, int resource, @NonNull List objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View custom = inflater.inflate(R.layout.general_student_layout, parent, false);
        TextView student_name = custom.findViewById(R.id.list_student_name_tv);
        TextView major_tv = custom.findViewById(R.id.list_major_tv);
        TextView minor_tv = custom.findViewById(R.id.list_minor_tv);
        TextView email_tv = custom.findViewById(R.id.list_email_tv);
        Student student = getItem(position);
        student_name.setText("Name: "+student.getName()+" "+student.getSurname());
        major_tv.setText("Major: "+student.getMajor());
        minor_tv.setText("Minor: "+student.getMinor());
        email_tv.setText("Email: "+student.getEmail());
        return custom;
    }
}
