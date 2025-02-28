package com.projet.model;

public class Offre {
    private int id;
    private int societeId;
    private int demandeId;
    private String statut;
    private Societe societe; // Objet Société associé à l'offre

    // Constructeurs
    public Offre() {
    }

    public Offre(int id, int societeId, int demandeId, String statut) {
        this.id = id;
        this.societeId = societeId;
        this.demandeId = demandeId;
        this.statut = statut;
    }

    // Getters et setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSocieteId() {
        return societeId;
    }

    public void setSocieteId(int societeId) {
        this.societeId = societeId;
    }

    public int getDemandeId() {
        return demandeId;
    }

    public void setDemandeId(int demandeId) {
        this.demandeId = demandeId;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Societe getSociete() {
        return societe;
    }

    public void setSociete(Societe societe) {
        this.societe = societe;
    }

    @Override
    public String toString() {
        return "Offre{" +
                "id=" + id +
                ", societeId=" + societeId +
                ", demandeId=" + demandeId +
                ", statut='" + statut + '\'' +
                '}';
    }
}