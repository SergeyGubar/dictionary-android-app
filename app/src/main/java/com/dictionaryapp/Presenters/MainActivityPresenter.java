package com.dictionaryapp.Presenters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dictionaryapp.Helpers.WordsSqlService;
import com.dictionaryapp.Interfaces.CategoriesService;
import com.dictionaryapp.android.app.AddActivity;
import com.dictionaryapp.Interfaces.MainActivityApi;
import com.dictionaryapp.android.app.R;

/**
 * Created by Sergey on 7/31/2017.
 */

public class MainActivityPresenter {
    private Context mCtx;
    private MainActivityApi mApi;
    private CategoriesService mService;

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
        final EditText newCategoryNameEditText = (EditText) inflatedView.findViewById(R.id.new_category_edit_text);
        Button addButton = (Button) inflatedView.findViewById(R.id.add_new_category_button);
        builder.setView(inflatedView);
        final AlertDialog newCategoryDialog = builder.create();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String categoryName = newCategoryNameEditText.getText().toString();
                if(!categoryName.isEmpty()) {
                    mService = new WordsSqlService(mCtx);
                    mService.addCategory(categoryName);
                    mApi.updateAdapter();
                    Toast.makeText(mCtx, R.string.new_category_added, Toast.LENGTH_SHORT).show();
                    newCategoryDialog.hide();
                } else {
                    Toast.makeText(mCtx, R.string.must_enter_category_name, Toast.LENGTH_SHORT).show();
                }


            }
        });
        newCategoryDialog.show();
    }


}
