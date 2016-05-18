package com.example.manchotstudios.com.spring;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import beans.Projet;
import beans.Tache;
import dbaccess.Sync;
import handlers.ProjetHandler;
import handlers.TacheHandler;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Tache> late;
    private ArrayList<Tache> today;
    private TacheHandler tacheHandler;

    private Spinner spinner;
    private ListView lstLate;
    private ListView lstToday;

    String jsonString;
    Sync sync;

    private ArrayList<Projet> projets = new ArrayList<>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sync = new Sync(this);

        //Retrouve les élémnents dont on a besoin
        spinner = (Spinner) findViewById(R.id.spin);
        lstLate = (ListView) findViewById(R.id.lstLate);
        lstToday = (ListView) findViewById(R.id.lstToday);

        new BackgroundTask().execute();

        //Gère le listener du spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListesTaches((Projet) spinner.getSelectedItem());
                //ajouter les valeurs dans l'adapter
                TaskAdapter adaptLate = new TaskAdapter(late);
                lstLate.setAdapter(adaptLate);
                TaskAdapter adaptToday = new TaskAdapter(today);
                lstToday.setAdapter(adaptToday);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        lstLate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Tache t = late.get(position);
                AlertDialog.Builder info = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Description de la tâche")
                        .setMessage(late.get(position).getDescription())
                        .setPositiveButton("Mettre à jour", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent myIntent = new Intent(((Dialog) dialog).getContext(), TacheActivity.class);
                                myIntent.putExtra("tache", t);
                                startActivityForResult(myIntent, 60);   //indique le code à retourner à la fin de l'activité
                            }
                        })
                        .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                info.show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (60): {
                if (resultCode == Activity.RESULT_OK) {
                    int updatedRows = data.getIntExtra("nbUpdatedRows", 0);
                    if (updatedRows > 0) {

                        Toast.makeText(getApplicationContext(), R.string.modif_tache, Toast.LENGTH_SHORT).show();

                        //Sélectionne les taches associées à chaque projet et les met dans la liste de taches du projet
                        for (Projet p : projets) {
                            ArrayList<Tache> taches = tacheHandler.selectTacheFromProjetID(p.getId());
                            p.setTaches(taches);
                        }
                        updateListesTaches((Projet) spinner.getSelectedItem());
                        TaskAdapter adaptLate = new TaskAdapter(late);
                        lstLate.setAdapter(adaptLate);
                        TaskAdapter adaptToday = new TaskAdapter(today);
                        lstToday.setAdapter(adaptToday);
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.update_tache_error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.manchotstudios.com.spring/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.example.manchotstudios.com.spring/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }


    class TaskAdapter extends ArrayAdapter<Tache> {
        /**
         * Constructeur de TaskAdaptertest
         */
        TaskAdapter(ArrayList<Tache> task) {
            super(MainActivity.this, R.layout.row, task);
        }

        /**
         * obtient la vue en cours
         *
         * @param pos         la position de la vue
         * @param convertView la view
         * @param parent      le parent (Dans notre cas la listView)
         * @return la vue en cours
         */
        public View getView(int pos, View convertView, ViewGroup parent) {
            TaskWrapper wrapper;

            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.row, parent, false);
                wrapper = new TaskWrapper(convertView);
                convertView.setTag(wrapper);
            } else {
                wrapper = (TaskWrapper) convertView.getTag();
            }
            wrapper.setTask(getItem(pos));
            return convertView;
        }
    }

    class TaskWrapper {
        private TextView title = null;
        private View row = null;

        /**
         * Constructeur
         *
         * @param row le layout de rangée
         */
        TaskWrapper(View row) {
            this.row = row;
        }

        /**
         * Obtient la Textview de titre
         *
         * @return la Textview de titre
         */
        public TextView getTitle() {
            if (title == null) {
                title = (TextView) row.findViewById(R.id.lblTask);
            }
            return title;
        }

        /**
         * Met les valeur de l'objet task dans la rangée
         *
         * @param t la tâche à insérer
         */
        public void setTask(Tache t) {
            getTitle().setText(t.getNom());
        }
    }

    //Classe du SpinAdapter
    //Adapter pour le projet
    class SpinAdapter extends ArrayAdapter<Projet> {

        // Your sent context
        private Context context;
        // Your custom values for the spinner (User)
        private ArrayList<Projet> values;

        public SpinAdapter(Context context, int textViewResourceId, ArrayList<Projet> values) {
            super(context, textViewResourceId, values);
            this.context = context;
            this.values = values;
        }

        public int getCount() {
            return values.size();
        }

        public Projet getItem(int position) {
            return values.get(position);
        }

        public long getItemId(int position) {
            return position;
        }


        // And the "magic" goes here
        // This is for the "passive" state of the spinner
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
            TextView label = new TextView(context);
            label.setTextColor(Color.WHITE);
            // Then you can get the current item using the values array (Users array) and the current position
            // You can NOW reference each method you has created in your bean object (User class)
            label.setText(values.get(position).getNom());

            // And finally return your dynamic (or custom) view for each spinner item
            return label;
        }

        // And here is when the "chooser" is popped up
        // Normally is the same view, but you can customize it if you want
        @Override
        public View getDropDownView(int position, View convertView,
                                    ViewGroup parent) {
            TextView label = new TextView(context);
            label.setTextColor(Color.BLACK);
            label.setText(values.get(position).getNom());

            return label;
        }
    }

    public void updateListesTaches(Projet p) {
        ArrayList<Tache> taches = p.getTaches();
        Date todayDate = new Date();
        late = new ArrayList<>();
        today = new ArrayList<>();
        for (Tache t : taches) {
            if (todayDate.after(t.getDateDebutPrevue()) && t.getDateFinReelle() == null) {
                late.add(t);
            } else if (todayDate == t.getDateDebutPrevue() && t.getDateFinReelle() == null) {
                today.add(t);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up f, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if (id == R.id.synchronisation) {
            new BackgroundTask().execute();
        }

        if (id == R.id.deconnecxion) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    String json_string;

    public void getJson(View view) {
        new BackgroundTask().execute();
    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{

       String json_url;
        @Override
        protected void onPreExecute(){
           json_url = "http://aacspring.xyz/controllers/ctrl_android.php?getProjet=2";
        }

        @Override
        protected String doInBackground(Void... voids){
            return sync.getJsonFromWeb(json_url);
        }

        @Override
        protected void onProgressUpdate(Void... values){
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result){

            try{
                sync.syncTask(result);
                populate();

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void populate(){

        ProjetHandler projetHandler = new ProjetHandler(getApplicationContext());
        tacheHandler = new TacheHandler(getApplicationContext());
        late = new ArrayList<>();
        today = new ArrayList<>();

        //Va chercher les informations dans la base de données et les insère dans le spinner
        ArrayList<Projet> projetsData = projetHandler.selectAllProjet();

        //Met les projets associés dans le spinner
        if(!projets.isEmpty()) projets.removeAll(projets);
        for (Projet projet : projetsData) {
            projets.add(projet);
        }

        //Sélectionne les taches associées à chaque projet et les met dans la liste de taches du projet

        for (Projet p : projets) {
            ArrayList<Tache> taches = tacheHandler.selectTacheFromProjetID(p.getId());
            p.setTaches(taches);
        }

        //Sync'occupe de chaque row du spinner
        SpinAdapter adapter = new SpinAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, projets);

        //met un forme au spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        updateListesTaches((Projet) spinner.getSelectedItem());

        TaskAdapter adaptToday = new TaskAdapter(today);
        lstToday.setAdapter(adaptToday);

        Toast.makeText(getApplicationContext(), "Synchronisation effectuée avec succès!", Toast.LENGTH_SHORT).show();
    }
}
