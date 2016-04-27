package beans;

/**
 * Created by Amélie on 2016-04-25.
 * Les différents types de tâches possibles. (ex: rendez-vous, travail externe, travail interne, etc.)
 */
public class TypeTache {

    private int id;
    private String nom;
    private String description;

    public TypeTache() {

    }

    public TypeTache(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
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
}
