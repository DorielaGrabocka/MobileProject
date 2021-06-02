package com.example.activities.listview_adapters;

import android.content.Context;
import android.content.res.TypedArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.activities.R;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.models.Comment;
import com.example.models.Student;

import java.util.List;

public class CommentsListArrayAdapter extends ArrayAdapter<Comment> {
    Context context;
    public CommentsListArrayAdapter(@NonNull Context context, int resource, List<Comment> comments) {
        super(context, resource, comments);
        this.context=context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View custom = inflater.inflate(R.layout.comments_list_design, parent, false);
        StudentDAOImplementation stDAO = new StudentDAOImplementation(context);
        Comment comment = getItem(position);
        Student st = stDAO.find(comment.getStudentId());
        TextView student_tv = (TextView) custom.findViewById(R.id.student_tv);
        TextView comment_tv = (TextView) custom.findViewById(R.id.comment_tv);
        student_tv.setText(st.getName()+" "+st.getSurname());
        comment_tv.setText(comment.getComment());
        return custom;
    }
}
