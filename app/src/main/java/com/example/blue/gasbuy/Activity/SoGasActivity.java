package com.example.blue.gasbuy.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.blue.gasbuy.Adapter.SoGasAdpater;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SoGas;

import java.util.ArrayList;

public class SoGasActivity extends AppCompatActivity {


    private ArrayList<SoGas> lstSoGas;
    LinearLayout soga_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_gas);
        soga_layout=(LinearLayout)findViewById(R.id.activity_sogas);
        soga_layout.setBackgroundResource(R.drawable.bg_round);
        ListView listView = (ListView)findViewById(R.id.listviewSoGas);
        lstSoGas = new ArrayList<SoGas>();
        SoGasAdpater soGasAdapter = new SoGasAdpater(lstSoGas,SoGasActivity.this);
        listView.setAdapter(soGasAdapter);
        Data();
    }
    private void Data() {
        SoGas item1,item2,item3;
        item1 = new SoGas(1,"1995","Gas1","Chờ",4.5);
//        Toast.makeText(SoGas.this,"ahii"+ item1.getSanpham(),Toast.LENGTH_LONG).show();
        lstSoGas.add(item1);
//        Toast.makeText(SoGas.this,"ahii"+lstSoGas.get(0).getSanpham(),Toast.LENGTH_LONG).show();
        item2 = new SoGas(2,"1","Gas1","Chờ",5.0);
        lstSoGas.add(item2);
        item3 = new SoGas(3,"10","Gas1","Chờ",4.9);
        lstSoGas.add(item3);
    }
}
