package DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import DTO.CongViec_DTO;
import Database.DbHepper;

public class CongViec_DAO {
    private DbHepper dbHepper;
    SQLiteDatabase db;

    public  CongViec_DAO(Context context){
        dbHepper=new DbHepper(context);
        db=dbHepper.getWritableDatabase();
    }
    public  long addCongViec(CongViec_DTO viec_dto){
        ContentValues values=new ContentValues();
        values.put("namecv",viec_dto.getName());
        values.put("content",viec_dto.getContent());
        values.put("status",viec_dto.getStatus());
        values.put("start",viec_dto.getStar());
        values.put("endd",viec_dto.getAnd());

        return db.insert("CongViec",null,values);
    }
    public  int Update_Cv(CongViec_DTO viec_dto){
        ContentValues values=new ContentValues();
        values.put("namecv",viec_dto.getName());
        values.put("content",viec_dto.getContent());
        values.put("status",viec_dto.getStatus());
        values.put("start",viec_dto.getStar());
        values.put("endd",viec_dto.getAnd());

        String[]dk=new String[]{String.valueOf(viec_dto.getIdcv())};
        return db.update("CongViec",values,"idcv=?",dk);
    }
    public  int Delete_Cv(CongViec_DTO viec_dto){
        String[]dk=new String[]{String.valueOf(viec_dto.getIdcv())};
        return db.delete("Congviec","idcv=?",dk);
    }

    public ArrayList<CongViec_DTO> getAll(){
        ArrayList<CongViec_DTO>list_Cv=new ArrayList<>();
        Cursor c= db.rawQuery("SELECT * FROM CongViec ",null);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list_Cv.add(new CongViec_DTO(c.getInt(0),c.getString(1), c.getString(2),c.getInt(3),c.getString(4),c.getString(5) ));
            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz","getAll:k lay dc giu lieu");
        }

        return list_Cv;
    }
    public ArrayList<CongViec_DTO> sapXep(String xapxep){
        ArrayList<CongViec_DTO>list_Cv=new ArrayList<>();
        Cursor c= db.rawQuery("SELECT * FROM CongViec order by namecv  "+xapxep,null);
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list_Cv.add(new CongViec_DTO(c.getInt(0),c.getString(1), c.getString(2),c.getInt(3),c.getString(4),c.getString(5) ));
            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz","getAll:k lay dc giu lieu");
        }

        return list_Cv;
    }

    public ArrayList<CongViec_DTO> timKiem(String name){
        ArrayList<CongViec_DTO>list_Cv=new ArrayList<>();
        String sql = "SELECT * FROM CongViec where namecv like "+"'%"+name+"%'";
        Cursor c= db.rawQuery(sql,new String[]{});
        if (c!=null&&c.getCount()>0){
            c.moveToFirst();
            do {
                list_Cv.add(new CongViec_DTO(c.getInt(0),c.getString(1), c.getString(2),c.getInt(3),c.getString(4),c.getString(5) ));
            }while (c.moveToNext());
        }else{
            Log.d("zzzzzzzzzzzz","getAll:k lay dc giu lieu");
        }

        return list_Cv;
    }

}
