package com.example.blue.gasbuy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.blue.gasbuy.Adapter.SoGasAdpater;
import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SoGas;

import java.util.ArrayList;

public class SoGasActivity extends AppCompatActivity {


    private ArrayList<SoGas> lstSoGas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_so_gas);
        DatabaseManager databaseManager=new DatabaseManager(this);
        lstSoGas=databaseManager.selectSogas();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Sổ Gas");
        ListView listView = (ListView)findViewById(R.id.listviewSoGas);

        SoGasAdpater soGasAdapter = new SoGasAdpater(lstSoGas,SoGasActivity.this);

        listView.setAdapter(soGasAdapter);
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent =new Intent(SoGasActivity.this, DonHangActivity.class);
               intent.putExtra("id",id+1);
               intent.putExtra("tien",lstSoGas.get(position).getTien());
               startActivity(intent);
           }
       });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
