package com.example.crudapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crudapp.R;
import com.example.crudapp.model.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{

    public ContactAdapter() {
    }

    private List<Contact> contacts;


    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;

    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contactList = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_contact,parent,false);
        return new ViewHolder(contactList);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.name.setText(contact.getName());
        holder.phone.setText(contact.getPhone());
        holder.email.setText(contact.getEmail());
        holder.adress.setText("End:"+ contact.getAdress());



    }


    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }


    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name;
        private TextView email;
        private TextView phone;
        private TextView adress;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           name = itemView.findViewById(R.id.txtNomeFromCard);
           email = itemView.findViewById(R.id.txtEmail);
           phone = itemView.findViewById(R.id.txtTelefone);
           adress = itemView.findViewById(R.id.txtEndereco);

        }


    }
}
