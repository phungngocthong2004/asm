package com.example.asm_ph32356;

import static android.app.PendingIntent.getActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import Adapter.CongViec_Adapter;
import DAO.CongViec_DAO;
import DTO.CongViec_DTO;
import Frament.FragmnetCaidat;
import Frament.fragment_qlcv;
import Frament.fragmnet_giothieu;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static  final  int FRAMENT_CONGVIEC=0;
    private static  final  int FRAMENT_GIOITHieu=1;
    private static final  int FRAMENT_SETTING=2;

    //khi mo hien luon fragment cong viec
    private int mCruentFragment=FRAMENT_CONGVIEC;
    private DrawerLayout mDrawerLayout;
    BottomNavigationView bottomNavigationView;
    fragment_qlcv fraQlcv;
    FragmnetCaidat caidat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.tooobarr);
        bottomNavigationView=findViewById(R.id.bottomtab);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản Lý Công Việc");

       fraQlcv=new fragment_qlcv();
        caidat=new FragmnetCaidat();
        //hien navicationview nút 3 gach

        mDrawerLayout=findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.navi_open,R.string.navi_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();



        //xu ly su kien khi kich vaof item cuar navicationview
        NavigationView navigationView=findViewById(R.id.navication_view);
        navigationView.setNavigationItemSelectedListener(this);


        //khi mo appp vaof luon frament cv
        replaceFragment(new fragment_qlcv());
        //caapj nhap trnag thai cho itemmenu
        navigationView.getMenu().findItem(R.id.nav_qlsp).setChecked(true);


       


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id=item.getItemId();
                if (id==R.id.nav_qlsp){
                    if (mCruentFragment!=FRAMENT_CONGVIEC){
                        replaceFragment(new fragment_qlcv());
                        mCruentFragment=FRAMENT_CONGVIEC;
                        getSupportActionBar().setTitle("Quản Lý Công Việc");
                    }
                } else if (id==R.id.nav_gioithieu) {
                    if (mCruentFragment!=FRAMENT_GIOITHieu){
                        replaceFragment(new fragmnet_giothieu());
                        mCruentFragment=FRAMENT_GIOITHieu;
                        getSupportActionBar().setTitle("Giới Thiệu Bản Thân");
                    }
                }else{
                    getSupportFragmentManager().beginTransaction().replace(R.id.content_fraem,fraQlcv).commit();
                    getSupportActionBar().setTitle("Quản Lý Công Việc");
                }

                mDrawerLayout.closeDrawer(GravityCompat.START);
                return true;

            }
        });

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==R.id.nav_qlsp){
            if (mCruentFragment!=FRAMENT_CONGVIEC){
                replaceFragment(new fragment_qlcv());
                mCruentFragment=FRAMENT_CONGVIEC;
                getSupportActionBar().setTitle("Quản Lý Công Việc");
            }
        }else if(id==R.id.nav_gioithieu){
            if (mCruentFragment!=FRAMENT_GIOITHieu){
                replaceFragment(new fragmnet_giothieu());
                mCruentFragment=FRAMENT_GIOITHieu;
                getSupportActionBar().setTitle("Giới Thiệu Bản Thân");
            }

        }else if(id==R.id.nav_seting){
            if (mCruentFragment!=FRAMENT_SETTING){
                replaceFragment(new FragmnetCaidat());
                mCruentFragment=FRAMENT_SETTING;
                getSupportActionBar().setTitle("Setting");
            }
        }else if(id==R.id.nav_dang_xuat){
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            builder.setTitle("Đăng xuất");
            builder.setMessage("Bạn Có Muốn Đăng Xuất không");
            builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                 startActivity(new Intent(MainActivity.this, Login.class));
                }
            });
            builder.setNegativeButton("không",null);
            builder.show();
        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    //xu ly su kien khi nguoi dung an vao nut back ma navication vaan mo thif dong navication con neu khong mo thucj hienj thoat appp
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content_fraem,fragment);
        fragmentTransaction.commit();
    }




}