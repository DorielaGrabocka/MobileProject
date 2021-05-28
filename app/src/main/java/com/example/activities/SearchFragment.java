package com.example.activities;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.google_books_api.BookModel;
import com.example.google_books_api.BooksDataService;
import com.example.google_books_api.BooksRecyclerViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SearchFragment extends Fragment {

    private RecyclerView books_recycler_view;
    private EditText title_et;
    private FloatingActionButton search_btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        books_recycler_view = view.findViewById(R.id.books_recycler_view);
        title_et = view.findViewById(R.id.title_et);
        search_btn = view.findViewById(R.id.search_btn);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final Context context = v.getContext();
                    //Toast.makeText(context, "Floating button is clicked", Toast.LENGTH_SHORT).show();
                    if(title_et.getText().toString().isEmpty()){
                        Toast.makeText(context, "Please enter a title!", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        final BooksDataService dataService = new BooksDataService(context);
                        dataService.getBookData(title_et.getText().toString(),
                                new BooksDataService.VolleyResponseListener() {
                                    @Override
                                    public void onError(String message) {
                                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onResponse(List<BookModel> bookModels) {
                                        BooksRecyclerViewAdapter adapter = new BooksRecyclerViewAdapter(context);
                                        adapter.setBooks(bookModels);
                                        books_recycler_view.setAdapter(adapter);
                                        books_recycler_view.setLayoutManager(new LinearLayoutManager(context));
                                    }
                                });
                    }
                }

        });

        return view;
    }




    public void startSearchingBooks(View view){
        final Context context = view.getContext();
        Toast.makeText(context, "Floating button is clicked", Toast.LENGTH_SHORT).show();
        if("".equals(title_et.getText())){
            Toast.makeText(context, "Please enter a title!", Toast.LENGTH_SHORT).show();
        }
        else{
            final BooksDataService dataService = new BooksDataService(context);
            dataService.getBookData(title_et.getText().toString(),
                    new BooksDataService.VolleyResponseListener() {
                        @Override
                        public void onError(String message) {
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onResponse(List<BookModel> bookModels) {
                            BooksRecyclerViewAdapter adapter = new BooksRecyclerViewAdapter(context);
                            adapter.setBooks(bookModels);
                            books_recycler_view.setAdapter(adapter);
                            books_recycler_view.setLayoutManager(new LinearLayoutManager(context));
                        }
                    });
        }

    }


}
