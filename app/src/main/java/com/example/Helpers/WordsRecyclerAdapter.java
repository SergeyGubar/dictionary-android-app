package com.example.Helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.app.R;
import com.example.android.app.Word;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergey on 8/10/2017.
 */

public class WordsRecyclerAdapter extends RecyclerView.Adapter<WordsRecyclerAdapter.WordsViewHolder> {
    List<Word> listOfWords = new ArrayList<>(4);
    @Override
    public WordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context ctx = parent.getContext();
        int layoutIdForListItem = R.layout.word_layout;
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View inflatedView = inflater.inflate(layoutIdForListItem, parent, false);
        return new WordsViewHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(WordsViewHolder holder, int position) {
        // FIXME: 8/10/2017 REMOVE DUMMY WORD
        Word wordToBind = new Word("a", "b");

        holder.rusWordTextView.setText(wordToBind.getRusWord());
        holder.engWordTextView.setText(wordToBind.getEngWord());
    }



    @Override
    public int getItemCount() {
        if(listOfWords == null) {
            return 0;
        }
        return listOfWords.size();
    }

    public void addWord(Word wordToAdd) {
        listOfWords.add(wordToAdd);
        notifyDataSetChanged();
    }
    public void removeWord(Word wordToRemove) {
        listOfWords.remove(wordToRemove);
        notifyDataSetChanged();
    }

    class WordsViewHolder extends RecyclerView.ViewHolder {
        TextView engWordTextView;
        TextView rusWordTextView;
        public WordsViewHolder(View itemView) {
            super(itemView);
            engWordTextView = (TextView) itemView.findViewById(R.id.default_text_view);
            rusWordTextView = (TextView) itemView.findViewById(R.id.russian_text_view);
        }
    }

}
