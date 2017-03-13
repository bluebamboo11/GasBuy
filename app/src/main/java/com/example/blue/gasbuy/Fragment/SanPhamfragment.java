package com.example.blue.gasbuy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;

import com.example.blue.gasbuy.Activity.SanPhamActivity;
import com.example.blue.gasbuy.Adapter.DSSanPhamAdapter;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;
import com.example.blue.gasbuy.SaveLoadPreferences;

import java.util.List;

/**
 * Created by blue on 23/02/2017.
 */

public class SanPhamfragment extends Fragment {
    private static List<SanPham> arrSanpham;

    private GridView gridView;
    private  ListView listView;

    public SanPhamfragment() {
    }

    public void setArrSanpham(List<SanPham> arrSanpham) {
        this.arrSanpham = arrSanpham;
    }



    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_san_pham, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_dm_SanPham);
        gridView = (GridView) rootView.findViewById(R.id.grid_SanPham);
        SaveLoadPreferences saveLoadPreferences=new SaveLoadPreferences(getContext());
        boolean b=saveLoadPreferences.loadBoolean(SaveLoadPreferences.KEY_VIEW);
        if (b) {
            listView.setVisibility(View.GONE);
            DSSanPhamAdapter sanPhamAdapter = new DSSanPhamAdapter(getContext(), R.layout.grid_sanpham, arrSanpham);
                  gridView.setAdapter(sanPhamAdapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), SanPhamActivity.class);
                    int idm = arrSanpham.get(position).getId();
                    intent.putExtra("logic", false);
                    intent.putExtra("key", idm);
                    startActivity(intent);

                }
            });
        } else {
           gridView.setVisibility(View.GONE);
                    DSSanPhamAdapter sanPhamAdapter = new DSSanPhamAdapter(getContext(), R.layout.list_ds_san_pham, arrSanpham);
            listView.setAdapter(sanPhamAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), SanPhamActivity.class);
                    int idm = arrSanpham.get(position).getId();
                    intent.putExtra("logic", false);
                    intent.putExtra("key", idm);
                    startActivity(intent);
                }
            });
        }
        return rootView;
    }


}
