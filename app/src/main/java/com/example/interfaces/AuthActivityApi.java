package com.example.interfaces;

import android.app.Activity;
import android.widget.TextView;

/**
 * Created by Sergey on 7/5/2017.
 */

public interface AuthActivityApi {

    Activity getActivity();

    String getEmailText();

    String getPasswordText();

    void resetFields();

}
