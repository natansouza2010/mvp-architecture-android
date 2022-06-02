package com.example.crudapp.model;

import java.io.Serializable;
import java.util.Objects;

public class Contact implements Serializable {
    private Long id;
    private String name;
    private String phone;
    private String adress;
    private String email;

    public Contact() {
    }

    public Contact(Long id,String name, String phone, String adress, String email) {
        this.id= id;
        this.name = name;
        this.phone = phone;
        this.adress = adress;
        this.email = email;
    }

    public Contact(String name, String phone, String adress, String email) {
        this.name = name;
        this.phone = phone;
        this.adress = adress;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", adress='" + adress + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return name.equals(contact.name) && phone.equals(contact.phone) && adress.equals(contact.adress) && email.equals(contact.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, adress, email);
    }
}
