package com.example.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.Interfaces.SqlService;
import com.example.android.app.Word;



/**
 * Created by Sergey on 8/18/2017.
 */

public class WordsSqlService extends SQLiteOpenHelper implements SqlService {

    private static final String DATABASE_NAME = "words.db";



    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;

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
                WordDbContract.WordEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                WordDbContract.WordEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        db.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WordDbContract.WordEntry.TABLE_NAME);
        onCreate(db);
    }

    public void addWord(Word word) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WordDbContract.WordEntry.COLUMN_RUS_WORD, word.getRusWord());
        cv.put(WordDbContract.WordEntry.COLUMN_ENG_WORD, word.getEngWord());
        cv.put(WordDbContract.WordEntry.COLUMN_CATEGORY, word.getCategory());
        db.insert(WordDbContract.WordEntry.TABLE_NAME, null, cv);
    }

    public Cursor getWordsWithinCategory(String category) {
        db = getReadableDatabase();
        return db.query(WordDbContract.WordEntry.TABLE_NAME,
                null,
                WordDbContract.WordEntry.COLUMN_CATEGORY + " = " +  "\"" + category + "\"",
                null,
                null,
                null,
                WordDbContract.WordEntry.COLUMN_TIMESTAMP
        );
    }


    @Override
    public void updateWord(String oldEng, String oldRus, String newEng, String newRus) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WordDbContract.WordEntry.COLUMN_ENG_WORD, newEng);
        cv.put(WordDbContract.WordEntry.COLUMN_RUS_WORD, newRus);

        db.update(WordDbContract.WordEntry.TABLE_NAME,
                cv,
                WordDbContract.WordEntry.COLUMN_ENG_WORD + " = " + "\"" + oldEng + "\""  + " AND " +
                        WordDbContract.WordEntry.COLUMN_RUS_WORD + " = " + "\"" + oldRus + "\"" ,
                null
                );

    }

    @Override
    public boolean removeWord(long id) {
        db = getWritableDatabase();
        return db.delete(WordDbContract.WordEntry.TABLE_NAME,
                WordDbContract.WordEntry._ID + " = " + id,
                null) > 0;
    }

}
