package dbaccess;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import beans.Projet;

/**
 * Created by Amélie on 2016-05-06.
 */
public class ProjetQueryHandler{

    public static final String PROJET_ID = "projet_id";
    public static final String PROJET_NOM = "projet_nom";
    public static final String PROJET_ETAT = "projet_etat";
    public static final String PROJET_TABLE_NAME = "projet";
    private static final String PROJET_INSERT = "INSERT INTO " + PROJET_TABLE_NAME +
            " VALUES (?, ?, ?)";

    public void insertProjet(SQLiteDatabase db, Projet p) {
        SQLiteStatement stmt = db.compileStatement(PROJET_INSERT);
        stmt.bindLong(1, p.getId());
        stmt.bindString(2, p.getNom());
        stmt.bindLong(3, p.getEtat());
        stmt.execute();
    }

    public void updateProjet(Projet p) {

    }

    public void deleteProjet(Projet p) {

    }

    public ResultSet selectProjetResultSetFromEmployeID(int employeID) {
        ResultSet rs = null;
        return rs;
    }

    public ArrayList<Projet> selectProjetArrayFromEmployeID(int employeID) {
        ArrayList<Projet> projets = new ArrayList<>();
        return projets;
    }

    public ResultSet selectProjetResultSetFromProjetID(int projetID) {
        ResultSet rs = null;
        return rs;
    }

    public Projet selectProjetFromID(int projetID) {
        Projet p = new Projet();
        return p;
    }

}
