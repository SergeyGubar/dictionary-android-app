package com.example.Presenters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.widget.Toast;

import com.example.android.app.AuthActivity;
import com.example.android.app.R;
import com.example.interfaces.RegisterActivityApi;
import com.example.interfaces.Registrable;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Sergey on 7/7/2017.
 */

public class RegisterActivityPresenter implements Registrable {

    private RegisterActivityApi mApi;
    private Context context;
    private ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    public RegisterActivityPresenter(RegisterActivityApi mApi, Context context) {
        this.mApi = mApi;
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(context);
    }

    public void signUp() {
        progressDialog.setTitle(R.string.register_process_text);;
        progressDialog.setMessage("Just a moment");
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(mApi.getEmailText(), mApi.getPasswordText()).addOnCompleteListener(
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(context, "Registered succesfully!", Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                            mApi.getActivity().finish();
                            context.startActivity(new Intent(context, AuthActivity.class));
                        } else {
                            Toast.makeText(context, "Something went wrong, try again",
                                    Toast.LENGTH_SHORT).show();
                            progressDialog.hide();
                        }
                    }
                }
        );
    }
}
