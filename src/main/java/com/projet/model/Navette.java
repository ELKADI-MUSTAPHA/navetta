package com.projet.model;

public class Navette {
    private int id;
    private int societeId;
    private String villeDepart;
    private String villeArrivee;
    private String heureDepart;
    private String heureArrivee;
    private String periodeDebut;
    private String periodeFin;
    private String description;
    private int nombreAbonnes;
    private int placesDisponibles; 
    private double prix;

    // Constructeurs
    public Navette() {}

    public Navette(int id, int societeId, String villeDepart, String villeArrivee, String heureDepart, String heureArrivee, String periodeDebut, String periodeFin, String description, int nombreAbonnes, int placesDisponibles, double prix) {
        this.id = id;
        this.societeId = societeId;
        this.villeDepart = villeDepart;
        this.villeArrivee = villeArrivee;
        this.heureDepart = heureDepart;
        this.heureArrivee = heureArrivee;
        this.periodeDebut = periodeDebut;
        this.periodeFin = periodeFin;
        this.description = description;
        this.nombreAbonnes = nombreAbonnes;
        this.placesDisponibles = placesDisponibles;
        this.prix = prix;
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

    public String getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(String heureDepart) {
        this.heureDepart = heureDepart;
    }

    public String getHeureArrivee() {
        return heureArrivee;
    }

    public void setHeureArrivee(String heureArrivee) {
        this.heureArrivee = heureArrivee;
    }

    public String getPeriodeDebut() {
        return periodeDebut;
    }

    public void setPeriodeDebut(String periodeDebut) {
        this.periodeDebut = periodeDebut;
    }

    public String getPeriodeFin() {
        return periodeFin;
    }

    public void setPeriodeFin(String periodeFin) {
        this.periodeFin = periodeFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNombreAbonnes() {
        return nombreAbonnes;
    }

    public void setNombreAbonnes(int nombreAbonnes) {
        this.nombreAbonnes = nombreAbonnes;
    }

    // Getter et setter pour placesDisponibles
    public int getPlacesDisponibles() {
        return placesDisponibles;
    }

    public void setPlacesDisponibles(int placesDisponibles) {
        this.placesDisponibles = placesDisponibles;
    }
    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }
}