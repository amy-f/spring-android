package dbaccess;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.text.DateFormat;

import java.text.SimpleDateFormat;

import beans.Tache;

/**
 * Created by Amélie on 2016-05-06.
 */
public class TacheQueryHandler {

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

    private static final String TACHE_INSERT = "INSERT INTO " + TACHE_TABLE_NAME
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public void insertTache(SQLiteDatabase db, Tache t) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        SQLiteStatement stmt = db.compileStatement(TACHE_INSERT);
        stmt.bindLong(1, t.getId());
        stmt.bindString(2, t.getNom());
        stmt.bindString(3, t.getAdresse());
        stmt.bindDouble(4, t.getLongitude());
        stmt.bindDouble(5, t.getLatitude());
        stmt.bindString(6, df.format(t.getDateDebutPrevue()));
        stmt.bindString(7, df.format(t.getDateDebutReelle()));
        stmt.bindString(8, df.format(t.getDateFinPrevue()));
        stmt.bindString(9, df.format(t.getDateFinReelle()));
        stmt.bindString(10, t.getCommentaire());
        stmt.bindLong(11, t.getEtat());
        stmt.bindDouble(12, t.getProgression());
        stmt.bindLong(13, t.getProjetID());
        stmt.execute();
    }

}
