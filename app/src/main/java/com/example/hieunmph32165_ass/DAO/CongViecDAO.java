package com.example.hieunmph32165_ass.DAO;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import com.example.hieunmph32165_ass.DTO.CongViecDTO;
import com.example.hieunmph32165_ass.Database.DbHelper;

public class CongViecDAO {
    DbHelper myDbHelper;
    SQLiteDatabase db;

    public CongViecDAO(Context context) {
        myDbHelper = new DbHelper(context);
        db = myDbHelper.getWritableDatabase();
    }

    public long ADDROW(CongViecDTO objcongviec) {
        ContentValues values = new ContentValues();
        values.put("tencv", objcongviec.getTenCV());
        values.put("noidung", objcongviec.getNoiDung());
        values.put("trangthai", objcongviec.getTrangThai());
        values.put("ngaybatdau", objcongviec.getNgayBatDau());
        values.put("ngayketthuc", objcongviec.getNgayKetThuc());
        return db.insert("CongViec", null, values);
    }

    public long Update(CongViecDTO objcongviec) {
        ContentValues values = new ContentValues();
        values.put("tencv", objcongviec.getTenCV());
        values.put("noidung", objcongviec.getNoiDung());
        values.put("trangthai", objcongviec.getTrangThai());
        values.put("ngaybatdau", objcongviec.getNgayBatDau());
        values.put("ngayketthuc", objcongviec.getNgayKetThuc());
        return db.update("CongViec", values, "id=?", new String[]{String.valueOf(objcongviec.getId())});
    }

    public long Delete(String id) {
        return db.delete("CongViec", "id = ?", new String[]{id});
    }

    public List<CongViecDTO> getAll() {
        String sql = "select * from CongViec";
        return getData(sql);
    }

    @SuppressLint("Range")
    public List<CongViecDTO> getData(String sql, String... selectinArgs) {
        List<CongViecDTO> list = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, selectinArgs);
        while (cursor.moveToNext()) {
            CongViecDTO sp = new CongViecDTO();
            sp.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("id"))));
            sp.setTenCV(cursor.getString(cursor.getColumnIndex("tencv")));
            sp.setNoiDung(cursor.getString(cursor.getColumnIndex("noidung")));
            sp.setTrangThai(cursor.getString(cursor.getColumnIndex("trangthai")));
            sp.setNgayBatDau(cursor.getString(cursor.getColumnIndex("ngaybatdau")));
            sp.setNgayKetThuc(cursor.getString(cursor.getColumnIndex("ngayketthuc")));
            list.add(sp);
        }
        return list;
    }
}
