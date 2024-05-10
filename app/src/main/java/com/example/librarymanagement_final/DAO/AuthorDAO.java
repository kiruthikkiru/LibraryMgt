package com.example.librarymanagement_final.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.librarymanagement_final.Model.Author;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {
    private SQLiteDatabase db;

    public AuthorDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertAuthor(String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        db.insertOrThrow("authors", null, values);
    }

    public Author getAuthorById(int authorId) {
        Cursor cursor = db.query("authors", null, "author_id = ?", new String[]{String.valueOf(authorId)}, null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            cursor.close();
            return new Author(authorId, name);
        }
        if (cursor != null) cursor.close();
        return null;
    }

    public List<Author> getAllAuthors() {
        List<Author> authors = new ArrayList<>();
        Cursor cursor = db.query("authors", null, null, null, null, null, null);
        while (cursor != null && cursor.moveToNext()) {
            int authorId = cursor.getInt(cursor.getColumnIndexOrThrow("author_id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            authors.add(new Author(authorId, name));
        }
        if (cursor != null) cursor.close();
        return authors;
    }

    public void updateAuthor(int authorId, String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        db.update("authors", values, "author_id = ?", new String[]{String.valueOf(authorId)});
    }

    public void deleteAuthor(int authorId) {
        db.delete("authors", "author_id = ?", new String[]{String.valueOf(authorId)});
    }
}
