package com.example.android.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.Fragments.WordsFragment;
import com.example.Helpers.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;

import static android.R.attr.fragment;

/**
 * Created by Sergey on 7/27/2017.
 */

public class WordsFragmentAdapter extends FragmentPagerAdapter {
    private final String KEY = "ACTIVITY";
    private final String[] NAMES = {"Colors", "Numbers", "Stuff", "Family"};
    private Context mCtx;

    public WordsFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public WordsFragmentAdapter(FragmentManager fragmentManager, Context ctx) {
        super(fragmentManager);
        mCtx = ctx;
    }

    @Override
    public Fragment getItem(int position) {

        WordsFragment fragment = new WordsFragment();
        Bundle b = new Bundle();
        switch (position) {
            case 0:
                b.putString(KEY, "Colors");
                fragment.setArguments(b);
                return fragment;
            case 1:
                b.putString(KEY, "Numbers");
                fragment.setArguments(b);
                return fragment;
            case 2:
                b.putString(KEY, "Stuff");
                fragment.setArguments(b);
                return fragment;
            case 3:
                b.putString(KEY, "Family");
                fragment.setArguments(b);
                return fragment;
            default:
                return fragment;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return NAMES[position];
    }
}
