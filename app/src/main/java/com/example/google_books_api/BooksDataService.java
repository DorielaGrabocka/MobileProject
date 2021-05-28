package com.example.google_books_api;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BooksDataService {

    public static final String QUERY_FOR_BOOK = "https://www.googleapis.com/books/v1/volumes?q=";
    private Context context;

    public BooksDataService(Context context) {
        this.context = context;
    }

    //used for callback
    public interface VolleyResponseListener{
        void onError(String message);
        void onResponse(List<BookModel> bookModels);

    }

    public void getBookData(String bookTitle, VolleyResponseListener bookDataResponseListener) {

        //String url = "https://www.googleapis.com/books/v1/volumes?q=pride+prejudice";
        String url = QUERY_FOR_BOOK+bookTitle;
        List<BookModel> bookModels = new ArrayList<>();

        //get an Object and then get an Array
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    //Toast.makeText(context, response.toString(), Toast.LENGTH_LONG).show();
                try{
                    JSONArray items_list = response.getJSONArray("items");

                    for(int i=0; i< items_list.length();i++){

                        JSONObject one_book_from_api = (JSONObject) items_list.get(i);
                        BookModel one_book = new BookModel();
                        JSONObject main_object = one_book_from_api.getJSONObject("volumeInfo");
                        if(!main_object.has("title")){

                            one_book.setTitle("Nan");
                        }
                        else{

                            one_book.setTitle(main_object.getString("title"));
                        }

                        if(!main_object.has("authors")){
                            one_book.setAuthors(Arrays.asList("NaN"));
                        }
                        else{
                        one_book.setAuthors(
                                Arrays.asList(main_object.getJSONArray("authors").toString().split(",")));
                        }

                        if(!main_object.has("publishedDate")){
                            one_book.setDate("NaN");
                        }
                        else {
                            one_book.setDate(main_object.getString("publishedDate"));
                        }


                        if(!main_object.has("pageCount")){
                            one_book.setPages("NaN");
                        }
                        else{
                        one_book.setPages(main_object.getString("pageCount"));
                        }


                        if(!main_object.has("averageRating")){
                            one_book.setPages("NaN");
                        }
                        else{
                            String data = main_object.getString("averageRating");
                            one_book.setRating(data==null?"NaN": data);
                        }

                        if(!main_object.has("publisher")){
                            one_book.setPublisher("NaN");
                        }
                        else{
                            one_book.setPublisher(main_object.getString("publisher"));
                        }
                        if(!main_object.has("searchInfo")){
                            one_book.setTextSnippet("Nothing specified");
                        }
                        else{
                        one_book.setTextSnippet(main_object.getJSONObject("searchInfo").getString("textSnippet"));
                        }

                        //one_book.setImageUrl(main_object.getJSONObject("imageLinks").getString("smallThumbnail"));

                        bookModels.add(one_book);
                    }

                    bookDataResponseListener.onResponse(bookModels);

                }catch (JSONException e){
                    //bookDataResponseListener.onError("Something went wrong!");
                    e.printStackTrace();
                }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(context, "An error has occurred!", Toast.LENGTH_SHORT).show();

                //bookDataResponseListener.onError("Something went wrong!");
            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
    }




}
