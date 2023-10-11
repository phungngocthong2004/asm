package Adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.asm_ph32356.NotifyConfig;
import com.example.asm_ph32356.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DAO.CongViec_DAO;
import DTO.CongViec_DTO;

public class CongViec_Adapter extends  RecyclerView.Adapter<CongViec_Adapter.Viewholder> {
    Context context;
    ArrayList<CongViec_DTO>listCv;
    CongViec_DAO congViec_dao;
    CongViec_DTO viecDto;


    public CongViec_Adapter(Context context, ArrayList<CongViec_DTO> listCv) {
        this.context = context;
        this.listCv = listCv;
        congViec_dao=new CongViec_DAO(context);
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View view=inflater.inflate(R.layout.item_congviec,null);
        View view=inflater.inflate(R.layout.item_congviec,parent,false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, @SuppressLint("RecyclerView") int position) {

        String status;
        if(listCv.get(position).getStatus() == -1){
            status = "Đã Xóa";
        } else if (listCv.get(position).getStatus() == 0 ) {
            status = "Mới tạo";
        }else if (listCv.get(position).getStatus() == 1 ) {
            status = "Đang thực hiện";
        }else{
            status = "Đang hoàn thành";
        }


       viecDto=listCv.get(position);
        holder.txttieude.setText(listCv.get(position).getName());
        holder.txtStatus.setText("Status: "+status);
        holder.txtngaybd.setText(listCv.get(position).getStar()+"   -");
        holder.txtngaykt.setText(listCv.get(position).getAnd());
        holder.tvccd.setText(listCv.get(position).getCccd());

        holder.txtsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View v= inflater.inflate(R.layout.sua_congvc,null);
                builder.setView(v);
                Dialog dialog=builder.create();
                dialog.show();


                EditText edtten=v.findViewById(R.id.nameCv);
                EditText edcontent=v.findViewById(R.id.noidung);
                EditText editstatus=v.findViewById(R.id.trangthai);
                EditText edtnbd=v.findViewById(R.id.ngaybatdau);
                EditText edtnkt=v.findViewById(R.id.ngayketthuc);
                EditText edcccd=v.findViewById(R.id.edcccd);
                Button btnsuacv=v.findViewById(R.id.btnUpdateCv);

                edtten.setText(listCv.get(position).getName());
                edcontent.setText(listCv.get(position).getContent()+"");
                edtnbd.setText(listCv.get(position).getStar());
                edtnkt.setText(listCv.get(position).getAnd());
                editstatus.setText(listCv.get(position).getStatus()+"");
                edcccd.setText(listCv.get(position).getCccd());
                btnsuacv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder=new AlertDialog.Builder(context);
                        builder.setTitle("Cập nhật");
                        builder.setMessage("Bạn có Muốn Cập nhật không?");
                        builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                viecDto=listCv.get(position);
                                viecDto.setName(edtten.getText().toString());
                                viecDto.setContent(edcontent.getText().toString());
                                viecDto.setStatus(Integer.parseInt(editstatus.getText().toString()));
                                viecDto.setStar(edtnbd.getText().toString());
                                viecDto.setAnd(edtnkt.getText().toString());
                                viecDto.setAnd(edcccd.getText().toString());
                                if (Integer.parseInt(editstatus.getText().toString())<-1|Integer.parseInt(editstatus.getText().toString())>2){
                                    Toast.makeText(context, "Trang thái phải từ -1 --> 2", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                    return;
                                }
                                int id=congViec_dao.Update_Cv(viecDto);
                                if (id>0){
                                    Toast.makeText(context, "update thanh cong", Toast.LENGTH_SHORT).show();
                                    listCv.clear();
                                    listCv.addAll(congViec_dao.getAll());
                                    notifyItemChanged(holder.getAdapterPosition());
                                    dialog.dismiss();
                                }else{
                                    Toast.makeText(context, "update khong thanh cong", Toast.LENGTH_SHORT).show();
                                }
//                                listCv.remove(position);
//                                listCv.add(position,viec_dto);
//                                Toast.makeText(context, "Đã UPadate Thành Công", Toast.LENGTH_SHORT).show();
//                                notifyItemChanged(holder.getAdapterPosition());
                                dialog.dismiss();

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
        });
        holder.txtxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("xóa Công Việc");
                builder.setIcon(R.drawable.canhbao);
                builder.setMessage("bạn có muốn xóa Công vIệc này không");
                builder.setPositiveButton("có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        CongViec_DTO v=listCv.get(holder.getAdapterPosition());
                        int id= congViec_dao.Delete_Cv(v);
                        if (id>0){
                            listCv.clear();
                            listCv.addAll(congViec_dao.getAll());
                            Toast.makeText(context, "Xóa Thành công", Toast.LENGTH_SHORT).show();
                            notifyDataSetChanged();

                            Notification customNotification = new NotificationCompat.Builder(context, NotifyConfig.CHANEL_ID)
                                    .setSmallIcon(android.R.drawable.ic_delete)
                                    .setContentTitle("xóa")
                                    .setContentText("Bạn Đã xóa: "+viecDto.getName())
                                    .setColor(Color.BLUE)
                                    .setAutoCancel(true)
                                    .build();

// Khởi tạo Manager để quản lý notify
                            NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(context);

// Cần kiểm tra quyền trước khi hiển thị notify
                            if (ActivityCompat.checkSelfPermission(context,
                                    android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {

                                // Gọi hộp thoại hiển thị xin quyền người dùng
                                ActivityCompat.requestPermissions((Activity) context,
                                        new String[]{Manifest.permission.POST_NOTIFICATIONS}, 999999);
                                return; // thoát khỏi hàm nếu chưa được cấp quyền
                            }
// nếu đã cấp quyền rồi thì sẽ vượt qua lệnh if trên và đến đây thì hiển thị notify
// mỗi khi hiển thị thông báo cần tạo 1 cái ID cho thông báo riêng
                            int id_notiy = (int) new Date().getTime();// lấy chuỗi time là phù hợp
//lệnh hiển thị notify
                            notificationManagerCompat.notify(id_notiy , customNotification);
                        }else{
                            Toast.makeText(context, "Xóa Thất bại", Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                builder.setNegativeButton("không",null);
                builder.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCv.size();
    }

    public  class Viewholder extends RecyclerView.ViewHolder {
        ImageView imganh;
        TextView txttieude,txtStatus,txtngaybd,txtngaykt,txtsua,txtxoa,tvccd;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imganh=itemView.findViewById(R.id.imganh);
            txttieude=itemView.findViewById(R.id.tv_tieude);
            txtStatus=itemView.findViewById(R.id.tv_status);
            txtngaybd=itemView.findViewById(R.id.tv_ngaybd);
            txtngaykt=itemView.findViewById(R.id.tv_ngaykt);
            txtsua=itemView.findViewById(R.id.tv_sua);
            txtxoa=itemView.findViewById(R.id.tv_xoa);
            tvccd=itemView.findViewById(R.id.tv_cccd);

        }
    }
}
