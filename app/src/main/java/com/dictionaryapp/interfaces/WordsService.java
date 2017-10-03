package com.dictionaryapp.interfaces;

import android.database.Cursor;

import com.dictionaryapp.android.app.Word;

/**
 * Created by Sergey on 8/20/2017.
 */

public interface WordsService {

    void addWord(Word word);

    Cursor getWordsWithinCategory(String category);

    boolean removeWord(long id);

    void updateWord(long id, String newEng, String newRus);

}
