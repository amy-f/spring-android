package com.example.manchotstudios.com.spring;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;

import beans.Tache;
import handlers.TacheHandler;

public class TacheActivity extends AppCompatActivity {

    Tache maTache;
    TacheHandler maTacheHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache);

        //Prend les données de la tâche passée en paramètre
        maTache = getIntent().getExtras().getParcelable("tache");
        maTacheHandler = new TacheHandler(getApplicationContext());

        //Change la valeur du titre
        TextView nomTache = (TextView) findViewById(R.id.lblNomTache);
        nomTache.setText(maTache.getNom());

        EditText commentaire = (EditText) findViewById(R.id.editText);
        commentaire.setText(maTache.getCommentaire());

        //Met à jour la valeur de base et les valeurs d'incrémentation de la progression de la tache
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        TextView seekBarText = (TextView) findViewById(R.id.seekBarValue);
        int progression = (int) (maTache.getProgression() * 100);
        seekBar.setProgress(progression);
        seekBar.setMax(100);
        seekBarText.setText(String.valueOf(progression) + "%");
        seekBar.incrementProgressBy(10);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                TextView seekBarText = (TextView) findViewById(R.id.seekBarValue);
                progress = progress / 10;
                progress = progress * 10;
                seekBarText.setText(String.valueOf(progress) + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        Button submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText commentaire = (EditText) findViewById(R.id.editText);
                SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);

                //Creates toast to indicate you need to set a comment
                if (commentaire.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.submit_tache_error, Toast.LENGTH_SHORT).show();
                }
                else {

                    //Insert values in Tache object
                    if (seekBar.getProgress() > 0 && maTache.getDateDebutReelle() == null) {
                        maTache.setDateDebutReelle(new Date());
                    }
                    if (seekBar.getProgress() == 100) {
                        maTache.setDateFinReelle(new Date());
                    }
                    maTache.setCommentaire(commentaire.getText().toString());
                    maTache.setProgression((float) seekBar.getProgress() / 100);

                    //Envoie les données de la tâche à la base de données
                    int updatedRows = maTacheHandler.updateTache(maTache);

                    //Affiche un toast pour confirmer la modification de la tâche et retourne à l'activité initiale
                    //Retourne à l'activité principale
                    Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                    resultIntent.putExtra("nbUpdatedRows", updatedRows);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

    }

}
