package com.example.blue.gasbuy;

import java.io.Serializable;

/**
 * Created by blue on 25/04/2017.
 */

public class SanPhamFirebase implements Serializable {
    public String id;
    public String anh;
    public String ten;
    public float gia;
    public String thongtin;
    public String thongso;
    public String baohanh;

    public SanPhamFirebase() {
    }

    public SanPhamFirebase(String id, String anh, String ten, float gia, String thongtin, String thongso, String baohanh) {
        this.id = id;
        this.anh = anh;
        this.ten = ten;
        this.gia = gia;
        this.thongtin = thongtin;
        this.thongso = thongso;
        this.baohanh = baohanh;
    }
}
