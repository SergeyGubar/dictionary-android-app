package com.example.Helpers;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Sergey on 9/7/2017.
 */

public class WordsProvider extends ContentProvider {

    private WordsSqlService mWordsHelper;

    public static final int WORDS = 100;
    public static final int WORD_WITH_ID = 101;
    public static final int WORDS_WITHIN_CATEGORY = 200;
    public static final int WORDS_WITHIN_CATEGORY_WITH_ID = 201;
    public static final UriMatcher sUriMatcher = buildUriMatcher();


    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(WordDbContract.AUTHORTITY, WordDbContract.PATH_WORDS, WORDS);
        matcher.addURI(WordDbContract.AUTHORTITY, WordDbContract.PATH_WORDS + "/#",
                WORD_WITH_ID);
        matcher.addURI(WordDbContract.AUTHORTITY, WordDbContract.PATH_WORDS + "/*",
                WORDS_WITHIN_CATEGORY);
        matcher.addURI(WordDbContract.AUTHORTITY, WordDbContract.PATH_WORDS + "/*/#",
                WORDS_WITHIN_CATEGORY_WITH_ID);
        return matcher;
    }

    @Override
    public boolean onCreate() {
        Context ctx = getContext();
        mWordsHelper = new WordsSqlService(ctx);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        int match = sUriMatcher.match(uri);
        Cursor queryResult;
        switch (match) {
            case WORDS:
                // TODO: 9/8/2017 Query data for the whole data
                break;
            case WORD_WITH_ID:
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri + " :(");
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
