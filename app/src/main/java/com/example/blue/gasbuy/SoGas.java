package com.example.blue.gasbuy;

/**
 * Created by Thanh Thúy on 3/23/2017.
 */

public class SoGas {
    private int stt;
    private String ngaySinh;
    private String sanpham;
    private String khuyenmai;
    private Double tien;

    public SoGas() {
    }

    public SoGas(int stt, String ngaySinh, String sanpham, String khuyenmai, Double tien) {
        this.stt = stt;
        this.ngaySinh = ngaySinh;
        this.sanpham = sanpham;
        this.khuyenmai = khuyenmai;
        this.tien = tien;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getSanpham() {
        return sanpham;
    }

    public void setSanpham(String sanpham) {
        this.sanpham = sanpham;
    }

    public String getKhuyenmai() {
        return khuyenmai;
    }

    public void setKhuyenmai(String khuyenmai) {
        this.khuyenmai = khuyenmai;
    }

    public Double getTien() {
        return tien;
    }

    public void setTien(Double tien) {
        this.tien = tien;
    }
}
