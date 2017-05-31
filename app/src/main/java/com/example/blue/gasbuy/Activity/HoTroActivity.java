package com.example.blue.gasbuy.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.blue.gasbuy.R;

public class HoTroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ho_tro);
        getSupportActionBar().setTitle("Hỗ Trợ ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:

                onBackPressed();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
