package com.dictionaryapp.presenters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.dictionaryapp.android.app.AuthActivity;
import com.dictionaryapp.android.app.R;
import com.dictionaryapp.interfaces.RegisterActivityApi;
import com.dictionaryapp.interfaces.Registrable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sergey on 7/7/2017.
 */

public class RegisterActivityPresenter implements Registrable {

    private RegisterActivityApi mApi;
    private Context mCtx;
    private ProgressDialog mProgressDialog;
    private FirebaseAuth mAuth;

    public RegisterActivityPresenter(RegisterActivityApi mApi, Context context) {
        this.mApi = mApi;
        this.mCtx = context;
        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(context);
    }

    public void signUp() {
        String email = mApi.getEmailText();
        String password = mApi.getPasswordText();

        if(!email.equals("") && !password.equals("")) {
            mProgressDialog.setTitle(R.string.register_process_text);;
            mProgressDialog.setMessage("Just a moment");
            mProgressDialog.show();
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(
                    new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(mCtx, mCtx.getString(R.string.registered_succesfully), Toast.LENGTH_SHORT).show();
                                mProgressDialog.hide();
                                mApi.getActivity().finish();
                                mCtx.startActivity(new Intent(mCtx, AuthActivity.class));
                            } else {
                                Toast.makeText(mCtx, mCtx.getString(R.string.registration_failed),
                                        Toast.LENGTH_SHORT).show();
                                mProgressDialog.hide();
                            }
                        }
                    }
            );
        } else {
            Toast.makeText(mCtx, mCtx.getString(R.string.empty_fields_warning), Toast.LENGTH_SHORT).show();
        }

    }


}
