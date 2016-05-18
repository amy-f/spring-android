package com.example.manchotstudios.com.spring;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {

    private EditText txtUsername;
    private EditText txtPassword;
    private Button btnConnect;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        //Retrouve les éléments de l'interface
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnConnect = (Button) findViewById(R.id.btnConnect);
        btnConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Validate fields
                if (txtUsername.getText().toString().equals("") || txtPassword.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), R.string.login_edittext_error, Toast.LENGTH_SHORT).show();
                }
                else {
                    int oldPrefsID = prefs.getInt("utilisateur_id", 0);
                    boolean oldFirstUseValue = prefs.getBoolean("firstUse", false);
                    if (validateLogin()) {

                        //Change les préférences et renvoie à la MainActivity
                        prefs.edit().putBoolean("isLoggedIn", true).apply();

                        //Si même utilisateur que la dernière fois qu'il se logue, ne change rien, autrement
                        //il va chercher les infos liées à cet utilisateur
                        if (prefs.getInt("utilisateur_id", 0) != oldPrefsID || prefs.getBoolean("firstUse", true)) {
                            //drop tables and sync
                            prefs.edit().putBoolean("firstUse", false);
                        }
                        Intent resultIntent = new Intent(getApplicationContext(), MainActivity.class);
                        resultIntent.putExtra("oldFirstUseValue", oldFirstUseValue);
                        setResult(Activity.RESULT_OK, resultIntent);
                        finish();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), R.string.login_authentification_error, Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
    }

    /**
     * Valide les informations écrites dans le login
     * @return si les identifiants sont valides ou non.
     */
    public boolean validateLogin() {
        String username = txtUsername.getText().toString();
        String password = md5(txtPassword.getText().toString());

        //TODO: Remove after tests are done. Replace with remote database access logic
        String dummyUsername = "admin";
        String dummyPassword = md5("admin");

        //Envoie une requête à la base de données Web pour comparer. Envoie les données JSON en paramètre.
        //TODO: À faire quand la classe "Sync" sera terminée.

        //Reçoit une réponse du site Web avec les données de l'utilisateur
        //TODO: À faire quand la classe "Sync" sera terminée.

        //Si ça correspond, met les bonnes valeurs dans les préférences et va chercher la liste des tâches associées.
        //Autrement, retourne faux
        if (username.equals(dummyUsername) && password.equals(dummyPassword)) {
            prefs.edit().putString("utilisateur_nom", "Admin");
            prefs.edit().putInt("utilisateur_id", 1);
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Convertit une String donnée en format md5. Utilisé pour convertir les mots de passe.
     * Origine de cette fonction : http://stackoverflow.com/questions/4846484/md5-hashing-in-android
     * @param s la String passée en paramètre
     * @return la String passée en paramètre convertie au format md5
     */
    public static String md5(String s) {
        String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
