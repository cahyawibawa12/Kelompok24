package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper (Context context){
        super(context, "nepo.db", null, 1);
//        context.deleteDatabase("nepo.db");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tb_kegiatan(id INTEGER PRIMARY KEY AUTOINCREMENT, judul TEXT, deskripsi TEXT, tanggal_mulai TEXT, tanggal_akhir TEXT)");
        db.execSQL("CREATE TABLE tb_user(id INTEGER PRIMARY KEY AUTOINCREMENT, nama_lengkap TEXT, username TEXT, password TEXT)");
        db.execSQL("CREATE TABLE tb_form(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, tanggal_lahir TEXT, umur TEXT, jenis_kelamin TEXT,kegiatan TEXT, pengalaman TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tb_kegiatan");
        db.execSQL("DROP TABLE IF EXISTS tb_user");
        db.execSQL("DROP TABLE IF EXISTS tb_form");
    }

    public boolean tambahKegiatan(KegiatanHandler kegiatanHandler){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("judul",kegiatanHandler.getJudul());
        values.put("deskripsi",kegiatanHandler.getDeskripsi());
        values.put("tanggal_mulai",kegiatanHandler.getTanggal_Mulai());
        values.put("tanggal_akhir",kegiatanHandler.getTanggal_Akhir());
        return db.insert("tb_kegiatan", null,values)>0;
    }

    public Cursor tampilKegiatan(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from tb_kegiatan", null);
    }

    public boolean tambahUser(UserHandler userHandler){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_lengkap",userHandler.getNama_Lengkap());
        values.put("username",userHandler.getUsername());
        values.put("password",userHandler.getPassword());
        return db.insert("tb_user", null,values)>0;
    }

    public Cursor tampilUser(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from tb_user", null);
    }

    public boolean suntingUser(UserHandler userHandler, int id_user) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama_lengkap", userHandler.getNama_Lengkap());
        values.put("username",userHandler.getUsername());
        values.put("password",userHandler.getPassword());
        return db.update("tb_user", values, "id" + "=" + id_user, null) > 0;
    }

    public boolean tambahForm (FormHandler formHandler){
        SQLiteDatabase db = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama",formHandler.getNama());
        values.put("tanggal_lahir",formHandler.getTanggal_lahir());
        values.put("umur",formHandler.getUmur());
        values.put("jenis_kelamin",formHandler.getJenis_kelamin());
        values.put("kegiatan",formHandler.getKegiatan());
        values.put("pengalaman",formHandler.getPengalaman());
        return db.insert("tb_form", null, values) > 0 ;
    }

    public Cursor tampilForm(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from tb_form", null);
    }

    public Cursor detailForm(int id_form) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("Select * from tb_form where id = " + id_form, null);
    }

    public boolean suntingForm(FormHandler listformHandler, int id_form) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("nama", listformHandler.getNama());
        values.put("tanggal_lahir",listformHandler.getTanggal_lahir());
        values.put("umur",listformHandler.getUmur());
        values.put("jenis_kelamin",listformHandler.getJenis_kelamin());
        values.put("kegiatan",listformHandler.getKegiatan());
        values.put("pengalaman",listformHandler.getPengalaman());
        return db.update("tb_form", values, "id" + "=" + id_form, null) > 0;
    }

    public boolean hapusForm (int id_form) {
        SQLiteDatabase db = getReadableDatabase();
        return db.delete("tb_form", "id" + "=" + id_form, null) > 0;
    }

    public void deletesemua (){
        SQLiteDatabase db = getReadableDatabase();
        db.execSQL("DELETE FROM tb_kegiatan");
        db.close();
    }

    public boolean cekUsername(String username){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_user WHERE username = ?", new String[]{username});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }
    public int cekUsernameDanPassword(String username, String password){
        int id = -1;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_user WHERE username = ? AND password = ?", new String[]{username, password});
        while (cursor.moveToNext()) {
            id = Integer.valueOf(cursor.getString(0));
        }
        return id;
    }

    public Cursor tampilkanLastID(){
        String query = "SELECT * FROM tb_user ORDER BY id DESC LIMIT 1";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    public Cursor tampilkanPenggunaDariID(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tb_user WHERE id = ?", new String[]{id});
        return cursor;
    }
}
