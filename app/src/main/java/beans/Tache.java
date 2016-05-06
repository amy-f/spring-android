package beans;

import java.util.Date;

/**
 * Created by Amélie on 2016-04-25.
 * La définition de la tâche associée à un projet donné
 */
public class Tache {

    private int id;
    private String nom;
    private String description;
    private String adresse;
    private float longitude;
    private float latitude;
    private Date dateDebutPrevue;
    private Date dateDebutReelle;
    private Date dateFinPrevue;
    private Date dateFinReelle;
    private String commentaire;
    private int etat;
    private int projetID;

    public Tache() {}

    public Tache(int id, String nom, String description, String adresse, float longitude, float latitude, Date dateDebutPrevue, Date dateDebutReelle, Date dateFinPrevue, Date dateFinReelle, String commentaire, int etat, int projetID) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dateDebutPrevue = dateDebutPrevue;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinPrevue = dateFinPrevue;
        this.dateFinReelle = dateFinReelle;
        this.commentaire = commentaire;
        this.etat = etat;
        this.projetID = projetID;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateDebutPrevue() {
        return dateDebutPrevue;
    }

    public void setDateDebutPrevue(Date dateDebutPrevue) {
        this.dateDebutPrevue = dateDebutPrevue;
    }

    public Date getDateDebutReelle() {
        return dateDebutReelle;
    }

    public void setDateDebutReelle(Date dateDebutReelle) {
        this.dateDebutReelle = dateDebutReelle;
    }

    public Date getDateFinPrevue() {
        return dateFinPrevue;
    }

    public void setDateFinPrevue(Date dateFinPrevue) {
        this.dateFinPrevue = dateFinPrevue;
    }

    public Date getDateFinReelle() {
        return dateFinReelle;
    }

    public void setDateFinReelle(Date dateFinReelle) {
        this.dateFinReelle = dateFinReelle;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getProjetID() {
        return projetID;
    }

    public void setProjetID(int projetID) {
        this.projetID = projetID;
    }
}
