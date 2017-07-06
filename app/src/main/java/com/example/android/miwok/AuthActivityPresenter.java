package com.example.android.miwok;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Sergey on 7/5/2017.
 */

public class AuthActivityPresenter {
    private FirebaseAuth mAuth;
    private AuthActivityApi mApi;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Context ctx;


    public AuthActivityPresenter(AuthActivityApi mApi, final Context ctx) {
        this.mApi = mApi;
        this.ctx = ctx;
        this.mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Intent intent = new Intent(ctx, MainActivity.class);
                    intent.putExtra("UID", mAuth.getCurrentUser().getUid());
                    ctx.startActivity(intent);
                } else {

                }
            }
        };
    }

    public void addListener() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void removeListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
    public void signIn() {
        String email = mApi.getEmailText();
        String password = mApi.getPasswordText();
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mApi.getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (!task.isSuccessful()) {
                    Toast.makeText(ctx, "Auth went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}