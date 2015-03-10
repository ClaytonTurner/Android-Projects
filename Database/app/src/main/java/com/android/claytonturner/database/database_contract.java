package com.android.claytonturner.database;

/**
 * Created by Clayton on 3/10/2015.
 */
public final class database_contract {
    // only using 1 table
    public static final String DB_NAME = "bar_information.db";
    public static final int DB_VERSION = 1;

    public static final String TABLE_NAME = "bars";
    public static final String[] COLUMNS =
            {"_id","name","address","phone"};

    private database_contract() {} // stops instantiation
}
