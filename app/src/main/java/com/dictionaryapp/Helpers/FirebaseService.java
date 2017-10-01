package com.dictionaryapp.Helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dictionaryapp.android.app.AuthActivity;
import com.dictionaryapp.android.app.Word;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by Sergey on 7/31/2017.
 */

public class FirebaseService {

    private FirebaseService() {

    }

    public static FirebaseAuth getFirebaseAuthInstance() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseDatabase getFirebaseDatabaseInstance() {
        return FirebaseDatabase.getInstance();
    }

    public static void addWord(final Context ctx, Word word, String category) {
        DatabaseReference mDataBase = getWordReference().child(FirebaseService.getUserUid())
                .child(category);
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
    }

    public static FirebaseUser getUser() {
        return FirebaseService.getFirebaseAuthInstance().getCurrentUser();
    }

    public static String getUserUid() {
        return FirebaseService.getUser().getUid();
    }

    public static Word getWord(DataSnapshot dataSnapshot) {
        //It ain't work in other way. Fuck firebase
        HashMap<String, String> wordHashMap = (HashMap<String, String>) dataSnapshot.getValue();
        Word tempWord = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
        return tempWord;
    }

    public static DatabaseReference getWordReference() {
        return FirebaseService.getFirebaseDatabaseInstance().getReference().child("Words");
    }

    public static void logOut(Activity currActivity) {
        FirebaseAuth.getInstance().signOut();
        currActivity.startActivity(new Intent(currActivity, AuthActivity.class));
        currActivity.finish();
    }

}
