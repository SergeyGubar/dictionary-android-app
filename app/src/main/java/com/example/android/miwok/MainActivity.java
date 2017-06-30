/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.HashMap;

import static android.os.Build.VERSION_CODES.M;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            findViewById(R.id.numbers).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent numbers = new Intent(getApplicationContext(), NumbersActivity.class);
                    startActivity(numbers);
                }
            });
            findViewById(R.id.colors).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent colors = new Intent(MainActivity.this, ColorsActivity.class);
                    startActivity(colors);
                }
            });
            findViewById(R.id.family).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent family = new Intent(MainActivity.this, FamilyActivity.class);
                    startActivity(family);
                }
            });
            findViewById(R.id.phrases).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent phrases = new Intent(MainActivity.this, PhrasesActivity.class);
                    startActivity(phrases);
                }
            });
        } catch (NullPointerException ex) {
            Toast.makeText(getApplicationContext(), "Ooops, something went wrong", Toast.LENGTH_SHORT).show();
            Log.e("MainActivity", "Null pointer, gg");
        }

    }
}
