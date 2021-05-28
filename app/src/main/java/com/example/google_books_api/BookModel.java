package com.example.google_books_api;

import java.util.ArrayList;
import java.util.List;

public class BookModel {
    private String title;
    private List<String> authors;
    private String imageUrl;
    private String publisher;
    private String date;
    private String pages;
    private String rating;
    private String textSnippet;

    public BookModel() {
    }

    public BookModel(String title, ArrayList<String> authors, String imageUrl,
                     String publisher, String date, String pages,
                     String rating, String textSnippet) {
        this.title = title;
        this.authors = authors;
        this.imageUrl = imageUrl;
        this.publisher = publisher;
        this.date = date;
        this.pages = pages;
        this.rating = rating;
        this.textSnippet = textSnippet;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        StringBuilder authors=new StringBuilder();
        for (String a: this.authors){
            authors.append(a).append("  ");
        }

        return authors.toString().replace('[',' ').replace(']',' ');
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTextSnippet() {
        return textSnippet;
    }

    public void setTextSnippet(String textSnippet) {
        this.textSnippet = textSnippet;
    }

    @Override
    public String toString() {
        return "BookModel{" +
                "title='" + title + '\'' +
                ", authors=" + authors +
                ", imageUrl='" + imageUrl + '\'' +
                ", publisher='" + publisher + '\'' +
                ", date='" + date + '\'' +
                ", pages='" + pages + '\'' +
                ", rating='" + rating + '\'' +
                ", textSnippet='" + textSnippet + '\'' +
                '}';
    }
}
