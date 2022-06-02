package com.example.crudapp.async;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.crudapp.activities.main.MainActivityContract;
import com.example.crudapp.dao.ContactDAO;
import com.example.crudapp.model.Contact;

import java.util.List;

public class TaskDB extends AsyncTask<Void,Void, List<Contact>> {
    private Context context;
    private ContactDAO dao;
    private List<Contact> contactList;
    private ProgressDialog progressDialog;


    public TaskDB(Context context) {
        this.context = context;
       //
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Carregando dados");
        progressDialog.show();


    }



    @Override
    protected List<Contact> doInBackground(Void... voids) {
        dao = new ContactDAO(context);

        try{

            contactList = dao.findAll();
            return contactList;

        }catch (Exception e){

        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Contact> contactList) {
        super.onPostExecute(contactList);
        progressDialog.setMessage("Lista Carregada");
        progressDialog.dismiss();
    }

}


