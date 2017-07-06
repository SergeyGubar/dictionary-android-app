package com.example.android.miwok;

import android.widget.ListView;

import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Sergey on 7/6/2017.
 */

public interface WordsActivityApi {

    String getUid();

    String getActivityName();

    AVLoadingIndicatorView getLoadingIndicator();

    ArrayList<Word> getWordsList();

    ListView getListView();

}
