package com.example.crudapp.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudapp.R;
import com.example.crudapp.activities.add.AddContact;
import com.example.crudapp.activities.card.CardContact;
import com.example.crudapp.activities.card.CardContactContract;
import com.example.crudapp.activities.card.CardContactPresenter;
import com.example.crudapp.dao.ContactDAO;
import com.example.crudapp.model.Contact;

import java.io.Serializable;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentCard#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentCard extends Fragment implements CardContactContract.View {


    private TextView txtName;
    private TextView txtPhone;
    private TextView txtEmail;
    private TextView txtAdress;
    private Button btnUpdate;
    private Button btnDelete;
    private ContactDAO contactDAO;
    private CardContactPresenter presenter;
    private Contact contato;

    public static FragmentCard newInstance(Contact contact) {

        Bundle args = new Bundle();
        args.putSerializable("contact", contact);


        FragmentCard fragment = new FragmentCard();
        fragment.setArguments(args);
        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_card, container, false);
        //Binding
        txtName = view.findViewById(R.id.txtNomeFromCard);
        txtPhone = view.findViewById(R.id.txtTelefoneFromCard);
        txtEmail = view.findViewById(R.id.txtEmailFromCard);
        txtAdress = view.findViewById(R.id.txtEnderecoFromCard);
        btnDelete = view.findViewById(R.id.btnDelete);
        btnUpdate = view.findViewById(R.id.btnUpdate);






        //DAO
        contactDAO = new ContactDAO(getActivity().getApplicationContext());

        //Presenter
        presenter = new CardContactPresenter(contactDAO, this);

        //Recuperar Dados do Contato

//        Bundle dados = getActivity().getIntent().getExtras();

        Contact contact = (Contact) getArguments().getSerializable("contact");


        if(contact != null) {
            txtName.setText(contact.getName());
            txtPhone.setText("Telefone : " + contact.getPhone());
            txtAdress.setText("Endereço : " + contact.getAdress());
            txtEmail.setText( "Email : " +contact.getEmail());
        }





        //Events
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity().getApplicationContext(), AddContact.class);
                intent.putExtra("contact", contact);
                startActivity(intent);
                getActivity().finish();

//                Snackbar.make(view, "Contato será Alterado", Snackbar.LENGTH_SHORT).setAction("Confirmar",null).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
                dialog.setTitle("Confirmar exclusão");
                dialog.setMessage("Deseja excluir o contato: " + contact.getName() + " ?");
                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.deleteContact(contact.getId());
                        getActivity().finish();
                    }
                });
                dialog.setNegativeButton("Não", null);

                //exibe o Dialog
                dialog.create();
                dialog.show();

//                contactDAO.delete(contact.getId());
//                Snackbar.make(view, "Contato será deletado", Snackbar.LENGTH_SHORT).setAction("Excluir",null).show();
            }
        });




        return view;
    }


    @Override
    public void onSuccess(String message) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG);

    }

    @Override
    public void onError(String message) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG);
    }

    @Override
    public void onAlert(String message) {
        Toast.makeText(requireContext(),message,Toast.LENGTH_LONG);
    }
}