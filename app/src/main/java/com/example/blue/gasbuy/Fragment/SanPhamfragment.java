package com.example.blue.gasbuy.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.example.blue.gasbuy.SanPhamFirebase;
import com.example.blue.gasbuy.SaveLoadPreferences;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by blue on 23/02/2017.
 */

public class SanPhamfragment extends Fragment {

    private List<SanPham> sanPhams;
    private GridView gridView;
    private ListView listView;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage;
    private DSSanPhamAdapter sanPhamAdapter;
    private String tab;
    private  ChildEventListener childEventListenerData;
    public SanPhamfragment() {

    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        storage = FirebaseStorage.getInstance();
        sanPhams = new ArrayList<>();
        View rootView = inflater.inflate(R.layout.fragment_san_pham, container, false);
        listView = (ListView) rootView.findViewById(R.id.list_dm_SanPham);
        gridView = (GridView) rootView.findViewById(R.id.grid_SanPham);
        SaveLoadPreferences saveLoadPreferences = new SaveLoadPreferences(getContext());
        boolean b = saveLoadPreferences.loadBoolean(SaveLoadPreferences.KEY_VIEW);
//        if (b) {
//            listView.setVisibility(View.GONE);
//            sanPhamAdapter = new DSSanPhamAdapter(getContext(), R.layout.list_ds_san_pham, sanPhams);
//            gridView.setAdapter(sanPhamAdapter);
//            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    Intent intent = new Intent(getContext(), SanPhamActivity.class);
//                    intent.putExtra("sanpham", sanPhams.get(position));
//                    intent.putExtra("logic", false);
//                    startActivity(intent);
//
//                }
//            });
//        } else {
            gridView.setVisibility(View.GONE);
            sanPhamAdapter = new DSSanPhamAdapter(getContext(), R.layout.list_ds_san_pham, sanPhams);
            listView.setAdapter(sanPhamAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getContext(), SanPhamActivity.class);
                    intent.putExtra("logic", false);
                    intent.putExtra("sanpham", sanPhams.get(position));
                    startActivity(intent);
                }
            });
//        }
        loadData();
        return rootView;
    }

    private void loadData() {

         childEventListenerData = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final SanPhamFirebase sanPhamFirebase = dataSnapshot.getValue(SanPhamFirebase.class);
              Log.e("url",sanPhamFirebase.anh);
                StorageReference storageRef = storage.getReferenceFromUrl(sanPhamFirebase.anh);
                storageRef.getBytes(1024 * 1024).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                    @Override
                    public void onSuccess(byte[] bytes) {
                        SanPham sanPham = new SanPham(sanPhamFirebase.id, bytes, sanPhamFirebase.ten, sanPhamFirebase.gia, sanPhamFirebase.thongso, sanPhamFirebase.thongso, sanPhamFirebase.baohanh);
                        sanPhams.add(sanPham);
                        sanPhamAdapter.notifyDataSetChanged();
                    }
                });

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    @Override
    public void onResume() {
        super.onResume();

        databaseReference.child("SanPham").child(tab).addChildEventListener(childEventListenerData);
        databaseReference.child("SanPham").child(tab).keepSynced(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        databaseReference.child("SanPham").child(tab).removeEventListener(childEventListenerData);
    }
}
