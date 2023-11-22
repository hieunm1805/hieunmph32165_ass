package com.example.hieunmph32165_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hieunmph32165_ass.Database.DbHelper;

import com.example.hieunmph32165_ass.Database.DbHelper;

public class UserDAO {
    DbHelper myDbHelper;
    SQLiteDatabase db;

    public UserDAO(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public boolean Sigin(String username, String password, String email, String fullname) {
        db = myDbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("email", email);
        contentValues.put("fullname", fullname);
        long check = db.insert("User", null, contentValues);
        if (check != -1) {
            return true;
        } else {
            return false;
        }
    }
    public boolean checkLogin(String username, String password) {
        db = myDbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM User WHERE user =? and pass = ?",
                new String[]{username, password});
        if (c.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }
}
