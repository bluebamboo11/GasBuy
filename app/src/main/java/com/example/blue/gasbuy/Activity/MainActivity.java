package com.example.blue.gasbuy.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.blue.gasbuy.Adapter.SanPhamAdapter;
import com.example.blue.gasbuy.Database.Database;
import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private List<SanPham> arrSanpham = new ArrayList<>();
    private ListView listView;
    private TextView txtTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSanPham();
        createDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_main);
        listView = (ListView) findViewById(R.id.listdonhang);
        txtTongTien = (TextView) findViewById(R.id.text_tongTien);
        setControls();

        // truyền tới SanPhamActivity một đối tượng sản phẩm và một biên boolean
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SanPhamActivity.class);
                intent.putExtra("logic", true);
                Bundle bundle = new Bundle();
                bundle.putSerializable("SanPham", arrSanpham.get(position));
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });


    }
    // tạo Drawer cho Activity
    private void createDrawer() {
        toolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(toolbar);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    // gán dữ liệu và sử lý sự kiện
    private void setControls() {
        float tien = 0;
        for (int i = 0; i < arrSanpham.size(); i++) {
            tien = tien + arrSanpham.get(i).getGiaSanPham() * arrSanpham.get(i).getSoLuong();
        }
        int tongtien = (int) tien;

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTongTien.setText(decimalFormat.format(tongtien) + " VND");
        SanPhamAdapter adapter = new SanPhamAdapter(this, R.layout.list_sanpham, arrSanpham, txtTongTien);
        listView.setAdapter(adapter);
        final Button btnKhachHang = (Button) findViewById(R.id.button_khachHang);
        btnKhachHang.setOnClickListener(new ClickActivity(KhachHangActivity.class));
        ImageView imgShop = (ImageView) findViewById(R.id.shop);
        imgShop.setOnClickListener(new ClickActivity(DMSanPhamActivity.class));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == android.R.id.undo) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    // thêm dữ liệu cho sản phẩm
    private void setSanPham() {
        DatabaseManager data = new DatabaseManager(this);
        arrSanpham = data.getAllData(Database.TAB_SANPHAM);
    }
    // tạo class OnclickListener sử lý việc chuyến activity
    private class ClickActivity implements View.OnClickListener {
        Class aClass;

        public ClickActivity(Class aClass) {
            this.aClass = aClass;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, aClass);
            startActivity(intent);
        }
    }
}
