package com.android.claytonturner.database;

import android.content.ContentValues;
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
        String tableName = database_contract.TABLE_NAME;
        String[] names = {"Midtown Bar & Grill","Warehouse",
                "The Alley Charleston","Closed for Business",
                "Fish","Fuel","Tattooed Moose"};
        String[] address = {"559 King Street","45 Spring Street",
                "131 Columbus Street","453 King Street",
                "442 King Street","211 Rutledge Avenue",
                "1137 Morrison Drive"};
        String[] phone = {"(843)-737-4284","(843)-202-0712",
                "(843)-818-4080","(843)-853-8466",
                "(843)-722-3474","(843)-737-5959",
                "(843)-277-2990"};

        for(int i = 0; i < names.length; i++){
            ContentValues values = new ContentValues();
            values.put("name",names[i]);
            values.put("address",address[i]);
            values.put("phone",phone[i]);
            db.insert(tableName, null, values);
        }
    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        // This is version 1 so no actions are required.
        // Possible actions include dropping/recreating
        // tables, saving/restoring data in tables, etc.

    }
}
