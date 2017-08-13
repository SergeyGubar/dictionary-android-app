package com.example.android.app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Presenters.AuthActivityPresenter;
import com.example.Interfaces.AuthActivityApi;

public class AuthActivity extends AppCompatActivity implements AuthActivityApi {
    private EditText emailText;
    private EditText passwordText;
    private Button loginButton;
    private AuthActivityPresenter presenter;
    private TextView registerText;
    private TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        emailText = (EditText) findViewById(R.id.email_edit_text);
        passwordText = (EditText) findViewById(R.id.password_edit_text);
        loginButton = (Button) findViewById(R.id.login_btn);
        registerText = (TextView) findViewById(R.id.register_text_view);
        presenter = new AuthActivityPresenter(this, this);
        forgotPasswordTextView = (TextView) findViewById(R.id.forgot_password_text_view);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signIn();
            }
        });
        registerText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signUpActivityStart();
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.forgotPassActivityStart();
            }
        });

    }


    /**
     * These two methods are used for Firebase interaction, can be removed, if
     * other database is chosen (implement Authorized interface)
     */
    @Override
    public void onStart() {
        super.onStart();
        presenter.addFirebaseListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.removeFirebaseListener();
    }

    /**
     * Api methods for interaction of presenter and activity
     */
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
        return passwordText.getText().toString();
    }

    @Override
    public void resetFields() {
        emailText.setText("");
        passwordText.setText("");
    }




}
