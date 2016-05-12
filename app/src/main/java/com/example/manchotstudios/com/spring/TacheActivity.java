package com.example.manchotstudios.com.spring;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import beans.Tache;

public class TacheActivity extends AppCompatActivity {

    Tache maTache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tache);

        //Prend les données de la tâche passée en paramètre
        maTache = getIntent().getExtras().getParcelable("tache");

        //Change la valeur du titre
        TextView nomTache = (TextView) findViewById(R.id.lblNomTache);
        nomTache.setText(maTache.getNom());

        //Met à jour la valeur de base et les valeurs d'incrémentation de la progression de la tache
        SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        TextView seekBarText = (TextView) findViewById(R.id.seekBarValue);
        int progression = (int) maTache.getProgression() * 100;
        seekBar.setProgress(progression);
        seekBarText.setText(String.valueOf(progression) + "%");
        seekBar.incrementProgressBy(10);

    }

}
