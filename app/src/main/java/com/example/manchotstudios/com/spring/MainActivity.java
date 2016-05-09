package com.example.manchotstudios.com.spring;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.DataSetObserver;
import android.os.Bundle;
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

import beans.Projet;
import handlers.ProjetHandler;
import handlers.TacheHandler;


public class  MainActivity extends AppCompatActivity {

    private ArrayList<Task> late;
    private ArrayList<Task> today;
    private ArrayList<Task> rdv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Va chercher les handlers
        ProjetHandler projetHandler = new ProjetHandler(getApplicationContext());
        TacheHandler tacheHandler = new TacheHandler(getApplicationContext());

        //TODO: Enlever après les tests. Ne faire qu'une seule fois!!
        Projet droidProjet = new Projet(1, "TP Android", 1);
        Projet webProjet = new Projet(2, "TP Web PHP", 1);
        projetHandler.insertProjet(droidProjet);
        projetHandler.insertProjet(webProjet);

        //Va chercher les informations dans la base de données


        //ajoute des tâches fictive dans l'array list late
        //// TODO: 2016-04-26 communiquer avec la bd
        late = new ArrayList<Task>();
        late.add(new Task("Tondre la pelouse", false, false));
        late.add(new Task("Torturer le chat", false, false));
        late.add(new Task("Peindre le garage", false, false));

        //// TODO: 2016-04-26 communiquer avec la bd
        today = new ArrayList<Task>();
        today.add(new Task("Acheter du bois", false, false));
        today.add(new Task("Faire le ménage", false, false));
        today.add(new Task("Laver le chien", false, false));

        //// TODO: 2016-04-26 communiquer avec la bd
        rdv = new ArrayList<Task>();
        rdv.add(new Task("Acheter du bois", false, false));
        rdv.add(new Task("Faire le ménage", false, false));
        rdv.add(new Task("Laver le chien", false, false));

        //Retrouve les élémnents dont on a besoin
        Spinner spin = (Spinner) findViewById(R.id.spin);
        ListView lstLate = (ListView) findViewById(R.id.lstLate);
        ListView lstToday = (ListView) findViewById(R.id.lstToday);
        ListView lstRDV = (ListView) findViewById(R.id.lstMeet);

        //S'occupe de chaque row du spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);

        //met des valeurs fictive dans le spinner
        //// TODO: 2016-04-26 le faire communiquer avec la bd (valeurs fictive)
        adapter.add("Coop");
        adapter.add("TP Final");
        adapter.add("Alexander");

        //met un forme au spinner
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);

        //ajouter les valeurs dans l'adapter
        TaskAdapter adaptLate = new TaskAdapter(late);
        lstLate.setAdapter(adaptLate);

        TaskAdapter adaptToday = new TaskAdapter(today);
        lstToday.setAdapter(adaptToday);

        TaskAdapter adaptRDV = new TaskAdapter(rdv);
        lstRDV.setAdapter(adaptRDV);

        lstLate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder info = new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Description de la tâche")
                        .setMessage(late.get(position).getTitle())
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                info.show();
            }
        });
    }



    class TaskAdapter extends ArrayAdapter<Task>{
        /**
         * Constructeur de TaskAdaptertest
         */
        TaskAdapter(ArrayList<Task> task){super(MainActivity.this, R.layout.row,task);}

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
        public CheckBox getStarted(){
            if(started == null){
                started = (CheckBox) row.findViewById(R.id.chkStarted);
            }
            return started;
        }

        /**
         * Obtient le checkbox de done
         * @return le checkbox de done
         */
        public CheckBox getDone() {
            if (done == null) {
                done = (CheckBox) row.findViewById(R.id.chkDone);
            }
            return done;
        }

        /**
         * Met les valeur de l'objet task dans la rangée
         * @param t la tâche à insérer
         */
        public void setTask(Task t){
            getTitle().setText(t.getTitle());
            getStarted().setChecked(t.getStarted());
            getDone().setChecked(t.getDone());
        }
    }

}
