package com.example.android.app;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.Presenters.ForgotPasswordPresenter;
import com.example.Interfaces.ForgotPasswordApi;
import com.example.Interfaces.ResetPassword;

public class ForgotPasswordActivity extends AppCompatActivity implements ForgotPasswordApi {
    private EditText mEmailEditText;
    private Button mResetButton;
    private ResetPassword mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mEmailEditText = (EditText) findViewById(R.id.forgot_email_edit_text);
        mResetButton = (Button) findViewById(R.id.reset_password_btn);
        mPresenter = new ForgotPasswordPresenter(this, this);
        mResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.sendResetPasswordLink();
            }
        });

    }

    @Override
    public String getEmailText() {
        return mEmailEditText.getText().toString();
    }

    @Override
    public void resetEmailText() {
        mEmailEditText.setText("");
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
