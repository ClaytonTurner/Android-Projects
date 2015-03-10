package com.android.claytonturner.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Clayton on 3/10/2015.
 */
public class Database_Sqliteopenhelper extends SQLiteOpenHelper{
    public static final String key_id = "_id";
    public static final String key_name = "name";
    public static final String key_address = "address";
    public static final String key_phone = "phone_num";
    public Database_Sqliteopenhelper(Context context){
        super(context, Database_Contract.DB_NAME, null,
            Database_Contract.DB_VERSION);
    }
    public void onCreate(SQLiteDatabase db){
        String createSql =
                "create table if not exists " + Database_Contract.TABLE_NAME
                        + "("
                        + "  "+key_id+" integer primary key autoincrement,"
                        + "  "+key_name+"      text not null,"
                        + "  "+key_address+"      text,"
                        + "  "+key_phone+" text"
                        + ")";
        db.execSQL(createSql);
        insertContacts(db);

    }

    private void insertContacts(SQLiteDatabase db){
        // initial inserts for the database
        String tableName = Database_Contract.TABLE_NAME;
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
            values.put(key_name,names[i]);
            values.put(key_address,address[i]);
            values.put(key_phone,phone[i]);
            db.insert(tableName, null, values);
        }
    }

    public Cursor fetchAllBars(SQLiteDatabase db){
        Cursor c = db.query(Database_Contract.TABLE_NAME,
                new String[] {key_id,key_name,key_address,key_phone},
                null,null,null,null,null);
        if (c != null)
            c.moveToFirst();
        return c;
    }

    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){
        // This is version 1 so no actions are required.
        // Possible actions include dropping/recreating
        // tables, saving/restoring data in tables, etc.
        db.execSQL("DROP TABLE IF EXISTS " + Database_Contract.TABLE_NAME);
        onCreate(db);
    }
}
