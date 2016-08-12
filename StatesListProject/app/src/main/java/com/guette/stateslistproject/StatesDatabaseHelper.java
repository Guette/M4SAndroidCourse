package com.guette.stateslistproject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by MAGUETTE on 02/08/2016.
 */
public class StatesDatabaseHelper extends SQLiteOpenHelper {

    public static final String TABLENAME="States";
    public static final String IDSTATE="idStates";
    public static final String NAMESTATE="nameStates";
    public static final String CAPITALSTATE="capitalStates";
    public static final String LATITUDE="latitude";
    public static final String LONGITUDE="longitude";
    private static final String DATABASENAME="StateLand.db";
    private static final int DATABASEVERSION=1;
    private static final String CREATETABLE="CREATE TABLE "+ TABLENAME+ " ("+
            IDSTATE +" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NAMESTATE +" TEXT NOT NULL, "+
            CAPITALSTATE +" TEXT NOT NULL, "+
            LATITUDE + " TEXT  NOT NULL, "+
            LONGITUDE + " TEXT  NOT NULL "+
            ");";

    public StatesDatabaseHelper(Context context) {
        super(context, DATABASENAME, null, DATABASEVERSION);
        context.deleteDatabase(DATABASENAME);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        sqLiteDatabase.execSQL(CREATETABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        Log.w(StatesDatabaseHelper.class.getName(),"Upgrading database from version "+ oldVersion + " to " + newVersion
                + "which will destroy all old data");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(sqLiteDatabase);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }
}
