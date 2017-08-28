package com.example.android.app;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.Helpers.WordsSqlService;
import com.example.Interfaces.SqlCategories;
import com.example.Presenters.AddActivityPresenter;
import com.example.Interfaces.AddActivityApi;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class AddActivity extends AppCompatActivity implements AddActivityApi {
    private final String TAG = "AddActivity";
    private Spinner mSpinner;
    private Button mAddButton;
    private EditText mEngWordEditText;
    private EditText mRusWordEditText;
    private AddActivityPresenter mPresenter;
    private SqlCategories mService;
    private Toolbar mToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        mToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mAddButton = (Button) findViewById(R.id.button_add);
        mSpinner = (Spinner) findViewById(R.id.spinner);
        mEngWordEditText = (EditText) findViewById(R.id.edit_text_eng);
        mRusWordEditText = (EditText) findViewById(R.id.edit_text_rus);
        mPresenter = new AddActivityPresenter(this, this);
        mService = new WordsSqlService(this);

        mPresenter.setAdapter();
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.addWord();
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
    public String getEngText() {
        return mEngWordEditText.getText().toString().trim();
    }

    @Override
    public String getRusText() {
        return mRusWordEditText.getText().toString().trim();
    }

    @Override
    public Object getSelectedSpinnerItem() {
        return mSpinner.getSelectedItem();
    }

    @Override
    public void resetText() {
        mRusWordEditText.setText("");
        mEngWordEditText.setText("");
    }

    @Override
    public void setSpinnerAdapter() {
        List<String> categoriesNames = mService.getCategoriesNames();

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,
                categoriesNames);

        mSpinner.setAdapter(adapter);
    }
}
