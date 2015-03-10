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

    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){

    }
}
