package com.example.google_books_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.activities.R;

import java.util.ArrayList;
import java.util.List;


public class BooksRecyclerViewAdapter extends RecyclerView.Adapter<BooksRecyclerViewAdapter.ViewHolder>{

    private List<BookModel> books = new ArrayList<>();
    private Context context;

    public BooksRecyclerViewAdapter(Context context) {
        this.context = context;

    }

    public void setBooks(List<BookModel> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.books_from_api_recycleview_design,
                parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title_tv.setText("Title: "+books.get(position).getTitle());
        holder.author_tv.setText("Authors: "+books.get(position).getAuthors());
        holder.publisher_tv.setText("Publisher: "+books.get(position).getPublisher());
        holder.date_tv.setText("Date: "+books.get(position).getDate());
        holder.pages_tv.setText("Pages: "+books.get(position).getPages());
        holder.rating_tv.setText("Rating: "+books.get(position).getRating());
        holder.snippet_tv.setText("Text Snippet: "+books.get(position).getTextSnippet());

        //missing image
        /*Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.image);*/
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardView parent;
        private TextView title_tv, author_tv, publisher_tv, date_tv, pages_tv, rating_tv, snippet_tv;
        private ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.book_title_tv);
            author_tv = itemView.findViewById(R.id.book_author_tv);
            publisher_tv = itemView.findViewById(R.id.book_publisher_tv);
            date_tv = itemView.findViewById(R.id.book_date_tv);
            pages_tv = itemView.findViewById(R.id.book_pages_tv);
            rating_tv = itemView.findViewById(R.id.book_rating_tv);
            snippet_tv = itemView.findViewById(R.id.snippet_tv);
            image = itemView.findViewById(R.id.image);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
