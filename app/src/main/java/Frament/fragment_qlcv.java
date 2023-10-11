package Frament;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_ph32356.NotifyConfig;
import com.example.asm_ph32356.R;

import java.util.ArrayList;
import java.util.Date;

import Adapter.CongViec_Adapter;
import DAO.CongViec_DAO;
import DTO.CongViec_DTO;

public class fragment_qlcv extends Fragment {

    RecyclerView rc_CongViec;
    Button btnthem;
    CongViec_DAO congViec_dao;
    CongViec_Adapter adapter;
    ArrayList<CongViec_DTO> listcv = new ArrayList<>();
    SearchView searchView;
    CongViec_DTO objcv;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_quan_ly_san_pham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rc_CongViec = view.findViewById(R.id.rc_congvc);
        btnthem = view.findViewById(R.id.btnthem);
        ImageButton btnsxtaang=view.findViewById(R.id.btnsxtang);
        ImageButton btnsxgiam=view.findViewById(R.id.btnsxgiam);


        searchView=view.findViewById(R.id.seaview);
        searchView.clearFocus();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                    listcv.clear();
                    listcv.addAll(congViec_dao.timKiem(newText));
                    adapter.notifyDataSetChanged();
                    if (newText.isEmpty()){
                        listcv.clear();
                        listcv.addAll(congViec_dao.getAll());
                        adapter.notifyDataSetChanged();
                    }
                return true;
            }
        });

        btnsxtaang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listcv.clear();
                listcv.addAll(congViec_dao.sapXep("asc"));
                adapter.notifyDataSetChanged();
            }
        });
        btnsxgiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listcv.clear();
                listcv.addAll(congViec_dao.sapXep("desc"));
                adapter.notifyDataSetChanged();
            }
        });


        congViec_dao = new CongViec_DAO(getContext());
        listcv = congViec_dao.getAll();
        adapter = new CongViec_Adapter(getContext(), listcv);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rc_CongViec.setLayoutManager(linearLayoutManager);
        rc_CongViec.setAdapter(adapter);



            btnthem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    themCongViec();
                }
            });
      

    }


    void themCongViec() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View view = getLayoutInflater().inflate(R.layout.itemthemcv, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.show();

        EditText edttencv = view.findViewById(R.id.ed_tencv);
//        EditText edconten = view.findViewById(R.id.ed_nd);
        EditText editstayus = view.findViewById(R.id.ed_Trangthai);
        EditText edtnbd = view.findViewById(R.id.ed_ngaybatdau);
        EditText edtnkt = view.findViewById(R.id.ed_nhapngayketthuc);
        EditText edccd = view.findViewById(R.id.edcccd);
        Button btnthemmcv = view.findViewById(R.id.btnthemCv);

        btnthemmcv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Thêm");
                builder.setMessage("Bạn có Muốn Thêm Sản Phẩm Không?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String tencv = edttencv.getText().toString();
                        int trangthai = Integer.parseInt(editstayus.getText().toString());
                        String ngaybd = edtnbd.getText().toString();
                        String ngayketthuc = edtnkt.getText().toString();
//                        String content = edconten.getText().toString();
                        String cccd = edccd.getText().toString();
                        if (trangthai<-1|trangthai>2){
                            Toast.makeText(getContext(), "Trang thái phải từ -1 --> 2", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        CongViec_DAO dao = new CongViec_DAO(getContext());
                        CongViec_DTO viecDto = new CongViec_DTO();
                        viecDto.setName(tencv);
                        viecDto.setStatus(trangthai);
                        viecDto.setStar(ngaybd);
                        viecDto.setAnd(ngayketthuc);
//                        viecDto.setContent(content);
                        viecDto.setCccd(cccd);
                        long id = dao.addCongViec(viecDto);
                        if (id > 0) {
                            Toast.makeText(getContext(), "Thêm Thành Công", Toast.LENGTH_SHORT).show();
                            listcv.clear();
                            listcv.addAll(congViec_dao.getAll());
                            adapter.notifyDataSetChanged();
                            dialog.dismiss();

                            Notification customNotification = new NotificationCompat.Builder(getContext(), NotifyConfig.CHANEL_ID)
                                    .setSmallIcon(R.drawable.baseline_add_task_24)
                                    .setContentTitle("Thêm")
                                    .setContentText("Bạn Đã Thêm công Việc: "+viecDto.getName())
                                    .setColor(Color.BLUE)
                                    .setAutoCancel(true)
                                    .build();


                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());


                            if (ActivityCompat.checkSelfPermission(getContext(),
                                    android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {


                                ActivityCompat.requestPermissions((Activity) getContext(),
                                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 999999);
                                return;
                            }

                            int id_notiy = (int) new Date().getTime();// lấy chuỗi time là phù hợp

                            notificationManagerCompat.notify(id_notiy , customNotification);
                        } else {
                            Toast.makeText(getContext(), "Thêm Thất Bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialog.dismiss();
                    }
                });
                builder.show();


            }
        });
    }
}

