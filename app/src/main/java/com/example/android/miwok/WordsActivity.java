package com.example.android.miwok;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class WordsActivity extends AppCompatActivity {
    private FloatingActionButton floatButton;
    private final String TAG = "WordsListActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        floatButton = (FloatingActionButton) findViewById(R.id.float_btn);
        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayList<Word> listOfWords = new ArrayList<>();
        final DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference().child("Words").
                child(getIntent().getStringExtra("activity"));


        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordsActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        mDataBase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    HashMap<String, String> wordHashMap = (HashMap<String, String>) postSnapshot.getValue();
                    Word test = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
                    listOfWords.add(test);
                }
                WordAdapter adapter = new WordAdapter(WordsActivity.this, listOfWords);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
