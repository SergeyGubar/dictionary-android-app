package com.dictionaryapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dictionaryapp.helpers.WordsRecyclerAdapter;
import com.dictionaryapp.presenters.WordsActivityPresenter;
import com.dictionaryapp.android.app.R;
import com.dictionaryapp.interfaces.WordsActivityApi;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Sergey on 7/27/2017.
 */

public class WordsFragment extends Fragment implements WordsActivityApi {
    private final String TAG = WordsFragment.class.getSimpleName();
    private WordsActivityPresenter mPresenter;
    private AVLoadingIndicatorView mAvi;
    private RecyclerView mRecyclerView;
    private WordsRecyclerAdapter mWordsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.words_recycler_view);
        mAvi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        LayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mPresenter = new WordsActivityPresenter(getContext(), this);
        mWordsAdapter = new WordsRecyclerAdapter(mPresenter.getWords(), getContext(), mPresenter);
        mRecyclerView.setAdapter(mWordsAdapter);
        mPresenter.startAnimation();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        mWordsAdapter.swapCursor(mPresenter.getWords());
    }

    @Override
    public String getCategoryName() {
        return getArguments().getString("ACTIVITY");
    }

    @Override
    public AVLoadingIndicatorView getLoadingIndicator() {
        return mAvi;
    }

    @Override
    public WordsActivityPresenter getPresenter() {
        return mPresenter;
    }
}