package handlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import beans.Projet;
import dbaccess.DBHandler;
import dbaccess.ProjetQueryHandler;

/**
 * Created by Amélie on 2016-05-09.
 */
public class ProjetHandler {

    DBHandler db;
    ProjetQueryHandler dbQuery;

    public ProjetHandler(Context appContext) {
        db = new DBHandler(appContext, "spring", null, 1);
        dbQuery = new ProjetQueryHandler();
    }

    public void insertProjet(Projet p) {
        SQLiteDatabase writableDB = db.getWritableDatabase();
        dbQuery.insertProjet(writableDB, p);
    }

}
