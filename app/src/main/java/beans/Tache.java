package beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Amélie on 2016-04-25.
 * La définition de la tâche associée à un projet donné
 */
public class Tache implements Parcelable{

    private int id;
    private String nom;
    private String description;
    private String adresse;
    private String codePostal;
    private String ville;
    private double longitude;
    private double latitude;
    private Date dateDebutPrevue;
    private Date dateDebutReelle;
    private Date dateFinPrevue;
    private Date dateFinReelle;
    private String commentaire;
    private int etat;
    private float progression;
    private int projetID;

    public Tache() {}

    //TODO: This constructor is not good, it lacks zip code and city. Remove after tests.
    public Tache(int id, String nom, String description, String adresse, double longitude, double latitude, Date dateDebutPrevue, Date dateDebutReelle, Date dateFinPrevue, Date dateFinReelle, String commentaire, int etat, float progression, int projetID) {
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
        this.progression = progression;
        this.projetID = projetID;
    }

    public Tache(int id, String nom, String description, String adresse, String codePostal, String ville, double longitude, double latitude, Date dateDebutPrevue, Date dateDebutReelle, Date dateFinPrevue, Date dateFinReelle, String commentaire, int etat, float progression, int projetID) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.codePostal = codePostal;
        this.ville = ville;
        this.longitude = longitude;
        this.latitude = latitude;
        this.dateDebutPrevue = dateDebutPrevue;
        this.dateDebutReelle = dateDebutReelle;
        this.dateFinPrevue = dateFinPrevue;
        this.dateFinReelle = dateFinReelle;
        this.commentaire = commentaire;
        this.etat = etat;
        this.progression = progression;
        this.projetID = projetID;
    }

    public static final Parcelable.Creator<Tache> CREATOR = new Parcelable.Creator<Tache>()
    {
        @Override
        public Tache createFromParcel(Parcel source)
        {
            return new Tache(source);
        }

        @Override
        public Tache[] newArray(int size)
        {
            return new Tache[size];
        }
    };

    public Tache(Parcel in) {
        this.id = in.readInt();
        this.nom = in.readString();
        this.description = in.readString();
        this.adresse = in.readString();
        //this.codePostal = in.readString();  //TODO: Uncomment after tests
        //this.ville = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.dateDebutPrevue = convertStringToDate(in.readString());
        this.dateDebutReelle = convertStringToDate(in.readString());
        this.dateFinPrevue = convertStringToDate(in.readString());
        this.dateFinReelle = convertStringToDate(in.readString());
        this.commentaire = in.readString();
        this.etat = in.readInt();
        this.progression = in.readFloat();
        this.projetID = in.readInt();
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public float getProgression() {
        return progression;
    }

    public void setProgression(float progression) {
        this.progression = progression;
    }

    public int getProjetID() {
        return projetID;
    }

    public void setProjetID(int projetID) {
        this.projetID = projetID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(nom);
        dest.writeString(description);
        dest.writeString(adresse);
        //dest.writeString(codePostal);     //TODO: Uncomment after tests
        //dest.writeString(ville);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
        dest.writeString(convertDateToString(dateDebutPrevue));
        dest.writeString(convertDateToString(dateDebutReelle));
        dest.writeString(convertDateToString(dateFinPrevue));
        dest.writeString(convertDateToString(dateFinReelle));
        dest.writeString(commentaire);
        dest.writeInt(etat);
        dest.writeFloat(progression);
        dest.writeInt(projetID);
    }

    private String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (date == null) {
            return "";
        }
        else {
            return df.format(date);
        }
    }

    private Date convertStringToDate(String str) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (str.equals("")) {
            return null;
        }
        else {
            Date d;
            try {
                d = df.parse(str);
                return d;
            }
            catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }
}
