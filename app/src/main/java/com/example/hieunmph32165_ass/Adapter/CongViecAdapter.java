package com.example.hieunmph32165_ass.Adapter;

import android.app.Activity;
import android.app.AlertDialog;
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

import java.util.List;

public class CongViecAdapter extends RecyclerView.Adapter<CongViecAdapter.ViewHolder>{
    Context context;
    List<CongViecDTO> list;

    public CongViecAdapter(Context context, List<CongViecDTO> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((ManHinhChinh_Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_itemcv, parent, false);
        ViewHolder objView = new ViewHolder(v);
        return objView;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tencv.setText("Tên CV : " + list.get(position).getTenCV());
        holder.trangthaicv.setText("Trạng thái : " + list.get(position).getTrangThai());
        holder.ngaybatdau.setText(list.get(position).getNgayBatDau());
        holder.ngayketthuc.setText(list.get(position).getNgayKetThuc());

        holder.imgedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CongViecDTO dto = list.get(holder.getAdapterPosition());
                Update(dto);
            }
        });
        holder.imgdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CongViecDTO dto = list.get(holder.getAdapterPosition());
                Delete(dto);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
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
    void Update(CongViecDTO objcv) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_suacv, null);
        builder.setView(v);
        builder.setCancelable(false);
        AlertDialog dialog = builder.create();

        TextInputEditText edtTenSua = v.findViewById(R.id.edtTenCongViec);
        TextInputEditText edtNDSua = v.findViewById(R.id.edtNoiDung);
        TextInputEditText edtTTSua = v.findViewById(R.id.edtTrangThai);
        TextInputEditText edtBatdau = v.findViewById(R.id.edtBatDau);
        TextInputEditText edtKetthuc = v.findViewById(R.id.edtKetThuc);
        Button btnThoat = v.findViewById(R.id.btnHuy);
        Button btnSua = v.findViewById(R.id.btnSua);

        edtTenSua.setText(objcv.getTenCV());
        edtNDSua.setText(objcv.getNoiDung());
        edtTTSua.setText(objcv.getTrangThai());
        edtBatdau.setText(objcv.getNgayBatDau());
        edtKetthuc.setText(objcv.getNgayKetThuc());
        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtTenSua.getText().toString();
                String content = edtNDSua.getText().toString();
                String status = edtTTSua.getText().toString();
                String start = edtBatdau.getText().toString();
                String end = edtKetthuc.getText().toString();
                if (name.isEmpty() || content.isEmpty() || start.isEmpty() || end.isEmpty() || status.isEmpty()) {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                CongViecDAO congViecDAO1 = new CongViecDAO(context);
                CongViecDTO congViecDTO = new CongViecDTO();
                congViecDTO.setId(objcv.getId());
                congViecDTO.setTenCV(name);
                congViecDTO.setNoiDung(content);
                congViecDTO.setTrangThai(status);
                congViecDTO.setNgayBatDau(start);
                congViecDTO.setNgayKetThuc(end);
                int kq = congViecDAO1.Update(congViecDTO);
                if (kq > 0) {
                    list.clear();
                    list.addAll(congViecDAO1.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                } else {
                    Toast.makeText(context, "Không update được", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();

    }
    public void Delete(CongViecDTO objcv) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Thông báo");
        builder.setIcon(android.R.drawable.ic_delete);
        builder.setMessage("Bạn có muốn xóa hay Không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CongViecDAO congViecDAO1 = new CongViecDAO(context);
                int kq = congViecDAO1.Delete(objcv);
                if (kq > 0) {
                    list.clear();
                    list.addAll(congViecDAO1.getAll());
                    notifyDataSetChanged();
                    Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();

                } else {
                    Toast.makeText(context, "Không xóa được", Toast.LENGTH_SHORT).show();
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
