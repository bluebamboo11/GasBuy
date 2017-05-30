package com.example.blue.gasbuy.Activity;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blue.gasbuy.Adapter.SanPhamAdapter;
import com.example.blue.gasbuy.Database.Database;
import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.DonHangFirebase;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;
import com.example.blue.gasbuy.SaveLoadPreferences;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private List<SanPham> arrSanpham = new ArrayList<>();
    private SanPhamAdapter adapter2;
    private TextView txtTongTien;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SaveLoadPreferences saveLoadPreferences = new SaveLoadPreferences(this);
        if (saveLoadPreferences.loadString(SaveLoadPreferences.Ten, "").equals("")) {
            Intent intent = new Intent(this, KhachHangActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        setSanPham();
        createDrawer();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.title_main);
        String ten = saveLoadPreferences.loadString(SaveLoadPreferences.Ten, "");
        TextView textTen=(TextView) findViewById(R.id.text_name);
        TextView textava=(TextView) findViewById(R.id.text_name_ava);
        textTen.setText(ten);
        if(!ten.equals("")){
            String []a=ten.split("");
            textava.setText(a[1]);
        }
        txtTongTien = (TextView) findViewById(R.id.text_tongTien);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL) {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }

            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
            }
        };
        Drawable drawable = ContextCompat.getDrawable(getApplicationContext(), R.drawable.divider_item);
        dividerItemDecoration.setDrawable(drawable);
        recyclerView.addItemDecoration(dividerItemDecoration);
        setControls();

        Button btnDatMua = (Button) findViewById(R.id.button_dat_mua);
        btnDatMua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSanPham();
                sendDonHang();
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
        adapter2 = new SanPhamAdapter(this, R.layout.list_sanpham, arrSanpham, txtTongTien);
        recyclerView.setAdapter(adapter2);

        final Button btnKhachHang = (Button) findViewById(R.id.button_khachHang);
        LinearLayout itemDonhang = (LinearLayout) findViewById(R.id.item_don_hang);
        LinearLayout itemSoGa = (LinearLayout) findViewById(R.id.item_so_ga);
        LinearLayout itemQuaTang = (LinearLayout) findViewById(R.id.item_qua_tang);
        LinearLayout itemHoTro = (LinearLayout) findViewById(R.id.item_ho_tro);
        LinearLayout itemDanhGia = (LinearLayout) findViewById(R.id.item_danh_gia);
        LinearLayout itemCaiDat = (LinearLayout) findViewById(R.id.item_cai_dat);
        LinearLayout itemPhanHoi = (LinearLayout) findViewById(R.id.item_phan_hoi);
        btnKhachHang.setOnClickListener(new ClickActivity(KhachHangActivity.class));
        itemHoTro.setOnClickListener(new ClickActivity(HoTroActivity.class));
        itemPhanHoi.setOnClickListener(new ClickActivity(PhanhoiActivity.class));
        itemSoGa.setOnClickListener(new ClickActivity(SoGasActivity.class));
        itemDonhang.setOnClickListener(new ClickActivity(DonHangActivity.class));
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

    private void sendDonHang() {

        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        StringBuilder sanPhamId = new StringBuilder();
        for (int i = 0; i < arrSanpham.size(); i++) {
            sanPhamId.append(arrSanpham.get(i).getId());
            sanPhamId.append(":").append(arrSanpham.get(i).getSoLuong());
            if (i < arrSanpham.size() - 1) {
                sanPhamId.append(",");
            }
        }
        StringBuilder sanphams = new StringBuilder();
        for (int i = 0; i < arrSanpham.size(); i++) {
            sanphams.append("- ");
            sanphams.append(arrSanpham.get(i).getTenSanPham());
            sanphams.append("(").append(arrSanpham.get(i).getSoLuong()).append(")");
            if (i < arrSanpham.size() - 1) {
                sanphams.append(" \n");
            }
        }
        SaveLoadPreferences saveLoadPreferences = new SaveLoadPreferences(this);

        final DonHangFirebase donHangFirebase = new DonHangFirebase(saveLoadPreferences.loadString(SaveLoadPreferences.Ten, "")
                , adapter2.getTongtien(), sanphams.toString()
                , saveLoadPreferences.loadString(SaveLoadPreferences.DIA_CHi, ""),
                saveLoadPreferences.loadString(SaveLoadPreferences.SDT, ""), sanPhamId.toString(),
                Double.parseDouble(saveLoadPreferences.loadString(SaveLoadPreferences.X, "")),
                Double.parseDouble(saveLoadPreferences.loadString(SaveLoadPreferences.Y, "")));
        String diachi = saveLoadPreferences.loadString(SaveLoadPreferences.DIA_CHI_F, "");
        databaseReference.child(diachi).push().setValue(donHangFirebase);
        DatabaseManager databaseManager = new DatabaseManager(this);
        long id = databaseManager.insertSoGas(donHangFirebase);

        for (SanPham sanPham : arrSanpham) {
            databaseManager.insertDonHang(sanPham, id);
        }
         saveLoadPreferences.saveInteger(SaveLoadPreferences.DON_HANG,(int)id);
        saveLoadPreferences.saveInteger(SaveLoadPreferences.TONG_TIEN,donHangFirebase.tongtien);
Intent intent=new Intent(this, DonHangActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("tien",donHangFirebase.tongtien);
        startActivity(intent);
    }

}
