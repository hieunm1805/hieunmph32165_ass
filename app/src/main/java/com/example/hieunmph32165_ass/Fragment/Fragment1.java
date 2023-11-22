package com.example.hieunmph32165_ass.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hieunmph32165_ass.DAO.CongViecDAO;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import com.example.hieunmph32165_ass.Adapter.CongViecAdapter;
import com.example.hieunmph32165_ass.DTO.CongViecDTO;
import com.example.hieunmph32165_ass.R;

public class Fragment1 extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton floatAdd;
    private CongViecDAO congViecDAO;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_listcv, container, false);

        recyclerView = view.findViewById(R.id.rcCV);
        floatAdd = view.findViewById(R.id.addcv);
        congViecDAO = new CongViecDAO(getContext());
        List<CongViecDTO> list = congViecDAO.getAll();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        CongViecAdapter adapter = new CongViecAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.layout_themcv, null);
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                dialog.show();

                EditText edtTencongViec = view1.findViewById(R.id.edtTenCongViec);
                EditText edtNoiDung = view1.findViewById(R.id.edtNoiDung);
                EditText edtTrangThai = view1.findViewById(R.id.edtTrangThai);
                EditText edtBatDau = view1.findViewById(R.id.edtBatDau);
                EditText edtKetThuc = view1.findViewById(R.id.edtKetThuc);
                Button btnThoat = view1.findViewById(R.id.btnHuy);
                Button btnThem = view1.findViewById(R.id.btnThem);

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String tencv = edtTencongViec.getText().toString();
                        String noidung = edtNoiDung.getText().toString();
                        String trangthai = edtTrangThai.getText().toString();
                        String batdau = edtBatDau.getText().toString();
                        String ketthuc = edtKetThuc.getText().toString();

                        CongViecDTO congViecDTO = new CongViecDTO(tencv, noidung, trangthai, batdau, ketthuc);
                        boolean check = congViecDAO.ADDROW(congViecDTO);
                        if (check) {
                            Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                            List<CongViecDTO> list = congViecDAO.getAll();
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            recyclerView.setLayoutManager(linearLayoutManager);
                            CongViecAdapter adapter = new CongViecAdapter(getContext(), list);
                            recyclerView.setAdapter(adapter);
                            dialog.dismiss();
                        } else {
                            Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                btnThoat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
