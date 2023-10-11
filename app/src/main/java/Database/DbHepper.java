package Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHepper extends SQLiteOpenHelper {

    static  String namedata="QuanLySanPhamBan";
    static  int version=1;
    public  DbHepper(Context context){
        super(context,namedata,null,version);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String tb_taikhoan="CREATE TABLE TaiKhoan ( username TEXT PRIMARY KEY,  email  TEXT    NOT NULL,  passwrod TEXT    NOT NULL,fullname TEXT    NOT NULL);";
        sqLiteDatabase.execSQL(tb_taikhoan);

        String tb_congViec="CREATE TABLE CongViec ( idcv    INTEGER PRIMARY KEY AUTOINCREMENT, namecv  TEXT    NOT NULL, content TEXT   , status  INTEGER NOT NULL, start   TEXT    NOT NULL,  endd TEXT    NOT NULL);";
        sqLiteDatabase.execSQL(tb_congViec);

//        String insert_tk="INSERT INTO TaiKhoan values('phungngocthong2004','phungngocthong2004@gmail.com','thong2004','phungngocthong')";
//        sqLiteDatabase.execSQL(insert_tk);

//        String insert_cv="INSERT INTO CongViec values(1, 'Bán Bơ','Bán Bơ Đậu lành',0,'13/7/2023','20/9/2023','0913243435'),(2,'Bán Xe','Bán Xe máy',1,'11/12/2022','11/12/2023','093438755546')";
//        sqLiteDatabase.execSQL(insert_cv);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i1!=i) {
            String Droptablecongviec = "drop table if exists CongViec";
            sqLiteDatabase.execSQL(Droptablecongviec);
            String Droptabletai = "drop table if exists TaiKhoan";
            sqLiteDatabase.execSQL(Droptabletai);
        }
    }
}
