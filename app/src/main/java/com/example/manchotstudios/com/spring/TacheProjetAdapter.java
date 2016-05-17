package com.example.manchotstudios.com.spring;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by christian on 2016-05-16.
 */
public class TacheProjetAdapter  extends ArrayAdapter{

    List list = new ArrayList();
    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     */
    public TacheProjetAdapter(Context context, int resource) {
        super(context, resource);
    }


    public void add(TacheProjet object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row = convertView;
        TacheProjetHolder tacheProjetHolder;
        if(row == null){
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row_layout,parent,false);
            tacheProjetHolder = new TacheProjetHolder();
            tacheProjetHolder.tx_tache_id = (TextView) row.findViewById(R.id.tx_tache_id);
            tacheProjetHolder.tx_tache_nom = (TextView) row.findViewById(R.id.tx_tache_nom);
            tacheProjetHolder.tx_tache_desc = (TextView) row.findViewById(R.id.tx_tache_desc);
            tacheProjetHolder.tx_tache_adresse = (TextView) row.findViewById(R.id.tx_tache_adresse);
            tacheProjetHolder.tx_tache_ville = (TextView) row.findViewById(R.id.tx_tache_ville);
            tacheProjetHolder.tx_tache_cp = (TextView) row.findViewById(R.id.tx_tache_cp);
            tacheProjetHolder.tx_tache_longitude = (TextView) row.findViewById(R.id.tx_tache_longitude);
            tacheProjetHolder.tx_tache_latitude = (TextView) row.findViewById(R.id.tx_tache_latitude);
            tacheProjetHolder.tx_tache_debut_prevu = (TextView) row.findViewById(R.id.tx_tache_debut_prevu);
            tacheProjetHolder.tx_tache_fin_prevu = (TextView) row.findViewById(R.id.tx_tache_fin_prevu);
            tacheProjetHolder.tx_tache_debut = (TextView) row.findViewById(R.id.tx_tache_debut);
            tacheProjetHolder.tx_tache_fin = (TextView) row.findViewById(R.id.tx_tache_fin);
            tacheProjetHolder.tx_tache_commentaire = (TextView) row.findViewById(R.id.tx_tache_commentaire);
            tacheProjetHolder.tx_tache_etat = (TextView) row.findViewById(R.id.tx_tache_etat);
            tacheProjetHolder.tx_tache_progression = (TextView) row.findViewById(R.id.tx_tache_progression);
            tacheProjetHolder.tx_tache_projet_id = (TextView) row.findViewById(R.id.tx_tache_projet_id);
            tacheProjetHolder.tx_projet_nom = (TextView) row.findViewById(R.id.tx_projet_nom);
            tacheProjetHolder.tx_projet_etat = (TextView) row.findViewById(R.id.tx_projet_etat);
            row.setTag(tacheProjetHolder);
        }
        else{
            tacheProjetHolder = (TacheProjetHolder)row.getTag();
        }

        TacheProjet tacheProjet = (TacheProjet)this.getItem(position);
        tacheProjetHolder.tx_tache_id.setText(tacheProjet.getTache_id());
        tacheProjetHolder.tx_tache_nom.setText(tacheProjet.getTache_nom());
        tacheProjetHolder.tx_tache_desc.setText(tacheProjet.getTache_desc());
        tacheProjetHolder.tx_tache_adresse.setText(tacheProjet.getTache_adresse());
        tacheProjetHolder.tx_tache_ville.setText(tacheProjet.getTache_ville());
        tacheProjetHolder.tx_tache_cp.setText(tacheProjet.getTache_cp());
        tacheProjetHolder.tx_tache_longitude.setText(tacheProjet.getTache_longitude());
        tacheProjetHolder.tx_tache_latitude.setText(tacheProjet.getTache_latitude());
        tacheProjetHolder.tx_tache_debut_prevu.setText(tacheProjet.getTache_debut_prevu());
        tacheProjetHolder.tx_tache_fin_prevu.setText(tacheProjet.getTache_fin_prevu());
        tacheProjetHolder.tx_tache_debut.setText(tacheProjet.getTache_debut());
        tacheProjetHolder.tx_tache_fin.setText(tacheProjet.getTache_fin());
        tacheProjetHolder.tx_tache_commentaire.setText(tacheProjet.getTache_commentaire());
        tacheProjetHolder.tx_tache_etat.setText(tacheProjet.getTache_etat());
        tacheProjetHolder.tx_tache_progression.setText(tacheProjet.getTache_progression());
        tacheProjetHolder.tx_tache_projet_id.setText(tacheProjet.getTache_projet_id());
        tacheProjetHolder.tx_projet_nom.setText(tacheProjet.getProjet_nom());
        tacheProjetHolder.tx_projet_etat.setText(tacheProjet.getProjet_etat());


        return row;
    }

    static class TacheProjetHolder{
        TextView tx_tache_id, tx_tache_nom, tx_tache_desc, tx_tache_adresse, tx_tache_ville, tx_tache_cp, tx_tache_longitude, tx_tache_latitude, tx_tache_debut_prevu, tx_tache_fin_prevu, tx_tache_debut, tx_tache_fin, tx_tache_commentaire, tx_tache_etat, tx_tache_progression, tx_tache_projet_id, tx_projet_nom, tx_projet_etat;
    }
}
