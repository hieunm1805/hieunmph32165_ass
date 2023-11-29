package com.example.hieunmph32165_ass.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    static String DB_name = "quanly_Ungdung";
    static int DB_VERSION = 5;

    public DbHelper(Context context)     {
        super(context,DB_name,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql_user = "CREATE TABLE User (\n" +
                "    id       INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    username TEXT    UNIQUE\n" +
                "                     NOT NULL,\n" +
                "    password TEXT    UNIQUE\n" +
                "                     NOT NULL,\n" +
                "    email    TEXT    NOT NULL\n" +
                "                     UNIQUE,\n" +
                "    fullname TEXT    NOT NULL\n" +
                ");";
        sqLiteDatabase.execSQL(sql_user);
        String user = "INSERT INTO User(username,password,email,fullname) VALUES" +
                " ('hieu1805','123','hieunm','Nguyen Manh Hieu')";
        sqLiteDatabase.execSQL(user);
        String sql_congviev ="CREATE TABLE CongViec (\n" +
                "    id          INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    tencv       TEXT    NOT NULL,\n" +
                "    noidung     TEXT    NOT NULL,\n" +
                "    trangthai   TEXT    NOT NULL,\n" +
                "    ngaybatdau  TEXT    NOT NULL,\n" +
                "    ngayketthuc TEXT    NOT NULL\n" +
                ");";
        sqLiteDatabase.execSQL(sql_congviev);

        String db_sql_congviec = "INSERT INTO CongViec (tenvc, noidung, trangthai, ngaybatdau, ngayketthuc) VALUES ('Thiết kế đồ hoạ', 'Thiết kế banner', 'Đang làm', '23/1/2021', '30/11/2027')," +
                "('Giám đốc điều hành', 'Quản lý dự án', 'Đang làm', '23/1/2020', '18/2/2030')," +
                "('Nhân viên kho', 'Dọn kho', 'Đang làm', '23/1/2022', '30/11/2025')";
        sqLiteDatabase.execSQL(db_sql_congviec);


    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE user");
        sqLiteDatabase.execSQL("DROP TABLE CongViec");
        onCreate(sqLiteDatabase);
    }
}
