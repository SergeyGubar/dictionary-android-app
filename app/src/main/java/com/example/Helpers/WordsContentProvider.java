package com.example.Helpers;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Sergey on 9/7/2017.
 */

public class WordsContentProvider extends ContentProvider {

    public static final int WORDS = 100;
    public static final int WORD_WITH_ID = 101;
    public static final int WORDS_WITHIN_CATEGORY = 200;
    public static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final String TAG = WordsContentProvider.class.getSimpleName();
    private SQLiteDatabase mDb;
    private WordsSqlService mService;

    @Override
    public boolean onCreate() {
        Context ctx = getContext();
        mService = new WordsSqlService(ctx);
        return true;
    }

    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(WordDbContract.AUTHORTITY, WordDbContract.PATH_WORDS, WORDS);
        matcher.addURI(WordDbContract.AUTHORTITY, WordDbContract.PATH_WORDS + "/#",
                WORD_WITH_ID);
        matcher.addURI(WordDbContract.AUTHORTITY, WordDbContract.PATH_WORDS + "/*",
                WORDS_WITHIN_CATEGORY);
        return matcher;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
                        @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        mDb = mService.getReadableDatabase();
        int match = sUriMatcher.match(uri);
        Cursor returnCursor;
        String categoryName = getLastUriArgument(uri.toString());
        Log.v(TAG, categoryName);
        switch (match) {
            case WORDS:
                Log.v(TAG, "Trying to access the whole word list");
                returnCursor = mDb.query(WordDbContract.WordEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        WordDbContract.WordEntry.COLUMN_TIMESTAMP);
                return returnCursor;
            case WORDS_WITHIN_CATEGORY:
                Log.v(TAG, "Trying to access URI with type WORDS_WITHIN_CATEGORY");
                returnCursor = mDb.query(WordDbContract.WordEntry.TABLE_NAME,
                        null,
                        WordDbContract.WordEntry.COLUMN_CATEGORY + " = " + "\"" + categoryName + "\"",
                        null,
                        null,
                        null,
                        WordDbContract.WordEntry.COLUMN_TIMESTAMP);
                return returnCursor;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri + " :(");
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int match = sUriMatcher.match(uri);
        mDb = mService.getWritableDatabase();
        Uri returnUri;
        switch (match) {
            case WORDS:
                long id = mDb.insert(WordDbContract.WordEntry.TABLE_NAME, null, values);
                if (id > 0) {
                    returnUri = ContentUris.withAppendedId(WordDbContract.WordEntry.CONTENT_URI, id);
                } else {
                    throw new SQLException("Failed to insert data: " + uri);
                }
                break;
            default:
                throw new UnsupportedOperationException("Wrong uri: " + uri.toString());
        }
        try {
            getContext().getContentResolver().notifyChange(uri, null);
        } catch (NullPointerException ex) {
            Log.e(TAG, "Resolver wasn't notified \n " + ex.getMessage());
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        mDb = mService.getWritableDatabase();
        String id = getLastUriArgument(uri.toString());
        switch (match) {
            case WORD_WITH_ID:
                return mDb.delete(WordDbContract.WordEntry.TABLE_NAME,
                        WordDbContract.WordEntry._ID + " = " + id,
                        null);
            default:
                throw new SQLException("Unknown URI");
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);
        String id = getLastUriArgument(uri.toString());
        switch (match) {
            case WORD_WITH_ID:
                return mDb.update(WordDbContract.WordEntry.TABLE_NAME,
                        values,
                        WordDbContract.WordEntry._ID + " = " + id,
                        null
                );
            default:
                throw new SQLException("Unknown URI" + uri.toString());
        }
    }

    //There should be an extension method for the String class, but unfortunately, Java doesn't provide
    //an opportunity to add extension methods. Should've done it in Kotlin
    private static String getLastUriArgument(String uriString) {
        Pattern p = Pattern.compile("[^/]+$");
        Matcher m = p.matcher(uriString);
        String lastArgument = "";

        while (m.find()) {
            lastArgument = m.group();
        }
        return lastArgument;
    }


}
