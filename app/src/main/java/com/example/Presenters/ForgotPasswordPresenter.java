package com.example.Presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.interfaces.ForgotPasswordApi;
import com.example.interfaces.ResetPassword;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sergey on 7/19/2017.
 */

public class ForgotPasswordPresenter implements ResetPassword {
    private Context mCtx;
    private ForgotPasswordApi mApi;


    public ForgotPasswordPresenter(Context mCtx, ForgotPasswordApi mApi) {
        this.mCtx = mCtx;
        this.mApi = mApi;
    }

    @Override
    public void sendResetPasswordLink() {
        String email = mApi.getEmailText();
        FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(mCtx, "Email sent", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(mCtx, "Something went wrong :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mApi.resetEmailText();


    }
}
