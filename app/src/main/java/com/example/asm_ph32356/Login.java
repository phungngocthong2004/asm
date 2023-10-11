package com.example.asm_ph32356;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import DAO.TaiKhoan_DAO;

public class Login extends AppCompatActivity {

    EditText edusername,eduserpass;
    Button btnDangnhap;
    TextView txtfogotpass,txtsigup;
    private TaiKhoan_DAO taiKhoan_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edusername=findViewById(R.id.ed_username);
        eduserpass=findViewById(R.id.ed_passrord);
        btnDangnhap=findViewById(R.id.btnDn);
        txtfogotpass=findViewById(R.id.tv_forgotpass);
        txtsigup=findViewById(R.id.tv_singpup);

        taiKhoan_dao=new TaiKhoan_DAO(Login.this);
        edusername.setText("phungngocthong2004");
        eduserpass.setText("thong2004");
        btnDangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //laays dux lieeu  nguou dung nhap vaof
                String user = edusername.getText().toString();
                String pass = eduserpass.getText().toString();

                boolean check=taiKhoan_dao.Checklogin(user,pass);
                //if nguoi dung nhap ddungs thoong baos thanh cong vaf chuyeen sang mainactivity
                if (check){
                    Toast.makeText(Login.this, "Đăng nhập Thành  công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this, MainActivity.class));
                }else{
                    //neeu ngươì dùng nhập không khớp với dữ liêu Trong database thì thông báo
                    Toast.makeText(Login.this, "Đăng Nhập Thất bại.Vui Lòng Kiểm  Tra lại Tài Khoản Và Mật Khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });
        txtsigup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Dangky.class));
            }
        });
        txtfogotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, forgotpass_atyvity.class));
            }
        });
    }
}