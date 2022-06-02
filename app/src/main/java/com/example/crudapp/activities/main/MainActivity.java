package com.example.crudapp.activities.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.crudapp.R;
import com.example.crudapp.activities.add.AddContact;
import com.example.crudapp.activities.card.CardContact;
import com.example.crudapp.adapter.ContactAdapter;
import com.example.crudapp.async.TaskDB;
import com.example.crudapp.dao.ContactDAO;
import com.example.crudapp.fragments.FragmentCard;
import com.example.crudapp.model.Contact;
import com.example.crudapp.helpers.RecyclerItemClickListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity implements MainActivityContract.View{
//
    private MainActivityContract.Presenter presenter;
    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;
    private List<Contact> contactList;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ContactAdapter adapterContact;
    private ContactDAO dao;
    private EditText inputSearch;
    private ImageView btnSearch;

    private TaskDB taskDB;

    private FragmentCard fragmentCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int orientacao = getResources().getConfiguration().orientation;
        if(orientacao == Configuration.ORIENTATION_PORTRAIT){
            setContentView(R.layout.activity_main);
            bindingComponentsActivityMain();
            //        Action Fab
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), AddContact.class);
                    startActivity(intent);
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Confirmar", null).show();
                }
            });

            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String input = inputSearch.getText().toString().trim();
                    contactList.clear();
                    if(input.equals("")){
                        try {
                            presenter.findAllContacts();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else{
                        contactList = dao.findForName(input);
                    }

                    //Configurar Adapter
                    ContactAdapter adapterContact = new ContactAdapter(contactList);

                    //Configurar Recycler View
                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(layoutManager);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setAdapter(adapterContact);

                }
            });
//


        }else{
            setContentView(R.layout.activity_main2);
            bindingComponentsActivityMain();



        }



//        fragmentCard = new FragmentCard();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.frameCard, fragmentCard);
//        transaction.commit();







        //Instancia o repositorio
        dao = new ContactDAO(getApplicationContext());

        //Cria o presenter
        presenter = new MainActivityPresenter(this, this);







        //Configura o Adapter Contact
       configureAdapterFromList();
       // Seta os valores para a lista


        try {
            presenter.findAllContacts();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        //Configura o RecycleView
       configureRecycleView();

        //Configurar Adapter
//        ContactAdapter adapterContact = new ContactAdapter();



        //Adicionar Evento Recycler View
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(
                getApplicationContext(),
                recyclerView, new RecyclerItemClickListener.OnItemClickListener() {

                        @Override
                        public void onItemClick(View view, int position) {

                            int orientacao = getResources().getConfiguration().orientation;
                            if(orientacao == Configuration.ORIENTATION_PORTRAIT){
                                Intent intent = new Intent(getApplicationContext(), CardContact.class);
                                Contact cont = contactFromList(position);
                                intent.putExtra("contact", cont);
                                startActivity(intent);
                            }else{
                                Contact cont = contactFromList(position);
                                fragmentCard = FragmentCard.newInstance(cont);

                                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                                transaction.replace(R.id.frameCard, fragmentCard);
                                transaction.commit();

                            }









                        }

                        @Override
                        public void onLongItemClick(View view, int position) {

                        }

                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        }
        }


        ));

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                configureAdapterFromList();
//                // Seta os valores para a lista
//                try {
//                    presenter.findAllContacts();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                //Configura o RecycleView
//                configureRecycleView();
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });






    }




    @Override
    protected void onStart() {
        super.onStart();

        //Configura o Adapter Contact
        configureAdapterFromList();
        // Seta os valores para a lista
        try {
            presenter.findAllContacts();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Configura o RecycleView
        configureRecycleView();

    }

    private Contact contactFromList(int position){
        Contact contact = contactList.get(position);
        return contact;
    }

    private void bindingComponentsActivityMain(){
        floatingActionButton = findViewById(R.id.fabAdd);
        recyclerView = findViewById(R.id.recyclerView);
//        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        btnSearch = findViewById(R.id.btnSearch);
        inputSearch = findViewById(R.id.inputSearch);
    }


    private void configureRecycleView(){
        //Configurar Recycler View
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterContact);
    }


    private void configureAdapterFromList(){
        //Configurar Adapter
        adapterContact = new ContactAdapter();

        //Configurar Recycler View
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterContact);


    }


    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getContacts(List<Contact> contacts) {
        adapterContact.setContacts(contacts);
        contactList = contacts;
    }



}