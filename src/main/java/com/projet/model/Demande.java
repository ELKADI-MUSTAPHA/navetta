package com.projet.model;

import java.sql.Time;
import java.sql.Date;

public class Demande {
    private int id;
    private int utilisateurId;
    private String villeDepart;
    private String villeArrivee;
    private Time heureDepart;
    private Time heureArrivee;
    private Date periodeDebut;
    private Date periodeFin;
    private String description;
    private double prix;
    
    // Constructeurs
    public Demande() {}

    public Demande(int id, int utilisateurId, String villeDepart, String villeArrivee, Time heureDepart, Time heureArrivee, Date periodeDebut, Date periodeFin, String description, double prix) {
        this.id = id;
        this.utilisateurId = utilisateurId;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.description = description;
        this.prix = prix;
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

    public String getVilleDepart() {
        return villeDepart;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public String getVilleArrivee() {
        return villeArrivee;
    }

    public void setVilleArrivee(String villeArrivee) {
        this.villeArrivee = villeArrivee;
    }

    public Time getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Time heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Time getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(Time heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public Date getPeriodeDebut() {
        return periodeDebut;
    }

    public void setPeriodeDebut(Date periodeDebut) {
        this.periodeDebut = periodeDebut;
    }

    public Date getPeriodeFin() {
        return periodeFin;
    }

    public void setPeriodeFin(Date periodeFin) {
        this.periodeFin = periodeFin;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}