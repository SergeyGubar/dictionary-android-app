package com.dictionaryapp.helpers;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by Sergey on 8/18/2017.
 */

public class WordDbContract {

    public static final String AUTHORTITY = "com.dictionaryapp.android.app";
    public static final String PATH_WORDS = "words";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORTITY);

    private WordDbContract() {

    }

    public static final class WordEntry implements BaseColumns {
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_WORDS)
                .build();
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_CATEGORY = "wordCategory";
        public static final String COLUMN_ENG_WORD = "engWord";
        public static final String COLUMN_RUS_WORD = "rusWord";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
