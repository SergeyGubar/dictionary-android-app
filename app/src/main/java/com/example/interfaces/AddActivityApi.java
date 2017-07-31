package com.example.interfaces;

import java.util.Objects;

/**
 * Created by Sergey on 7/5/2017.
 */

public interface AddActivityApi {
    String getEngText();

    String getRusText();

    Object getSelectedSpinnerItem();

    void resetText();

    void setSpinnerAdapter();

}
