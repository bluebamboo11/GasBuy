package com.example.blue.gasbuy.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;

import java.text.DecimalFormat;

public class SanPhamActivity extends AppCompatActivity {
   private TextView txtTen, txtGia, txtThongTin, txtThongSo, txtBaoHanh;
    private ImageView imgSanPham;
    private int id;
    private Button btnSanPham;
    private SanPham sanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
     btnSanPham = (Button) findViewById(R.id.button_them_san_pham);
        addControls();
        setControls();
        btnSanPham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SanPhamActivity.this, MainActivity.class);
                DatabaseManager data = new DatabaseManager(SanPhamActivity.this);
                data.insert(sanPham);
                // gán cờ cho intent để xóa hết lịch sử  duyệt activity
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    // Gán sự kiện cho nút backhome
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addControls() {

        txtTen = (TextView) findViewById(R.id.txtten);
        txtGia = (TextView) findViewById(R.id.txtgia);
        txtThongSo = (TextView) findViewById(R.id.txtthong_so);
        txtThongTin = (TextView) findViewById(R.id.txtThong_tin);
        txtBaoHanh = (TextView) findViewById(R.id.txtbao_hanh);
        imgSanPham = (ImageView) findViewById(R.id.imgSanpham);
    }
    // gửi dữ liệu lên layout
    private void setControls() {
        Intent intent = getIntent();
        boolean logic = intent.getBooleanExtra("logic", true);
// lấy biến boolean từ intent nếu false thì lấy dữ liệu từ cơ sở dữ liệu thông qua id lấy từ intent DmSanPhamActivity
// nếu true thì lấy đối tượng sản phẩm được gủi từ  mainActivity
        if (!logic) {
            btnSanPham.setVisibility(View.VISIBLE);
          sanPham= (SanPham) getIntent().getSerializableExtra("sanpham");

        } else {
            btnSanPham.setVisibility(View.GONE);
            Bundle bundle = intent.getBundleExtra("bundle");
            sanPham = (SanPham) bundle.getSerializable("SanPham");

        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtGia.setText(decimalFormat.format(sanPham.getGiaSanPham()) + " VND");
        txtTen.setText(sanPham.getTenSanPham());
        txtThongSo.setText(sanPham.getThong_so());
        txtThongTin.setText(sanPham.getThong_tin());
        txtBaoHanh.setText(sanPham.getBao_hanh());
        Bitmap bitmap = BitmapFactory.decodeByteArray(sanPham.getImgSanPham(), 0, sanPham.getImgSanPham().length);
        imgSanPham.setImageBitmap(bitmap);
    }
}
