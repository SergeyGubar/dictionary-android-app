package com.example.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.Presenters.WordsActivityPresenter;
import com.example.android.app.R;
import com.example.android.app.Word;
import com.example.interfaces.WordsActivityApi;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Sergey on 7/27/2017.
 */

public class WordsFragment extends Fragment implements WordsActivityApi {
    private final String TAG = "WordsFragment";
    private WordsActivityPresenter presenter;
    private AVLoadingIndicatorView avi;
    private ArrayList<Word> listOfWords;
    private ListView listView;
    private FloatingActionButton floatButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.words_fragment, container, false);
        floatButton = (FloatingActionButton) rootView.findViewById(R.id.float_btn);
        listView = (ListView) rootView.findViewById(R.id.list);
        avi = (AVLoadingIndicatorView) rootView.findViewById(R.id.avi);
        listOfWords = new ArrayList<>();


        presenter = new WordsActivityPresenter(getContext(), this);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startAddActivity();
            }
        });
        presenter.startAnimation();
        presenter.displayWordsData();
        return rootView;

    }
    @Override
    public String getUid() {
        return getArguments().getString("UID");
    }

    @Override
    public String getActivityName() {
        return getArguments().getString("ACTIVITY");
    }

    @Override
    public AVLoadingIndicatorView getLoadingIndicator() {
        return avi;
    }

    @Override
    public ArrayList<Word> getWordsList() {
        return listOfWords;
    }

    @Override
    public ListView getListView() {
        return listView;
    }

}