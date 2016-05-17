package com.example.manchotstudios.com.spring;

/**
 * Created by christian on 2016-05-16.
 */
public class TacheProjet {

    private String tahce_id;
    private String tache_nom;
    private String tache_desc;
    private String tache_adresse;
    private String tache_ville;
    private String tache_cp;
    private String tache_longitude;
    private String tache_latitude;
    private String tache_debut_prevu;
    private String tache_fin_prevu;
    private String tache_debut;
    private String tache_fin;
    private String tache_commentaire;
    private String tache_etat;
    private String tache_progression;
    private String tache_projet_id;
    private String projet_nom;
    private String projet_etat;

    public TacheProjet(String tache_id, String tache_nom, String tache_desc, String tache_adresse, String tache_ville, String tache_cp, String tache_longitude, String tache_latitude, String tache_debut_prevu, String tache_fin_prevu, String tache_debut, String tache_fin, String tache_commentaire, String tache_etat, String tache_progression, String tache_projet_id, String projet_nom, String projet_etat){
        this.setTache_id(tache_id);
        this.setTache_nom(tache_nom);
        this.setTache_desc(tache_desc);
        this.setTache_adresse(tache_adresse);
        this.setTache_ville(tache_ville);
        this.setTache_cp(tache_cp);
        this.setTache_longitude(tache_longitude);
        this.setTache_latitude(tache_latitude);
        this.setTache_debut_prevu(tache_debut_prevu);
        this.setTache_fin_prevu(tache_fin_prevu);
        this.setTache_debut(tache_debut);
        this.setTache_fin(tache_fin);
        this.setTache_commentaire(tache_commentaire);
        this.setTache_etat(tache_etat);
        this.setTache_progression(tache_progression);
        this.setTache_projet_id(tache_projet_id);
        this.setProjet_nom(projet_nom);
        this.setProjet_etat(projet_etat);
    }

    public String getTache_id() {
        return tahce_id;
    }

    public void setTache_id(String tahce_id) {
        this.tahce_id = tahce_id;
    }

    public String getTache_nom() {
        return tache_nom;
    }

    public void setTache_nom(String tache_nom) {
        this.tache_nom = tache_nom;
    }

    public String getTache_desc() {
        return tache_desc;
    }

    public void setTache_desc(String tache_desc) {
        this.tache_desc = tache_desc;
    }

    public String getTache_adresse() {
        return tache_adresse;
    }

    public void setTache_adresse(String tache_adresse) {
        this.tache_adresse = tache_adresse;
    }

    public String getTache_ville() {
        return tache_ville;
    }

    public void setTache_ville(String tache_ville) {
        this.tache_ville = tache_ville;
    }

    public String getTache_cp() {
        return tache_cp;
    }

    public void setTache_cp(String tache_cp) {
        this.tache_cp = tache_cp;
    }

    public String getTache_longitude() {
        return tache_longitude;
    }

    public void setTache_longitude(String tache_longitude) {
        this.tache_longitude = tache_longitude;
    }

    public String getTache_latitude() {
        return tache_latitude;
    }

    public void setTache_latitude(String tache_latitude) {
        this.tache_latitude = tache_latitude;
    }

    public String getTache_debut_prevu() {
        return tache_debut_prevu;
    }

    public void setTache_debut_prevu(String tache_debut_prevu) {
        this.tache_debut_prevu = tache_debut_prevu;
    }

    public String getTache_fin_prevu() {
        return tache_fin_prevu;
    }

    public void setTache_fin_prevu(String tache_fin_prevu) {
        this.tache_fin_prevu = tache_fin_prevu;
    }

    public String getTache_debut() {
        return tache_debut;
    }

    public void setTache_debut(String tache_debut) {
        this.tache_debut = tache_debut;
    }

    public String getTache_fin() {
        return tache_fin;
    }

    public void setTache_fin(String tache_fin) {
        this.tache_fin = tache_fin;
    }

    public String getTache_commentaire() {
        return tache_commentaire;
    }

    public void setTache_commentaire(String tache_commentaire) {
        this.tache_commentaire = tache_commentaire;
    }

    public String getTache_etat() {
        return tache_etat;
    }

    public void setTache_etat(String tache_etat) {
        this.tache_etat = tache_etat;
    }

    public String getTache_progression() {
        return tache_progression;
    }

    public void setTache_progression(String tache_progression) {
        this.tache_progression = tache_progression;
    }

    public String getTache_projet_id() {
        return tache_projet_id;
    }

    public void setTache_projet_id(String tache_projet_id) {
        this.tache_projet_id = tache_projet_id;
    }

    public String getProjet_nom() {
        return projet_nom;
    }

    public void setProjet_nom(String projet_nom) {
        this.projet_nom = projet_nom;
    }

    public String getProjet_etat() {
        return projet_etat;
    }

    public void setProjet_etat(String projet_etat) {
        this.projet_etat = projet_etat;
    }
}
