package com.example.Interfaces;

import com.example.Helpers.WordsRecyclerAdapter;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Sergey on 7/6/2017.
 */

public interface WordsActivityApi {


    String getCategoryName();

    AVLoadingIndicatorView getLoadingIndicator();

    WordsRecyclerAdapter getAdapter();

}
