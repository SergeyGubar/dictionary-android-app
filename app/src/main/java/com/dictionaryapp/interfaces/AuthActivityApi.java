package com.dictionaryapp.interfaces;

import android.app.Activity;

/**
 * Created by Sergey on 7/5/2017.
 */

public interface AuthActivityApi {

    Activity getActivity();

    String getEmailText();

    String getPasswordText();

    void resetFields();

}
