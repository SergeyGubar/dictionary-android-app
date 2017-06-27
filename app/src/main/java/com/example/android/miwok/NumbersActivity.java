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
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class NumbersActivity extends AppCompatActivity {
    LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_words);


        //region Collecion Initialize


        Gson gson = new Gson();
        Context context = this;
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.android.miwok.DATA",
                Context.MODE_PRIVATE);


        if (sharedPreferences.getString("words list", "").isEmpty()) {
            ArrayList<Word> listOfWords = new ArrayList<>();
            listOfWords.add(new Word("Один", "One", R.drawable.number_one));
            listOfWords.add(new Word("Два", "Two", R.drawable.number_two));
            listOfWords.add(new Word("Три", "Three", R.drawable.number_three));
            listOfWords.add(new Word("Четыре", "Four", R.drawable.number_four));
            listOfWords.add(new Word("Пять", "Five", R.drawable.number_five));
            listOfWords.add(new Word("Шесть", "Six", R.drawable.number_six));
            listOfWords.add(new Word("Семь", "Seven", R.drawable.number_seven));
            listOfWords.add(new Word("Восемь", "Eight", R.drawable.number_eight));
            listOfWords.add(new Word("Девять", "Nine", R.drawable.number_nine));
            listOfWords.add(new Word("Десять", "Ten", R.drawable.number_ten));
            CollectionInitializer.initializeCollection(context, sharedPreferences, gson, listOfWords,
                    "words list");
        }

        String s = sharedPreferences.getString("words list", "");

        Type collectionType = new TypeToken<Collection<Word>>() {}.getType();
        Collection<Word> parsedgson = gson.fromJson(s, collectionType);

        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, (ArrayList<Word>) parsedgson);
        listView.setAdapter(adapter);

    }


}





