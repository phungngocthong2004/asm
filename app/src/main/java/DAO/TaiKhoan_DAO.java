package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.StringJoiner;

import DTO.TaiKhoan_DTO;
import Database.DbHepper;

public class TaiKhoan_DAO {
    private DbHepper dbHepper;
    SQLiteDatabase db;

    public TaiKhoan_DAO(Context context){
        dbHepper=new DbHepper(context);
    }

    //login
    public  boolean Checklogin(String username,String password){
        db=dbHepper.getReadableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM TaiKhoan where username=? and passwrod=?",new String[]{username,password});

        return c.getCount()>0;

    }
    //Đăng ký
    public boolean checkDangky(String username,String email,String passwrod,String fullname ){
        db=dbHepper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("username",username);
        values.put("email",email);
        values.put("passwrod",passwrod);
        values.put("fullname",fullname);
        long check= db.insert("TaiKhoan",null,values);
        if (check!=1){
            return  true;
        }else{
            return false;
        }
    }
    //check check fỏgotpass
    public boolean checkfogotpass(String email,String username){
        db=dbHepper.getReadableDatabase();
        Cursor c=db.rawQuery("SELECT * FROM TaiKhoan where email=? and username=?",new String[]{email,username});
        return c.getCount()>0;
    }
    //check doimk
    public  boolean  doimk(String username,String passwword){
        db=dbHepper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("passwrod",passwword);
        String[]dk=new String[]{username};
        int check=  db.update("TaiKhoan",values,"username=?",dk);
        return check!=-1;
    }
    //lay du lieu từ data
    public ArrayList<TaiKhoan_DTO> getAll(){
        ArrayList<TaiKhoan_DTO>listtk=new ArrayList<>();

        Cursor cursor=db.rawQuery("SELECT * FROM TaiKhoan ",null);
        if (cursor!=null&cursor.getCount()>0){
            cursor.moveToFirst();
            do {
                listtk.add(new TaiKhoan_DTO(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }else{
            Log.d("zzzzzzzzz","getAll:khong lay dc du lieu");
        }
        return listtk;

    }
}
