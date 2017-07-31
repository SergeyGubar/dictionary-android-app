package com.example.Presenters;

import android.content.Context;
import android.content.Intent;

import com.example.Helpers.FirebaseService;
import com.example.android.app.AddActivity;
import com.example.interfaces.MainActivityApi;

/**
 * Created by Sergey on 7/31/2017.
 */

public class MainActivityPresenter {
    private Context mCtx;
    private MainActivityApi mApi;

    public MainActivityPresenter(Context mCtx, MainActivityApi mApi) {
        this.mCtx = mCtx;
        this.mApi = mApi;
    }
    public void startAddActivity() {
        Intent intent = new Intent(mCtx, AddActivity.class);
//      intent.putExtra("UID", FirebaseService.getUserUid());
        mCtx.startActivity(intent);
    }

}
