package com.example.android.miwok;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.HashMap;


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
