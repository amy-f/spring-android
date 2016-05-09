package beans;

import java.util.Date;

/**
 * Created by Amélie on 2016-04-25.
 * La classe Projet, qui contient les données relatives à un projet
 */
public class Projet {

    private int id;
    private String nom;
    private int etat;

    /**
     * Constructeur sans paramètres
     */
    public Projet() {}

    /**
     * Constructeur avec paramètres
     * @param id l'ID du projet dans la base de données
     * @param nom le nom du projet
     */
    public Projet(int id, String nom, Date dateDebut, Date dateFin) {
        this.id = id;
        this.nom = nom;

    }

    public Projet(int id, String nom, int etat) {
        this.id = id;
        this.nom = nom;
        this.etat = etat;
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

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }
}
