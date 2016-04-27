package beans;

import java.util.Date;

/**
 * Created by Amélie on 2016-04-25.
 * La classe Projet, qui contient les données relatives à un projet
 */
public class Projet {

    private int id;
    private String nom;
    private Date dateDebut;
    private Date dateFin;

    /**
     * Constructeur sans paramètres
     */
    public Projet() {}

    /**
     * Constructeur avec paramètres
     * @param id l'ID du projet dans la base de données
     * @param nom le nom du projet
     * @param dateDebut la date de début du projet
     * @param dateFin la date de fin du projet
     */
    public Projet(int id, String nom, Date dateDebut, Date dateFin) {
        this.id = id;
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
}
