package com.dictionaryapp.presenters;

import android.content.Context;

import com.dictionaryapp.helpers.WordsSqlService;
import com.dictionaryapp.interfaces.CategoriesService;
import com.dictionaryapp.interfaces.DeleteCategoryApi;

/**
 * Created by Sergey on 10/3/2017.
 */

public class DeleteActivityPresenter {

    private Context mCtx;
    private DeleteCategoryApi mApi;
    private CategoriesService mService;

    public DeleteActivityPresenter(Context ctx, DeleteCategoryApi api) {
        this.mCtx = ctx;
        this.mApi = api;
        this.mService = new WordsSqlService(ctx);
    }

    public void removeCategory() {
        String categoryName = mApi.getSelectedCategoryName();
        mService.removeCategory(categoryName);
    }


}
