package com.example.android.app;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.example.Presenters.WordsActivityPresenter;
import com.example.interfaces.WordsActivityApi;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;


public class WordsActivity extends AppCompatActivity implements WordsActivityApi {
    private final String TAG = "WordsListActivity";
    private WordsActivityPresenter presenter;
    private AVLoadingIndicatorView avi;
    private ArrayList<Word> listOfWords;
    private ListView listView;
    private FloatingActionButton floatButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words);
        floatButton = (FloatingActionButton) findViewById(R.id.float_btn);
        listView = (ListView) findViewById(R.id.list);
        avi = (AVLoadingIndicatorView) findViewById(R.id.avi);
        listOfWords = new ArrayList<>();



        presenter = new WordsActivityPresenter(this, this);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.startAddActivity();
            }
        });
        presenter.startAnimation();
        presenter.displayWordsData();

    }


    @Override
    public String getUid() {
        return getIntent().getStringExtra("UID");
    }

    @Override
    public String getActivityName() {
        return getIntent().getStringExtra("activity");
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
