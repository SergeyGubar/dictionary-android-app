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

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

import static android.R.attr.tag;

public class NumbersActivity extends AppCompatActivity {
    private FloatingActionButton floatButton;
    private final String TAG = "NumbersActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_words);
        floatButton = (FloatingActionButton) findViewById(R.id.float_btn);
        final ListView listView = (ListView) findViewById(R.id.list);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumbersActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<Word> listOfWords = new ArrayList<>();


        final DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference().child("Words");
        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    HashMap<String, String> wordHashMap = (HashMap<String, String>) postSnapshot.getValue();
                    Word test = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
                    listOfWords.add(test);
                }
                Log.e(TAG, String.valueOf(listOfWords.size()));
                WordAdapter adapter = new WordAdapter(NumbersActivity.this, listOfWords);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}





