package com.example.blue.gasbuy;

/**
 * Created by blue on 25/04/2017.
 */

public class DonHangFirebase {
    public String ten;
    public int tongtien;
    public String sanpham;
    public String diachi;
    public String sdt;
    public String idSanPham;
    public double x;
    public double y;

    public DonHangFirebase() {
    }

    public DonHangFirebase(String ten, int tongtien, String sanpham, String diachi, String sdt, String idSanPham, double x, double y) {
        this.ten = ten;
        this.tongtien = tongtien;
        this.sanpham = sanpham;
        this.diachi = diachi;
        this.sdt = sdt;
        this.idSanPham = idSanPham;
        this.x = x;
        this.y = y;
    }

    public DonHangFirebase(String ten, int tongtien, String sanpham, String diachi, String sdt) {
        this.ten = ten;
        this.tongtien = tongtien;
        this.sanpham = sanpham;
        this.diachi = diachi;
        this.sdt = sdt;
    }
}
