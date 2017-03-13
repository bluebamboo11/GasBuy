package com.example.blue.gasbuy.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by blue on 27/02/2017.
 */

public class Database {
    public  static final   String DATA_NAME="GasBuyDB.sqlite";
    public  static final   String TAB_SANPHAM="SanPham";
    public  static final   String TAB_DMSANPHAM="DmSanPham";
    // tạo một SQLiteDatabase lấy dữ liệu từ file assets
    public static SQLiteDatabase initDatabase(Context context, String databaseName){
        try {
            String outFileName = context.getApplicationInfo().dataDir + "/databases/" + databaseName;
            File f = new File(outFileName);
            if(!f.exists()) {
                InputStream e = context.getAssets().open(databaseName);
                File folder = new File(context.getApplicationInfo().dataDir + "/databases/");
                if (!folder.exists()) {
                    folder.mkdir();
                }
                FileOutputStream myOutput = new FileOutputStream(outFileName);
                byte[] buffer = new byte[1024];

                int length;
                while ((length = e.read(buffer)) > 0) {
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                e.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
    }
}
