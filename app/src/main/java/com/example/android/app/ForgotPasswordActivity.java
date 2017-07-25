package com.example.android.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Presenters.ForgotPasswordPresenter;
import com.example.interfaces.ForgotPasswordApi;
import com.example.interfaces.ResetPassword;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordApi {
    private EditText emailEditText;
    private Button resetButton;
    private ResetPassword presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        emailEditText = (EditText) findViewById(R.id.forgot_email_edit_text);
        resetButton = (Button) findViewById(R.id.reset_password_btn);
        presenter = new ForgotPasswordPresenter(this, this);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.sendResetPasswordLink();
            }
        });

    }

    @Override
    public String getEmailText() {
        return emailEditText.getText().toString();
    }

    @Override
    public void resetEmailText() {
        emailEditText.setText("");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
