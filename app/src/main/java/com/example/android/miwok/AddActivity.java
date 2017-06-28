package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddActivity extends AppCompatActivity {

    EditText engWordEdit;
    EditText rusWordEdit;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button addButton = (Button) findViewById(R.id.button_add);
        engWordEdit = (EditText) findViewById(R.id.edit_text_eng);
        rusWordEdit = (EditText) findViewById(R.id.edit_text_rus);
        mDataBase = FirebaseDatabase.getInstance().getReference();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word(rusWordEdit.getText().toString(),engWordEdit.getText().toString());
                mDataBase.child("Words").child("engWord").setValue("Hello");
                mDataBase.child("Words").child("rusWord").setValue("Привет");
            }
        });
    }
}
