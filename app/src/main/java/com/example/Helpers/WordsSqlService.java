package com.example.Helpers;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import com.example.Interfaces.SqlCategories;
import com.example.Interfaces.SqlWords;
import com.example.android.app.Word;
import java.util.ArrayList;
import java.util.List;



/**
 * Created by Sergey on 8/18/2017.
 */

public class WordsSqlService extends SQLiteOpenHelper implements SqlWords, SqlCategories {

    private static final String DATABASE_NAME = "words.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db;
    private final ContentResolver mContentResolver;

    public WordsSqlService(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
        mContentResolver = ctx.getContentResolver();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String wordTableSqlQuery = "CREATE TABLE " +
                WordDbContract.WordEntry.TABLE_NAME + " (" +
                WordDbContract.WordEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                WordDbContract.WordEntry.COLUMN_ENG_WORD + " TEXT NOT NULL, " +
                WordDbContract.WordEntry.COLUMN_RUS_WORD + " TEXT NOT NULL, " +
                WordDbContract.WordEntry.COLUMN_CATEGORY + " TEXT NOT NULL, " +
                WordDbContract.WordEntry.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                "); ";
        db.execSQL(wordTableSqlQuery);

        final String categoryTableSqlQuery = "CREATE TABLE " +
                CategoryDbContract.TABLE_NAME + " (" +
                CategoryDbContract._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                CategoryDbContract.COLUMN_CATEGORY_NAME + " TEXT NOT NULL, " +
                CategoryDbContract.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                "); ";
        db.execSQL(categoryTableSqlQuery);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + WordDbContract.WordEntry.TABLE_NAME);
        final String sqlQuery = "DROP TABLE IF EXISTS " + CategoryDbContract.COLUMN_CATEGORY_NAME;
        db.execSQL(sqlQuery);
        onCreate(db);
    }

    @Override
    public void addWord(Word word) {
        ContentValues cv = new ContentValues();
        cv.put(WordDbContract.WordEntry.COLUMN_RUS_WORD, word.getRusWord());
        cv.put(WordDbContract.WordEntry.COLUMN_ENG_WORD, word.getEngWord());
        cv.put(WordDbContract.WordEntry.COLUMN_CATEGORY, word.getCategory());
        mContentResolver.insert(WordDbContract.WordEntry.CONTENT_URI, cv);
    }

    // TODO: 9/9/2017 : implement this method
    @Override
    public Cursor getWordsWithinCategory(String category) {
        /*db = getReadableDatabase();
        return db.query(WordDbContract.WordEntry.TABLE_NAME,
                null,
                WordDbContract.WordEntry.COLUMN_CATEGORY + " = " +  "\"" + category + "\"",
                null,
                null,
                null,
                WordDbContract.WordEntry.COLUMN_TIMESTAMP
        );*/
        Uri uri = WordDbContract.WordEntry.CONTENT_URI.buildUpon().appendPath(category).build();
        return mContentResolver.query(uri, null, null, null, null);
    }

    // TODO: 9/9/2017 : implement this method
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

    // TODO: 9/9/2017 : implement this method
    @Override
    public boolean removeWord(long id) {
        db = getWritableDatabase();
        return db.delete(WordDbContract.WordEntry.TABLE_NAME,
                WordDbContract.WordEntry._ID + " = " + id,
                null) > 0;
    }

    // TODO: 9/9/2017 : implement this method
    @Override
    public Cursor getAllCategories() {
        db = getReadableDatabase();
        return db.query(true,
                CategoryDbContract.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                CategoryDbContract.COLUMN_TIMESTAMP,
                null);
    }

    // TODO: 9/9/2017 : implement this method
    @Override
    public String getCategoryName(int position, Cursor cursor) {
        if(cursor.moveToPosition(position)) {
            return cursor.getString(cursor.getColumnIndex(CategoryDbContract.COLUMN_CATEGORY_NAME));
        } else {
            return null;
        }
    }

    // TODO: 9/9/2017 : implement this method
    @Override
    public void addCategory(String categoryName) {
        db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(CategoryDbContract.COLUMN_CATEGORY_NAME, categoryName);
        db.insert(CategoryDbContract.TABLE_NAME, null, cv);
    }

    // TODO: 9/9/2017 : implement this method
    @Override
    public void removeCategory(String categoryName) {
        throw new UnsupportedOperationException("Will be implemented in the nearest future");
    }

    // TODO: 9/9/2017 : implement this method
    @Override
    public List<String> getCategoriesNames() {
        List<String> names = new ArrayList<>();

        Cursor cursor = getAllCategories();
        for(int i = 0; i < cursor.getCount(); i++) {
            if(cursor.moveToPosition(i)) {
                names.add(cursor.getString(cursor.getColumnIndex(CategoryDbContract.COLUMN_CATEGORY_NAME)));
            }
        }
        return names;
    }



}
