package com.example.manchotstudios.com.spring;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class DisplayListView extends AppCompatActivity {

    String jsonString;

    JSONObject jsonObject;
    JSONArray jsonArray;
    TacheProjetAdapter tacheProjetAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_listview_layout);
        listView = (ListView)findViewById(R.id.listview);
        tacheProjetAdapter = new TacheProjetAdapter(this,R.layout.row_layout);
        listView.setAdapter(tacheProjetAdapter);
        jsonString = getIntent().getExtras().getString("json_data");
        try {
            jsonObject = new JSONObject(jsonString);
            jsonArray = jsonObject.getJSONArray("tacheProjet");

            int count = 0;
            String tache_id, tache_nom, tache_desc, tache_adresse, tache_ville, tache_cp, tache_longitude, tache_latitude, tache_debut_prevu, tache_fin_prevu, tache_debut, tache_fin, tache_commentaire, tache_etat, tache_progression, tache_projet_id, projet_nom, projet_etat;
            while(count < jsonArray.length()){
                JSONObject JO = jsonArray.getJSONObject(count);
                tache_id = JO.getString("tache_id");
                tache_nom = JO.getString("tache_nom");
                tache_desc = JO.getString("tache_desc");
                tache_adresse = JO.getString("tache_adresse");
                tache_ville = JO.getString("tache_ville");
                tache_cp = JO.getString("tache_cp");
                tache_longitude = JO.getString("tache_longitude");
                tache_latitude = JO.getString("tache_latitude");
                tache_debut_prevu = JO.getString("tache_debut_prevu");
                tache_fin_prevu = JO.getString("tache_fin_prevu");
                tache_debut = JO.getString("tache_debut");
                tache_fin = JO.getString("tache_fin");
                tache_commentaire = JO.getString("tache_commentaire");
                tache_etat = JO.getString("tache_etst");
                tache_progression = JO.getString("tache_progression");
                tache_projet_id = JO.getString("tache_projet_id");
                projet_nom = JO.getString("projet_nom");
                projet_etat = JO.getString("projet_etat");

                TacheProjet tacheProjet = new TacheProjet(tache_id, tache_nom, tache_desc, tache_adresse, tache_ville, tache_cp, tache_longitude, tache_latitude, tache_debut_prevu, tache_fin_prevu, tache_debut, tache_fin, tache_commentaire, tache_etat, tache_progression, tache_projet_id, projet_nom, projet_etat);
                tacheProjetAdapter.add(tacheProjet);
                count ++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
