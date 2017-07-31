package com.example.interfaces;

import android.widget.ListView;

import com.example.android.app.Word;
import com.example.android.app.WordAdapter;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;

/**
 * Created by Sergey on 7/6/2017.
 */

public interface WordsActivityApi {

/*
    String getUid();
*/

    String getActivityName();

    AVLoadingIndicatorView getLoadingIndicator();


    WordAdapter getAdapter();

}
