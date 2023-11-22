package com.example.hieunmph32165_ass.DTO;

public class CongViecDTO {
    public int id;
    public String TenCV, NoiDung, TrangThai, NgayBatDau, NgayKetThuc;

    public CongViecDTO(int id, String tenCV, String noiDung, String trangThai, String ngayBatDau, String ngayKetThuc) {
        this.id = id;
        TenCV = tenCV;
        NoiDung = noiDung;
        TrangThai = trangThai;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
    }

    public CongViecDTO(String tenCV, String noiDung, String trangThai, String ngayBatDau, String ngayKetThuc) {
        TenCV = tenCV;
        NoiDung = noiDung;
        TrangThai = trangThai;
        NgayBatDau = ngayBatDau;
        NgayKetThuc = ngayKetThuc;
    }

    public CongViecDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCV() {
        return TenCV;
    }

    public void setTenCV(String tenCV) {
        TenCV = tenCV;
    }

    public String getNoiDung() {
        return NoiDung;
    }

    public void setNoiDung(String noiDung) {
        NoiDung = noiDung;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public String getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        NgayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        NgayKetThuc = ngayKetThuc;
    }
}
