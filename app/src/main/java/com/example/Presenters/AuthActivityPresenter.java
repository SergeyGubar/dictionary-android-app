package com.example.Presenters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.android.app.ForgotPasswordActivity;
import com.example.android.app.MainActivity;
import com.example.android.app.R;
import com.example.android.app.RegisterActivity;
import com.example.Interfaces.AuthActivityApi;
import com.example.Interfaces.Authorized;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Sergey on 7/5/2017.
 */

public class AuthActivityPresenter implements Authorized {
    private FirebaseAuth mAuth;
    private AuthActivityApi mApi;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private Context ctx;
    private ProgressDialog progressDialog;

    public AuthActivityPresenter(final AuthActivityApi mApi, final Context ctx) {
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
                    mApi.getActivity().finish();
                }
            }
        };

    }

    public void addFirebaseListener() {
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void removeFirebaseListener() {
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signIn() {
        String email = mApi.getEmailText();
        String password = mApi.getPasswordText();
        if (!email.equals("") && !password.equals("")) {
            progressDialog = new ProgressDialog(ctx, ProgressDialog.STYLE_SPINNER);
            progressDialog.setTitle(R.string.login_process_text);
            progressDialog.setMessage("Just a moment");
            progressDialog.show();
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(mApi.getActivity(),
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(ctx, "Auth went wrong", Toast.LENGTH_SHORT).show();
                            }
                            progressDialog.hide();
                        }
                    });
        } else {
            mApi.resetFields();
            Toast.makeText(ctx, "Enter email and password!", Toast.LENGTH_SHORT).show();
        }

    }

    public void signUpActivityStart() {
        Intent intent = new Intent(ctx, RegisterActivity.class);
        ctx.startActivity(intent);
    }

    public void forgotPassActivityStart() {
        ctx.startActivity(new Intent(ctx, ForgotPasswordActivity.class));
    }

}
