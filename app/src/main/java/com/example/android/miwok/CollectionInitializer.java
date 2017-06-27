package com.example.android.miwok;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sergey on 6/27/2017.
 */

public final class CollectionInitializer {
    private CollectionInitializer() {

    }

    public static void initializeCollection(Context context, SharedPreferences sharedPreferences, Gson gson, ArrayList<Word> listOfWords,
                                            String key) {
        String text = gson.toJson(listOfWords);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, text);
        editor.commit();

    }
}
