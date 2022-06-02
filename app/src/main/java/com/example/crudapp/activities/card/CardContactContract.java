package com.example.crudapp.activities.card;

import com.example.crudapp.model.Contact;

public interface CardContactContract {

    interface View{
        void onSuccess(String message);
        void onError(String message);
        void onAlert(String message);

    }

    interface Presenter{
        void deleteContact(Long id);
    }
}
