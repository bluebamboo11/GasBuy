package com.example.blue.gasbuy.Activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SaveLoadPreferences;

public class KhachHangActivity extends AppCompatActivity {
    private LocationManager locationManager;
    private LocationListener listener;
    private ProgressDialog progressDialog;
    private Button buttonDk;
    private EditText editTextTen;
    private EditText editTextDiaChi;
    private EditText editTextSDT;
    private TextView textViewAvatar;
    private Spinner spinnerTinh;
    private Spinner spinnerQuan;
    private Spinner spinnerPhuong;


    private SaveLoadPreferences saveLoadPreferences;
    ArrayAdapter<CharSequence> arrayAdapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_khach_hang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setElevation(0f);

        saveLoadPreferences = new SaveLoadPreferences(this);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        buttonDk = (Button) findViewById(R.id.buton_dangky);

        progressDialog = new ProgressDialog(this);
        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                saveLoadPreferences.saveString(SaveLoadPreferences.Y, String.valueOf(location.getLongitude()));
                saveLoadPreferences.saveString(SaveLoadPreferences.X, String.valueOf(location.getLatitude()));
                Intent intent = new Intent(KhachHangActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                locationManager.removeUpdates(listener);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        editTextTen = (EditText) findViewById(R.id.text_ten);
        editTextDiaChi = (EditText) findViewById(R.id.text_diachi);
        editTextSDT = (EditText) findViewById(R.id.text_sdt);
        textViewAvatar = (TextView) findViewById(R.id.text_avatar);
        spinnerTinh=(Spinner) findViewById(R.id.spinner_tinh);
        spinnerQuan=(Spinner) findViewById(R.id.spinner_quan);
        spinnerPhuong=(Spinner) findViewById(R.id.spinner_phuong);

        loadData();
        configure_button();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 10:
                configure_button();
                break;
            default:
                break;
        }
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

    void configure_button() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                        , 10);
            }
            return;
        }
        buttonDk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(KhachHangActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(KhachHangActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.INTERNET}
                                , 10);
                    }
                    return;
                }
                String ten = editTextTen.getText().toString();
                String sdt = editTextSDT.getText().toString();
                String diachi = editTextDiaChi.getText().toString();
                String tinh=spinnerTinh.getSelectedItem().toString();
                String quan=spinnerQuan.getSelectedItem().toString();
                String phuong=spinnerPhuong.getSelectedItem().toString();
                if (ten.equals("") || sdt.equals("") || diachi.equals("")) {
                    Toast.makeText(KhachHangActivity.this, "Moi nhap day du thong tin", Toast.LENGTH_LONG).show();
                } else {
                    progressDialog.show();
                    saveLoadPreferences.saveString(SaveLoadPreferences.DIA_CHi, diachi);
                    saveLoadPreferences.saveString(SaveLoadPreferences.SDT, sdt);
                    saveLoadPreferences.saveString(SaveLoadPreferences.Ten, ten);
                    saveLoadPreferences.saveString(SaveLoadPreferences.DIA_CHI_F,tinh+"/"+quan+"/"+phuong);

                    if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
                        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, listener);
                    }else {
                        Log.e("null","chua cap quyen");
                    }
                }

            }
        });
    }

    private void loadData() {
        String ten = saveLoadPreferences.loadString(SaveLoadPreferences.Ten, "");
        String sdt = saveLoadPreferences.loadString(SaveLoadPreferences.DIA_CHi, "");
        String diachi = saveLoadPreferences.loadString(SaveLoadPreferences.SDT, "");
        editTextDiaChi.setText(diachi);
        editTextSDT.setText(sdt);
        editTextTen.setText(ten);

        TextView textName=(TextView) findViewById(R.id.text_name);
spinnerQuan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position){
            case 0:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.badinh,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
            case 1:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.bac_tu_liem,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
            case 2:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.cau_giay,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
            case 3:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.dong_da,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
            case 4:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.ha_dong,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
            case 5:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.hai_ba_trung,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
            case 6:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.hoan_kiem,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
            case 7:arrayAdapter=ArrayAdapter.createFromResource(KhachHangActivity.this, R.array.hoang_mai,android.R.layout.simple_spinner_item);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerPhuong.setAdapter(arrayAdapter);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
});
        textName.setText(ten);
        if(!ten.equals("")){
            String []a=ten.split("");
            textViewAvatar.setText(a[1]);
        }

    }
}
