package com.example.hieunmph32165_ass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DangNhap_Activity extends AppCompatActivity {
    EditText ed_username, ed_pass;
    Button btn_login, btn_regis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        ed_username = findViewById(R.id.ed_username_login);
        ed_pass = findViewById(R.id.ed_password_login);
        btn_login = findViewById(R.id.btn_login);
        btn_regis = findViewById(R.id.btn_register);

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
            }
        });

    }
}