package com.architectureclass.bloodshare.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context) {
        super(context, DatabaseConstants.DB_NAME, null, DatabaseConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableSchedule =
            "CREATE TABLE " + DatabaseConstants.TABLE_SCHEDULE + "(" +
            DatabaseConstants.COLUMN_ID + " varchar PRIMARY KEY, "+
            DatabaseConstants.COLUMN_PLACE + " varchar, "+
            DatabaseConstants.COLUMN_RECIPIENT + " varchar, "+
            DatabaseConstants.COLUMN_DUE_DATE + " varchar, "+
            DatabaseConstants.COLUMN_CREATED_AT + " datetime, "+
            DatabaseConstants.COLUMN_DONE + " integer " +
            ")";
        db.execSQL(createTableSchedule);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
