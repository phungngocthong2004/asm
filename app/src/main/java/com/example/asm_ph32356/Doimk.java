package com.example.asm_ph32356;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import DAO.TaiKhoan_DAO;
import DTO.TaiKhoan_DTO;

public class Doimk extends AppCompatActivity {

    EditText edpass,ednhaplai;
    Button btndoimk;
    TaiKhoan_DAO taiKhoan_dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doimk);
        ednhaplai=findViewById(R.id.ed_nhaplaimk);
        edpass=findViewById(R.id.ed_mk);
        btndoimk=findViewById(R.id.btndoimk);
        taiKhoan_dao=new TaiKhoan_DAO(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("username");




        btndoimk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pass=edpass.getText().toString();
                String nhaplai=edpass.getText().toString();
                if (pass.isEmpty()|nhaplai.isEmpty()){
                    Toast.makeText(Doimk.this, "Bạn cần Nhập đầy đủ dữ liệu", Toast.LENGTH_SHORT).show();
                } else if (!pass.equals(nhaplai)) {
                    Toast.makeText(Doimk.this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                } else if (pass.length()<5 | pass.length()>25) {
                    Toast.makeText(Doimk.this, "Mật khẩu Phải từ 5-25 ký tự", Toast.LENGTH_SHORT).show();
                }else{
                    boolean check= taiKhoan_dao.doimk(username,edpass.getText().toString());
                    if (check){
                        Toast.makeText(Doimk.this, "Thành Công", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Doimk.this,Login.class));
                    }else{
                        Toast.makeText(Doimk.this, "Đổi Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}