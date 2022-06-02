package com.example.crudapp.activities.add;

import com.example.crudapp.model.Contact;

public interface AddContactContract {

    interface View {
        void onError(String message);
        void onAlert(String message);
        void onSucess(String message);
    }


    interface Presenter{
        boolean validateEmail(String email);
        void saveContact(Contact contact);
        void updateContact(Contact contact);
    }
}
