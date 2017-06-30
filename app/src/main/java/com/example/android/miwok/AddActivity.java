package com.example.android.miwok;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.StringTokenizer;

public class AddActivity extends AppCompatActivity {

    EditText engWordEdit;
    private EditText rusWordEdit;
    private DatabaseReference mDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button addButton = (Button) findViewById(R.id.button_add);
        engWordEdit = (EditText) findViewById(R.id.edit_text_eng);
        rusWordEdit = (EditText) findViewById(R.id.edit_text_rus);
        mDataBase = FirebaseDatabase.getInstance().getReference().child("Words");

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word(rusWordEdit.getText().toString().trim(),
                        engWordEdit.getText().toString().trim());
                HashMap<String,String> wordHash = new HashMap<>();
                wordHash.put("engWord", word.getEngWord());
                wordHash.put("rusWord", word.getRusWord());

                mDataBase.child(word.getEngWord() + " | " + word.getRusWord()).setValue(wordHash).addOnCompleteListener
                        (new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(AddActivity.this, "Word Saved!",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(AddActivity.this, "An error " +
                                    "has occured",Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                engWordEdit.setText("");
                rusWordEdit.setText("");
            }
        });
    }
}
