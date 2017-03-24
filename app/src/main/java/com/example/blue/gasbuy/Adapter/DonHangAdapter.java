package com.example.blue.gasbuy.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blue.gasbuy.DonHang;
import com.example.blue.gasbuy.R;

import java.util.ArrayList;

/**
 * Created by Thanh Thúy on 3/23/2017.
 */

public class DonHangAdapter extends BaseAdapter{
    public ArrayList<DonHang> donHangList;
    Activity activity;

    public DonHangAdapter(ArrayList<DonHang> donHangList, Activity activity) {
        this.donHangList = donHangList;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return donHangList.size();
    }

    @Override
    public Object getItem(int position) {
        return donHangList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder {
        TextView ten;
        TextView giatien;
        TextView trangthai;
        ImageView img;
        LinearLayout relativeLayout;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if(itemView==null)
        {
            LayoutInflater inflater = (LayoutInflater)activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            itemView = inflater.inflate(R.layout.list_donhang,null);
            viewHolder.ten=(TextView)itemView.findViewById(R.id.ten);
            viewHolder.trangthai=(TextView)itemView.findViewById(R.id.trangthai);
            viewHolder.giatien=(TextView)itemView.findViewById(R.id.giatien);
            viewHolder.img = (ImageView)itemView.findViewById(R.id.imghuy);
            viewHolder.relativeLayout = (LinearLayout) itemView.findViewById(R.id.relativeLayout1);
            itemView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) itemView.getTag();
        }
        if (position %2 ==0){
            viewHolder.relativeLayout.setBackgroundColor(activity.getResources().getColor(R.color.colorPrimary));
        } else {
            viewHolder.relativeLayout.setBackgroundColor(activity.getResources().getColor(R.color.color_table));
        }
        DonHang model = donHangList.get(position);
        viewHolder.ten.setText(model.getTen().toString());
        viewHolder.trangthai.setText(model.getTrangThai().toString());
        viewHolder.giatien.setText(model.getGiaTien()+"");
        if(model.isHuy())
        {
            viewHolder.img.setImageResource(R.drawable.ic_check_circle);

        }
        else
        {
            viewHolder.img.setVisibility(View.VISIBLE);
        }
        return itemView;
    }
}
