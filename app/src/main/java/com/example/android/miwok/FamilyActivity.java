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
import java.util.List;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_words);

        Gson gson = new Gson();
        SharedPreferences pref = this.getSharedPreferences("com.example.android.miwok.DATA",
                Context.MODE_PRIVATE);
        if (pref.getString("family list", "").isEmpty()) {
            ArrayList<Word> wordsList = new ArrayList<>();
            wordsList.add(new Word("Отец", "Father", R.drawable.family_father));
            wordsList.add(new Word("Мать", "Mother", R.drawable.family_mother));
            wordsList.add(new Word("Бабушка", "Grandmother", R.drawable.family_grandmother));
            wordsList.add(new Word("Дедушка", "Grandfather", R.drawable.family_grandfather));
            wordsList.add(new Word("Брат", "Brother", R.drawable.family_older_brother));
            wordsList.add(new Word("Сестра", "Sister", R.drawable.family_older_sister));
            CollectionInitializer.initializeCollection(this, pref, gson, wordsList, "family list");
        }

        String jsonText = pref.getString("family list", "");
        Type collectionType = new TypeToken<Collection<Word>>() {}.getType();

        Collection<Word> parsedJson = gson.fromJson(jsonText, collectionType);

        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, (ArrayList<Word>) parsedJson);
        listView.setAdapter(adapter);

    }
}
