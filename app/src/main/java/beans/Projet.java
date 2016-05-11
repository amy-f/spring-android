package beans;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Amélie on 2016-04-25.
 * La classe Projet, qui contient les données relatives à un projet
 */
public class Projet {

    private int id;
    private String nom;
    private int etat;
    private ArrayList<Tache> taches;

    /**
     * Constructeur sans paramètres
     */
    public Projet() {}

    public Projet(int id, String nom, int etat) {
        this.id = id;
        this.nom = nom;
        this.etat = etat;
        this.taches = new ArrayList<>();
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

    public ArrayList<Tache> getTaches() {
        return taches;
    }

    public void setTaches(ArrayList<Tache> taches) {
        this.taches = taches;
    }
}
