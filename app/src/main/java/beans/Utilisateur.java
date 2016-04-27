package beans;

/**
 * Created by Amélie on 2016-04-25.
 * Les données concernant un utilisateur du programme.
 */
public class Utilisateur {

    //TODO: Relier droits à fonction?
    private int id;
    private String prenom;
    private String nom;
    private String user;
    private String password;
    private String droit;
    private int fonctionID;

    public Utilisateur() {

    }

    public Utilisateur(int id, String prenom, String nom, String user, String password, String droit, int fonctionID) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.user = user;
        this.password = password;
        this.droit = droit;
        this.fonctionID = fonctionID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDroit() {
        return droit;
    }

    public void setDroit(String droit) {
        this.droit = droit;
    }

    public int getFonctionID() {
        return fonctionID;
    }

    public void setFonctionID(int fonctionID) {
        this.fonctionID = fonctionID;
    }
}
