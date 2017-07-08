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
import android.os.ResultReceiver;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.firebase.auth.FirebaseAuth;

import static android.R.attr.tag;
import static com.example.android.miwok.R.id.colors;
import static com.example.android.miwok.R.id.numbers;

public class MainActivity extends AppCompatActivity {

    private final String KEY = "activity";
    private final String USERID = "UID";
    private final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(numbers).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent numbers = new Intent(MainActivity.this, WordsActivity.class);
                numbers.putExtra(KEY, "Numbers");
                numbers.putExtra(USERID, getIntent().getStringExtra("UID"));
                startActivity(numbers);
            }
        });
        findViewById(colors).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent colors = new Intent(MainActivity.this, WordsActivity.class);
                colors.putExtra(KEY, "Colors");
                colors.putExtra(USERID, getIntent().getStringExtra("UID"));
                startActivity(colors);
            }
        });
        findViewById(R.id.family).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent family = new Intent(MainActivity.this, WordsActivity.class);
                family.putExtra(KEY, "Family");
                family.putExtra(USERID, getIntent().getStringExtra("UID"));

                startActivity(family);
            }
        });
        findViewById(R.id.phrases).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stuff = new Intent(MainActivity.this, WordsActivity.class);
                stuff.putExtra(KEY, "Stuff");
                stuff.putExtra(USERID, getIntent().getStringExtra("UID"));
                startActivity(stuff);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log_out_menu_item:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
        }
        return true;
    }
}
