package com.example.crudapp.activities.add;

import android.text.TextUtils;
import android.util.Patterns;

import com.example.crudapp.dao.ContactDAO;
import com.example.crudapp.model.Contact;

public class AddContactPresenter implements AddContactContract.Presenter{

    private AddContactContract.View view;
    private ContactDAO dao;

    public AddContactPresenter(AddContactContract.View view, ContactDAO dao) {
        this.view = view;
        this.dao = dao;
    }


    @Override
    public boolean validateEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    @Override
    public void saveContact(Contact contact) {
        if(contact != null){
            dao.save(contact);
            view.onSucess("Contato salvo com sucesso");
        }else{
            view.onError("Erro ao salvar");
        }



    }

    @Override
    public void updateContact(Contact contact) {
        if(contact != null){
            dao.update(contact);
            view.onSucess("Contato alterado com sucesso");
        }else{
            view.onError("Erro ao alterar");
        }
    }
}
