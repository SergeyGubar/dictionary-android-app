package com.example.android.miwok;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AuthActivity extends AppCompatActivity implements AuthActivityApi {
    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private AuthActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        emailText = (EditText) findViewById(R.id.email_edit_text);
        passwordText = (EditText) findViewById(R.id.password_edit_text);
        loginButton = (Button) findViewById(R.id.login_btn);
        presenter = new AuthActivityPresenter(this, this);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signIn();
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.addListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.removeListener();
    }


    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public String getEmailText() {
        return emailText.getText().toString();
    }

    @Override
    public String getPasswordText() {
        return  passwordText.getText().toString();
    }

}
