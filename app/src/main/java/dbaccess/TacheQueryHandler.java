package dbaccess;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.text.DateFormat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import beans.Tache;

/**
 * Created by Amélie on 2016-05-06.
 */
public class TacheQueryHandler {

    public static final String TACHE_ID = "tache_id";
    public static final String TACHE_NOM = "tache_nom";
    public static final String TACHE_ADRESSE = "tache_adresse";
    public static final String TACHE_DESCRIPTION = "tache_description";
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

    private static final String TACHE_INSERT = "INSERT INTO " + TACHE_TABLE_NAME
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insertTache(SQLiteDatabase db, Tache t) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        SQLiteStatement stmt = db.compileStatement(TACHE_INSERT);
        stmt.bindLong(1, t.getId());
        stmt.bindString(2, t.getNom());
        stmt.bindString(3, t.getDescription());
        stmt.bindString(4, t.getAdresse());
        stmt.bindDouble(5, t.getLongitude());
        stmt.bindDouble(6, t.getLatitude());
        stmt.bindString(7, df.format(t.getDateDebutPrevue()));
        if (t.getDateDebutReelle() == null) {
            stmt.bindString(8, "");
        }
        else {
            stmt.bindString(8, df.format(t.getDateDebutReelle()));
        }
        stmt.bindString(9, df.format(t.getDateFinPrevue()));
        if (t.getDateFinReelle() == null) {
            stmt.bindString(10, "");
        }
        else {
            stmt.bindString(10, df.format(t.getDateFinReelle()));
        }
        if (t.getCommentaire() == null) {
            stmt.bindString(11, "");
        }
        else {
            stmt.bindString(11, df.format(t.getDateFinReelle()));
        }
        stmt.bindLong(12, t.getEtat());
        stmt.bindDouble(13, t.getProgression());
        stmt.bindLong(14, t.getProjetID());
        stmt.execute();
    }

   /* public ArrayList<Tache> selectTacheFromProjetID(SQLiteDatabase mDB, int projetID) {
        ArrayList<Tache> taches = new ArrayList<>();

        Cursor cursor = mDB.rawQuery(PROJET_SELECT_ALL, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(TACHE_ID));
                String name = cursor.getString(cursor.getColumnIndex(TACHE_NOM));
                String desc = cursor.getString(cursor.getColumnIndex(TACHE_DESCRIPTION));
                int state = cursor.getInt(cursor.getColumnIndex(PROJET_ETAT));
                Tache t = new Tache(id, name, state);
                taches.add(t);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return taches;
    }*/

}
