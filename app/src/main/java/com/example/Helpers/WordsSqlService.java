package com.example.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.android.app.Word;

import static com.example.Helpers.WordDbContract.WordEntry.COLUMN_CATEGORY;
import static com.example.Helpers.WordDbContract.WordEntry.TABLE_NAME;


/**
 * Created by Sergey on 8/18/2017.
 */

public class WordsSqlService extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "words.db";
    private static final int DATABASE_VERSION = 1;
    private static SQLiteDatabase db;

    public WordsSqlService(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String sqlQuery = "CREATE TABLE " +
                TABLE_NAME + " (" +
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public static void addWord(Word word, WordsSqlService service) {

        Log.v("HUI", word.getEngWord() + " | " + word.getRusWord() + "|" + word.getCategory());

        db = service.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(WordDbContract.WordEntry.COLUMN_RUS_WORD, word.getRusWord());
        cv.put(WordDbContract.WordEntry.COLUMN_ENG_WORD, word.getEngWord());
        cv.put(WordDbContract.WordEntry.COLUMN_CATEGORY, word.getCategory());
        db.insert(WordDbContract.WordEntry.TABLE_NAME, null, cv);
    }

    public static Cursor getWordsWithinCategory(String category, WordsSqlService service) {
        db = service.getReadableDatabase();
        return db.query(WordDbContract.WordEntry.TABLE_NAME,
                null,
                WordDbContract.WordEntry.COLUMN_CATEGORY + " = " +  "\"" + category + "\"",
                null,
                null,
                null,
                WordDbContract.WordEntry.COLUMN_TIMESTAMP
        );

    }

}
