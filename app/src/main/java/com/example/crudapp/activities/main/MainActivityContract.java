package com.example.crudapp.activities.main;

import com.example.crudapp.model.Contact;

import java.util.List;
import java.util.concurrent.ExecutionException;

public interface MainActivityContract {
    interface View {
//        void popularityList(List<Contact> contactList);
        void onError(String message);

        void getContacts(List<Contact> contacts);
    }

    interface Presenter {
        void findAllContacts() throws ExecutionException, InterruptedException;
        void findForName(String inputText) throws ExecutionException, InterruptedException;
    }
}
