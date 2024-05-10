package com.example.librarymanagement_final.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.librarymanagement_final.Model.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherDAO {
    private SQLiteDatabase db;

    public PublisherDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertPublisher(String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        try {
            db.insertOrThrow("publishers", null, values);
        } catch (SQLException e) {
            e.printStackTrace();  // Log the exception
        }
    }

    public List<Publisher> getAllPublishers() {
        List<Publisher> publishers = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query("publishers", new String[]{"publisher_id", "name"}, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("publisher_id"));
                String name = cursor.getString(cursor.getColumnIndex("name"));
                publishers.add(new Publisher(id, name));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return publishers;
    }

    public Publisher getPublisherById(int publisherId) {
        Cursor cursor = null;
        try {
            cursor = db.query("publishers", new String[]{"publisher_id", "name"}, "publisher_id = ?", new String[]{String.valueOf(publisherId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndex("name"));
                return new Publisher(publisherId, name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return null;
    }

    public void updatePublisher(int publisherId, String name) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        try {
            db.update("publishers", values, "publisher_id = ?", new String[]{String.valueOf(publisherId)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePublisher(int publisherId) {
        try {
            db.delete("publishers", "publisher_id = ?", new String[]{String.valueOf(publisherId)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
