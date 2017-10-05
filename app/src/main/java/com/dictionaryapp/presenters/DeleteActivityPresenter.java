package com.dictionaryapp.presenters;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.dictionaryapp.android.app.R;
import com.dictionaryapp.helpers.CategoryDbContract;
import com.dictionaryapp.helpers.WordsSqlService;
import com.dictionaryapp.interfaces.CategoriesService;
import com.dictionaryapp.interfaces.DeleteCategoryApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 10/3/2017.
 */

public class DeleteActivityPresenter {
    //TODO : Delete all words within category
    private Context mCtx;
    private DeleteCategoryApi mApi;
    private CategoriesService mService;

    public DeleteActivityPresenter(Context ctx, DeleteCategoryApi api) {
        this.mCtx = ctx;
        this.mApi = api;
        this.mService = new WordsSqlService(mCtx);
    }

    public void removeCategory() {
        String categoryName = mApi.getSelectedCategoryName();
        mService.removeCategory(categoryName);
        String toastMessage = mCtx.getString(R.string.category_deleted);
        Toast.makeText(mCtx, toastMessage, Toast.LENGTH_SHORT).show();
        initializeSpinnerDataSource();
    }

    public void initializeSpinnerDataSource() {
        Cursor items = mService.getAllCategories();
        int position = 0;

        List<CharSequence> categories = new ArrayList<>();
        while (items.moveToPosition(position)) {
            String categoryName = items.getString(items.getColumnIndex
                    (CategoryDbContract.COLUMN_CATEGORY_NAME));
            categories.add(categoryName);
            position++;
        }

        items.close();
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<>(mCtx, android.R.layout.simple_spinner_item,
                categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mApi.setSpinnerAdapter(adapter);
    }
}
