package com.example.Presenters;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.android.app.AddActivity;
import com.example.android.app.Word;
import com.example.android.app.WordAdapter;
import com.example.interfaces.WordsActivityApi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by Sergey on 7/6/2017.
 */

public class WordsActivityPresenter {
    private Context ctx;
    private WordsActivityApi mApi;
    private DatabaseReference mDataBase;

    public WordsActivityPresenter(Context ctx, WordsActivityApi mApi) {
        this.ctx = ctx;
        this.mApi = mApi;
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Words").
                child(mApi.getUid()).child(mApi.getActivityName());
    }

    public void startAnimation() {
        mApi.getLoadingIndicator().show();
        Runnable progress = new Runnable() {
            @Override
            public void run() {
                mApi.getLoadingIndicator().hide();
            }
        };
        Handler pdCanceller = new Handler();
        pdCanceller.postDelayed(progress, 3000);
    }


    public void startAddActivity() {
        Intent intent = new Intent(ctx, AddActivity.class);
        intent.putExtra("UID", mApi.getUid());
        ctx.startActivity(intent);
    }

    public void displayWordsData() {
        mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                HashMap<String, String> wordHashMap = (HashMap<String, String>) dataSnapshot.getValue();
                Word tempWord = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
                mApi.getWordsList().add(tempWord);
                WordAdapter adapter = new WordAdapter(ctx, mApi.getWordsList());
                
                mApi.getLoadingIndicator().hide();
                mApi.getListView().setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                mApi.getLoadingIndicator().show();
                HashMap<String, String> wordHashMap = (HashMap<String, String>) dataSnapshot.getValue();
                Word tempWord = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
                int indexToRemove = mApi.getWordsList().indexOf(tempWord);
                mApi.getWordsList().remove(indexToRemove);
                WordAdapter adapter = new WordAdapter(ctx, mApi.getWordsList());
                mApi.getLoadingIndicator().hide();
                mApi.getListView().setAdapter(adapter);
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
