package com.example.librarymanagement_final.Helper;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LibraryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "library.db";
    private static final int DATABASE_VERSION = 1;

    public LibraryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create tables
        db.execSQL("CREATE TABLE books (book_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, author_id INTEGER, publisher_id INTEGER, year INTEGER);");
        db.execSQL("CREATE TABLE authors (author_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");
        db.execSQL("CREATE TABLE publishers (publisher_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");
        db.execSQL("CREATE TABLE members (member_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, membership_date DATE);");
        db.execSQL("CREATE TABLE lending (lending_id INTEGER PRIMARY KEY AUTOINCREMENT, book_id INTEGER, member_id INTEGER, borrowed_date DATE, return_date DATE);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade logic if needed
    }
}
