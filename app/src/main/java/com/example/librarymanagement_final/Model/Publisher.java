package com.example.librarymanagement_final.Model;

public class Publisher {
    private int publisherId;
    private String name;

    public Publisher(int publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public String getName() {
        return name;
    }
}
