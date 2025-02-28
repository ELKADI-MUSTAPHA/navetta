package com.projet.model;

public class Abonnement {
    private int id;
    private int utilisateurId;
    private int navetteId;
    private String status;
    private Utilisateur utilisateur;
    
    // Constructeurs, getters et setters
    public Abonnement() {}

    public Abonnement(int id, int utilisateurId, int navetteId, String status) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.navetteId = navetteId;
        this.status = status;
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

    public int getNavetteId() {
        return navetteId;
    }

    public void setNavetteId(int navetteId) {
        this.navetteId = navetteId;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Utilisateur getUtilisateur() { return utilisateur; } // Ajout du getter
    public void setUtilisateur(Utilisateur utilisateur) { this.utilisateur = utilisateur; } // Ajout du setter
}