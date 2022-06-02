package com.example.crudapp.activities.add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.crudapp.R;
import com.example.crudapp.dao.ContactDAO;
import com.example.crudapp.model.Contact;
import com.google.android.material.textfield.TextInputEditText;

public class AddContact extends AppCompatActivity implements AddContactContract.View {

    private TextView txtAddOrUpdate;
    private Button btnSaveOrUpdateContact;
    private TextInputEditText inputName;
    private TextInputEditText inputEmail;
    private TextInputEditText inputPhone;
    private TextInputEditText inputAdress;
    private ContactDAO contactDAO;
    private Contact contactFromIntent;
    private AddContactPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);
        bindingComponentsAddContactActivity();
        //Instancia DAO
        contactDAO = new ContactDAO(getApplicationContext());

        //Instancia presenter
        presenter = new AddContactPresenter(this, contactDAO);

        //Pega os dados da Tela main activity
        Bundle dados = getIntent().getExtras();

        inputPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher("BR"));


        if(dados != null){
            contactFromIntent=(Contact) dados.getSerializable("contact");
            if(contactFromIntent != null) {
                txtAddOrUpdate.setText("Alterar Contato");
                inputName.setText(contactFromIntent.getName());
                inputPhone.setText(contactFromIntent.getPhone());
                inputEmail.setText(contactFromIntent.getEmail());
                inputAdress.setText(contactFromIntent.getAdress());
                btnSaveOrUpdateContact.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View view) {
                        String name = inputName.getText().toString();
                        String email = inputEmail.getText().toString();
                        String phone = inputPhone.getText().toString();
                        String adress = inputAdress.getText().toString();
                        Contact newCont = new Contact(name,phone,adress,email);
                        newCont.setId(contactFromIntent.getId());
                        if(validateInputs(name,email,phone,adress)) {
                            if (!validateEmail()) {
                                return;
                            } else {
                                presenter.updateContact(newCont);
                                finish();
                            }
                        }
                    }



                });



            }
        }else{


        btnSaveOrUpdateContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String phone = inputPhone.getText().toString();
                String adress = inputAdress.getText().toString();

                Contact cont = new Contact(name,phone,adress, email);
                if(validateInputs(name,email,phone,adress)){
                    if(!validateEmail()){
                        return;
                    }else{
                        if(contactFromIntent == null) {
                            presenter.saveContact(cont);
                        }
                        finish();
                      }

                    }
                }




        });




        }
    }


    private boolean validateEmail() {
        String emailInput = inputEmail.getText().toString().trim();

       if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
           inputEmail.setError("Please enter a valid email");
            return false;
        } else {
           inputEmail.setError(null);
            return true;
        }
    }






    public boolean validateInputs(String name, String email, String telefone, String endereco){
        if(name == null || name.equals("")){
            onAlert("Campo nome está vazio");
            return false;
        }
        if(email == null || email.equals("")){
            onAlert("Campo email está vazio");
            return false;
        }
        if(telefone == null || telefone.equals("")){
            onAlert("Campo telefone está vazio");
            return false;
        }
        if(endereco == null || endereco.equals("")){
            onAlert("Campo endereco está vazio" );
            return false;
        }
        return true;





    }






    public void bindingComponentsAddContactActivity(){
        txtAddOrUpdate = findViewById(R.id.txtAddOrUpdate);
        inputName = findViewById(R.id.inputName);
        inputEmail = findViewById(R.id.inputEmail);
        inputPhone = findViewById(R.id.inputPhone);
        inputAdress = findViewById(R.id.inputAdress);
        btnSaveOrUpdateContact = findViewById(R.id.btnSaveOrUpdateContact);
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAlert(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSucess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}