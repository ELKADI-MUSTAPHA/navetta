package com.projet.model;

public class UserRating {
    private int id;
    private int societeId;
    private int utilisateurId;
    private int rating;
    private String dateCreation;

    // Constructeurs
    public UserRating() {}

    public UserRating(int id, int utilisateurId, int societeId, String dateCreation, int rating) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.societeId = societeId;
        this.dateCreation = dateCreation;
        this.rating = rating;
    }
    
    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getSocieteId() { return societeId; }
    public void setSocieteId(int societeId) { this.societeId = societeId; }

    public int getUtilisateurId() { return utilisateurId; }
    public void setUtilisateurId(int utilisateurId) { this.utilisateurId = utilisateurId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getDateCreation() { return dateCreation; }
    public void setDateCreation(String dateCreation) { this.dateCreation = dateCreation; }
}
