package com.example.Helpers;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Interfaces.SqlService;
import com.example.Presenters.WordsActivityPresenter;
import com.example.android.app.MainActivity;
import com.example.android.app.R;
import com.example.android.app.Word;
import com.google.gson.InstanceCreator;

import java.nio.charset.CharacterCodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.google.gson.internal.UnsafeAllocator.create;

/**
 * Created by Sergey on 8/10/2017.
 */

public class WordsRecyclerAdapter extends RecyclerView.Adapter<WordsRecyclerAdapter.WordsViewHolder> {
    private Cursor mCursor;
    private Context mCtx;
    private SqlService mService;
    private WordsActivityPresenter mPresenter;



    public WordsRecyclerAdapter() {

    }

    public WordsRecyclerAdapter(Cursor mCursor) {
        this.mCursor = mCursor;
    }

    public WordsRecyclerAdapter(Cursor mCursor, Context ctx) {
        this.mCursor = mCursor;
        this.mCtx = ctx;
        this.mService = new WordsSqlService(ctx);
    }

    public WordsRecyclerAdapter(Cursor cursor, Context ctx, WordsActivityPresenter presenter) {
        this.mCursor = cursor;
        this.mCtx = ctx;
        this.mService = new WordsSqlService(ctx);
        this.mPresenter = presenter;
    }

    @Override
    public WordsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final Context ctx = parent.getContext();
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
        final long id = mCursor.getLong(mCursor.getColumnIndex(WordDbContract.WordEntry._ID));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                View view = LayoutInflater.from(mCtx).inflate(R.layout.word_dialog, null);
                final Button deleteButton = (Button) view.findViewById(R.id.delete_word_button);
                final Button editButton = (Button) view.findViewById(R.id.edit_word_button);

                builder.setView(view);
                final AlertDialog dialog = builder.create();
                deleteButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mService.removeWord(id);
                        Toast.makeText(mCtx, R.string.word_deleted, Toast.LENGTH_SHORT).show();
                        // FIXME: 8/20/2017 etot govnokod nado bi ubrat
                        swapCursor(mPresenter.getWords());
                        dialog.hide();
                    }
                });
                editButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mCtx, "Feature is not implemented yet :(", Toast.LENGTH_SHORT).show();
                        dialog.hide();
                    }
                });
                dialog.show();
            }
        });
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
