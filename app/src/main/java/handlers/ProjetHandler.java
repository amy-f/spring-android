package handlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

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

        if(!dbQuery.projetExiste(writableDB, p.getId())){
            dbQuery.insertProjet(writableDB, p);
        }else{
            dbQuery.updateProjet(writableDB, p);
        }
    }

    public ArrayList<Projet> selectAllProjet() {
        ArrayList<Projet> projets;
        SQLiteDatabase writableDB = db.getWritableDatabase();
        projets = dbQuery.selectAllProjet(writableDB);
        return projets;
    }

}
