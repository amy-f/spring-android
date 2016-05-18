package com.example.manchotstudios.com.spring;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import android.preference.PreferenceManager;
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

import java.util.ArrayList;
import java.util.Date;

import beans.Projet;
import beans.Tache;
import handlers.ProjetHandler;
import handlers.TacheHandler;


public class MainActivity extends AppCompatActivity {

    private ArrayList<Tache> late;
    private ArrayList<Tache> today;
    private ProjetHandler projetHandler;
    private TacheHandler tacheHandler;

    private Spinner spinner;
    private ListView lstLate;
    private ListView lstToday;
    private TextView txtHelloMessage;
    private SharedPreferences prefs;

    private ArrayList<Projet> projets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initialise les variables du projet
        projetHandler = new ProjetHandler(getApplicationContext());
        tacheHandler = new TacheHandler(getApplicationContext());
        late = new ArrayList<>();
        today = new ArrayList<>();
        spinner = (Spinner) findViewById(R.id.spin);
        lstLate = (ListView) findViewById(R.id.lstLate);
        lstToday = (ListView) findViewById(R.id.lstToday);
        txtHelloMessage = (TextView) findViewById(R.id.txtHelloMessage);

        //Gère le listener du spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListesTaches((Projet) spinner.getSelectedItem());
                //ajouter les valeurs dans l'adapter
                TacheAdapter adaptLate = new TacheAdapter(late);
                lstLate.setAdapter(adaptLate);
                TacheAdapter adaptToday = new TacheAdapter(today);
                lstToday.setAdapter(adaptToday);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //S'occupe de chaque row du spinner
        SpinAdapter adapter = new SpinAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, projets);

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

        //Vérifie si l'utilisateur est bien connecté et/ou si l'utilisateur ouvre l'app pour la première fois.
        //Si oui, il renvoie à une page de login
        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if (prefs.getBoolean("firstUse", true) || prefs.getBoolean("isLoggedIn", false) ) {
            Intent myIntent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivityForResult(myIntent, 30);
        }
    }

    public void initElements() {

        //Va chercher les informations dans la base de données et les insère dans le spinner
        ArrayList<Projet> projetsData = projetHandler.selectAllProjet();

        //Met les projets associés dans le spinner
        for (Projet projet : projetsData) {
            projets.add(projet);
        }

        //Sélectionne les taches associées à chaque projet et les met dans la liste de taches du projet
        for (Projet p : projets) {
            ArrayList<Tache> taches = tacheHandler.selectTacheFromProjetID(p.getId());
            p.setTaches(taches);
        }

        //Retrouve les élémnents dont on a besoin
        spinner = (Spinner) findViewById(R.id.spin);
        lstLate = (ListView) findViewById(R.id.lstLate);
        lstToday = (ListView) findViewById(R.id.lstToday);
        txtHelloMessage = (TextView) findViewById(R.id.txtHelloMessage);

        //Change le message de bienvenue selon l'utilisateur présentement connecté
        txtHelloMessage.setText("Bonjour " + prefs.getString("utilisateur_nom", "Utilisateur") + "!");

        //Gère le listener du spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateListesTaches((Projet) spinner.getSelectedItem());
                //ajouter les valeurs dans l'adapter
                TacheAdapter adaptLate = new TacheAdapter(late);
                lstLate.setAdapter(adaptLate);
                TacheAdapter adaptToday = new TacheAdapter(today);
                lstToday.setAdapter(adaptToday);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //S'occupe de chaque row du spinner
        SpinAdapter adapter = new SpinAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, projets);

        //met un forme au spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        updateListesTaches((Projet) spinner.getSelectedItem());

        //ajouter les valeurs dans l'adapter
        TacheAdapter adaptLate = new TacheAdapter(late);
        lstLate.setAdapter(adaptLate);

        TacheAdapter adaptToday = new TacheAdapter(today);
        lstToday.setAdapter(adaptToday);

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
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (30) : {
                if (resultCode == Activity.RESULT_OK) {
                    if (!data.getBooleanExtra("oldFirstUseValue", false)) {
                        //Insère les données dans le projet (ONE TIME ONLY!)
                        Projet droidProjet = new Projet(1, "TP Android", 1);
                        Projet webProjet = new Projet(2, "TP Web PHP", 1);
                        ArrayList<Tache> droidTaches = new ArrayList<>();
                        ArrayList<Tache> webTaches = new ArrayList<>();
                        droidTaches.add(new Tache(1, "Interface Android", "Préparation de l'interface Android", "475 rue du Cégep", "Sherbrooke", "J1K 2Z3",
                                45.411185, -71.886196, new Date(), null, new Date(), null, null, 1, 0, droidProjet.getId()));
                        droidTaches.add(new Tache(2, "Base de données", "Préparation et tests de la base de données SQLite", "475 rue du Cégep", "Sherbrooke", "J1K 2Z3",
                                45.411185, -71.886196, new Date(), null, new Date(), null, null, 1, 0, droidProjet.getId()));
                        droidTaches.add(new Tache(3, "Synchronisation", "Synchronisation avec la base de données Web", "475 rue du Cégep", "Sherbrooke", "J1K 2Z3",
                                45.411185, -71.886196, new Date(), null, new Date(), null, null, 1, 0, droidProjet.getId()));
                        webTaches.add(new Tache(4, "Interface Web", "Préparation de l'interface Web", "475 rue du Cégep","Sherbrooke", "J1K 2Z3",
                                45.411185, -71.886196, new Date(), null, new Date(), null, null, 1, 0, webProjet.getId()));
                        webTaches.add(new Tache(5, "Base de données", "Préparation de la base de données mySQL", "475 rue du Cégep", "Sherbrooke", "J1K 2Z3",
                                45.411185, -71.886196, new Date(), null, new Date(), null, null, 1, 0, webProjet.getId()));
                        projetHandler.insertProjet(droidProjet);
                        projetHandler.insertProjet(webProjet);
                        for (int i = 0; i < droidTaches.size(); i++) {
                            tacheHandler.insertTache(droidTaches.get(i));
                        }
                        for (int i = 0; i < webTaches.size(); i++) {
                            tacheHandler.insertTache(webTaches.get(i));
                        }
                    }
                    initElements();
                }
                break;
            }
            case (60) : {
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
                        TacheAdapter adaptLate = new TacheAdapter(late);
                        lstLate.setAdapter(adaptLate);
                        TacheAdapter adaptToday = new TacheAdapter(today);
                        lstToday.setAdapter(adaptToday);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.update_tache_error, Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            }
        }
    }



    class TacheAdapter extends ArrayAdapter<Tache>{
        /**
         * Constructeur de TaskAdaptertest
         */
        TacheAdapter(ArrayList<Tache> task){super(MainActivity.this, R.layout.row,task);}

        /**
         * obtient la vue en cours
         * @param pos la position de la vue
         * @param convertView la view
         * @param parent le parent (Dans notre cas la listView)
         * @return la vue en cours
         */
        public View getView(int pos, View convertView, ViewGroup parent){
            TacheWrapper wrapper;

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.row, parent, false);
                wrapper = new TacheWrapper(convertView);
                convertView.setTag(wrapper);
            }else{
                wrapper = (TacheWrapper) convertView.getTag();
            }
            wrapper.setTask(getItem(pos));
            return convertView;
        }
    }

    class TacheWrapper {
        private TextView title = null;
        private View row = null;

        /**
         * Constructeur
         * @param row le layout de rangée
         */
        TacheWrapper(View row){this.row = row;}

        /**
         * Obtient la Textview de titre
         * @return la Textview de titre
         */
        public TextView getTitle(){
            if(title == null){
                title = (TextView) row.findViewById(R.id.lblTask);
            }
            return title;
        }

        /**
         * Met les valeur de l'objet task dans la rangée
         * @param t la tâche à insérer
         */
        public void setTask(Tache t){
            getTitle().setText(t.getNom());
        }
    }

    //Classe du SpinAdapter
    //Adapter pour le projet
    class SpinAdapter extends ArrayAdapter<Projet> {

        private Context context;
        private ArrayList<Projet> values;

        public SpinAdapter(Context context, int textViewResourceId, ArrayList<Projet> values) {
            super(context, textViewResourceId, values);
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //Création d'un TextView dynamique pour la valeur du spinner
            TextView label = new TextView(context);
            label.setTextColor(Color.WHITE);
            label.setText(values.get(position).getNom());

            // And finally return your dynamic (or custom) view for each spinner item
            return label;
        }

        //Gère la vue du menu déroulant lorsqu'il est déroulé
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
            }
            else if (todayDate == t.getDateDebutPrevue() && t.getDateFinReelle() == null) {
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
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        if(id == R.id.synchronisation){
            return true;
        }

        if(id == R.id.deconnecxion){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
