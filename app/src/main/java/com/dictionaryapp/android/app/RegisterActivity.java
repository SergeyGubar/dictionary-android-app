package com.dictionaryapp.android.app;

import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dictionaryapp.Presenters.RegisterActivityPresenter;
import com.dictionaryapp.Interfaces.RegisterActivityApi;
import com.dictionaryapp.Interfaces.Registrable;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityApi {
    private Button registerButton;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Registrable presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        registerButton = (Button) findViewById(R.id.register_btn);
        emailEditText = (EditText) findViewById(R.id.register_email);
        passwordEditText = (EditText) findViewById(R.id.register_password);
        presenter = new RegisterActivityPresenter(this, this);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.signUp();
            }
        });
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

    @Override
    public String getEmailText() {
        return emailEditText.getText().toString().trim();
    }

    @Override
    public String getPasswordText() {
        return passwordEditText.getText().toString().trim();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
