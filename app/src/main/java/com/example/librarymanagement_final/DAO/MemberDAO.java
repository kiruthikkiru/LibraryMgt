//package com.example.librarymanagement_final.DAO;
//
//import android.content.ContentValues;
//import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
//import com.example.librarymanagement_final.Model.Member;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class MemberDAO {
//    private SQLiteDatabase db;
//
//    public MemberDAO(SQLiteDatabase db) {
//        this.db = db;
//    }
//
//    public void insertMember(String name, String membershipDate) {
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("membership_date", membershipDate);
//        db.insert("members", null, values);
//    }
//    public Member getMemberById(int memberId) {
//        Cursor cursor = db.query("members", new String[]{"member_id", "name", "membership_date"}, "member_id = ?", new String[]{String.valueOf(memberId)}, null, null, null);
//        if (cursor != null && cursor.moveToFirst()) {
//            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
//            String membershipDate = cursor.getString(cursor.getColumnIndexOrThrow("membership_date"));
//            cursor.close();
//            return new Member(memberId, name, membershipDate);
//        }
//        if (cursor != null) cursor.close();
//        return null;
//    }
//
//    public List<Member> getAllMembers() {
//        List<Member> members = new ArrayList<>();
//        Cursor cursor = db.query("members", new String[]{"member_id", "name", "membership_date"}, null, null, null, null, null);
//        while (cursor.moveToNext()) {
//            int memberId = cursor.getInt(cursor.getColumnIndexOrThrow("member_id"));
//            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
//            String membershipDate = cursor.getString(cursor.getColumnIndexOrThrow("membership_date"));
//            members.add(new Member(memberId, name, membershipDate));
//        }
//        cursor.close();
//        return members;
//    }
//
//    public void updateMember(int memberId, String name, String membershipDate) {
//        ContentValues values = new ContentValues();
//        values.put("name", name);
//        values.put("membership_date", membershipDate);
//        db.update("members", values, "member_id = ?", new String[]{String.valueOf(memberId)});
//    }
//
//    public void deleteMember(int memberId) {
//        db.delete("members", "member_id = ?", new String[]{String.valueOf(memberId)});
//    }
//}




package com.example.librarymanagement_final.DAO;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.SQLException;
import com.example.librarymanagement_final.Model.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDAO {
    private SQLiteDatabase db;

    public MemberDAO(SQLiteDatabase db) {
        this.db = db;
    }

    public void insertMember(String name, String membershipDate) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("membership_date", membershipDate);
        try {
            db.insertOrThrow("members", null, values);
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log error as appropriate
        }
    }

    public Member getMemberById(int memberId) {
        Cursor cursor = null;
        try {
            cursor = db.query("members", new String[]{"member_id", "name", "membership_date"}, "member_id = ?", new String[]{String.valueOf(memberId)}, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String membershipDate = cursor.getString(cursor.getColumnIndexOrThrow("membership_date"));
                return new Member(memberId, name, membershipDate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query("members", new String[]{"member_id", "name", "membership_date"}, null, null, null, null, null);
            while (cursor.moveToNext()) {
                int memberId = cursor.getInt(cursor.getColumnIndexOrThrow("member_id"));
                String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
                String membershipDate = cursor.getString(cursor.getColumnIndexOrThrow("membership_date"));
                members.add(new Member(memberId, name, membershipDate));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }
        return members;
    }

    public void updateMember(int memberId, String name, String membershipDate) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("membership_date", membershipDate);
        try {
            db.update("members", values, "member_id = ?", new String[]{String.valueOf(memberId)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteMember(int memberId) {
        try {
            db.delete("members", "member_id = ?", new String[]{String.valueOf(memberId)});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
