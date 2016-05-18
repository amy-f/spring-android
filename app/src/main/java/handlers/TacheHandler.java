package handlers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import beans.Tache;
import dbaccess.DBHandler;
import dbaccess.TacheQueryHandler;

/**
 * Created by Amélie on 2016-05-09.
 */
public class TacheHandler {

    DBHandler db;
    TacheQueryHandler dbQuery;

    public TacheHandler(Context appContext) {
        db = new DBHandler(appContext, "spring", null, 1);
        dbQuery = new TacheQueryHandler();
    }

    public void insertTache(Tache t) {
        SQLiteDatabase writableDB = db.getWritableDatabase();
        if(!dbQuery.tacheExiste(writableDB, t.getId())){
            dbQuery.insertTache(writableDB, t);
        }else{
            dbQuery.updateTache(writableDB, t);
        }
    }

    public ArrayList<Tache> selectTacheFromProjetID(int projetID) {
        SQLiteDatabase writableDB = db.getWritableDatabase();
        return dbQuery.selectTacheFromProjetID(writableDB, projetID);
    }

    public int updateTache(Tache t) {
        SQLiteDatabase writableDB = db.getWritableDatabase();
        return dbQuery.updateTache(writableDB, t);
    }

}
