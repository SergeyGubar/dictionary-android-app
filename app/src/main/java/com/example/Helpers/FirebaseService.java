package com.example.Helpers;

import com.example.android.app.Word;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

/**
 * Created by Sergey on 7/31/2017.
 */

public class FirebaseService {
    public static FirebaseAuth getFirebaseAuthInstance() {
        return FirebaseAuth.getInstance();
    }

    public static FirebaseDatabase getFirebaseDatabaseInstance() {
        return FirebaseDatabase.getInstance();
    }

    public static FirebaseUser getUser() {
        return FirebaseService.getFirebaseAuthInstance().getCurrentUser();
    }

    public static String getUserUid() {
        return FirebaseService.getUser().getUid();
    }

    public static Word getWord(DataSnapshot dataSnapshot) {
        HashMap<String, String> wordHashMap = (HashMap<String, String>) dataSnapshot.getValue();
        Word tempWord = new Word(wordHashMap.get("engWord"), wordHashMap.get("rusWord"));
        return tempWord;
    }

    public static DatabaseReference getWordReference() {
        return FirebaseService.getFirebaseDatabaseInstance().getReference().child("Words");
    }

}
