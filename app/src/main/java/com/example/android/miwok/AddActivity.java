package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AddActivity extends AppCompatActivity implements AddActivityApi {
    private final String TAG = "AddActivity";
    private Spinner spinner;
    private Button addButton;
    private EditText engWordEdit;
    private EditText rusWordEdit;
    private AddActivityPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        addButton = (Button) findViewById(R.id.button_add);
        spinner = (Spinner) findViewById(R.id.spinner);
        engWordEdit = (EditText) findViewById(R.id.edit_text_eng);
        rusWordEdit = (EditText) findViewById(R.id.edit_text_rus);
        presenter = new AddActivityPresenter(this, this);

        presenter.setAdapter();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addWord();
            }
        });
    }

    @Override
    public String getEngText() {
        return engWordEdit.getText().toString().trim();
    }

    @Override
    public String getRusText() {
        return rusWordEdit.getText().toString().trim();
    }

    @Override
    public String getUid() {
        return getIntent().getStringExtra("UID");
    }

    @Override
    public Object getSelectedSpinnerItem() {
        return spinner.getSelectedItem();
    }

    @Override
    public void resetText() {
        rusWordEdit.setText("");
        engWordEdit.setText("");
    }

    @Override
    public void setSpinnerAdapter() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.activities_names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
