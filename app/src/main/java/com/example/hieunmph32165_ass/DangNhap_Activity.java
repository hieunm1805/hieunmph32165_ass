package com.example.hieunmph32165_ass;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hieunmph32165_ass.DAO.UserDAO;

public class DangNhap_Activity extends AppCompatActivity {
    EditText ed_username, ed_pass;
    TextView tv_forgotPass;
    Button btn_login, btn_regis;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        ed_username = findViewById(R.id.ed_username_login);
        ed_pass = findViewById(R.id.ed_password_login);
        tv_forgotPass = findViewById(R.id.tv_forgotPass);
        btn_login = findViewById(R.id.btn_login);
        btn_regis = findViewById(R.id.btn_register);

        userDAO = new UserDAO(this);

        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplication(), DangKy_Activity.class);
                startActivity(i);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = ed_username.getText().toString().trim();
                String password = ed_pass.getText().toString().trim();

                boolean check = userDAO.checkLogin(username, password);

                if (check) {
                    if (username.equals("") && password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Không được để trống tài khoản, mật khẩu", Toast.LENGTH_SHORT).show();
                    } else if (username.equals("")) {
                        Toast.makeText(getApplicationContext(), "Không được để trống tài khoản", Toast.LENGTH_SHORT).show();
                    } else if (password.equals("")) {
                        Toast.makeText(getApplicationContext(), "Không được để trống mật khẩu", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent intent = new Intent(DangNhap_Activity.this, ManHinhChinh_Activity.class);
                        startActivity(intent);
                    }
                }else{
                    Toast.makeText(DangNhap_Activity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tv_forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogForgotPass();
            }
        });

    }

    private void dialogForgotPass() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.layout_forgotpass, null);
        builder.setView(v);

        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        EditText ed_sendEmail = alertDialog.findViewById(R.id.ed_sendEmail);
        Button btn_sendEmail = alertDialog.findViewById(R.id.btn_sendEmail);
        Button btn_cancel = alertDialog.findViewById(R.id.btn_cancel);

        btn_sendEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_sendEmail.getText().toString();
                String matkhau = userDAO.fotgotPass(email);

                if (matkhau.equals("")){
                    Toast.makeText(DangNhap_Activity.this, "không tìm thấy tài khoản", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(DangNhap_Activity.this, matkhau, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
    }
}