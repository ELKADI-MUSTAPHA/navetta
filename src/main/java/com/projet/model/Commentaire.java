package com.projet.model;

public class Commentaire {
    private int id;
    private int utilisateurId;
    private int societeId;
    private String comment;
    private String dateCreation;
    private int rating;
    private Utilisateur utilisateur;

    // Constructeurs
    public Commentaire() {}

    public Commentaire(int id, int utilisateurId, int societeId, String comment, String dateCreation, int rating) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.societeId = societeId;
        this.comment = comment;
        this.dateCreation = dateCreation;
        this.rating = rating;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUtilisateurId() {
        return utilisateurId;
    }

    public void setUtilisateurId(int utilisateurId) {
        this.utilisateurId = utilisateurId;
    }

    public int getSocieteId() {
        return societeId;
    }

    public void setSocieteId(int societeId) {
        this.societeId = societeId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }
}
