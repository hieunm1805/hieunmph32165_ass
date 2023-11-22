package com.example.hieunmph32165_ass;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.hieunmph32165_ass.Fragment.Fragment1;
import com.example.hieunmph32165_ass.Fragment.Fragment2;
import com.example.hieunmph32165_ass.Fragment.Fragment3;
import com.google.android.material.navigation.NavigationView;

public class ManHinhChinh_Activity extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_man_hinh_chinh);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.Toolbar);
        navigationView = findViewById(R.id.navitation);
        //setup toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        // set fragment mặc định
        Fragment1 frag1 = new Fragment1();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_contaier1, frag1)
                .commit();

        // nhấn item của navitation
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment1 frag1 = new Fragment1();
                Fragment2 frag2 = new Fragment2();
                Fragment3 frag3 = new Fragment3();
                if (item.getItemId() == R.id.QLCV) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frag_contaier1, frag1)
                            .commit();
                    getSupportActionBar().setTitle(item.getTitle());
                } else if (item.getItemId() == R.id.GT) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frag_contaier1, frag2)
                            .commit();
                    getSupportActionBar().setTitle(item.getTitle());
                } else if (item.getItemId() == R.id.CaiDat) {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.frag_contaier1, frag3)
                            .commit();
                    getSupportActionBar().setTitle(item.getTitle());
                }else if (item.getItemId() == R.id.DangXuat) {
                    startActivity(new Intent(ManHinhChinh_Activity.this, DangNhap_Activity.class));
                    finish();
                }
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}
