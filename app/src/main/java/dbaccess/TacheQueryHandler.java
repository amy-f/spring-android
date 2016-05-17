package dbaccess;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.text.DateFormat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import beans.Tache;

/**
 * Created by Amélie on 2016-05-06.
 */
public class TacheQueryHandler {

    public static final String TACHE_ID = "tache_id";
    public static final String TACHE_NOM = "tache_nom";
    public static final String TACHE_ADRESSE = "tache_adresse";
    public static final String TACHE_CODEPOSTAL = "tache_code_postal";
    public static final String TACHE_VILLE = "tache_ville";
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
            + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String TACHE_SELECT_FROM_PROJET_ID = "SELECT * FROM " + TACHE_TABLE_NAME
            + " WHERE " + ProjetQueryHandler.PROJET_ID + " = ?";

    private static final String TACHE_UPDATE_FROM_TACHE_ID = "UPDATE " + TACHE_TABLE_NAME +
            " SET " + TACHE_DEBUT_REEL + " = ?, " + TACHE_FIN_REEL + " = ?, " + TACHE_COMMENTAIRE + " = ?, "
            + TACHE_PROGRESSION + " = ? WHERE " + TACHE_ID + " = ?";

    public void insertTache(SQLiteDatabase db, Tache t) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        SQLiteStatement stmt = db.compileStatement(TACHE_INSERT);
        stmt.bindLong(1, t.getId());
        stmt.bindString(2, t.getNom());
        stmt.bindString(3, t.getDescription());
        stmt.bindString(4, t.getAdresse());
        stmt.bindString(5, t.getCodePostal());
        stmt.bindString(6, t.getVille());
        stmt.bindDouble(7, t.getLongitude());
        stmt.bindDouble(8, t.getLatitude());
        stmt.bindString(9, df.format(t.getDateDebutPrevue()));
        if (t.getDateDebutReelle() == null) {
            stmt.bindString(10, "");
        }
        else {
            stmt.bindString(10, df.format(t.getDateDebutReelle()));
        }
        stmt.bindString(11, df.format(t.getDateFinPrevue()));
        if (t.getDateFinReelle() == null) {
            stmt.bindString(12, "");
        }
        else {
            stmt.bindString(12, df.format(t.getDateFinReelle()));
        }
        if (t.getCommentaire() == null) {
            stmt.bindString(13, "");
        }
        else {
            stmt.bindString(13, df.format(t.getDateFinReelle()));
        }
        stmt.bindLong(14, t.getEtat());
        stmt.bindDouble(15, t.getProgression());
        stmt.bindLong(16, t.getProjetID());
        stmt.execute();
    }

   public ArrayList<Tache> selectTacheFromProjetID(SQLiteDatabase mDB, int projetID) {
       ArrayList<Tache> taches = new ArrayList<>();
       SQLiteStatement stmt = mDB.compileStatement(TACHE_SELECT_FROM_PROJET_ID);
       stmt.bindLong(1, projetID);

       Cursor cursor = mDB.rawQuery(TACHE_SELECT_FROM_PROJET_ID, new String[] {String.valueOf(projetID)});
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndex(TACHE_ID));
                String name = cursor.getString(cursor.getColumnIndex(TACHE_NOM));
                String desc = cursor.getString(cursor.getColumnIndex(TACHE_DESCRIPTION));
                String address = cursor.getString(cursor.getColumnIndex(TACHE_ADRESSE));
                String zipCode = cursor.getString(cursor.getColumnIndex(TACHE_CODEPOSTAL));
                String city = cursor.getString(cursor.getColumnIndex(TACHE_VILLE));
                double longitude = cursor.getDouble(cursor.getColumnIndex(TACHE_LONGITUDE));
                double latitude = cursor.getDouble(cursor.getColumnIndex(TACHE_LATITUDE));
                Date[] dates = convertDates(cursor);
                Date debutPrevu = dates[0];
                Date debutReel = dates[1];
                Date finPrevu = dates[2];
                Date finReel = dates[3];
                String commentaire = cursor.getString(cursor.getColumnIndex(TACHE_COMMENTAIRE));
                int state = cursor.getInt(cursor.getColumnIndex(TACHE_ETAT));
                float progression = cursor.getFloat(cursor.getColumnIndex(TACHE_PROGRESSION));
                //Tache t = new Tache(id, name, desc, address, longitude, latitude, debutPrevu, debutReel, finPrevu, finReel, commentaire, state, progression, projetID);
                Tache t = new Tache(id, name, desc, address, zipCode, city, longitude, latitude, debutPrevu, debutReel, finPrevu, finReel, commentaire, state, progression, projetID);
                taches.add(t);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return taches;
    }

    public int updateTache(SQLiteDatabase mDB, Tache t) {

        //Met les données nécessaires dans la requête préparée
        try {
            SQLiteStatement stmt = mDB.compileStatement(TACHE_UPDATE_FROM_TACHE_ID);
            stmt.bindString(1, convertDateToString(t.getDateDebutReelle()));
            stmt.bindString(2, convertDateToString(t.getDateFinReelle()));
            stmt.bindString(3, t.getCommentaire());
            stmt.bindDouble(4, t.getProgression());
            stmt.bindLong(5, t.getId());

            //Exécute la query et retourne le nombre de lignes modifiées
            return stmt.executeUpdateDelete();
        }
        catch (Exception e) {
           e.printStackTrace();
        }
        return 0;
    }

    private String convertDateToString(Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (date == null) {
            return "";
        }
        else {
            return df.format(date);
        }
    }

    private Date convertStringToDate(String str) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        if (str.equals("")) {
            return null;
        }
        else {
            Date d;
            try {
                d = df.parse(str);
                return d;
            }
            catch (ParseException ex) {
                ex.printStackTrace();
                return null;
            }
        }
    }

    private Date[] convertDates(Cursor cursor) {
        Date[] dates = new Date[4];
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        if (cursor.getString(cursor.getColumnIndex(TACHE_DEBUT_PREVU)).equals("")) {
            dates[0] = null;
        }
        else {
            try {
                dates[0] = df.parse(cursor.getString(cursor.getColumnIndex(TACHE_DEBUT_PREVU)));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (cursor.getString(cursor.getColumnIndex(TACHE_DEBUT_REEL)).equals("")) {
            dates[1] = null;
        }
        else {
            try {
                dates[1] = df.parse(cursor.getString(cursor.getColumnIndex(TACHE_DEBUT_REEL)));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (cursor.getString(cursor.getColumnIndex(TACHE_FIN_PREVU)).equals("")) {
            dates[2] = null;
        }
        else {
            try {
                dates[2] = df.parse(cursor.getString(cursor.getColumnIndex(TACHE_FIN_PREVU)));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if (cursor.getString(cursor.getColumnIndex(TACHE_FIN_REEL)).equals("")) {
            dates[3] = null;
        }
        else {
            try {
                dates[3] = df.parse(cursor.getString(cursor.getColumnIndex(TACHE_FIN_REEL)));
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return dates;
    }

}
