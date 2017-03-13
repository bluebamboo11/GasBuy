package com.example.blue.gasbuy;

import java.io.Serializable;

/**
 * Created by blue on 21/02/2017.
 */

public class SanPham implements Serializable {
    private int id;
    private byte []imgSanPham;
    private String tenSanPham;
    private float giaSanPham;
    private int soLuong;
    private String thong_tin;
    private String thong_so;
    private String bao_hanh;

    public int getId() {
        return id;
    }

    public SanPham(byte []imgSanPham, String tenSanPham, float giaSanPham, int soLuong, String thong_tin, String thong_so, String bao_hanh) {
        this.imgSanPham = imgSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.thong_tin = thong_tin;
        this.thong_so = thong_so;
        this.bao_hanh = bao_hanh;
    }

    public SanPham(int id, byte[] imgSanPham, String tenSanPham, float giaSanPham, int soLuong, String thong_tin, String thong_so, String bao_hanh) {
        this.id = id;
        this.imgSanPham = imgSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.soLuong = soLuong;
        this.thong_tin = thong_tin;
        this.thong_so = thong_so;
        this.bao_hanh = bao_hanh;
    }

    public SanPham(int id, byte[] imgSanPham, String tenSanPham, float giaSanPham, String thong_tin, String thong_so, String bao_hanh) {
        this.id = id;
        this.imgSanPham = imgSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.thong_tin = thong_tin;
        this.thong_so = thong_so;
        this.bao_hanh = bao_hanh;
    }

    public String getThong_tin() {
        return thong_tin;
    }

    public String getThong_so() {
        return thong_so;
    }

    public String getBao_hanh() {
        return bao_hanh;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public byte []getImgSanPham() {
        return imgSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public float getGiaSanPham() {
        return giaSanPham;
    }
}
