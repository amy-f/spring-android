package dbaccess;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.manchotstudios.com.spring.R;
import com.example.manchotstudios.com.spring.TacheProjet;
import com.example.manchotstudios.com.spring.TacheProjetAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import beans.Projet;
import beans.Tache;
import handlers.ProjetHandler;
import handlers.TacheHandler;

/**
 * Created by christian on 2016-05-16.
 */
public class Sync extends AppCompatActivity {

    String jsonString;
    Projet projet;
    Tache tache;
    ProjetHandler projetHandler;
    TacheHandler tacheHandler;

    JSONObject jsonObject;
    JSONArray jsonArray;
    TacheProjetAdapter tacheProjetAdapter;
    ListView listView;

    public Sync(){

    }


    public void init(String jsonString) {
        try {
            jsonArray = new JSONArray(jsonString);

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

                projet.setId(Integer.parseInt(tache_projet_id));
                projet.setNom(projet_nom);
                projet.setEtat(Integer.parseInt(projet_etat));
                projetHandler.insertProjet(projet);

                tache.setId(Integer.parseInt(tache_id));
                tache.setNom(tache_nom);
                tache.setDescription(tache_desc);
                tache.setAdresse(tache_adresse);
                tache.setCodePostal(tache_cp);
                tache.setVille(tache_ville);
                tache.setLongitude(Double.parseDouble(tache_longitude));
                tache.setLatitude(Double.parseDouble(tache_latitude));
                Date ddp = tache.convertStringToDate(tache_debut_prevu);
                tache.setDateDebutPrevue(ddp);
                Date dfp = tache.convertStringToDate(tache_fin_prevu);
                tache.setDateFinPrevue(dfp);
                Date dd = tache.convertStringToDate(tache_debut);
                tache.setDateDebutReelle(dd);
                Date df = tache.convertStringToDate(tache_fin);
                tache.setDateFinReelle(df);
                tache.setCommentaire(tache_commentaire);
                tache.setEtat(Integer.parseInt(tache_etat));
                tache.setProgression(Float.parseFloat(tache_progression));
                tache.setProjetID(Integer.parseInt(tache_projet_id));
                tacheHandler.insertTache(tache);


                count ++;
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
