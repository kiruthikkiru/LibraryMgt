package com.example.librarymanagement_final.Model;


public class Book {
    private int id;
    private String title;
    private int authorId;
    private int publisherId;
    private int year;

    public Book(int id, String title, int authorId, int publisherId, int year) {
        this.id = id;
        this.title = title;
        this.authorId = authorId;
        this.publisherId = publisherId;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getAuthorId() {
        return authorId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public int getYear() {
        return year;
    }




}

