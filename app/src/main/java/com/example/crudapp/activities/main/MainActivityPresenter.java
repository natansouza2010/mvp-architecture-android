package com.example.crudapp.activities.main;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.crudapp.async.TaskDB;
import com.example.crudapp.dao.ContactDAO;
import com.example.crudapp.model.Contact;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivityPresenter implements MainActivityContract.Presenter{
    private List<Contact> contactList;

    private TaskDB taskDB;
    private Context context;


    MainActivityContract.View view;

    public MainActivityPresenter(MainActivityContract.View view,
                                 Context context) {
        this.context = context;
        this.view = view;
    }




    public void findAllContacts() throws ExecutionException, InterruptedException {
//        taskDB = new TaskDB()
        TaskDB taskDB = new TaskDB(context);
        contactList = taskDB.execute().get();
        if(contactList != null) {
            view.getContacts(contactList);
        }else {
            view.onError("Contacts is empty or null");
        }





//        contactList = contactDAO.findAll();
//        if(contactList != null){
//            view.getContacts(contactList);
//        }else{
//            view.onError("Contacts is empty or null");
//        }
       

    }

    @Override
    public void findForName(String inputText) throws ExecutionException, InterruptedException {
        TaskDB taskDB = new TaskDB(context);
        contactList = taskDB.execute().get();
        if(contactList != null) {
            view.getContacts(contactList);
        }else {
            view.onError("Contacts is empty or null");
        }
    }

}
