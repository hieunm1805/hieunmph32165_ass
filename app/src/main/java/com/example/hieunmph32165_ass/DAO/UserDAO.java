package com.example.hieunmph32165_ass.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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
        Cursor c = db.rawQuery("SELECT * FROM User WHERE username =? and password = ?",
                new String[]{username, password});
        return c.getCount() > 0;

    }

    public String fotgotPass(String email){
        db = myDbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT password FROM User WHERE email = ?", new String[]{email});
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            return cursor.getString(0);
        }else {
            return "Mật khẩu không tồn tại";
        }
    }
}
