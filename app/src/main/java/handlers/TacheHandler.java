package handlers;

import android.content.Context;

import dbaccess.DBHandler;

/**
 * Created by Amélie on 2016-05-09.
 */
public class TacheHandler {

    DBHandler db;

    public TacheHandler(Context appContext) {
        db = new DBHandler(appContext, "spring", null, 1);
    }

}
