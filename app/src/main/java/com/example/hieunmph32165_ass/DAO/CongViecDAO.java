package com.example.hieunmph32165_ass.DAO;

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

    public CongViecDAO(Context context){
    myDbHelper = new DbHelper(context);
    db =myDbHelper.getWritableDatabase();
    }
    public boolean ADDROW(CongViecDTO objcongviec){
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tencv",objcongviec.getTenCV());
        values.put("noidung",objcongviec.getNoiDung());
        values.put("trangthai",objcongviec.getTrangThai());
        values.put("ngaybatdau",objcongviec.getNgayBatDau());
        values.put("ngayketthuc",objcongviec.getNgayKetThuc());
        long check = sqLiteDatabase.insert("CongViec",null , values);
        //return db.insert("CongViec",null,values);
        if (check == -1) return false;
        return true;
    }
    public int Update(CongViecDTO objcongviec){
        SQLiteDatabase sqLiteDatabase = myDbHelper.getWritableDatabase();
        String[] dieukien = {String.valueOf(objcongviec.getId())};
        ContentValues values = new ContentValues();
        values.put("tencv",objcongviec.getTenCV());
        values.put("noidung",objcongviec.getNoiDung());
        values.put("trangthai",objcongviec.getTrangThai());
        values.put("ngaybatdau",objcongviec.getNgayBatDau());
        values.put("ngayketthuc",objcongviec.getNgayKetThuc());
        return db.update("CongViec",values,"id=?",dieukien);
    }
    public int Delete(CongViecDTO objcv){
        String[] dieukien = new String[]{String.valueOf(objcv.getId())};
        return db.delete("CongViec","id=?",dieukien);
    }
    public List<CongViecDTO> getAll(){
        List<CongViecDTO> list_congviec = new ArrayList<>();
        Cursor c =db.rawQuery("SELECT * FROM CongViec",null);
        if(c!=null && c.getCount() > 0){
            c. moveToFirst();
            do {
                int id_cv =c.getInt(0);
                String tencv = c.getString(1);
                String noidung = c.getString(2);
                String trangthai = c.getString(3);
                String ngaybatdau =c.getString(4);
                String ngayketthuc = c.getString(5);
                CongViecDTO congviecDTO = new CongViecDTO(id_cv,tencv,noidung,trangthai,ngaybatdau,ngayketthuc);
                congviecDTO.setId(id_cv);
                congviecDTO.setTenCV(tencv);
                congviecDTO.setNoiDung(noidung);
                congviecDTO.setTrangThai(trangthai);
                congviecDTO.setNgayBatDau(ngaybatdau);
                congviecDTO.setNgayKetThuc(ngayketthuc);
                list_congviec.add(congviecDTO);
            }while (c.moveToNext());
        }
        return list_congviec;
    }
}
