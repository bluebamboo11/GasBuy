package com.example.blue.gasbuy.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.blue.gasbuy.Adapter.DonHangAdapter;
import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.DonHang;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SaveLoadPreferences;

import java.util.ArrayList;

public class DonHangActivity extends AppCompatActivity {


    private ArrayList<DonHang> lstDonHang;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);

        SaveLoadPreferences saveLoadPreferences=new SaveLoadPreferences(this);

        ListView lview = (ListView) findViewById(R.id.listview);
        Long id=getIntent().getLongExtra("id",-1);
        int tongtien=getIntent().getIntExtra("tien",0);
        if(id==-1){
            id=(long)saveLoadPreferences.loadInteger(SaveLoadPreferences.DON_HANG,0);
            tongtien=saveLoadPreferences.loadInteger(SaveLoadPreferences.TONG_TIEN,0);
        }
        DatabaseManager databaseManager=new DatabaseManager(this);
        lstDonHang= databaseManager.selectDonHang(id);

        DonHangAdapter adapter = new DonHangAdapter(lstDonHang,this);
        lview.setAdapter(adapter);
TextView textTongTien=(TextView) findViewById(R.id.text_tongTien);
        textTongTien.setText(tongtien+" VND");
    }



}
