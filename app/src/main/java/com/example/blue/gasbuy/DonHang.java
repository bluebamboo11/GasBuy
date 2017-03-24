package com.example.blue.gasbuy;

/**
 * Created by Thanh Thúy on 3/23/2017.
 */

public class DonHang {
    private String ten;
    private double giaTien;
    private String trangThai;
    private boolean huy;

    public DonHang(String ten, double giaTien, String trangThai, boolean huy) {
        this.ten = ten;
        this.giaTien = giaTien;
        this.trangThai = trangThai;
        this.huy = huy;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public boolean isHuy() {
        return huy;
    }

    public void setHuy(boolean huy) {
        this.huy = huy;
    }
}
