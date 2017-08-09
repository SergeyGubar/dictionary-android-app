package com.example.Presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.Helpers.FirebaseService;
import com.example.android.app.Word;
import com.example.interfaces.AddActivityApi;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;


/**
 * Created by Sergey on 7/5/2017.
 */

public class AddActivityPresenter {
    private AddActivityApi mApi;
    private DatabaseReference mDataBase;
    private Context ctx;

    public void addWord() {

        String rus = mApi.getRusText();
        String eng = mApi.getEngText();
        String category = mApi.getSelectedSpinnerItem().toString();
        if (!rus.equals("") && !eng.equals("")) {
            Word word = new Word(rus, eng);
            FirebaseService.addWord(ctx, word, category);
        } else {
            Toast.makeText(ctx, "Empty word cannot be added", Toast.LENGTH_SHORT).show();
        }

        mApi.resetText();
    }

    public void setAdapter() {
        mApi.setSpinnerAdapter();
    }


    public AddActivityPresenter(Context ctx, AddActivityApi api) {
        this.mApi = api;
        this.ctx = ctx;
    }


}
