package com.dictionaryapp.android.app;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.dictionaryapp.interfaces.DeleteCategoryApi;
import com.dictionaryapp.presenters.DeleteActivityPresenter;

public class DeleteCategoryActivity extends AppCompatActivity implements DeleteCategoryApi {

    private Toolbar mToolbar;
    private Button mDeleteButton;
    private DeleteActivityPresenter mPresenter;
    private Spinner mCategoriesSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_category);

        mToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        mDeleteButton = (Button) findViewById(R.id.delete_category_button);
        mPresenter = new DeleteActivityPresenter(this, this);
        mCategoriesSpinner = (Spinner) findViewById(R.id.categories_spinner);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mPresenter.initializeSpinnerDataSource();


        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.removeCategory();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getSelectedCategoryName() {
        return mCategoriesSpinner.getSelectedItem().toString();
    }

    @Override
    public void setSpinnerAdapter(ArrayAdapter<CharSequence> adapter) {
        mCategoriesSpinner.setAdapter(adapter);
    }

    @Override
    public void lockDeleteFunction() {
        mCategoriesSpinner.setEnabled(false);
        mDeleteButton.setEnabled(false);
        Toast.makeText(this, "There are no categories to delete. Maybe you should add it?", Toast.LENGTH_SHORT)
                .show();
    }


}
