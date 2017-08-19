package com.example.Helpers;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.app.R;
import com.example.android.app.Word;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Sergey on 8/10/2017.
 */

public class WordsRecyclerAdapter extends RecyclerView.Adapter<WordsRecyclerAdapter.WordsViewHolder> {
    private Cursor mCursor;


    public WordsRecyclerAdapter(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    public WordsRecyclerAdapter() {

    }

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
        if(!mCursor.moveToPosition(position)) {
            return;
        }
        String engWord = mCursor.getString(mCursor.getColumnIndex(WordDbContract.WordEntry.COLUMN_ENG_WORD));
        String rusWord = mCursor.getString(mCursor.getColumnIndex(WordDbContract.WordEntry.COLUMN_RUS_WORD));
        long id = mCursor.getLong(mCursor.getColumnIndex(WordDbContract.WordEntry._ID));
        holder.itemView.setTag(id);
        holder.setWordText(engWord, rusWord);
    }

    public void swapCursor(Cursor newCursor) {
        if (mCursor != null) {
            mCursor.close();
        }
        mCursor = newCursor;
        if (mCursor != null) {
            notifyDataSetChanged();
        }
    }


    @Override
    public int getItemCount() {
        if (mCursor == null) {
            return 0;
        }
        return mCursor.getCount();
    }

    public void addWord(Word wordToAdd) {
        notifyDataSetChanged();
    }

    public void removeWord(Word wordToRemove) {
        notifyDataSetChanged();
    }

    class WordsViewHolder extends RecyclerView.ViewHolder {
        private TextView engWordTextView;
        private TextView rusWordTextView;

        public WordsViewHolder(View itemView) {
            super(itemView);
            engWordTextView = (TextView) itemView.findViewById(R.id.default_text_view);
            rusWordTextView = (TextView) itemView.findViewById(R.id.russian_text_view);
        }

        public void setWordText(String eng, String translation) {
            engWordTextView.setText(eng);
            rusWordTextView.setText(translation);
        }
    }

}
