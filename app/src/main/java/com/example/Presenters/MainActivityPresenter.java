package com.example.Presenters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Helpers.PreferencesKeys;
import com.example.android.app.AddActivity;
import com.example.Interfaces.MainActivityApi;
import com.example.android.app.R;

/**
 * Created by Sergey on 7/31/2017.
 */

public class MainActivityPresenter {
    private Context mCtx;
    private MainActivityApi mApi;

    public MainActivityPresenter(Context mCtx, MainActivityApi mApi) {
        this.mCtx = mCtx;
        this.mApi = mApi;
    }

    public void startAddActivity() {
        Intent intent = new Intent(mCtx, AddActivity.class);
        mCtx.startActivity(intent);
    }

    public void showNewCategoryAddDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
        View inflatedView = LayoutInflater.from(mCtx).inflate(R.layout.category_new_dialog, null);
        EditText newCategoryNameEditText = (EditText) inflatedView.findViewById(R.id.new_category_edit_text);
        Button addButton = (Button) inflatedView.findViewById(R.id.add_new_category_button);
        builder.setView(inflatedView);
        final AlertDialog newCategoryDialog = builder.create();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: 8/22/2017 : Here goes some weird shit, finish it
                SharedPreferences preferences = mApi.getSharedPreferences();
                SharedPreferences.Editor editor = preferences.edit();

                int numberOfCategories = 0;
                if(preferences.contains(PreferencesKeys.getCategoriesNumberKey())) {
                    numberOfCategories = preferences.getInt(PreferencesKeys.getCategoriesNumberKey(), 0);
                }


                numberOfCategories++;
                editor.putString(PreferencesKeys.getCategoriesNumberKey(), String.valueOf(numberOfCategories));



                Toast.makeText(mCtx, R.string.new_category_added, Toast.LENGTH_SHORT).show();
                newCategoryDialog.hide();

            }
        });
        newCategoryDialog.show();
    }


}
