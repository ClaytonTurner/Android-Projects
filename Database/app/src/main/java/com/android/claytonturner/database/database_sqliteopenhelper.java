package com.android.claytonturner.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clayton on 3/10/2015.
 */
public class database_sqliteopenhelper extends SQLiteOpenHelper{
    public database_sqliteopenhelper(Context context){
        super(context, database_contract.DB_NAME, null,
            database_contract.DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        String createSql =
                "create table " + database_contract.TABLE_NAME
                        + "("
                        + "  _id integer primary key autoincrement,"
                        + "  name      text not null,"
                        + "  hours      text,"
                        + "  phone_num text"
                        + ")";
        db.execSQL(createSql);
        insertContacts(db);

    }

    private void insertContacts(SQLiteDatabase db){
        // initial inserts for the database
    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        // This is version 1 so no actions are required.
        // Possible actions include dropping/recreating
        // tables, saving/restoring data in tables, etc.

    }
}
