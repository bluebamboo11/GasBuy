package com.example.blue.gasbuy.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by blue on 23/02/2017.
 */

public class DSSanPhamAdapter extends ArrayAdapter {
    private Context context;
    private int idLayout;
    private List<SanPham> arrSanpham;



    public DSSanPhamAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.idLayout = resource;
        arrSanpham = objects;

    }


    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(idLayout, null);
            viewHolder.imgSanPham = (ImageView) view.findViewById(R.id.imgSanpham);
            viewHolder.txtGia = (TextView) view.findViewById(R.id.textGia);
            viewHolder.txtTen = (TextView) view.findViewById(R.id.textTen);
            viewHolder.txtSoLuong = (TextView) view.findViewById(R.id.text_so_luong);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        if (viewHolder.txtSoLuong != null) {
            viewHolder.txtSoLuong.setText(Integer.toString(arrSanpham.get(position).getSoLuong()));
        }
        viewHolder.txtTen.setText(arrSanpham.get(position).getTenSanPham());
        int gia = (int) arrSanpham.get(position).getGiaSanPham();

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtGia.setText(decimalFormat.format(gia) + " VND");
        Bitmap bitmap= BitmapFactory.decodeByteArray(arrSanpham.get(position).getImgSanPham(),0,arrSanpham.get(position).getImgSanPham().length);

        viewHolder.imgSanPham.setImageBitmap(bitmap);

        return view;
    }

    private class ViewHolder {
        ImageView imgSanPham;
        TextView txtTen;
        TextView txtGia;
        TextView txtSoLuong;
    }

}
