package com.example.android.miwok;

import java.util.Objects;

/**
 * Created by Sergey on 7/5/2017.
 */

public interface AddActivityApi {
    String getEngText();

    String getRusText();

    String getUid();

    Object getSelectedSpinnerItem();

    void resetText();

    void setSpinnerAdapter();

}
