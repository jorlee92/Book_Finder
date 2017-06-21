package com.dant2.bookfinder;



public class Book {

    String bookAuthors;
    String bookTitle;
    String bookSubtitle;
    String bookPublisher;
    String bookPublishDate;
    String bookDescription;
    int bookPageCount;

    public String getBookAuthors() {
        if (bookAuthors == null){
            bookAuthors = "No Author";
        }

        return bookAuthors;
    }

    public void setBookAuthors(String bookAuthors) {
        this.bookAuthors = bookAuthors;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookSubtitle() {
        return bookSubtitle;
    }

    public void setBookSubtitle(String bookSubtitle) {
        this.bookSubtitle = bookSubtitle;
    }

    public String getBookPublisher() {
        return bookPublisher;
    }

    public void setBookPublisher(String bookPublisher) {
        this.bookPublisher = bookPublisher;
    }

    public String getBookPublishDate() {
        return bookPublishDate;
    }

    public void setBookPublishDate(String bookPublishDate) {
        this.bookPublishDate = bookPublishDate;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public int getBookPageCount() {
        return bookPageCount;
    }

    public void setBookPageCount(int bookPageCount) {
        this.bookPageCount = bookPageCount;
    }



    public Book(String bookAuthors, String bookTitle, String bookSubtitle, String bookPublisher, String bookPublishDate, String bookDescription, int bookPageCount) {
        this.bookAuthors = bookAuthors;
        this.bookTitle = bookTitle;
        this.bookSubtitle = bookSubtitle;
        this.bookPublisher = bookPublisher;
        this.bookPublishDate = bookPublishDate;
        this.bookDescription = bookDescription;
        this.bookPageCount = bookPageCount;
    }
}
