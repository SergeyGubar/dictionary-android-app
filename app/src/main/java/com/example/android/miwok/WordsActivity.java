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
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;


public class WordsActivity extends AppCompatActivity {
    private final String TAG = "WordsListActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        final String UID = getIntent().getStringExtra("UID");
        final FloatingActionButton floatButton = (FloatingActionButton) findViewById(R.id.float_btn);
        final ListView listView = (ListView) findViewById(R.id.list);
        final ArrayList<Word> listOfWords = new ArrayList<>();
        final DatabaseReference mDataBase = FirebaseDatabase.getInstance().getReference().child("Words").
                child(UID).child(getIntent().getStringExtra("activity"));
        final AVLoadingIndicatorView avi = (AVLoadingIndicatorView) findViewById(R.id.avi);

        avi.show();

        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(WordsActivity.this, AddActivity.class);
                intent.putExtra("UID", UID);
                startActivity(intent);
            }
        });


        mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String, String> wordHashMap = (HashMap<String, String>) dataSnapshot.getValue();
                Word tempWord = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
                listOfWords.add(tempWord);
                WordAdapter adapter = new WordAdapter(WordsActivity.this, listOfWords);
                avi.hide();
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                avi.show();
                HashMap<String, String> wordHashMap = (HashMap<String, String>) dataSnapshot.getValue();
                Word tempWord = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
                int indexToRemove = listOfWords.indexOf(tempWord);
                listOfWords.remove(indexToRemove);
                WordAdapter adapter = new WordAdapter(WordsActivity.this, listOfWords);
                avi.hide();
                listView.setAdapter(adapter);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
