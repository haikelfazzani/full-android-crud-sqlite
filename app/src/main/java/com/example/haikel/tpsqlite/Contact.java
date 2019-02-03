package com.example.haikel.tpsqlite;

public class Contact {

    private int id;
    private String nom, tel;

    public Contact() { }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return id + " " + nom + " " + tel;
    }
}
