package com.projet.model;

public class Utilisateur {
    private int id;
    private String nom;
    private String email;
    private String motDePasse;
    private String telephone; // Ajout du numéro de téléphone 📞

    // Getters et setters
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() { // Getter pour téléphone
        return telephone;
    }

    public void setTelephone(String telephone) { // Setter pour téléphone
        this.telephone = telephone;
    }
}
