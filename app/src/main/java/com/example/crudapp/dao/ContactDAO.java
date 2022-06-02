package com.example.crudapp.dao;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.crudapp.activities.main.MainActivityPresenter;
import com.example.crudapp.helpers.DBHelper;
import com.example.crudapp.model.Contact;
import com.example.crudapp.utils.GenericDAO;

import java.util.ArrayList;
import java.util.List;

public class ContactDAO implements GenericDAO<Contact, Long> {


    private DBHelper dbHelper;
    private SQLiteDatabase db;
    private SQLiteDatabase read;

    public ContactDAO(Context context) {
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        read =  dbHelper.getReadableDatabase();
    }



    @Override
    public void save(Contact contact) {
        ContentValues values = new ContentValues();
        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());
        values.put("adress", contact.getAdress());
        values.put("email", contact.getEmail());
        try{

            db.insert(DBHelper.TABLE_CONTACT, null, values);
            Log.e("INFO", "Contato salvo com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao salvar contato" + e.getMessage());
        }


    }

    @Override
    public void delete(Long key) {
        try{
            db.delete(DBHelper.TABLE_CONTACT, "id = ?" , new String[]{key.toString()});
            Log.e("INFO", "Contato deletado com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao deletar contato" + e.getMessage());
        }

    }

    @Override
    public void update(Contact contact) {
        ContentValues values = new ContentValues();
        System.out.println("Olá " + contact.getId());
        values.put("name", contact.getName());
        values.put("phone", contact.getPhone());
        values.put("adress", contact.getAdress());
        values.put("email", contact.getEmail());

        try{
            db.update(DBHelper.TABLE_CONTACT, values, "id= ?", new String[]{contact.getId().toString()});
            Log.e("INFO", "Contato alterado com sucesso");
        }catch (Exception e){
            Log.e("INFO", "Erro ao alterar contato");
        }
    }


    @Override
    public Contact findOne(Long key) {
        return null;
    }


    public List<Contact> findForName(String inputName){
        String sql = "select * from " + DBHelper.TABLE_CONTACT + " WHERE name = ? ;";
        Cursor c = db.rawQuery(sql, new String[] {inputName});
        List<Contact> contactList = new ArrayList<>();
        while(c.moveToNext()){
            Contact contact = new Contact();
            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String name = c.getString(c.getColumnIndex("name"));
            @SuppressLint("Range") String phone = c.getString(c.getColumnIndex("phone"));
            @SuppressLint("Range") String adress = c.getString(c.getColumnIndex("adress"));
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));
            contact.setId(id);
            contact.setName(name);
            contact.setEmail(email);
            contact.setAdress(adress);
            contact.setPhone(phone);
            contactList.add(contact);

        }
        return contactList;
    }



    @Override
    public List<Contact> findAll() {
        List<Contact> contactList = new ArrayList<>();
        String sql = "select * FROM "+ DBHelper.TABLE_CONTACT + " ;";
        Cursor c = read.rawQuery(sql, null);
        while (c.moveToNext()){
            Contact contact = new Contact();
            @SuppressLint("Range") Long id = c.getLong(c.getColumnIndex("id"));
            @SuppressLint("Range") String name = c.getString(c.getColumnIndex("name"));
            @SuppressLint("Range") String phone = c.getString(c.getColumnIndex("phone"));
            @SuppressLint("Range") String adress = c.getString(c.getColumnIndex("adress"));
            @SuppressLint("Range") String email = c.getString(c.getColumnIndex("email"));
            contact.setId(id);
            contact.setName(name);
            contact.setEmail(email);
            contact.setAdress(adress);
            contact.setPhone(phone);
            contactList.add(contact);
        }
        return contactList;

    }
}
