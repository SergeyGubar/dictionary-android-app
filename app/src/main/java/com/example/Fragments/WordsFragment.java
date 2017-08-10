package com.example.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.Helpers.WordsRecyclerAdapter;
import com.example.Presenters.WordsActivityPresenter;
import com.example.android.app.R;
import com.example.android.app.WordAdapter;
import com.example.interfaces.WordsActivityApi;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Sergey on 7/27/2017.
 */

public class WordsFragment extends Fragment implements WordsActivityApi {
    private final String TAG = "WordsFragment";
    private WordsActivityPresenter mPresenter;
    private AVLoadingIndicatorView mAvi;
    private ListView mListView;
    private WordAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private WordsRecyclerAdapter mWordsAdapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /*View rootView = inflater.inflate(R.layout.words_fragment, container, false);
        mListView = (ListView) rootView.findViewById(R.id.list);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.words_recycler_view);
        mAvi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        mAdapter = new WordAdapter(getActivity());
        mListView.setAdapter(mAdapter);
        mPresenter = new WordsActivityPresenter(getContext(), this);
        mPresenter.startAnimation();
        mPresenter.displayWordsData();
        return rootView;*/

        //
        View rootView = inflater.inflate(R.layout.words_fragment, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.words_recycler_view);
        mAvi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        LayoutManager layoutManager
                = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(layoutManager);
        mWordsAdapter = new WordsRecyclerAdapter();
        mRecyclerView.setAdapter(mWordsAdapter);
        mPresenter = new WordsActivityPresenter(getContext(), this);
        mPresenter.startAnimation();
        mPresenter.displayWordsData();
        return rootView;
    }

    @Override
    public String getActivityName() {
        return getArguments().getString("ACTIVITY");
    }

    @Override
    public AVLoadingIndicatorView getLoadingIndicator() {
        return mAvi;
    }

    @Override
    public WordsRecyclerAdapter getAdapter() {
        return mWordsAdapter;
    }

}