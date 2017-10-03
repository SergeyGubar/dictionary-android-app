package com.dictionaryapp.android.app;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.dictionaryapp.fragments.WordsFragment;
import com.dictionaryapp.helpers.WordsSqlService;
import com.dictionaryapp.interfaces.MainActivityApi;
import com.dictionaryapp.interfaces.CategoriesService;

/**
 * Created by Sergey on 7/27/2017.
 */

public class WordsFragmentAdapter extends FragmentPagerAdapter {
    private final String KEY = "ACTIVITY";
    private MainActivityApi mApi;
    private CategoriesService mService;
    private Cursor mCategories;

    public WordsFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public WordsFragmentAdapter(FragmentManager fragmentManager, MainActivityApi api) {
        super(fragmentManager);
        mApi = api;
        mService = new WordsSqlService(mApi.getContext());
        mCategories = mService.getAllCategories();
    }


    @Override
    public Fragment getItem(int position) {
        WordsFragment fragment = new WordsFragment();
        Bundle b = new Bundle();
        String categoryName = mService.getCategoryName(position, mCategories);
        b.putString(KEY, categoryName);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public int getCount() {
        return mCategories.getCount();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mService.getCategoryName(position, mCategories);
    }
}
