package com.example.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.Helpers.WordDbContract.WordEntry.TABLE_NAME;

/**
 * Created by Sergey on 8/18/2017.
 */

public class WordsSqlService extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "words.db";
    private static final int DATABASE_VERSION = 1;

    public WordsSqlService(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sqlQuery = "CREATE TABLE " +
                WordDbContract.WordEntry.TABLE_NAME + " (" +
                WordDbContract.WordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WordDbContract.WordEntry.COLUMN_ENG_WORD + " TEXT NOT NULL, " +
                WordDbContract.WordEntry.COLUMN_RUS_WORD + " TEXT NOT NULL, " +
                WordDbContract.WordEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WordDbContract.WordEntry.TABLE_NAME);
        onCreate(db);
    }
}
