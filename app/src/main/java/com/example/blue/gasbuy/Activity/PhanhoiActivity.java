package com.example.blue.gasbuy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.blue.gasbuy.R;

public class PhanhoiActivity extends AppCompatActivity {
    Button btSent_FeedBack;
    EditText txtFeedBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanhoi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        btSent_FeedBack = (Button) findViewById(R.id.btSend_FeedBack);

        txtFeedBack = (EditText) findViewById(R.id.txtFeedBack);
        btSent_FeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        //Xu ly Button

    }
}
