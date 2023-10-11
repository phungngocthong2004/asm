package com.example.asm_ph32356;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DAO.TaiKhoan_DAO;

public class Dangky extends AppCompatActivity {

    EditText ed_email,ed_name,ed_pass ,ed_nhaplaipass,ed_fullname;
    Button btndangky,btntrolai;
    TaiKhoan_DAO taiKhoan_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        ed_email=findViewById(R.id.ed_email);
        ed_fullname=findViewById(R.id.ed_fullname);
        ed_name=findViewById(R.id.ed_nguoidung);
        ed_pass=findViewById(R.id.ed_matkhau);
        ed_nhaplaipass=findViewById(R.id.ed_nhaplaipass);

        taiKhoan_dao=new TaiKhoan_DAO(this);
        btndangky=findViewById(R.id.btnDk);
        btntrolai=findViewById(R.id.btntrolai);
        btndangky.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=ed_name.getText().toString();
                String email=ed_email.getText().toString();
                String passs=ed_pass.getText().toString();
                String nhaplai=ed_nhaplaipass.getText().toString();
                String fullname=ed_fullname.getText().toString();

                if (username.isEmpty()|email.isEmpty()|passs.isEmpty()|nhaplai.isEmpty()|fullname.isEmpty()){
                    Toast.makeText(Dangky.this, "Bạn cần Nhập đầy đủ dữ liệu", Toast.LENGTH_SHORT).show();
                } else if (!passs.equals(nhaplai)) {
                    Toast.makeText(Dangky.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), " email không hợp lệ", Toast.LENGTH_SHORT).show();
                } else if (passs.length()<5 | passs.length()>25) {
                    Toast.makeText(Dangky.this, "Mật khẩu Phải từ 5-25 ký tự", Toast.LENGTH_SHORT).show();
                } else{
                    boolean check=taiKhoan_dao.checkDangky(username,email,passs,fullname);
                    if (check){
                        Toast.makeText(Dangky.this, "Đăng ký  Thành Công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Dangky.this, Login.class));
                        finish();
                    }else{
                        Toast.makeText(Dangky.this, "Đăng ký  Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btntrolai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Dangky.this,Login.class));
            }
        });
    }
}