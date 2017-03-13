package com.example.blue.gasbuy.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blue.gasbuy.Database.Database;
import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by blue on 21/02/2017.
 */

public class SanPhamAdapter extends ArrayAdapter {
    private Context context;
    private int idLayout;
    private List<SanPham> arrSanpham;
    private TextView textTongTien, txtCong, txtTru;
    private ImageView imgDelete;

    public SanPhamAdapter(Context context, int resource, List objects, TextView tongTien) {
        super(context, resource, objects);
        this.context = context;
        this.idLayout = resource;
        arrSanpham = objects;
        this.textTongTien = tongTien;
    }

    public SanPhamAdapter(Context context, int resource, List objects) {
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
        int a = (int) arrSanpham.get(position).getGiaSanPham();
        String[] arrgia = Integer.toString(a).split("");
        String gia = arrgia[1];
        for (int i = 2; i < arrgia.length; i++) {
            if ((arrgia.length - i) % 3 == 0) {
                gia = gia + ".";
            }
            gia = gia + arrgia[i];
        }

        Bitmap img = BitmapFactory.decodeByteArray(arrSanpham.get(position).getImgSanPham(), 0, arrSanpham.get(position).getImgSanPham().length);
        viewHolder.txtGia.setText((gia) + " " + "VND");
        viewHolder.imgSanPham.setImageBitmap(img);
        txtCong = (TextView) view.findViewById(R.id.text_cong);
        txtTru = (TextView) view.findViewById(R.id.text_Tru);
        imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
        addEvent(position);

        return view;
    }

    private class ViewHolder {
        ImageView imgSanPham;
        TextView txtTen;
        TextView txtGia;
        TextView txtSoLuong;
    }
    // sử lý sự kiện khi click
    private void addEvent(final int position) {
        txtCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrSanpham.get(position).setSoLuong(arrSanpham.get(position).getSoLuong() + 1);
                DatabaseManager databaseManager = new DatabaseManager(getContext());
                databaseManager.UpdateSl(arrSanpham.get(position).getId(), arrSanpham.get(position).getSoLuong());
                notifyDataSetChanged();
            }
        });

        txtTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrSanpham.get(position).getSoLuong() > 1) {
                    arrSanpham.get(position).setSoLuong(arrSanpham.get(position).getSoLuong() - 1);
                    DatabaseManager databaseManager = new DatabaseManager(getContext());
                    databaseManager.UpdateSl(arrSanpham.get(position).getId(), arrSanpham.get(position).getSoLuong());
                    notifyDataSetChanged();
                }
            }
        });
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(arrSanpham.get(position).getTenSanPham(), position);
            }
        });
    }
    // tạo một dialog thông báo tới người dùng
    private void openDialog(String name, final int stt) {
        AlertDialog.Builder aBuilder = new AlertDialog.Builder(context);
        aBuilder.setTitle(R.string.xoa_san_Pham);
        aBuilder.setMessage(context.getString(R.string.xoa_1) + " " + name + " " + context.getString(R.string.xoa_2));
        aBuilder.setNegativeButton(R.string.Huy, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        aBuilder.setPositiveButton(R.string.xoa, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DatabaseManager databaseManager = new DatabaseManager(getContext());
                databaseManager.Delete(arrSanpham.get(stt).getId());
                arrSanpham = databaseManager.getAllData(Database.TAB_SANPHAM);
                notifyDataSetChanged();

            }
        });
        AlertDialog alertDialog = aBuilder.create();
        alertDialog.show();
    }
    // ghi đề phương thức notifyDataSetChanged() mục đích khi gọi sẽ tự cập nhật text tổng tiền
    @Override
    public void notifyDataSetChanged() {
        float tien = 0;
        for (int i = 0; i < arrSanpham.size(); i++) {
            tien = tien + arrSanpham.get(i).getGiaSanPham() * arrSanpham.get(i).getSoLuong();
        }
        int tongtien = (int) tien;

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textTongTien.setText(decimalFormat.format(tongtien) + " VND");
        super.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return arrSanpham.size();
    }
}
