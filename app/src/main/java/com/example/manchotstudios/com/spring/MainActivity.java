package com.example.manchotstudios.com.spring;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
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
    //private ArrayList<Task> rdv;

    private ArrayList<Projet> projets = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Va chercher les handlers
        ProjetHandler projetHandler = new ProjetHandler(getApplicationContext());
        TacheHandler tacheHandler = new TacheHandler(getApplicationContext());
        late = new ArrayList<>();
        today = new ArrayList<>();

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
        Spinner spinner = (Spinner) findViewById(R.id.spin);
        ListView lstLate = (ListView) findViewById(R.id.lstLate);
        ListView lstToday = (ListView) findViewById(R.id.lstToday);
        //ListView lstRDV = (ListView) findViewById(R.id.lstMeet);

        //Gère le listener du spinner
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Spinner spinner = (Spinner) findViewById(R.id.spin);
                ListView lstLate = (ListView) findViewById(R.id.lstLate);
                ListView lstToday = (ListView) findViewById(R.id.lstToday);

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

        //S'occupe de chaque row du spinner
        SpinAdapter adapter = new SpinAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, projets);

        //met un forme au spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        updateListesTaches((Projet) spinner.getSelectedItem());

        //ajouter les valeurs dans l'adapter
        TaskAdapter adaptLate = new TaskAdapter(late);
        lstLate.setAdapter(adaptLate);

        TaskAdapter adaptToday = new TaskAdapter(today);
        lstToday.setAdapter(adaptToday);

        //TaskAdapter adaptRDV = new TaskAdapter(rdv);
        //lstRDV.setAdapter(adaptRDV);

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
                                startActivity(myIntent);
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



    class TaskAdapter extends ArrayAdapter<Tache>{
        /**
         * Constructeur de TaskAdaptertest
         */
        TaskAdapter(ArrayList<Tache> task){super(MainActivity.this, R.layout.row,task);}

        /**
         * obtient la vue en cours
         * @param pos la position de la vue
         * @param convertView la view
         * @param parent le parent (Dans notre cas la listView)
         * @return la vue en cours
         */
        public View getView(int pos, View convertView, ViewGroup parent){
            TaskWrapper wrapper;

            if (convertView == null){
                convertView = getLayoutInflater().inflate(R.layout.row, parent, false);
                wrapper = new TaskWrapper(convertView);
                convertView.setTag(wrapper);
            }else{
                wrapper = (TaskWrapper) convertView.getTag();
            }
            wrapper.setTask(getItem(pos));
            return convertView;
        }
    }

    class TaskWrapper{
        private TextView title = null;
        private CheckBox started = null;
        private CheckBox done = null;
        private View row = null;

        /**
         * Constructeur
         * @param row le layout de rangée
         */
        TaskWrapper(View row){this.row = row;}

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
         * Obtient le checkbox de started
         * @return le checkbox de started
         */
       /* public CheckBox getStarted(){
            if(started == null){
                started = (CheckBox) row.findViewById(R.id.chkStarted);
            }
            return started;
        }

        *//**
         * Obtient le checkbox de done
         * @return le checkbox de done
         *//*
        public CheckBox getDone() {
            if (done == null) {
                done = (CheckBox) row.findViewById(R.id.chkDone);
            }
            return done;
        }*/

        /**
         * Met les valeur de l'objet task dans la rangée
         * @param t la tâche à insérer
         */
        public void setTask(Tache t){
            getTitle().setText(t.getNom());
           /* getStarted().setChecked(t.getDateDebutReelle() != null);
            getDone().setChecked(t.getDateFinReelle() != null);*/
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

        public int getCount(){
            return values.size();
        }

        public Projet getItem(int position){
            return values.get(position);
        }

        public long getItemId(int position){
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
            if (todayDate.after(t.getDateDebutPrevue())) {
                late.add(t);
            }
            else if (todayDate == t.getDateDebutPrevue()) {
                today.add(t);
            }
        }
    }

}
