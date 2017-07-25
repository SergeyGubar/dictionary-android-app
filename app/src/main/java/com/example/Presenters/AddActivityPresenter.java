package com.example.Presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

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
    private AddActivityApi api;
    private DatabaseReference mDataBase;
    private Context ctx;
    public void addWord() {
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Words").child(api.getUid())
                .child(api.getSelectedSpinnerItem().toString());
        Word word = new Word(api.getRusText(), api.getEngText());
        HashMap<String, String> wordHash = new HashMap<>();
        wordHash.put("engWord", word.getEngWord());
        wordHash.put("rusWord", word.getRusWord());

        mDataBase.child(word.getEngWord() + " | " + word.getRusWord()).setValue(wordHash).addOnCompleteListener
                (new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ctx, "Word Saved!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ctx, "An error " +
                                    "has occured", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
        api.resetText();
    }

    public void setAdapter() {
        api.setSpinnerAdapter();
    }


    public AddActivityPresenter(Context ctx, AddActivityApi api) {
        this.api = api;
        this.ctx = ctx;
    }


}