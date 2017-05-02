package com.example.blue.gasbuy.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.blue.gasbuy.R;

public class PhanhoiActivity extends AppCompatActivity {
    Button btSent_FeedBack;
    EditText txtFeedBack;
    CheckBox ckCL;
    CheckBox ckTD;
    CheckBox ckGH;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phanhoi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        btSent_FeedBack = (Button) findViewById(R.id.btSend_FeedBack);

        txtFeedBack = (EditText) findViewById(R.id.txtFeedBack);
        ckCL = (CheckBox) findViewById(R.id.ckCL);
        ckGH = (CheckBox) findViewById(R.id.ckGH);
        ckTD = (CheckBox) findViewById(R.id.ckTD);

        btSent_FeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ckCL.isChecked() != false || ckGH.isChecked() != false || ckTD.isChecked() != false) {
                    if (txtFeedBack.getText().toString() != null) {
                        Toast.makeText(PhanhoiActivity.this,"Bạn đã gửi thành công",Toast.LENGTH_LONG).show();
                        ckTD.setChecked(false);
                        ckGH.setChecked(false);
                        ckTD.setChecked(false);
                        txtFeedBack.setText(null);
                        // thêm vào csdl tại đây
                    } else {
                        Toast.makeText(PhanhoiActivity.this, " Điền nội dung muốn phản hồi", Toast.LENGTH_LONG).show();
                    }


                } else {
                    Toast.makeText(PhanhoiActivity.this, "Bạn hãy chọn một mục bất kỳ hoặc nội dung muốn phản hồi", Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}
