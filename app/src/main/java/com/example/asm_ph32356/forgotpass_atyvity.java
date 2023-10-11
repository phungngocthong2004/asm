package com.example.asm_ph32356;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import DAO.TaiKhoan_DAO;

public class forgotpass_atyvity extends AppCompatActivity {

    EditText edtenlaypas,edtmaillaypass;
    Button btnlaylaymk;
    TaiKhoan_DAO taiKhoan_dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgotpass_atyvity);
        edtenlaypas=findViewById(R.id.ed_tenlaypass);
        edtmaillaypass=findViewById(R.id.ed_emailpass);
        btnlaylaymk=findViewById(R.id.btnlaaylaipass);

        taiKhoan_dao=new TaiKhoan_DAO(this);
        btnlaylaymk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenpass=edtenlaypas.getText().toString();
                String emaillaypas=edtmaillaypass.getText().toString();

                boolean check=taiKhoan_dao.checkfogotpass(emaillaypas,tenpass);
                if (check){
                    Toast.makeText(forgotpass_atyvity.this, "tài khoản Hợp lệ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(forgotpass_atyvity.this, Doimk.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("username",tenpass);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }else{
                    Toast.makeText(forgotpass_atyvity.this, "tài khoản không hợp lệ", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}