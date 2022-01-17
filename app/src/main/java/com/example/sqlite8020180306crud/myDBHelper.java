package com.example.sqlite8020180306crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.ColorLong;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class myDBHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Pegawai.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "pegawai";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NAMA = "pegawai_nama";
    private static final String COLUMN_EMAIL = "pegawai_email";
    private static final String COLUMN_ALAMAT = "pegawai_alamat";

    public myDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context= context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String queryKita = "CREATE TABLE " + TABLE_NAME +
                            " (" +
                                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                COLUMN_NAMA + " TEXT, " +
                                COLUMN_EMAIL + " TEXT, " +
                                COLUMN_ALAMAT + " TEXT"+
                            ");";

        db.execSQL(queryKita);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
    }

    void tambahPegawai(String nama, String email, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAMA,nama);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_ALAMAT, alamat);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Gagal!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Berhasil diTambahkan ! ", Toast.LENGTH_SHORT).show();
        }

    }

    Cursor bacaSemuaData(){
        String QueryKita        = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase dbKita   = this.getReadableDatabase();

        Cursor dataKita         = null;

        if (dbKita!=null){
             dataKita =  dbKita.rawQuery(QueryKita, null);

        }
        return dataKita;
    }
    void ubahPegawai(String baris_id, String nama , String email, String alamat){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues dataKita = new ContentValues();
        dataKita.put(COLUMN_NAMA, nama);
        dataKita.put(COLUMN_EMAIL, email);
        dataKita.put(COLUMN_ALAMAT, alamat);

        long hasil = db.update(TABLE_NAME, dataKita,"_id=?", new String[]{baris_id});

        if (hasil == -1){
            Toast.makeText(context,"Ada Gangguan!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "Berhasil di Ubah !", Toast.LENGTH_SHORT).show();
        }
    }

    void hapusPegawai(String row_id){
        SQLiteDatabase dbKita = this.getReadableDatabase();
        long result = dbKita.delete(TABLE_NAME, "_id=?", new String[]{row_id});

        if (result == -1){
            Toast.makeText(context, "gagal delete!", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(context, "berhasil delete!", Toast.LENGTH_SHORT).show();
        }
    }
}
