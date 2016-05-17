package dbaccess;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Amélie on 2016-05-06.
 */
public class DBHandler extends SQLiteOpenHelper {

    public static final String PROJET_TABLE_CREATE = "CREATE TABLE " + ProjetQueryHandler.PROJET_TABLE_NAME +
            " (" + ProjetQueryHandler.PROJET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + ProjetQueryHandler.PROJET_NOM + " TEXT, "
            + ProjetQueryHandler.PROJET_ETAT + " INTEGER);";

    public static final String TACHE_TABLE_CREATE = "CREATE TABLE " + TacheQueryHandler.TACHE_TABLE_NAME +
            " (" + TacheQueryHandler.TACHE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TacheQueryHandler.TACHE_NOM + " TEXT, "
            + TacheQueryHandler.TACHE_DESCRIPTION + " TEXT, "
            + TacheQueryHandler.TACHE_ADRESSE + " INTEGER, " +
            TacheQueryHandler.TACHE_CODEPOSTAL + " TEXT, " +
            TacheQueryHandler.TACHE_VILLE + " TEXT, " +
            TacheQueryHandler.TACHE_LONGITUDE + " REAL, " +
            TacheQueryHandler.TACHE_LATITUDE + " REAL, " +
            TacheQueryHandler.TACHE_DEBUT_PREVU + " TEXT, " +
            TacheQueryHandler.TACHE_DEBUT_REEL + " TEXT, " +
            TacheQueryHandler.TACHE_FIN_PREVU + " TEXT, " +
            TacheQueryHandler.TACHE_FIN_REEL + " TEXT, " +
            TacheQueryHandler.TACHE_COMMENTAIRE + " TEXT, " +
            TacheQueryHandler.TACHE_ETAT + " INTEGER, " +
            TacheQueryHandler.TACHE_PROGRESSION + " REAL, " +
            ProjetQueryHandler.PROJET_ID + " INTEGER);";

    public DBHandler(Context context, String name, android.database.sqlite.SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        //Créé les tables de la base de données
        db.execSQL(PROJET_TABLE_CREATE);
        db.execSQL(TACHE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TacheQueryHandler.TACHE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ProjetQueryHandler.PROJET_TABLE_NAME);
        onCreate(db);

        //Sync'il y a eu des changements dans la BD PHP, faire un Sync pour retrouver les nouvelles tâches

    }

}
