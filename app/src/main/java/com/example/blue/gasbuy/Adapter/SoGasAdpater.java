package com.example.blue.gasbuy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SoGas;

import java.util.ArrayList;

/**
 * Created by Thanh Thúy on 3/23/2017.
 */

public class SoGasAdpater extends BaseAdapter {
    private ArrayList<SoGas> lstSoGa;
    Activity activity;


    public SoGasAdpater(ArrayList<SoGas> lstSoGa, Activity activity) {
        this.lstSoGa = lstSoGa;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return lstSoGa.size();
    }

    @Override
    public Object getItem(int position) {
        return lstSoGa.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if(row==null)
        {
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.list_sogas,null);
            viewHolder.stt=(TextView)row.findViewById(R.id.stt);
            viewHolder.ngaysinh=(TextView)row.findViewById(R.id.ngaythang);
            viewHolder.sanpham=(TextView)row.findViewById(R.id.sanpham);
            viewHolder.khuyenmai=(TextView)row.findViewById(R.id.khuyenmai);
            viewHolder.tien=(TextView)row.findViewById(R.id.tien);
            row.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder)row.getTag();
        }
        SoGas soGaModel = lstSoGa.get(position);
        viewHolder.stt.setText(soGaModel.getStt()+"");
        viewHolder.khuyenmai.setText(soGaModel.getKhuyenmai());
        viewHolder.ngaysinh.setText(soGaModel.getNgaySinh());
        viewHolder.sanpham.setText(soGaModel.getSanpham());
        viewHolder.tien.setText(soGaModel.getTien()+"");
        return row;
    }
    public class ViewHolder
    {
        TextView stt;
        TextView ngaysinh;
        TextView sanpham;
        TextView khuyenmai;
        TextView tien;
    }

}
