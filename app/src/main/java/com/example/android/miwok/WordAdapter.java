package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 6/22/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.numbers_layout, parent, false);
        }

        Word currWord = getItem(position);
        try {
            TextView rusTextView = (TextView) listItemView.findViewById(R.id.russian_text_view);
            rusTextView.setText(currWord.getRusWord());
            /*rusTextView.setBackgroundColor(getContext().getResources().getColor(currWord.getColor()));*/


            TextView engTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
            engTextView.setText(currWord.getEngWord());
            /*engTextView.setBackgroundColor(getContext().getResources().getColor(currWord.getColor()));*/


            ImageView pictureView = (ImageView) listItemView.findViewById(R.id.image_placeholder);
            pictureView.setImageResource(currWord.getImageSource());
        } catch (NullPointerException ex) {
            Log.e("WordAdapter", "KOLENO PROSTRELENO");
        }

        return listItemView;
    }

    public WordAdapter(Context context, ArrayList<Word> list) {
        super(context, 0, list);
    }

}
