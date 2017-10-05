package com.dictionaryapp.presenters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dictionaryapp.interfaces.ForgotPasswordApi;
import com.dictionaryapp.interfaces.ResetPassword;
import com.dictionaryapp.android.app.R;
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
        if(!email.equals("")) {
            FirebaseAuth.getInstance().sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()) {
                        Toast.makeText(mCtx, R.string.email_sent, Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(mCtx, R.string.something_went_wrong, Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(mCtx, R.string.enter_valid_email, Toast.LENGTH_SHORT).show();
        }

        mApi.resetEmailText();

    }
}
