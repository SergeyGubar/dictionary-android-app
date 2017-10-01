package com.dictionaryapp.android.app;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dictionaryapp.Presenters.AuthActivityPresenter;
import com.dictionaryapp.Interfaces.AuthActivityApi;

public class AuthActivity extends AppCompatActivity implements AuthActivityApi {
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Button mLoginButton;
    private AuthActivityPresenter mPresenter;
    private TextView mRegisterText;
    private TextView forgotPasswordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        mEmailEditText = (EditText) findViewById(R.id.email_edit_text);
        mPasswordEditText = (EditText) findViewById(R.id.password_edit_text);
        mLoginButton = (Button) findViewById(R.id.login_btn);
        mRegisterText = (TextView) findViewById(R.id.register_text_view);
        mPresenter = new AuthActivityPresenter(this, this);
        forgotPasswordTextView = (TextView) findViewById(R.id.forgot_password_text_view);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.signIn();
            }
        });
        mRegisterText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.signUpActivityStart();
            }
        });

        forgotPasswordTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.forgotPassActivityStart();
            }
        });

    }


    /**
     * These two android callbacks are used for Firebase interaction, can be removed, if
     * other database is chosen (implement Authorized interface)
     */
    @Override
    public void onStart() {
        super.onStart();
        mPresenter.addFirebaseListener();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.removeFirebaseListener();
    }

    /**
     * Api methods for interaction of mPresenter and activity
     */
    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public String getEmailText() {
        return mEmailEditText.getText().toString();
    }

    @Override
    public String getPasswordText() {
        return mPasswordEditText.getText().toString();
    }

    @Override
    public void resetFields() {
        mEmailEditText.setText("");
        mPasswordEditText.setText("");
    }


}
