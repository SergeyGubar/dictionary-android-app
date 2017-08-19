package com.example.Presenters;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;

import com.example.Helpers.FirebaseService;
import com.example.Helpers.WordsSqlService;
import com.example.android.app.Word;
import com.example.Interfaces.WordsActivityApi;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import static com.example.Helpers.WordsSqlService.getWordsWithinCategory;

/**
 * Created by Sergey on 7/6/2017.
 */

public class WordsActivityPresenter {
    private Context mCtx;
    private WordsActivityApi mApi;
    private WordsSqlService mService;
    public WordsActivityPresenter(Context ctx, WordsActivityApi mApi) {
        this.mCtx = ctx;
        this.mApi = mApi;
    }

    public WordsActivityPresenter(Context mCtx, WordsActivityApi mApi, WordsSqlService mService) {
        this.mCtx = mCtx;
        this.mApi = mApi;
        this.mService = mService;
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

    public Cursor getWords() {
        Cursor cursor = WordsSqlService.getWordsWithinCategory(mApi.getActivityName(), mService);
        mApi.getLoadingIndicator().hide();
        return cursor;
    }
    

}
