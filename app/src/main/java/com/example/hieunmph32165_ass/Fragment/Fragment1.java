package com.example.hieunmph32165_ass.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
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

import java.util.ArrayList;
import java.util.List;

import com.example.hieunmph32165_ass.Adapter.CongViecAdapter;
import com.example.hieunmph32165_ass.DTO.CongViecDTO;
import com.example.hieunmph32165_ass.R;

public class Fragment1 extends Fragment {
    private RecyclerView recyclerView;
    private FloatingActionButton floatAdd;
    CongViecDAO congViecDAO;

    List<CongViecDTO> list;
    CongViecAdapter congViecAdapter;
    EditText edtTencongViec, edtNoiDung, edtTrangThai, edtBatDau, edtKetThuc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.layout_listcv, container, false);
        recyclerView = view.findViewById(R.id.rcCV);
        floatAdd = view.findViewById(R.id.addcv);
        list = new ArrayList<>();
        congViecDAO = new CongViecDAO(getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        list = congViecDAO.getAll();
        congViecAdapter = new CongViecAdapter(getContext(), list);
        recyclerView.setAdapter(congViecAdapter);

        floatAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CongViecDTO congViecDTO = new CongViecDTO();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                LayoutInflater inflater1 = getLayoutInflater();
                View view1 = inflater1.inflate(R.layout.layout_themcv, null);
                builder.setView(view1);
                AlertDialog dialog = builder.create();
                dialog.show();

                edtTencongViec = view1.findViewById(R.id.edtTenCongViec);
                edtNoiDung = view1.findViewById(R.id.edtNoiDung);
                edtTrangThai = view1.findViewById(R.id.edtTrangThai);
                edtBatDau = view1.findViewById(R.id.edtBatDau);
                edtKetThuc = view1.findViewById(R.id.edtKetThuc);
                Button btnThoat = view1.findViewById(R.id.btnHuy);
                Button btnThem = view1.findViewById(R.id.btnThem);

                btnThem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validate()>0){
                            congViecDTO.setTenCV(edtTencongViec.getText().toString());
                            congViecDTO.setNoiDung(edtNoiDung.getText().toString());
                            congViecDTO.setTrangThai(edtTrangThai.getText().toString());
                            congViecDTO.setNgayBatDau(edtBatDau.getText().toString());
                            congViecDTO.setNgayKetThuc(edtKetThuc.getText().toString());
                            if (congViecDAO.ADDROW(congViecDTO)>0){
                                Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                                congViecAdapter.notifyDataSetChanged();
                                capNhat();
                                dialog.dismiss();
                            }else {
                                Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
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

    public int validate() {
        int check = 1;
        if (edtTencongViec.getText().length() == 0 ||
                edtNoiDung.getText().length() == 0 ||
                edtTrangThai.getText().length() == 0 ||
                edtBatDau.getText().length() == 0 ||
                edtKetThuc.getText().length() == 0) {
            Toast.makeText(getContext(), "Khong bo trong", Toast.LENGTH_SHORT).show();
            check = -1;
        }
        return check;
    }

    public void capNhat(){
        list = congViecDAO.getAll();
        congViecAdapter = new CongViecAdapter(getContext(), list);
        recyclerView.setAdapter(congViecAdapter);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
