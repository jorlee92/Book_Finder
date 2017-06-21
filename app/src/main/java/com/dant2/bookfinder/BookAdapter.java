package com.dant2.bookfinder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class BookAdapter extends ArrayAdapter {
    String bookAuthors;
    String bookTitle;
    String bookSubtitle;
    String bookPublisher;
    String bookPublishDate;
    String bookDescription;
    int bookPageCount;

    public BookAdapter(Context context, int resource, ArrayList objects){
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Book currentBook = (Book) getItem(position);

        if(convertView == null){
            //If the view does not already exist create a new one
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_book, parent, false);
        }

        TextView authors = (TextView) convertView.findViewById(R.id.book_authors);
        authors.setText(currentBook.getBookAuthors());
        TextView title = (TextView) convertView.findViewById(R.id.book_title);
        title.setText(currentBook.getBookTitle());
        TextView subtitle = (TextView) convertView.findViewById(R.id.book_subtitle);
        subtitle.setText(currentBook.getBookSubtitle());
        TextView publisher = (TextView) convertView.findViewById(R.id.book_publisher);
        publisher.setText(currentBook.getBookPublisher());
        TextView description = (TextView) convertView.findViewById(R.id.book_description);
        description.setText(currentBook.getBookDescription());
        TextView publishdate = (TextView) convertView.findViewById(R.id.book_publish_date);
        publishdate.setText(currentBook.getBookPublishDate());
        TextView pages = (TextView) convertView.findViewById(R.id.book_pages);
        pages.setText(Integer.toString(currentBook.getBookPageCount()));
        return convertView;
    }
}
