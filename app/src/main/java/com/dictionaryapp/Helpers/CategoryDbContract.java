package com.dictionaryapp.Helpers;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Sergey on 8/24/2017.
 */

public class CategoryDbContract implements BaseColumns {
    public static final String AUTHORTITY = "com.example.android.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORTITY);
    public static final String PATH_CATEGORIES = "categories";


    public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORIES)
            .build();
    public static final String TABLE_NAME = "categories";
    public static final String COLUMN_CATEGORY_NAME = "categoryName";
    public static final String COLUMN_TIMESTAMP = "timestamp";
}
