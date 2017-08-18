package com.example.Presenters;

import android.content.Context;
import android.os.Handler;

import com.example.Helpers.FirebaseService;
import com.example.android.app.Word;
import com.example.Interfaces.WordsActivityApi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by Sergey on 7/6/2017.
 */

public class WordsActivityPresenter {
    private Context mCtx;
    private WordsActivityApi mApi;
    private DatabaseReference mDataBase;

    public WordsActivityPresenter(Context ctx, WordsActivityApi mApi) {
        this.mCtx = ctx;
        this.mApi = mApi;
        mDataBase = FirebaseService.getWordReference().
                child(FirebaseService.getUserUid()).child(mApi.getActivityName());
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


    public void displayWordsData() {
        // FIXME: 8/18/2017 : remove this logic
        mDataBase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Word word = FirebaseService.getWord(dataSnapshot);
                mApi.getAdapter().addWord(word);
                mApi.getLoadingIndicator().hide();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                mApi.getLoadingIndicator().show();
                Word tempWord = FirebaseService.getWord(dataSnapshot);
                mApi.getAdapter().removeWord(tempWord);
                mApi.getLoadingIndicator().hide();
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
