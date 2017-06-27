/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

import static android.os.Build.VERSION_CODES.M;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_words);




        Gson gson = new Gson();
        SharedPreferences pref = this.getSharedPreferences("com.example.android.miwok.DATA",
                Context.MODE_PRIVATE);
        if (pref.getString("colors list", "").isEmpty()) {
            ArrayList<Word> wordsList = new ArrayList<>();
            wordsList.add(new Word("Красный", "Red", R.drawable.color_red));
            wordsList.add(new Word("Черный", "Black", R.drawable.color_black));
            wordsList.add(new Word("Желтый", "Yellow", R.drawable.color_dusty_yellow));
            wordsList.add(new Word("Серый", "Gray", R.drawable.color_gray));
            CollectionInitializer.initializeCollection(this, pref, gson, wordsList, "colors list");
        }

        String jsonText = pref.getString("colors list", "");
        Type collectionType = new TypeToken<Collection<Word>>() {}.getType();

        Collection<Word> parsedJson = gson.fromJson(jsonText, collectionType);

        ListView colorListView = (ListView) findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, (ArrayList<Word>) parsedJson);
        colorListView.setAdapter(adapter);


    }
}
