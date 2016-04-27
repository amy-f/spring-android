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
    private Date dateDebutPrevue;
    private Date dateDebutReelle;
    private Date dateFinPrevue;
    private Date dateFinReelle;
    private String commentaire;
    private int typeTacheID;
    private int projetID;

    public Tache() {}

    public Tache(int id, String nom, String description, String adresse, Date dateDebutPrevue, Date dateDebutReelle, Date dateFinPrevue, Date dateFinReelle, String commentaire, int typeTacheID, int projetID) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.dateDebutPrevue = dateDebutPrevue;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinPrevue = dateFinPrevue;
        this.dateFinReelle = dateFinReelle;
        this.commentaire = commentaire;
        this.typeTacheID = typeTacheID;
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

    public int getTypeTacheID() {
        return typeTacheID;
    }

    public void setTypeTacheID(int typeTacheID) {
        this.typeTacheID = typeTacheID;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getProjetID() {
        return projetID;
    }

    public void setProjetID(int projetID) {
        this.projetID = projetID;
    }
}
