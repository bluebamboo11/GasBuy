package com.example.blue.gasbuy.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blue.gasbuy.PhanHoi;
import com.example.blue.gasbuy.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by youolo on 14/04/2017.
 */

public class PhanhoiAdapter extends ArrayAdapter<PhanHoi> {
    private int ResID;
    private List<PhanHoi> list;
    private Context context;


    public PhanhoiAdapter(@NonNull Context context, @LayoutRes int resource, List object) {
        super(context, resource, object);
        this.context = context;
        this.ResID = resource;
        this.list = object;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        PhanhoiAdapter.ViewHolder viewHolder = new PhanhoiAdapter.ViewHolder();
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(ResID, null);
            viewHolder.content = (EditText) view.findViewById(R.id.txtFeedBack);
            viewHolder.ckCL = (CheckBox) view.findViewById(R.id.ckCL);
            viewHolder.ckGH = (CheckBox) view.findViewById(R.id.ckGH);
            viewHolder.ckTD = (CheckBox) view.findViewById(R.id.ckTD);
            view.setTag(viewHolder);
        } else {
            viewHolder = (PhanhoiAdapter.ViewHolder) view.getTag();
        }
        viewHolder.content.setText(list.get(position).getContent());
        viewHolder.ckTD.setChecked(list.get(position).isCkTD());
        viewHolder.ckCL.setChecked(list.get(position).isCkCL());
        viewHolder.ckGH.setChecked(list.get(position).isCkGH());

        return view;
    }


    private class ViewHolder {
        EditText content;
        CheckBox ckGH;
        CheckBox ckTD;
        CheckBox ckCL;

    }

}
