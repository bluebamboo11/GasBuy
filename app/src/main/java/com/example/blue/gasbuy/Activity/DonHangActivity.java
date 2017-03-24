package com.example.blue.gasbuy.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.blue.gasbuy.Adapter.DonHangAdapter;
import com.example.blue.gasbuy.DonHang;
import com.example.blue.gasbuy.R;

import java.util.ArrayList;

public class DonHangActivity extends AppCompatActivity {

    Button btnDat,btnHuy;
    private ArrayList<DonHang> lstDonHang;
    TextView txtTimer;
    long lStartTime, lPauseTime, lSystemTime = 0L;
    Handler handler = new Handler();
    boolean isRun;
    RelativeLayout donhang_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang);
        btnDat = (Button)findViewById(R.id.btnDat);
        btnHuy = (Button)findViewById(R.id.btnHuy);
        txtTimer = (TextView)findViewById(R.id.txttimer);
        lstDonHang = new ArrayList<DonHang>();
        donhang_layout= (RelativeLayout) findViewById(R.id.activity_donhang);
        donhang_layout.setBackgroundResource(R.drawable.bg_round);
        ListView lview = (ListView) findViewById(R.id.listview);
        DonHangAdapter adapter = new DonHangAdapter(lstDonHang,this);
        lview.setAdapter(adapter);
        Data();
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            lSystemTime = SystemClock.uptimeMillis() - lStartTime;
            long lUpdateTime = lPauseTime + lSystemTime;
            long secs = (long)(lUpdateTime/1000);
            long mins= secs/60;
            secs = secs %60;
            long milliseconds = (long)(lUpdateTime%1000);
            txtTimer.setText(""+mins+":" + String.format("%02d",secs) + ":" + String.format("%03d",milliseconds));
            handler.postDelayed(this,0);
        }
    };
    private void Data() {
        DonHang item1,item2,item3;
        item1 = new DonHang("Gas1",10.2,"Chờ",true);
        lstDonHang.add(item1);
        item2 = new DonHang("Gas2",10.2,"Chờ",false);
        lstDonHang.add(item2);
        item3 = new DonHang("Gas3",10.2,"Chờ",true);
        lstDonHang.add(item3);
    }
    protected void onStart() {
        super.onStart();
        clickDat();
        clickHuy();
    }
    private void clickHuy() {
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isRun)
                    return;
                isRun = false;
                lPauseTime = 0;
                handler.removeCallbacks(runnable);
            }
        });
    }
    private void clickDat() {
        btnDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isRun)
                    return;
                isRun = true;
                lStartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable, 0);
            }
        });
    }
}
