package com.example.Presenters;

import android.content.Context;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;

import com.example.Helpers.WordsSqlService;
import com.example.Interfaces.WordsService;
import com.example.Interfaces.WordsActivityApi;


/**
 * Created by Sergey on 7/6/2017.
 */

public class WordsActivityPresenter {
    private final Context mCtx;
    private final WordsActivityApi mApi;
    private final WordsService mService;
    private final static String TAG = "WordsActivityPresenter";

    public WordsActivityPresenter(Context ctx, WordsActivityApi mApi) {
        this.mCtx = ctx;
        this.mApi = mApi;
        mService = new WordsSqlService(mCtx);
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
        String category = mApi.getCategoryName();
        Log.v(TAG, "Category called: " + category);
        Cursor cursor = mService.getWordsWithinCategory(category);
        mApi.getLoadingIndicator().hide();
        return cursor;
    }

}
