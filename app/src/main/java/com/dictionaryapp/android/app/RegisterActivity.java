package com.dictionaryapp.android.app;

import android.app.Activity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dictionaryapp.presenters.RegisterActivityPresenter;
import com.dictionaryapp.interfaces.RegisterActivityApi;
import com.dictionaryapp.interfaces.Registrable;

public class RegisterActivity extends AppCompatActivity implements RegisterActivityApi {
    private Button mRegisterButton;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private Registrable mPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRegisterButton = (Button) findViewById(R.id.register_btn);
        mEmailEditText = (EditText) findViewById(R.id.register_email);
        mPasswordEditText = (EditText) findViewById(R.id.register_password);
        mPresenter = new RegisterActivityPresenter(this, this);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.signUp();
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
        return mEmailEditText.getText().toString().trim();
    }

    @Override
    public String getPasswordText() {
        return mPasswordEditText.getText().toString().trim();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
