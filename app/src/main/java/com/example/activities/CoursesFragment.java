package com.example.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activities.recyclerview_adapters.FutureCoursesRecyclerViewAdapter;
import com.example.activities.recyclerview_adapters.PastCoursesRecyclerViewAdapter;
import com.example.activities.recyclerview_adapters.PresentCoursesRecyclerViewAdapter;
import com.example.database.implementations.StudentDAOImplementation;
import com.example.models.Student;

public class CoursesFragment extends Fragment {
    private ProgressBar currentCreditsPgBar, obtainedCreditsPgBar;
    private TextView obtained_credits_tv, current_credits_tv, remaining_credits_tv, gpa_tv;
    private RadioGroup type_of_courses_rg;
    private RecyclerView listOfCoursesView;
    private View view;
    private StudentDAOImplementation studentDAO;
    private Context context;
    private Student theUser;

    public CoursesFragment(Context context, Student user) {
        this.context = context;
        theUser = user;
    }

    private RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId==R.id.current_courses_rb){
                PresentCoursesRecyclerViewAdapter adapter = new PresentCoursesRecyclerViewAdapter(context, theUser);
                adapter.setCourses(studentDAO.getCurrentCourses());
                listOfCoursesView.setAdapter(adapter);
            }
            else if(checkedId==R.id.past_courses_rb){
                PastCoursesRecyclerViewAdapter adapter = new PastCoursesRecyclerViewAdapter(context, theUser);
                adapter.setCourses(studentDAO.getFinishedCourses());
                listOfCoursesView.setAdapter(adapter);
            }
            else{
                FutureCoursesRecyclerViewAdapter adapter = new FutureCoursesRecyclerViewAdapter(context);
                adapter.setCourses(studentDAO.getNextCourses());
                listOfCoursesView.setAdapter(adapter);
            }
            listOfCoursesView.setLayoutManager(new LinearLayoutManager(context));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_courses, container, false);
        studentDAO = new StudentDAOImplementation(context);
        initializeGUIComponents();
        studentDAO.setTheUser(theUser);
        fillGUIComponents();
        type_of_courses_rg.setOnCheckedChangeListener(listener);
        return view;
    }

    private void fillGUIComponents() {
        int obtainedCredits = studentDAO.getTheUser().getCredits();
        int currentCredits = studentDAO.getCurrentCourses()
                .stream()
                .map(c-> c.getCredits())
                .reduce((x,y)->x+y)
                .orElse(-1);
        int remainingCredits = 128-obtainedCredits;
        currentCreditsPgBar.setProgress(obtainedCredits+currentCredits);
        current_credits_tv.setText("Current Credits: "+ currentCredits);
        obtainedCreditsPgBar.setProgress(obtainedCredits);
        obtained_credits_tv.setText("Obtained Credits: "+ obtainedCredits);
        remaining_credits_tv.setText("Remaining Credits: "+ remainingCredits);
        gpa_tv.setText("GPA: "+String.format("%.2f", studentDAO.getTheUser().getGpa()));
        PresentCoursesRecyclerViewAdapter adapter = new PresentCoursesRecyclerViewAdapter(context, theUser);
        adapter.setCourses(studentDAO.getCurrentCourses());
        listOfCoursesView.setAdapter(adapter);
        listOfCoursesView.setLayoutManager(new LinearLayoutManager(context));
    }

    private void initializeGUIComponents() {
        currentCreditsPgBar = view.findViewById(R.id.current_progressbar);
        obtainedCreditsPgBar = view.findViewById(R.id.done_progressbar);
        obtained_credits_tv = view.findViewById(R.id.done_credits_tv);
        remaining_credits_tv = view.findViewById(R.id.remaining_credits_tv);
        current_credits_tv = view.findViewById(R.id.current_credits_tv);
        type_of_courses_rg = view.findViewById(R.id.type_of_courses_radiogroup);
        listOfCoursesView = view.findViewById(R.id.courses_recycler_view);
        listOfCoursesView.setHasFixedSize(true);
        gpa_tv = view.findViewById(R.id.gpa_tv);
    }
}
