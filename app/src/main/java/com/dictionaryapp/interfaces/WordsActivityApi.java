package com.dictionaryapp.interfaces;

import com.dictionaryapp.presenters.WordsActivityPresenter;
import com.wang.avi.AVLoadingIndicatorView;

/**
 * Created by Sergey on 7/6/2017.
 */

public interface WordsActivityApi {


    String getCategoryName();

    AVLoadingIndicatorView getLoadingIndicator();

    WordsActivityPresenter getPresenter();
}
