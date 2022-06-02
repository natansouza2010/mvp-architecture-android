package com.example.crudapp.activities.card;

import com.example.crudapp.dao.ContactDAO;
import com.example.crudapp.model.Contact;

public class CardContactPresenter implements CardContactContract.Presenter{

    private ContactDAO dao ;
    private CardContactContract.View view;

    public CardContactPresenter(ContactDAO dao, CardContactContract.View view) {
        this.dao = dao;
        this.view = view;
    }



    @Override
    public void deleteContact(Long id) {
        if(id != null) {
            dao.delete(id);
            view.onSuccess("Contato deletado com sucesso");
        }else{
            view.onError("Erro ao deletar");
        }

    }
}
