package com.emsi.Maven.JDBC.entities;
import java.util.Objects;

public class Restaurant {
    private Integer patente;
    private String nom;
    private String adresse;
    private String ville;
    private double ca;
    private String gerant;
    private String heureOuverture;
    private String heureFermeture;
    private String type;
    public static String path = "./src/main/resources/com/emsi/Maven/JDBC/Data/";

    public Restaurant(Integer patente, String nom, String adresse, String ville, double ca, String gerant, String heureOuverture, String heureFermeture, String type) {
        this.patente = patente;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.ca = ca;
        this.gerant = gerant;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.type = type;
    }

    public Restaurant(String nom, String adresse, String ville, double ca, String gerant, String heureOuverture, String heureFermeture, String type) {
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.ca = ca;
        this.gerant = gerant;
        this.heureOuverture = heureOuverture;
        this.heureFermeture = heureFermeture;
        this.type = type;
    }

    public Restaurant() {
    }

    public Integer getPatente() {
        return this.patente;
    }

    public void setPatente(Integer patente) {
        this.patente = patente;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return this.adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return this.ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public double getCa() {
        return this.ca;
    }

    public void setCa(double ca) {
        this.ca = ca;
    }

    public String getGerant() {
        return this.gerant;
    }

    public void setGerant(String gerant) {
        this.gerant = gerant;
    }

    public String getHeureOuverture() {
        return this.heureOuverture;
    }

    public void setHeureOuverture(String heureOuverture) {
        this.heureOuverture = heureOuverture;
    }

    public String getHeureFermeture() {
        return this.heureFermeture;
    }

    public void setHeureFermeture(String heureFermeture) {
        this.heureFermeture = heureFermeture;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Restaurant)) {
            return false;
        }
        Restaurant restaurant = (Restaurant) o;
        return Objects.equals(patente, restaurant.patente) && Objects.equals(nom, restaurant.nom) && Objects.equals(adresse, restaurant.adresse) && Objects.equals(ville, restaurant.ville) && ca == restaurant.ca && Objects.equals(gerant, restaurant.gerant) && Objects.equals(heureOuverture, restaurant.heureOuverture) && Objects.equals(heureFermeture, restaurant.heureFermeture) && Objects.equals(type, restaurant.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patente, nom, adresse, ville, ca, gerant, heureOuverture, heureFermeture, type);
    }

    @Override
    public String toString() {
        return "{" +
                " patente='" + getPatente() + "'" +
                ", nom='" + getNom() + "'" +
                ", adresse='" + getAdresse() + "'" +
                ", ville='" + getVille() + "'" +
                ", ca='" + getCa() + "'" +
                ", gerant='" + getGerant() + "'" +
                ", heureOuverture='" + getHeureOuverture() + "'" +
                ", heureFermeture='" + getHeureFermeture() + "'" +
                ", type='" + getType() + "'" +
                "}";
    }
}
