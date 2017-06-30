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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {
    private FloatingActionButton floatButton;
    private DatabaseReference mdDataBase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitvity_words);
        floatButton = (FloatingActionButton) findViewById(R.id.float_btn);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NumbersActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });




        final ArrayList<Word> listOfWords = new ArrayList<>();
        mdDataBase = FirebaseDatabase.getInstance().getReference().child("Words");
        mdDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                /*HashMap<String, String> wordHash = (HashMap<String,String>)dataSnapshot.getValue();
                listOfWords.add(new Word(wordHash.get("engWord"), wordHash.get("rusWord")));

                initializeListView(listOfWords);*/
                Word test = dataSnapshot.getValue(Word.class);
                listOfWords.add(test);
                initializeListView(listOfWords);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }

    private void initializeListView(ArrayList<Word> listOfWords)  {
        ListView listView = (ListView) findViewById(R.id.list);
        WordAdapter adapter = new WordAdapter(this, listOfWords);
        listView.setAdapter(adapter);
    }

}





