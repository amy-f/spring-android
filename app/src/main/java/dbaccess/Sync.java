package dbaccess;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.example.manchotstudios.com.spring.R;
import com.example.manchotstudios.com.spring.TacheProjet;
import com.example.manchotstudios.com.spring.TacheProjetAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;

import javax.net.ssl.HttpsURLConnection;

import beans.Projet;
import beans.Tache;
import handlers.ProjetHandler;
import handlers.TacheHandler;

/**
 * Created by christian on 2016-05-16.
 */
public class Sync  {

    String jsonString;
    Projet projet;
    Tache tache;
    ProjetHandler projetHandler;
    TacheHandler tacheHandler;

    JSONObject jsonObject;
    JSONArray jsonArray;
    TacheProjetAdapter tacheProjetAdapter;
    ListView listView;

    public Sync(Context c){
        projetHandler = new ProjetHandler(c);
        tacheHandler = new TacheHandler(c);
        projet = new Projet();
        tache = new Tache();
    }


    public void syncTask(String jsonString) throws Exception {
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
                tache_etat = JO.getString("tache_etat");
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

        } catch (Exception e) {
            throw e;
        }
    }

    //inspirée de stack overflow
    public void sendValue(String urlString, String idValue,String jsonString){
        try{
            URL url = new URL(urlString);
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter(idValue, jsonString);
            String query = builder.build().getEncodedQuery();

            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();

            conn.connect();

        }catch(Exception e){
            e.printStackTrace();
        }

    }


    public String getJsonFromWeb(String json_url){
        String json_string;
        try {
            URL url = new URL(json_url);
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((json_string = bufferedReader.readLine()) != null){
                stringBuilder.append(json_string + "\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
