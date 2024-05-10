package com.example.librarymanagement_final.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.librarymanagement_final.Model.Lending;
import java.util.ArrayList;
import java.util.List;

public class LendingDAO {
    private SQLiteDatabase db;

    public LendingDAO(SQLiteDatabase db) {
        this.db = db;
    }

    // Create (Insert a new lending record)
    public void insertLending(int bookId, int memberId, String borrowedDate, String returnDate) {
        ContentValues values = new ContentValues();
        values.put("book_id", bookId);
        values.put("member_id", memberId);
        values.put("borrowed_date", borrowedDate);
        values.put("return_date", returnDate);
        db.insert("lending", null, values);
    }

    // Read (Retrieve all lending records)
    public List<Lending> getLending() {
        List<Lending> lendings = new ArrayList<>();
        Cursor cursor = db.query("lending", new String[]{"lending_id", "book_id", "member_id", "borrowed_date", "return_date"}, null, null, null, null, null);
        while (cursor.moveToNext()) {
            int lendingId = cursor.getInt(cursor.getColumnIndexOrThrow("lending_id"));
            int bookId = cursor.getInt(cursor.getColumnIndexOrThrow("book_id"));
            int memberId = cursor.getInt(cursor.getColumnIndexOrThrow("member_id"));
            String borrowedDate = cursor.getString(cursor.getColumnIndexOrThrow("borrowed_date"));
            String returnDate = cursor.getString(cursor.getColumnIndexOrThrow("return_date"));
            lendings.add(new Lending(lendingId, bookId, memberId, borrowedDate, returnDate));
        }
        cursor.close();
        return lendings;
    }

    // Retrieve a lending record by ID
    public Lending getLendingById(int lendingId) {
        Lending lending = null;
        Cursor cursor = db.query("lending", new String[]{"lending_id", "book_id", "member_id", "borrowed_date", "return_date"},
                "lending_id = ?", new String[]{String.valueOf(lendingId)}, null, null, null);

        if (cursor.moveToFirst()) {
            int bookId = cursor.getInt(cursor.getColumnIndexOrThrow("book_id"));
            int memberId = cursor.getInt(cursor.getColumnIndexOrThrow("member_id"));
            String borrowedDate = cursor.getString(cursor.getColumnIndexOrThrow("borrowed_date"));
            String returnDate = cursor.getString(cursor.getColumnIndexOrThrow("return_date"));
            lending = new Lending(lendingId, bookId, memberId, borrowedDate, returnDate);
        }
        cursor.close();
        return lending;
    }

    // Update (Update an existing lending record)
    public void updateLending(int lendingId, int bookId, int memberId, String borrowedDate, String returnDate) {
        ContentValues values = new ContentValues();
        values.put("book_id", bookId);
        values.put("member_id", memberId);
        values.put("borrowed_date", borrowedDate);
        values.put("return_date", returnDate);
        db.update("lending", values, "lending_id = ?", new String[]{String.valueOf(lendingId)});
    }

    // Delete (Remove a lending record from the database)
    public void deleteLending(int lendingId) {
        db.delete("lending", "lending_id = ?", new String[]{String.valueOf(lendingId)});
    }
}
