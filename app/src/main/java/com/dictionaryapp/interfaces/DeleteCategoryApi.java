package com.dictionaryapp.interfaces;

import android.widget.ArrayAdapter;

/**
 * Created by Sergey on 10/3/2017.
 */

public interface DeleteCategoryApi {
    String getSelectedCategoryName();
    void setSpinnerAdapter(ArrayAdapter<CharSequence> adapter);
    void lockDeleteFunction();
}
