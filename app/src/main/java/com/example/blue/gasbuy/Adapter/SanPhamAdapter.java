package com.example.blue.gasbuy.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.blue.gasbuy.Activity.SanPhamActivity;
import com.example.blue.gasbuy.Database.DatabaseManager;
import com.example.blue.gasbuy.R;
import com.example.blue.gasbuy.SanPham;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by blue on 14/03/2017.
 */

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    private Context context;
    private int idLayout;
    private List<SanPham> arrSanpham;
    private TextView textTongTien, txtCong, txtTru;
    private ImageView imgDelete;
private  int tongtien;

    public SanPhamAdapter(Context context, int idLayout, List<SanPham> arrSanpham, TextView textTongTien) {
        this.context = context;
        this.idLayout = idLayout;
        this.arrSanpham = arrSanpham;
        this.textTongTien = textTongTien;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(idLayout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // tao 1 divide duoi cung
        if (position == arrSanpham.size()) {
            holder.view.setVisibility(View.VISIBLE);
            holder.cardView.setVisibility(View.GONE);

        } else {

            holder.txtSoLuong.setText(Integer.toString(arrSanpham.get(position).getSoLuong()));
            holder.txtTen.setText(arrSanpham.get(position).getTenSanPham());
            int a = (int) arrSanpham.get(position).getGiaSanPham();
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            Bitmap img = BitmapFactory.decodeByteArray(arrSanpham.get(position).getImgSanPham(), 0, arrSanpham.get(position).getImgSanPham().length);
            holder.txtGia.setText(decimalFormat.format(a) + " " + "VND");
            holder.imgSanPham.setImageBitmap(img);

        }
    }

    @Override
    public int getItemCount() {
        return (arrSanpham.size() + 1);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSanPham;
        TextView txtTen;
        TextView txtGia;
        TextView txtSoLuong;
        View view;
        CardView cardView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imgSanPham = (ImageView) itemView.findViewById(R.id.imgSanpham);
            txtGia = (TextView) itemView.findViewById(R.id.textGia);
            txtTen = (TextView) itemView.findViewById(R.id.textTen);
            txtSoLuong = (TextView) itemView.findViewById(R.id.text_so_luong);
            txtCong = (TextView) itemView.findViewById(R.id.text_cong);
            txtTru = (TextView) itemView.findViewById(R.id.text_Tru);
            imgDelete = (ImageView) itemView.findViewById(R.id.imgDelete);
            cardView = (CardView) itemView.findViewById(R.id.card);
            view = itemView.findViewById(R.id.botdivide);
            addEvent();

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // dieu kien de khong loi khi kich vao divide
                    if (getAdapterPosition() < arrSanpham.size()-1) {
                        Intent intent = new Intent(context, SanPhamActivity.class);
                        intent.putExtra("logic", true);
                        intent.putExtra("sanpham", arrSanpham.get(getAdapterPosition()));
                        context.startActivity(intent);
                    }
                }
            });
        }

        private void addEvent() {
            txtCong.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    arrSanpham.get(position).setSoLuong(arrSanpham.get(position).getSoLuong() + 1);
                    DatabaseManager databaseManager = new DatabaseManager(context);
                    databaseManager.UpdateSl(arrSanpham.get(position).getId(), arrSanpham.get(position).getSoLuong());
                    txtSoLuong.setText(arrSanpham.get(position).getSoLuong() + "");
                    setTongTien();
                }
            });

            txtTru.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (arrSanpham.get(position).getSoLuong() > 1) {
                        arrSanpham.get(position).setSoLuong(arrSanpham.get(position).getSoLuong() - 1);
                        DatabaseManager databaseManager = new DatabaseManager(context);
                        databaseManager.UpdateSl(arrSanpham.get(position).getId(), arrSanpham.get(position).getSoLuong());
                        txtSoLuong.setText(arrSanpham.get(position).getSoLuong() + "");
                        setTongTien();
                    }
                }
            });
            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openDialog(arrSanpham.get(getAdapterPosition()).getTenSanPham(), getAdapterPosition());
                }
            });
        }
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
                DatabaseManager databaseManager = new DatabaseManager(context);
                databaseManager.Delete(arrSanpham.get(stt).getId());
                arrSanpham.remove(stt);
                notifyItemRemoved(stt);
                setTongTien();

            }
        });
        AlertDialog alertDialog = aBuilder.create();
        alertDialog.show();
    }

    private void setTongTien() {
        float tien = 0;
        for (int i = 0; i < arrSanpham.size(); i++) {
            tien = tien + arrSanpham.get(i).getGiaSanPham() * arrSanpham.get(i).getSoLuong();
        }
         tongtien = (int) tien;

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        textTongTien.setText(decimalFormat.format(tongtien) + " VND");

    }

    public int getTongtien() {
        float tien = 0;
        for (int i = 0; i < arrSanpham.size(); i++) {
            tien = tien + arrSanpham.get(i).getGiaSanPham() * arrSanpham.get(i).getSoLuong();
        }
        tongtien = (int) tien;
        return tongtien;
    }
}
