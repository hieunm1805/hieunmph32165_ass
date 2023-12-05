package com.example.hieunmph32165_ass;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hieunmph32165_ass.DAO.UserDAO;

public class DangKy_Activity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText edtUsername,edtFullname,edtEmail,edtPass1,edtPass2;
    Button btnDangKy,btnBack;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edtUsername = findViewById(R.id.ed_username_regis);
        edtFullname = findViewById(R.id.ed_fullname_regis);
        edtEmail = findViewById(R.id.ed_email_regis);
        edtPass1 = findViewById(R.id.ed_password_regis);
        edtPass2 = findViewById(R.id.ed_rePassword_regis);
        btnDangKy = findViewById(R.id.btn_dangKy);
        btnBack = findViewById(R.id.btn_thoatDangKy);

        userDAO = new UserDAO(this);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), DangNhap_Activity.class);
                startActivity(i);
            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edtUsername.getText().toString().trim();
                String fullname = edtFullname.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String pass = edtPass1.getText().toString().trim();
                String rePass = edtPass2.getText().toString().trim();

                if (username.equals("")) {
                    Toast.makeText(DangKy_Activity.this, "Username không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (fullname.equals("")) {
                    Toast.makeText(DangKy_Activity.this, "Fullname không được để trống!", Toast.LENGTH_SHORT).show();
                }else if (email.equals("")) {
                    Toast.makeText(DangKy_Activity.this, "Email không được để trống!", Toast.LENGTH_SHORT).show();
                }else if (pass.equals("")) {
                    Toast.makeText(DangKy_Activity.this, "Password không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (rePass.equals("")) {
                    Toast.makeText(DangKy_Activity.this, "Password nhập lại không được để trống!", Toast.LENGTH_SHORT).show();
                } else if (!rePass.equals(pass)) {
                    Toast.makeText(DangKy_Activity.this, "Password nhập lại chưa đúng!", Toast.LENGTH_SHORT).show();
                } else {
                    boolean check = userDAO.Sigin(username,pass,email,fullname);
                    if (check){
                        Toast.makeText(DangKy_Activity.this, "Thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DangKy_Activity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}