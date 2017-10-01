package com.dictionaryapp.android.app;

/**
 * Created by Sergey on 6/22/2017.
 */

public class Word {
    private String rusWord;
    private String engWord;
    private String category;

    public String getRusWord() {
        return rusWord;
    }

    public String getEngWord() {
        return engWord;
    }


    public String getCategory() {
        return category;
    }

    public Word(String rusWord, String engWord) {
        this.rusWord = rusWord;
        this.engWord = engWord;
    }

    public Word(final String rusWord, final String engWord, final String category) {
        this.rusWord = rusWord;
        this.engWord = engWord;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Word)) {
            return false;
        }
        Word wordToCompare = (Word)o;
        return wordToCompare.engWord.equals(this.engWord) && wordToCompare.rusWord.equals(this.rusWord);
    }

    @Override
    public String toString() {
        return this.engWord + "\n" + this.rusWord;
    }
}
