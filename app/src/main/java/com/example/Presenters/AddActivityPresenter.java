package com.example.Presenters;

import android.content.Context;
import android.widget.Toast;

import com.example.Helpers.WordsSqlService;
import com.example.Interfaces.SqlService;
import com.example.android.app.R;
import com.example.android.app.Word;
import com.example.Interfaces.AddActivityApi;


/**
 * Created by Sergey on 7/5/2017.
 */

public class AddActivityPresenter {
    private AddActivityApi mApi;
    private SqlService mService;
    private Context mCtx;

    public AddActivityPresenter(Context ctx, AddActivityApi api) {
        this.mApi = api;
        this.mCtx = ctx;
        mService = new WordsSqlService(ctx);
    }

    public void addWord() {

        String rus = mApi.getRusText();
        String eng = mApi.getEngText();
        String category = mApi.getSelectedSpinnerItem().toString();
        if (!rus.equals("") && !eng.equals("")) {
            Word word = new Word(rus, eng, category);
            mService.addWord(word);
            Toast.makeText(mCtx, mCtx.getString(R.string.word_added), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(mCtx, mCtx.getString(R.string.word_not_added), Toast.LENGTH_SHORT).show();
        }

        mApi.resetText();
    }

    public void setAdapter() {
        mApi.setSpinnerAdapter();
    }


}
