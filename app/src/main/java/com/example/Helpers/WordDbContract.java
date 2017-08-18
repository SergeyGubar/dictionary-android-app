package com.example.Helpers;

import android.provider.BaseColumns;

/**
 * Created by Sergey on 8/18/2017.
 */

public class WordDbContract {
    private WordDbContract() {

    }

    public static final class WordEntry implements BaseColumns {
        public static final String TABLE_NAME = "words";
        public static final String COLUMN_CATEGORY = "wordCategory";
        public static final String COLUMN_ENG_WORD = "engWord";
        public static final String COLUMN_RUS_WORD = "rusWord";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }

}
