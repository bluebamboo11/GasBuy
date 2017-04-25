package com.example.blue.gasbuy.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.blue.gasbuy.SanPham;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blue on 27/02/2017.
 */
// Đối tương xử lý các thao tác với cơ sở dữ liệu.
public class DatabaseManager {
    SQLiteDatabase datasource;


    public DatabaseManager(Context context) {
        datasource = Database.initDatabase(context, Database.DATA_NAME);
    }
    // Lấy toàn bộ dữ liệu tù bảng trong CSDL
    public List<SanPham> getAllData(String tab_name) {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = datasource.rawQuery("SELECT * FROM " + tab_name + "", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            SanPham sanPham = getData(cursor);
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }
    // Xóa một đối tượng trong bảng theo ID
    public void Delete(String id) {
        datasource.delete(Database.TAB_SANPHAM, "ID = ?", new String[]{id });
    }
    // Sửa một đối tượng trong bảng theo ID
    public void UpdateSl(String id, int so_luong) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("so_luong", so_luong);
        datasource.update(Database.TAB_SANPHAM, contentValues, "ID = ?", new String[]{id });
    }
    // Lấy dữ liệu  một đối tượng trong bảng theo ID
    public SanPham getData(Cursor cursor) {
        String id = cursor.getString(0);

        String ten = cursor.getString(1);
        float gia = cursor.getFloat(2);
        int soLuong = cursor.getInt(3);
        byte[] anh = cursor.getBlob(4);
        String thongtin = cursor.getString(5);
        String thongso = cursor.getString(6);
        String baohanh = cursor.getString(7);
        SanPham sanPham = new SanPham(id, anh, ten, gia, soLuong, thongtin, thongso, baohanh);
        return sanPham;
    }
    // Lấy toàn bộ dữ liệu tù bảng  DmSanPham trong CSDL
    public List<SanPham> getAllDataDM() {
        List<SanPham> sanPhamList = new ArrayList<>();
        Cursor cursor = datasource.rawQuery("SELECT * FROM " + Database.TAB_DMSANPHAM + "", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String id = cursor.getString(0);
            String ten = cursor.getString(0);
            float gia = cursor.getFloat(1);
            byte[] anh = cursor.getBlob(2);
            String thongtin = cursor.getString(3);
            String thongso = cursor.getString(4);
            String baohanh = cursor.getString(5);
            SanPham sanPham = new SanPham(id, anh, ten, gia, thongtin, thongso, baohanh);
            sanPhamList.add(sanPham);
            cursor.moveToNext();
        }
        return sanPhamList;
    }

    // lay du lieu mot san pham tu CSDL
    public SanPham getDataIteam(String id) {
        Cursor cursor = datasource.rawQuery("SELECT * FROM " + Database.TAB_DMSANPHAM + " WHERE ID = ? ", new String[]{id + ""});
        cursor.moveToFirst();
        String ten = cursor.getString(0);
        float gia = cursor.getFloat(1);
        byte[] anh = cursor.getBlob(2);
        String thongtin = cursor.getString(3);
        String thongso = cursor.getString(4);
        String baohanh = cursor.getString(5);
        SanPham sanPham = new SanPham(id, anh, ten, gia, thongtin, thongso, baohanh);
        return sanPham;
    }
    // Thêm một đối tượng vào bảng
    public void insert(SanPham sanPham) {
        List<SanPham> lsanpham = new ArrayList<>();
        lsanpham = getAllData(Database.TAB_SANPHAM);
        boolean logic=true;
        for (int i = 0; i < lsanpham.size(); i++) {
            if (sanPham.getId().equals(lsanpham.get(i).getId())) {
                logic=false;
                UpdateSl(lsanpham.get(i).getId(), lsanpham.get(i).getSoLuong() + 1);
            }
        }
        if(logic) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",sanPham.getId());
            contentValues.put("ten", sanPham.getTenSanPham());
            contentValues.put("gia", sanPham.getGiaSanPham());
            contentValues.put("so_luong", 1);
            contentValues.put("anh", sanPham.getImgSanPham());
            contentValues.put("thong_tin", sanPham.getThong_tin());
            contentValues.put("thong_so", sanPham.getThong_so());
            contentValues.put("bao_hanh", sanPham.getBao_hanh());
            datasource.insert(Database.TAB_SANPHAM, null, contentValues);
        }
    }
}
