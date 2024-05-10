package com.example.librarymanagement_final.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.librarymanagement_final.Model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    private SQLiteDatabase db;

    public BookDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertBook(String title, int authorId, int publisherId, int year) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author_id", authorId);
        values.put("publisher_id", publisherId);
        values.put("year", year);
        db.insertOrThrow("books", null, values);
    }

    public Book getBookById(int bookId) {
        Cursor cursor = db.query("books", null, "book_id = ?", new String[]{String.valueOf(bookId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            int authorId = cursor.getInt(cursor.getColumnIndexOrThrow("author_id"));
            int publisherId = cursor.getInt(cursor.getColumnIndexOrThrow("publisher_id"));
            int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));
            cursor.close();
            return new Book(bookId, title, authorId, publisherId, year);
        }
        if (cursor != null) cursor.close();
        return null;
    }

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        Cursor cursor = db.query("books", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int bookId = cursor.getInt(cursor.getColumnIndexOrThrow("book_id"));
            String title = cursor.getString(cursor.getColumnIndexOrThrow("title"));
            int authorId = cursor.getInt(cursor.getColumnIndexOrThrow("author_id"));
            int publisherId = cursor.getInt(cursor.getColumnIndexOrThrow("publisher_id"));
            int year = cursor.getInt(cursor.getColumnIndexOrThrow("year"));
            books.add(new Book(bookId, title, authorId, publisherId, year));
        }
        if (cursor != null) cursor.close();
        return books;
    }

    public void updateBook(int bookId, String title, int authorId, int publisherId, int year) {
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("author_id", authorId);
        values.put("publisher_id", publisherId);
        values.put("year", year);
        db.update("books", values, "book_id = ?", new String[]{String.valueOf(bookId)});
    }

    public void deleteBook(int bookId) {
        db.delete("books", "book_id = ?", new String[]{String.valueOf(bookId)});
    }
}
