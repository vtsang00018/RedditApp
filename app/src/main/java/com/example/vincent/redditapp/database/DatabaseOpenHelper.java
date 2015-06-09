package com.example.vincent.redditapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/** Object to open the database / create the database
 * Created by Vincent on 6/8/2015.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    /**
     * Database Version
     */
    private static final int DATABASE_VERSION = 1;
    /**
     * Database Strings
     */
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA = ", ";
    /**
     * SQL Create Table Sentence
     */
    private static final String CREATE_POST_TABLE = "CREATE TABLE "
            + DatabaseContract.PostTable.TABLE_NAME + " ("
            + DatabaseContract.PostTable.TITLE + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.LINK + TEXT_TYPE + COMMA
            + DatabaseContract.PostTable.IMAGELINK + TEXT_TYPE + " )";
    /**
     * SQL Delete Table Sentence
     */
    private static final String DROP_POST_TABLE = "DROP TABLE IF EXISTS " + DatabaseContract.PostTable.TABLE_NAME;

    /**
     * Custom Constructor
     * @param context
     */
    public DatabaseOpenHelper(Context context) {
        super(context, DatabaseContract.DB_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_POST_TABLE);
        onCreate(db);
    }
}
