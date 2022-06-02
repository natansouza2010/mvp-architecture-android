package com.example.crudapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.crudapp.activities.main.MainActivityPresenter;

import java.util.concurrent.ExecutionException;

public class DBHelper extends SQLiteOpenHelper {
    public static int VERSION = 1;
    public static String NAME_DB = "DB_CONTACTS";
    public static String TABLE_CONTACT = "contacts";


    public DBHelper(@Nullable Context context) {
        super(context, NAME_DB, null, VERSION);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_CONTACT +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT, "+
        " name VARCHAR NOT NULL, phone VARCHAR NOT NULL, adress VARCHAR NOT NULL, email VARCHAR NOT NULL ); ";

        try{
            sqLiteDatabase.execSQL(sql);
            Log.i("INFO DB", "Sucesso ao criar tabela " );
        }catch (Exception e){
            Log.i("INFO DB", "Erro ao criar tabela "  + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
