package com.example.hieunmph32165_ass.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hieunmph32165_ass.DAO.CongViecDAO;
import com.example.hieunmph32165_ass.DTO.CongViecDTO;
import com.example.hieunmph32165_ass.ManHinhChinh_Activity;
import com.example.hieunmph32165_ass.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;
import java.util.List;

public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.ViewHolder> {
    Context context;
    List<CongViecDTO> list;

    CongViecDAO congViecDAO;
    TextInputEditText edtTenCongViec, edtNoiDung, edtTrangThai, edtBatDau, edtKetThuc;

    public CongViecAdapter(Context context, List<CongViecDTO> list) {
        this.context = context;
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.layout_itemcv, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tencv.setText(list.get(position).getTenCV());
        holder.trangthaicv.setText(list.get(position).getTrangThai());
        holder.ngaybatdau.setText(list.get(position).getNgayBatDau());
        holder.ngayketthuc.setText(list.get(position).getNgayKetThuc());

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Update(holder.getAdapterPosition());
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Delete(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tencv, trangthaicv, ngaybatdau, ngayketthuc;
        ImageButton imgedit, imgdelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tencv = itemView.findViewById(R.id.tencv);
            trangthaicv = itemView.findViewById(R.id.trangthaicv);
            ngaybatdau = itemView.findViewById(R.id.ngaybatdau);
            ngayketthuc = itemView.findViewById(R.id.ngayketthuc);
            imgedit = itemView.findViewById(R.id.btnedit);
            imgdelete = itemView.findViewById(R.id.btndelete);
        }
    }

    public void Update(int viTri) {
        CongViecDTO congViecDTO = list.get(viTri);
        Dialog dialog1 = new Dialog(context);
        dialog1.setContentView(R.layout.layout_suacv);
        edtTenCongViec = dialog1.findViewById(R.id.edtTenCongViec);
        edtNoiDung = dialog1.findViewById(R.id.edtNoiDung);
        edtTrangThai = dialog1.findViewById(R.id.edtTrangThai);
        edtBatDau = dialog1.findViewById(R.id.edtBatDau);
        edtKetThuc = dialog1.findViewById(R.id.edtKetThuc);
        Button btn_Sua = dialog1.findViewById(R.id.btnSua_update);
        Button btn_Huy = dialog1.findViewById(R.id.btnHuy_update);

        edtTenCongViec.setText(congViecDTO.getTenCV());
        edtNoiDung.setText(congViecDTO.getNoiDung());
        edtTrangThai.setText(congViecDTO.getTrangThai());
        edtBatDau.setText(congViecDTO.getNgayBatDau());
        edtKetThuc.setText(congViecDTO.getNgayKetThuc());

        btn_Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //bat dau chinh sua san pham
                congViecDAO = new CongViecDAO(context);
                if (validate() > 0) {
                    congViecDTO.setTenCV(edtTenCongViec.getText().toString());
                    congViecDTO.setNoiDung(edtNoiDung.getText().toString());
                    congViecDTO.setTrangThai(edtTrangThai.getText().toString());
                    congViecDTO.setNgayBatDau(edtBatDau.getText().toString());
                    congViecDTO.setNgayKetThuc(edtKetThuc.getText().toString());

                    if (congViecDAO.Update(congViecDTO) > 0) {
                        getDS();
                        dialog1.dismiss();
                        Toast.makeText(context, "Sua thanh cong", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Sua khong thanh cong", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_Huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.dismiss();
            }
        });

        dialog1.show();
    }

    public void Delete(int viTri) {
        new androidx.appcompat.app.AlertDialog.Builder(context)
                .setTitle("Confirm")
                .setMessage("Ban co muon xoa san pham ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // xoa
                        congViecDAO = new CongViecDAO(context);
                        if (congViecDAO.Delete(String.valueOf(list.get(viTri).getId())) > 0) {
                            getDS();
                            Toast.makeText(context, "Xoa thanh cong", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(context, "Xoa that bai", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void getDS() {
        list.clear();
        list = congViecDAO.getAll();
        notifyDataSetChanged();
    }

    public int validate() {
        int check = 1;
        if (edtTenCongViec.getText().length() == 0 ||
                edtNoiDung.getText().length() == 0 ||
                edtTrangThai.getText().length() == 0 ||
                edtBatDau.getText().length() == 0 ||
                edtKetThuc.getText().length() == 0) {
            Toast.makeText(context, "Khong bo trong", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }
}
