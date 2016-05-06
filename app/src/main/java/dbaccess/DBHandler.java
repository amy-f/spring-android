package dbaccess;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import beans.Projet;

/**
 * Created by Amélie on 2016-05-06.
 */
public class DBHandler extends SQLiteOpenHelper {

    public static final String PROJET_ID = "projet_id";
    public static final String PROJET_NOM = "projet_nom";
    public static final String PROJET_ETAT = "projet_etat";
    public static final String PROJET_TABLE_NAME = "projet";
    public static final String PROJET_TABLE_CREATE = "CREATE TABLE " + PROJET_TABLE_NAME +
            " (" + PROJET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + PROJET_NOM + " TEXT, "
            + PROJET_ETAT + " INTEGER);";

    public static final String TACHE_ID = "tache_id";
    public static final String TACHE_NOM = "tache_nom";
    public static final String TACHE_ADRESSE = "tache_adresse";
    public static final String TACHE_LONGITUDE = "tache_longitude";
    public static final String TACHE_LATITUDE = "tache_latitude";
    public static final String TACHE_DEBUT_PREVU = "tache_date_debut_prevue";
    public static final String TACHE_DEBUT_REEL = "tache_date_debut_reelle";
    public static final String TACHE_FIN_PREVU = "tache_date_fin_prevue";
    public static final String TACHE_FIN_REEL = "tache_date_fin_reelle";
    public static final String TACHE_COMMENTAIRE = "tache_commentaire";
    public static final String TACHE_ETAT = "tache_etat";
    public static final String TACHE_PROGRESSION = "tache_progression";
    public static final String TACHE_TABLE_NAME = "tache";
    public static final String TACHE_TABLE_CREATE = "CREATE TABLE " + TACHE_TABLE_NAME +
            " (" + TACHE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TACHE_NOM + " TEXT, "
            + TACHE_ADRESSE + " INTEGER, " +
            TACHE_LONGITUDE + " REAL, " +
            TACHE_LATITUDE + " REAL, " +
            TACHE_DEBUT_PREVU + " TEXT, " +
            TACHE_DEBUT_REEL + " TEXT, " +
            TACHE_FIN_PREVU + " TEXT, " +
            TACHE_FIN_REEL + " TEXT, " +
            TACHE_COMMENTAIRE + " TEXT, " +
            TACHE_ETAT + " INTEGER, " +
            TACHE_PROGRESSION + " REAL, " +
            PROJET_ID + " INTEGER);";

    public DBHandler(Context context, String name, android.database.sqlite.SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {

        //Créé les tables de la base de données
        db.execSQL(PROJET_TABLE_CREATE);
        db.execSQL(TACHE_TABLE_CREATE);

        //Insère les données de l'utilisateur dans la base de données
        //(sync avec la BD externe? Tester avec des données de test en local)

        //TODO: 1) Insérer des valeurs de tests pour le test de l'accès initial à la BD. Enlever après les tests.
        //TODO: 2) Déplacer le tout dans un gestionnaire de projet et/ou dans l'app??
        //Projet android = new Projet("TP Android", 1);
        //Projet php = new Projet("TP Web PHP", 1);
        //ProjetQueryHandler projetHandler = new ProjetQueryHandler();
        //projetHandler.insertProjet(android);
        //projetHandler.insertProjet(php);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TACHE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PROJET_TABLE_NAME);
        onCreate(db);

        //S'il y a eu des changements dans la BD PHP, faire un sync pour retrouver les nouvelles tâches

    }

}
