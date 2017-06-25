package com.dant2.bookfinder;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

public class MainActivity extends AppCompatActivity {
    private final String CLASSNAME = this.getClass().getSimpleName();
    ArrayList<Book> mBooks;
    private class BookAsyncTask extends AsyncTask<String, Void, ArrayList<Book>>{
        ArrayList<Book> res;
        protected ArrayList<Book> doInBackground(String... params) {
            try {
                res  = parseJsonData(getJsonData(params[0]));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return res;
        }
        protected void onPostExecute(ArrayList<Book> books){
            mBooks = books;
            updateUI();
            Log.v(CLASSNAME, "UI Updated");

            Log.v("Books", Integer.toString(mBooks.size()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BookAsyncTask task = new BookAsyncTask();
        task.execute("android");
    }
    private void updateUI(){
        ListView bookListView =  (ListView) findViewById(R.id.list);
        BookAdapter adapter = new BookAdapter(this, R.layout.single_book, mBooks);
        bookListView.setAdapter(adapter);
    }

    private ArrayList<Book> getBooksByKeyword(String keyword){
        ArrayList<Book> books = new ArrayList<Book>();

        //Only actually search if the string has a positive length
        if (keyword == null){
            return null;
            }
        else if (keyword.length() < 1){
            return null;
            }


        return null;
    }
    private String getJsonData(String keyword){
        HttpURLConnection connection;
        URL queryURL = null;
        String result = "";
        //Create a URL based on the keyword.
        String queryString = "https://www.googleapis.com/books/v1/volumes?q=android" + "" +  "&maxResults=5";
        try {
            Log.v(CLASSNAME, queryString);
             queryURL = new URL(queryString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        //Open a connection to the URL
        try {
            connection = (HttpURLConnection) queryURL.openConnection();
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(15000);
            connection.setRequestMethod("GET");
            connection.connect();

            //If the connection was successful.

            if(connection.getResponseCode() == 200){
                InputStream inputStream = connection.getInputStream();
                result = readInputStream(inputStream);
                Log.v(CLASSNAME, "Connection success");
            }
            else{
                Log.v(CLASSNAME, "Connection failed");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private String readInputStreamOld(InputStream inputStream) throws IOException{
        //TODO: Handle IOexception
        StringBuilder res = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader inputReader = new BufferedReader(inputStreamReader);
            String line = inputReader.readLine();
            while (line != null) {
                res.append(line);
                line = inputReader.readLine();
            }
        }
        return res.toString();
    }
    private String readInputStream(InputStream in) throws IOException{
        return IOUtils.toString(in);
    }
    private ArrayList<Book> parseJsonData(String data) throws JSONException{
        //TODO: Handle JSONException
        ArrayList<Book> res = new ArrayList<Book>();
        Log.v(CLASSNAME, data);
        JSONObject responseJSON = new JSONObject(data);
        JSONArray results = responseJSON.getJSONArray("items");

        for (int i = 0; i < results.length(); i++){
            JSONObject book = results.getJSONObject(i);
            JSONObject volumeInfo = book.getJSONObject("volumeInfo");

            String bookAuthors = volumeInfo.getJSONArray("authors").getString(0);
            String bookTitle = volumeInfo.optString("title", "undefined");
            String bookSubtitle = volumeInfo.optString("subtitle", "undefined");
            String bookPublisher =  volumeInfo.optString("publisher", "undefined");
            String bookPublishDate = volumeInfo.optString("publishedDate", "undefined");
            String bookDescription = volumeInfo.optString("description", "undefined");
            int bookPageCount = volumeInfo.optInt("pageCount", -1);

            res.add(new Book(bookAuthors,bookTitle, bookSubtitle,bookPublisher,bookPublishDate,bookDescription,bookPageCount));
            }
        return res;
        }
    }

